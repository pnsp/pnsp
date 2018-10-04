package com.iexiao.pnsp.base;

import java.io.Serializable;
import java.util.Date;

public class BaseBean implements Serializable{

	private static final long serialVersionUID = 1L;

	protected String addOprid;
	
	protected Date addDttm;
	
	protected String updOprid;
	
	protected Date updDttm;
	
	public BaseBean() {
		
	}

	public String getAddOprid() {
		return addOprid;
	}

	public void setAddOprid(String addOprid) {
		this.addOprid = addOprid;
	}

	public Date getAddDttm() {
		return addDttm;
	}

	public void setAddDttm(Date addDttm) {
		this.addDttm = addDttm;
	}

	public String getUpdOprid() {
		return updOprid;
	}

	public void setUpdOprid(String updOprid) {
		this.updOprid = updOprid;
	}

	public Date getUpdDttm() {
		return updDttm;
	}

	public void setUpdDttm(Date updDttm) {
		this.updDttm = updDttm;
	}
	
	
	
}
