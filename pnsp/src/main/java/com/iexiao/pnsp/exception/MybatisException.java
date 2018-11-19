package com.iexiao.pnsp.exception;

public class MybatisException extends RuntimeException implements WithTypeException{
	
	private static final long serialVersionUID = 1L;
	
	private Type type;
	
	public MybatisException(Type type,String msg) {
		super(msg);
		this.type = type;
	}
	
	public Type type() {
		return this.type;
	}
	
	public enum Type{
		RESULT_COUNT_MORE;
	}
}
