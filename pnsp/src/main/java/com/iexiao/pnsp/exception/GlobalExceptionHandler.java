package com.iexiao.pnsp.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.iexiao.pnsp.utils.RestCode;
import com.iexiao.pnsp.utils.RestResponse;

@ControllerAdvice //Spring识别异常处理类
public class GlobalExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ResponseStatus(HttpStatus.OK) //返回结果的HttpStatus为200
	@ExceptionHandler(value = Throwable.class) //定义为处理异常的方法
	@ResponseBody //返回结果序列化成json
	public RestResponse<Object> handler(HttpServletRequest request,Throwable throwable){
		//将错误信息输出
		LOGGER.error(throwable.getMessage());
		Object target = throwable;
		//将异常映射为RestCode，再将RestCode设置到RestResponse中
		RestCode restCode = Exception2CodeRepo.getCode(throwable);
		RestResponse<Object> response = new RestResponse<Object>(restCode.getCode(),restCode.getMsg());
		return response;
	}
	
}
