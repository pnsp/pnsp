package com.iexiao.pnsp.base.bean;

import java.io.Serializable;
import java.util.List;

import com.iexiao.pnsp.base.BaseQuery;

/**
 * 分页容器类
 * @author lizhiyong
 * @param <T>
 */
public class Pagination<T> implements Serializable{

	private static final long serialVersionUID = 6831216201371159802L;
	
	private Integer page;
	
	private Integer pageSize;
	
	//总数据条数
	private Integer totalCount;
	
	//总页面数
	private Integer pageCount;
	
	private List<T> list;
	
	private String param;
	
	private String url;
	
	private List<String> pageView;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public List<String> getPageView() {
		return pageView;
	}

	public void setPageView(List<String> pageView) {
		this.pageView = pageView;
	}
	
	public Integer getPageCount() {
		if(this.pageSize == null) {
			pageSize = BaseQuery.DEFAULT_SIZE;
		}
		if(this.getTotalCount() != null && pageSize != 0 && totalCount != 0) {
			pageCount = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		}
		return pageCount == null ? 0 : pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	
	public void pageView(String url, String param) {
		this.url = url;
		this.param = param;
	}
	
	public static Integer cpn(Integer page) {
		if(page == null || page < 1) {
			page = 1;
		}
		return page;
	}
}
