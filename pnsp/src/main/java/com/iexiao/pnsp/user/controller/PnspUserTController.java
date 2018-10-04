package com.iexiao.pnsp.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iexiao.pnsp.base.BaseController;
import com.iexiao.pnsp.base.constants.UserConstant;
import com.iexiao.pnsp.exception.UserException;
import com.iexiao.pnsp.user.dto.PnspUserTDTO;
import com.iexiao.pnsp.user.query.PnspUserTQuery;
import com.iexiao.pnsp.user.service.PnspUserTService;
import com.iexiao.pnsp.utils.AuthCode;
import com.iexiao.pnsp.utils.GetMacAddrAndIpUtil;
import com.iexiao.pnsp.utils.HashUtil;
import com.iexiao.pnsp.utils.RestResponse;
import com.iexiao.pnsp.utils.shiro.ShiroUtil;

@Controller
@RequestMapping("/pnspUserT")
public class PnspUserTController extends BaseController{

	private static final Logger LOGGER = LoggerFactory.getLogger(PnspUserTController.class);
	
	@Autowired
	private PnspUserTService pnspUserTService;
	
	@Autowired
	private AuthCode authCode;
	
	/**
	 * 
	 * URL:http://127.0.0.1:8001/pnspUserT/selectListByPage.do
	 * @author lizhiyong
	 * @date 2018年7月16日
	 * @return
	 */
	/*@RequestMapping("/selectListByPage.do")
	@ResponseBody
	public RestResponse<Object> selectListByPage(){
		PnspUserTQuery query = new PnspUserTQuery();
		List<PnspUserTQuery> selectListByPage = this.pnspUserTService.selectListByPage(query);
		return RestResponse.success(selectListByPage);
	}*/
	
	/**
	 * URL:http://127.0.0.1:8001/pnspUserT/getAuthCode
	 * @author lizhiyong
	 * @date 2018年7月30日
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getAuthCode")
	@ResponseBody
	public void getAuthCode(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("image/jpeg");
	    response.setHeader("Pragma", "No-cache");
	    response.setHeader("Cache-Control", "no-cache");
	    response.setDateHeader("Expires", 0);
	    authCode.getRandcode(request, response);
	}
	
	/**
	 * 注册用户
	 * @author lizhiyong
	 * @date 2018年7月17日
	 * @param dto
	 * @return
	 */
	@RequestMapping("/register")
	@ResponseBody
	public RestResponse<Object> register(@RequestBody PnspUserTDTO dto){
		if(StringUtils.isBlank(dto.getPhone())) {
			throw new UserException(UserException.Type.USER_AUTH_FAIL, UserConstant.PHONE_NOT_NULL);
		}else if(StringUtils.isBlank(dto.getPassword())) {
			throw new UserException(UserException.Type.USER_AUTH_FAIL, UserConstant.PASSWORD_NOT_NULL);
		}
		PnspUserTQuery query = new PnspUserTQuery();
		BeanUtils.copyProperties(dto,query);
		this.pnspUserTService.register(query);
		return RestResponse.success();
	}
	
	/**
	 * 登录
	 * @author lizhiyong
	 * @date 2018年7月17日
	 * @param dto
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public RestResponse<Object> login(@RequestBody PnspUserTDTO dto){
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		if(StringUtils.isBlank(dto.getPhone())) {
			throw new UserException(UserException.Type.USER_AUTH_FAIL, UserConstant.PHONE_NOT_NULL);
		}else if(StringUtils.isBlank(dto.getPassword())) {
			throw new UserException(UserException.Type.USER_AUTH_FAIL, UserConstant.PASSWORD_NOT_NULL);
		}
		if(StringUtils.isBlank(dto.getAuthCode())) {
			throw new UserException(UserException.Type.USER_AUTH_FAIL, UserConstant.AUTH_CODE_NOT_NULL);
		}else {
			if(null == session.getAttribute(UserConstant.AUTH_CODE)) {
				throw new UserException(UserException.Type.SESSION_INVALID, UserConstant.AUTH_CODE_EXPIRED);
			}else {
				if(!session.getAttribute(UserConstant.AUTH_CODE).equals(dto.getAuthCode())) {
					throw new UserException(UserException.Type.USER_AUTH_FAIL, UserConstant.AUTH_CODE_ERROR);
				}
			}
		}
		PnspUserTQuery query = new PnspUserTQuery();
		BeanUtils.copyProperties(dto,query);
		Subject subject = SecurityUtils.getSubject();
		LOGGER.info("UsernamePasswordToken ======================= start");
		UsernamePasswordToken token = new UsernamePasswordToken(query.getPhone(), 
				HashUtil.encryPasswd(query.getPassword()));
		LOGGER.info("UsernamePasswordToken ======================= end");
		subject.login(token);
		//开启线程去储存ip和mac到session
		new Thread() {
			public void run() {
				String ipAddress = GetMacAddrAndIpUtil.getIpAddress((HttpServletRequest) request);
				LOGGER.info("login ipAddress ================== " + ipAddress);
				if(null != ipAddress && ipAddress.length() > 0) {
					String macAddress = GetMacAddrAndIpUtil.getMacAddress(ipAddress);
					ShiroUtil.setSession(UserConstant.IP_SESSION, ipAddress, 60 * 60 * 24 * 1000);
					ShiroUtil.setSession(UserConstant.MAC_SESSION, macAddress, 60 * 60 * 24 * 1000);
				}
			}
		}.start();
		ShiroUtil.setSession(UserConstant.USER_SESSION, query.getPhone(), 60 * 60 * 24 * 1000);
		return RestResponse.success();
	}
	
	/**
	 * 登出
	 * @author lizhiyong
	 * @date 2018年7月23日
	 * @param dto
	 */
	@RequestMapping("/logout.do")
	@ResponseBody
	public RestResponse<Object> logout(){
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		Subject subject = SecurityUtils.getSubject();
		ShiroUtil.removeSession(UserConstant.ROLES_SESSION);
		ShiroUtil.removeSession(UserConstant.USER_SESSION);
		ShiroUtil.removeSession(UserConstant.MAC_SESSION);
		ShiroUtil.removeSession(UserConstant.IP_SESSION);
		subject.logout();
		return RestResponse.success();
	}
	
}
