package com.iexiao.pnsp.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.iexiao.pnsp.utils.RestCode;
import com.iexiao.pnsp.utils.RestResponse;

/**
 * 自定义过滤器：拦截所有管理相关请求检查其访问权限
 * isAccessAllowed：表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false
 * onAccessDenied：表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可
 * onPreHandle：会自动调用这两个方法决定是否继续处理
 * @author lizhiyong
 * @date 2018年7月24日
 */
public class ShiroFuncFilter extends AuthorizationFilter{

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		httpServletResponse = this.setHeader(httpServletResponse);
		Subject subject = getSubject(httpServletRequest, httpServletResponse);
		if(null != mappedValue) {
			String[] permissions = (String[]) mappedValue; //需要权限才能访问的资源(拦截配置的uri参数)
			for(String per : permissions) {
				if(subject.isPermitted(per)) {
					return true;
				}else {
					responseHandle(httpServletResponse);
				}
			}
		}
		//未传参
		//HttpServletRequest httpRequest = (HttpServletRequest) request;
		String uri = httpServletRequest.getRequestURI();
		String basePath = httpServletRequest.getContextPath();
		if(null != uri && uri.startsWith(basePath)) {
			uri = uri.replaceAll(basePath, "");
		}
		if(subject.isPermitted(uri)) {
			return true;
		}else {
			responseHandle(httpServletResponse);
		}
		return false;
	}

	private HttpServletResponse setHeader(HttpServletResponse httpServletResponse) {
		httpServletResponse.setHeader("Access-Control-Allow-Headers", "X-Requested-With, accept, content-type, xxxx");
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
	    //response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8080");
		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
		return httpServletResponse;
	}

	private void responseHandle(HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		RestResponse<Object> error = RestResponse.error(RestCode.PERMISSION_ERROR);
		out.println(JSON.toJSONString(error));
		out.flush();
		out.close();
	}


}
