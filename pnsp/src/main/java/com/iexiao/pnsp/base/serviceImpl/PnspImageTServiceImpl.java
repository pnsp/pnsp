package com.iexiao.pnsp.base.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iexiao.pnsp.base.dao.PnspImageTDao;
import com.iexiao.pnsp.base.query.PnspImageTQuery;
import com.iexiao.pnsp.base.service.PnspImageTService;

@Service
public class PnspImageTServiceImpl implements PnspImageTService{

	private static final Logger LOGGER = LoggerFactory.getLogger(PnspImageTServiceImpl.class);
	
	@Autowired
	private PnspImageTDao pnspImageTDao;
	
	/**
	 * 根据图片资源分组主键获取图片资源
	 * @author lizhiyong
	 * @date 2018年9月9日
	 * @param query
	 * @return
	 */
	public List<PnspImageTQuery> getImageListByGroupId(PnspImageTQuery query) {
		return this.pnspImageTDao.getImageListByGroupId(query);
	}

}
