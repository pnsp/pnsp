package com.iexiao.pnsp.exception;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import com.google.common.collect.ImmutableMap;
import com.iexiao.pnsp.utils.RestCode;

public class Exception2CodeRepo {
	
	private static final ImmutableMap<Object, RestCode> MAP = ImmutableMap.<Object, RestCode>builder()
            .put(IllegalStateException.class,RestCode.UNKNOWN_ERROR)
	        .put(UserException.Type.USER_NOT_FOUND,RestCode.USER_NOT_EXIST)
	        .put(UserException.Type.USER_NOT_LOGIN,RestCode.NOLOGIN)
	        .put(UserException.Type.USER_AUTH_FAIL,RestCode.CHECK_ERROR)
	        .put(UserException.Type.SESSION_INVALID,RestCode.CHECK_ERROR)
	        .build();
	
	private static Object getType(Throwable throwable){
	   try {
		  return FieldUtils.readDeclaredField(throwable, "type", true);
	   } catch (Exception e) {
		  return null;
	   }	
	}
	
	
	public static RestCode getCode(Throwable throwable) {
		if (throwable == null) {
			return RestCode.UNKNOWN_ERROR;
		}
		Object target = throwable;
		if (throwable instanceof WithTypeException) {
			Object type = getType(throwable);
			if (type != null ) {
				target = type;
			}
		}
		RestCode restCode =  MAP.get(target);
		if (restCode != null) {
			if(StringUtils.isNotBlank(throwable.getMessage())) {
				restCode.setMsg(throwable.getMessage());
			}
			return restCode;
		}
		Throwable rootCause = ExceptionUtils.getRootCause(throwable);
		if (rootCause != null) {
			return getCode(rootCause);
		}
		return RestCode.UNKNOWN_ERROR;
	}

}
