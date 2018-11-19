package com.iexiao.pnsp.utils;

public enum RestCode {
	
	OK("10000","请求成功"),
	UNKNOWN_ERROR("10001","服务异常,未知错误"),
	USER_NOT_EXIST("10002","账号不存在"),
	CHECK_ERROR("10003","参数校验失败"),
	TIMEOUT("10004","请求超时"),
	NOLOGIN("10005","未登录"),
	EXITLOGIN("10006","已登录"),
	BUZY("10007","请求繁忙"),
	SESSION_ERROR("10008","session错误"),
	PERMISSION_ERROR("10009","权限不足"),
	MYBATIS_ERROR("10010","mybatis执行异常"),
	COMMON_ERROR("99999","自定义异常");
	
	private String code;
	
	private String msg;
	
	private RestCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	
	
}
