package com.iexiao.pnsp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class FileUploadConfig {

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		//编码格式，默认为iso-8859-1
		multipartResolver.setDefaultEncoding("UTF-8");
		//最大上传文件的大小，单位为字节
		multipartResolver.setMaxUploadSize(104857600);
		//文件大小阈值，低于此值，只保留在内存里
		//multipartResolver.setMaxInMemorySize(40960);
		return multipartResolver;
	}
	
}
