package com.iexiao.pnsp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.iexiao.pnsp.interceptor.RequestLogIntercepter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private RequestLogIntercepter requestLogIntercepter;
	
	public void addInterceptors(InterceptorRegistry registry) {
		
		//excludePathPatterns不需要拦截的请求，addPathPatterns需要拦截的请求，写在前面的拦截器先执行
		registry.addInterceptor(requestLogIntercepter).excludePathPatterns("/static/**").addPathPatterns("/**");
		super.addInterceptors(registry);
		
	}
	
}
