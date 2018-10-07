package com.iexiao.pnsp.filter;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.iexiao.pnsp.utils.RestCode;
import com.iexiao.pnsp.utils.RestResponse;

/**
 * 自定义过滤器：拦截所有需登录而未登录的请求
 * @author lizhiyong
 * @date 2018年7月24日
 */
public class ShiroAuthLoginFilter extends FormAuthenticationFilter{

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		if(this.isLoginRequest(httpServletRequest, httpServletResponse)) {
			if(this.isLoginSubmission(httpServletRequest, httpServletResponse)) {
				return this.executeLogin(httpServletRequest, httpServletResponse);
			}else {
				return true;
			}
		}else {
			httpServletResponse.setContentType("application/json");
			httpServletResponse.setCharacterEncoding("UTF-8");
			PrintWriter out = httpServletResponse.getWriter();
			RestResponse<Object> error = RestResponse.error(RestCode.NOLOGIN);
			out.println(JSON.toJSONString(error));
			out.flush();
			out.close();
			return false;
		}
	}
	
	/*private HttpServletResponse setHeader(HttpServletResponse httpServletResponse) {
		httpServletResponse.setHeader("Access-Control-Allow-Headers", "X-Requested-With, accept, content-type, xxxx");
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
	    httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8080");
		//httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
		return httpServletResponse;
	}*/
	
}
