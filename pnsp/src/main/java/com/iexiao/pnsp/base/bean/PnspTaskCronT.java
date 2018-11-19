package com.iexiao.pnsp.base.bean;

import java.util.Date;

import com.iexiao.pnsp.base.BaseBean;

public class PnspTaskCronT extends BaseBean{

	/** 主键*/
	private String id;
	
	/** 定时任务函数名*/
	private String taskName;
	
	/** 定时任务描述*/
	private String taskDescr;
	
	/** 定时任务周期表达式*/
	private String cron;
	
	/** 生效日期*/
	private Date effdt;
	
	/** 生效状态：1 正常 0 失效*/
	private String effStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescr() {
		return taskDescr;
	}

	public void setTaskDescr(String taskDescr) {
		this.taskDescr = taskDescr;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public Date getEffdt() {
		return effdt;
	}

	public void setEffdt(Date effdt) {
		this.effdt = effdt;
	}

	public String getEffStatus() {
		return effStatus;
	}

	public void setEffStatus(String effStatus) {
		this.effStatus = effStatus;
	}
	
}
