package com.iexiao.pnsp.base.query;

import java.util.Date;

import com.iexiao.pnsp.base.BaseQuery;

/**
 * 接口请求日志记录
 * @author lizhiyong
 * @date 2018年10月23日
 */
public class PnspOpLogTQuery extends BaseQuery{
	
	/** 主键*/
	private String id;
	
	/** 操作人手机号码*/
	private String phone;
	
	/** 请求路径*/
	private String reqUrl;
	
	/** 操作内容*/
	private String opContent;
	
	/** 操作类型*/
	private String opType;
	
	/** 操作描述*/
	private String opDescr;
	
	/** 操作状态：0 失败 1 成功*/
	private String opStatus;
	
	/** 操作人IP*/
	private String opIp;
	
	/** 操作人mac*/
	private String opMac;
	
	/** 操作时间*/
	private Date opDttm;
	
	/** 关键字*/
	private String keyWord;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getReqUrl() {
		return reqUrl;
	}

	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}

	public String getOpContent() {
		return opContent;
	}

	public void setOpContent(String opContent) {
		this.opContent = opContent;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getOpDescr() {
		return opDescr;
	}

	public void setOpDescr(String opDescr) {
		this.opDescr = opDescr;
	}

	public String getOpStatus() {
		return opStatus;
	}

	public void setOpStatus(String opStatus) {
		this.opStatus = opStatus;
	}

	public String getOpIp() {
		return opIp;
	}

	public void setOpIp(String opIp) {
		this.opIp = opIp;
	}

	public String getOpMac() {
		return opMac;
	}

	public void setOpMac(String opMac) {
		this.opMac = opMac;
	}

	public Date getOpDttm() {
		return opDttm;
	}

	public void setOpDttm(Date opDttm) {
		this.opDttm = opDttm;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
}
