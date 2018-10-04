package com.iexiao.pnsp.exception;

public class UserException extends RuntimeException implements WithTypeException{

	private static final long serialVersionUID = 1L;
	
	private Type type;
	
	public UserException(Type type,String msg) {
		super(msg);
		this.type = type;
	}
	
	public Type type() {
		return this.type;
	}
	
	public enum Type{
		USER_NOT_LOGIN, USER_NOT_FOUND, USER_AUTH_FAIL, SESSION_INVALID;
	}
	
}
