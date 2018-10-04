package com.iexiao.pnsp.user.query;

import com.iexiao.pnsp.base.BaseQuery;

public class PnspUserTQuery extends BaseQuery{

	/** 主键*/
	private String id;
	
	/** 真实姓名*/
	private String lastName;
	
	/** 手机号码*/
	private String phone;
	
	/** 身份证*/
	private String idCard;
	
	/** 电子邮箱*/
	private String email;
	
	/** 性别：1 男 2 女*/
	private String sex;
	
	/** 年龄*/
	private Integer age;
	
	/** 密码*/
	private String password;
	
	/** 状态：1 正常 0 失效*/
	private String effStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEffStatus() {
		return effStatus;
	}

	public void setEffStatus(String effStatus) {
		this.effStatus = effStatus;
	}
	
}
