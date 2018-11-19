package com.iexiao.pnsp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.iexiao.pnsp.utils.shiro.ShiroUtil;

public class ResponseHeaderFilter implements Filter{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShiroUtil.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//LOGGER.info("[请求头过滤]");
		HttpServletResponse res = ((HttpServletResponse) response);
		res.setHeader("Access-Control-Allow-Headers", "X-Requested-With, accept, content-type, origin");
		res.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, OPTIONS");
		res.setHeader("Access-Control-Max-Age", "86400000"); //解决跨域请求预检问题
		res.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8080"); //指定跨域服务器
		res.setHeader("Access-Control-Allow-Credentials", "true"); //解决跨域请求不允许发送cookie地址问题
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}

}
