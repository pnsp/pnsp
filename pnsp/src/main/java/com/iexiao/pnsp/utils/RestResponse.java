package com.iexiao.pnsp.utils;

/**
 * 通信组件服务协议(响应结果)
 * @author Administrator
 *
 * @param <T>
 */
public class RestResponse<T> {
	
	private String code;
	
	private String msg;
	
	private T result;
	
	//成功且无返回结果
	public static <T> RestResponse<T> success(){
		RestResponse<T> response = new RestResponse<>();
		return response;
	}
	
	//成功且支持返回结果
	public static <T> RestResponse<T> success(T result){
		RestResponse<T> response = new RestResponse<>();
		response.setResult(result);
		return response;
	}

	public static <T> RestResponse<T> error(RestCode restCode){
		return new RestResponse<>(restCode.getCode(),restCode.getMsg());
	}
	 
	public RestResponse(){
		this.setCode(RestCode.OK.getCode());
		this.setMsg(RestCode.OK.getMsg());
	}
	
	public RestResponse(String code,String msg) {
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

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
	
	
	
}
