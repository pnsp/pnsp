package com.iexiao.pnsp.base.query;

import com.iexiao.pnsp.base.BaseQuery;

public class PnspImageGroupTQuery extends BaseQuery{
	
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
