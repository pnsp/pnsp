package com.iexiao.pnsp.base.query;

import com.iexiao.pnsp.base.BaseQuery;

public class PnspImageTQuery extends BaseQuery{
	
	/** 主键*/
	private String id;
	
	/** 分组主键*/
	private String groupId;
	
	/** 图片名称*/
	private String name;
	
	/** 图片描述*/
	private String descr;
	
	/** router插件name标识*/
	private String routerName;
	
	/** router插件path路径*/
	private String routerPath;
	
	/** 图片资源url*/
	private String url;
	
	/** 本地文件名(无后缀)*/
	private String srcName;
	
	/** 后缀/类型*/
	private String suffix;
	
	/** 状态：1 正常 0 失效*/
	private String effStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
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

	public String getRouterName() {
		return routerName;
	}

	public void setRouterName(String routerName) {
		this.routerName = routerName;
	}

	public String getRouterPath() {
		return routerPath;
	}

	public void setRouterPath(String routerPath) {
		this.routerPath = routerPath;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSrcName() {
		return srcName;
	}

	public void setSrcName(String srcName) {
		this.srcName = srcName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getEffStatus() {
		return effStatus;
	}

	public void setEffStatus(String effStatus) {
		this.effStatus = effStatus;
	}
	
}
