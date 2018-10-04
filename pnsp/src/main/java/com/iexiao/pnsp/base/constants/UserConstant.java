package com.iexiao.pnsp.base.constants;

public class UserConstant {
	
	/** Session******************************************************************************/
	
	public static final String PLAIN_USER_ATTRIBUTE = "user";
	
	public static final String USER_SESSION = "userSession";
	
	public static final String IP_SESSION = "ipSession";
	
	public static final String MAC_SESSION = "macSession";
	
	public static final String ROLES_SESSION = "rolesSession";
	
	public static final String PERMISSIONS_SESSION = "permissionsSession";
	
	public static final String AUTH_CODE = "authCode";
	
	/** 角色编码******************************************************************************/
	//默认角色
	public static final String DEFAULT_ROLE = "R001";
	//普通用户
	public static final String ROLE_ID_ORDINARY = "R001";
	//超级管理员
	public static final String ROLE_ID_SUPER = "R008";
	
	/** 校验异常信息******************************************************************************/
	
	public static final String USER_NOT_FOUND = "账号不存在";
	
	public static final String PASSWORD_ERROR = "密码错误";
	
	public static final String USER_FROZEN = "账号被冻结，请联系管理员";
	
	public static final String PHONE_NOT_NULL = "手机号码不能为空";
	
	public static final String PASSWORD_NOT_NULL = "密码不能为空";
	
	public static final String AUTH_CODE_NOT_NULL = "验证码不能为空";
	
	public static final String AUTH_CODE_EXPIRED = "验证码已过期，请重新获取";
	
	public static final String AUTH_CODE_ERROR = "验证码输入错误";
	
}
