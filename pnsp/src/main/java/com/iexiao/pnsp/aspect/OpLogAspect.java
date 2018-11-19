package com.iexiao.pnsp.aspect;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.iexiao.pnsp.annotation.OpLog;
import com.iexiao.pnsp.base.BaseDTO;
import com.iexiao.pnsp.base.query.PnspOpLogTQuery;
import com.iexiao.pnsp.base.service.PnspOpLogTService;
import com.iexiao.pnsp.constants.Constant;
import com.iexiao.pnsp.constants.UserConstant;
import com.iexiao.pnsp.utils.BeanHelper;
import com.iexiao.pnsp.utils.GetMacAddrAndIpUtil;
import com.iexiao.pnsp.utils.shiro.ShiroUtil;

/**
 * 数据库插入接口请求日志切面
 * @author lizhiyong
 * @date 2018年10月23日
 */
@Aspect
@Order(Integer.MAX_VALUE)
@Component
public class OpLogAspect {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OpLogAspect.class);
	
	@Autowired
	private PnspOpLogTService pnspOpLogTService;
	
	@Pointcut("execution(** com.iexiao.pnsp.*.controller.*.*(..)) && @annotation(com.iexiao.pnsp.annotation.OpLog)")
	public void opLogPointcut() {}
	
	@AfterReturning(value = "opLogPointcut()", returning = "rvt")
	public void afterReturning(JoinPoint joinPoint, Object rvt) {
		this.handlerOpLog(joinPoint,Constant.LOG_OP_STATUS_YES);
	}
	
	@AfterThrowing(value = "opLogPointcut()", throwing = "e")
	public void afterThrowing(JoinPoint joinPoint, Throwable e) {
		this.handlerOpLog(joinPoint,Constant.LOG_OP_STATUS_NO);
	}
	
	private void handlerOpLog(JoinPoint joinPoint, String logOpStatus) {
		PnspOpLogTQuery pnspOpLogTQuery = this.getOpLog(joinPoint);
		//操作状态
		pnspOpLogTQuery.setOpStatus(logOpStatus);
		//NULL处理
		BeanHelper.setDefaultProp(pnspOpLogTQuery, PnspOpLogTQuery.class);
		try {
			this.pnspOpLogTService.addOpLog(pnspOpLogTQuery);
		} catch (Exception e) {
			LOGGER.error("[插入日志失败]");
		}
	}
	
	private PnspOpLogTQuery getOpLog(JoinPoint joinPoint) {
		PnspOpLogTQuery pnspOpLogTQuery = new PnspOpLogTQuery();
		//主键
		BeanHelper.setUUID(pnspOpLogTQuery);
		//数据更新时间
		BeanHelper.onInsert(pnspOpLogTQuery);
		BeanHelper.onUpdate(pnspOpLogTQuery);
		pnspOpLogTQuery.setAddOprid(UserConstant.USER_SYSTEM_OPRID);
		pnspOpLogTQuery.setUpdOprid(UserConstant.USER_SYSTEM_OPRID);
		//操作人
		Subject subject = SecurityUtils.getSubject();
		String phone = (String) ShiroUtil.getSession(UserConstant.USER_SESSION, subject);
		if(StringUtils.isBlank(phone)) {
			phone = UserConstant.USER_SYSTEM_OPRID;
		}
		pnspOpLogTQuery.setPhone(phone);
		//操作时间
		pnspOpLogTQuery.setOpDttm(new Date());
		//IP与MAC
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = sra.getRequest();
		String ip = (String) ShiroUtil.getSession(UserConstant.IP_SESSION, subject);
		if(StringUtils.isBlank(ip)) {
			ip = GetMacAddrAndIpUtil.getIpAddress(request);
		}
		pnspOpLogTQuery.setOpIp(ip);
		String mac = (String) ShiroUtil.getSession(UserConstant.MAC_SESSION, subject);
		if(StringUtils.isBlank(mac)) {
			mac = GetMacAddrAndIpUtil.getMacAddress(ip);
		}
		pnspOpLogTQuery.setOpMac(mac);
		//请求路径
		String requestURI = request.getRequestURI();
		//pnspOpLogTQuery.setReqUrl(requestURI.substring(requestURI.indexOf("/")));
		pnspOpLogTQuery.setReqUrl(requestURI);
		//获取注解
		Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
		OpLog opLog = method.getAnnotation(OpLog.class);
		//操作类型
		pnspOpLogTQuery.setOpType(opLog.opType());
		//操作描述
		pnspOpLogTQuery.setOpDescr(opLog.opDescr());
		//关键字
		pnspOpLogTQuery.setKeyWord(opLog.keyWord());
		//操作内容(传参)
		this.setOpContent(joinPoint,pnspOpLogTQuery);
		return pnspOpLogTQuery;
	}

	private void setOpContent(JoinPoint joinPoint, PnspOpLogTQuery pnspOpLogTQuery) {
		Object[] args = joinPoint.getArgs();
		Map<Integer,Object> argMap = new HashMap<>();
		if(args.length == 0) {
			pnspOpLogTQuery.setOpContent(Constant.NULL_STR);
		}else {
			for(int i = 0; i < args.length; i++) {
				if(args[i] instanceof BaseDTO) {
					argMap.put(i, args[i]);
				}
			}
			if(MapUtils.isNotEmpty(argMap)) {
				if(argMap.toString().length() <= 2000) {
					pnspOpLogTQuery.setOpContent(argMap.toString());
				}else {
					pnspOpLogTQuery.setOpContent("content too long, not readable");
				}
			}else {
				pnspOpLogTQuery.setOpContent(Constant.NULL_STR);
			}
		}
	}
	
}
