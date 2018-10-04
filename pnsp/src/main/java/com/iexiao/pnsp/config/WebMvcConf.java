package com.iexiao.pnsp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//import com.iexiao.pnsp.interceptor.AllReqInterceptor;

//@Configuration
public class WebMvcConf extends WebMvcConfigurerAdapter{
	
	/*@Autowired
	private AllReqInterceptor allReqInterceptor;*/
	
	/*public void addInterceptors(InterceptorRegistry registry) {
		
		//excludePathPatterns不需要拦截的请求，addPathPatterns需要拦截的请求，写在前面的拦截器先执行
		registry.addInterceptor(allReqInterceptor).excludePathPatterns("/static").addPathPatterns("/**");
		super.addInterceptors(registry);
		
	}*/
	
}
