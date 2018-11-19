package com.iexiao.pnsp.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.iexiao.pnsp.constants.Constant;
import com.iexiao.pnsp.utils.shiro.ShiroUtil;

/**
 * 有请求到时日志上下文中加入requestId
 * @author lizhiyong
 * @date 2018年10月16日
 */
@Component
public class RequestLogIntercepter extends HandlerInterceptorAdapter{
	
	  private static final Logger LOGGER = LoggerFactory.getLogger(ShiroUtil.class);
	  
	  @Override
	  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	      throws Exception {
		  
		//LOGGER.info("[日志mdc加入requestId]");
		//日志ID
	    String requestId = UUID.randomUUID().toString().replaceAll("-", Constant.NULL_STR);
	    //日志mdc
	    ThreadContext.put(Constant.LOG_REQUEST_ID, requestId);
	    
	    return true;
	    
	  }

	  @Override
	  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
				throws Exception {
		  
		  //清空日志请求ID
		  ThreadContext.remove(Constant.LOG_REQUEST_ID);
		  
	  }
	  
}


