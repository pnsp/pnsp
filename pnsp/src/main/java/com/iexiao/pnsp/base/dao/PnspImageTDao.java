package com.iexiao.pnsp.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Profile;

import com.iexiao.pnsp.base.query.PnspImageTQuery;

@Mapper
public interface PnspImageTDao {
	
	/**
	 * 根据图片资源分组主键获取图片资源
	 * @author lizhiyong
	 * @date 2018年9月9日
	 * @param query
	 * @return
	 */
	List<PnspImageTQuery> getImageListByGroupId(PnspImageTQuery query);

}
