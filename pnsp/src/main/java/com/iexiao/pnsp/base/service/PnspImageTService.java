package com.iexiao.pnsp.base.service;

import java.util.List;

import com.iexiao.pnsp.base.query.PnspImageTQuery;

public interface PnspImageTService {
	
	/**
	 * 根据图片资源分组主键获取图片资源
	 * @author lizhiyong
	 * @date 2018年9月9日
	 * @param query
	 * @return
	 */
	public List<PnspImageTQuery> getImageListByGroupId(PnspImageTQuery query);

}
