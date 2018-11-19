package com.iexiao.pnsp.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.iexiao.pnsp.constants.Constant;

/**
 * 数据库插入接口请求日志注解类
 * @author lizhiyong
 * @date 2018年10月23日
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OpLog {

	String opDescr() default Constant.NULL_STR;
	
	String opType() default OpLog.QUERY;
	
	String keyWord() default Constant.NULL_STR;
	
	public static String QUERY = "query";
	
	public static String UPDATE = "update";
	
}
