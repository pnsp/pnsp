package com.iexiao.pnsp.base.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 定时任务入口
 * @author lizhiyong
 * @date 2018年10月18日
 */
@Component
public class TaskItem {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskItem.class);
	
	public void testTaskRun() {
		LOGGER.info("[Springtask running...]");
	}
}
