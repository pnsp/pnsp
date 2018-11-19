package com.iexiao.pnsp.base.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexiao.pnsp.base.dao.PnspOpLogTDao;
import com.iexiao.pnsp.base.query.PnspOpLogTQuery;
import com.iexiao.pnsp.base.service.PnspOpLogTService;

@Service
public class PnspOpLogTServiceImpl implements PnspOpLogTService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PnspOpLogTServiceImpl.class);

	@Autowired
	private PnspOpLogTDao pnspOpLogTDao;
	
	@Async("logExecutor")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public Integer addOpLog(PnspOpLogTQuery pnspOpLogTQuery) {
		return this.pnspOpLogTDao.addOpLog(pnspOpLogTQuery);
	}
	
}
