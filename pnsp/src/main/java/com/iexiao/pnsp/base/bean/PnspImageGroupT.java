package com.iexiao.pnsp.base.bean;

import com.iexiao.pnsp.base.BaseBean;

/**
 * 图片资源分组
 * @author lizhiyong
 * @date 2018年9月9日
 */
public class PnspImageGroupT extends BaseBean{

	/** 主键*/
	private String id;
	
	/** 分组名称*/
	private String name;
	
	/** 分组描述*/
	private String descr;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	
}
