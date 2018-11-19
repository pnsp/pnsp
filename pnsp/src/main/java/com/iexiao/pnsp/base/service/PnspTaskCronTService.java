package com.iexiao.pnsp.base.service;

import java.util.List;

import com.iexiao.pnsp.base.query.PnspTaskCronTQuery;

public interface PnspTaskCronTService {
	
	/**
	 * 获取所有正在生效的Cron
	 * @author lizhiyong
	 * @date 2018年10月18日
	 * @return
	 */
	public List<PnspTaskCronTQuery> getAllTaskList();
	
}
