package com.iexiao.pnsp.base.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iexiao.pnsp.base.dao.PnspTaskCronTDao;
import com.iexiao.pnsp.base.query.PnspTaskCronTQuery;
import com.iexiao.pnsp.base.service.PnspTaskCronTService;

@Service
public class PnspTaskCronTServiceImpl implements PnspTaskCronTService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PnspTaskCronTServiceImpl.class);
	
	@Autowired
	private PnspTaskCronTDao pnspTaskCornTDao;

	public List<PnspTaskCronTQuery> getAllTaskList() {
		return pnspTaskCornTDao.getAllTaskList();
	}

}
