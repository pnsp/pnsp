package com.iexiao.pnsp.exception;

public class CommonException extends RuntimeException implements WithTypeException{

	private static final long serialVersionUID = 1L;
	
	private Type type;
	
	public CommonException(Type type,String msg) {
		super(msg);
		this.type = type;
	}
	
	public Type type() {
		return this.type;
	}
	
	public enum Type{
		COMMON_ERROR;
	}
	
}
