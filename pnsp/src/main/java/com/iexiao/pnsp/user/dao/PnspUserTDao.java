package com.iexiao.pnsp.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iexiao.pnsp.user.query.PnspUserTQuery;

@Mapper
public interface PnspUserTDao {

	List<PnspUserTQuery> selectListByPage(PnspUserTQuery query);

	Integer register(PnspUserTQuery query);

	String getPasswordByPhone(String username);

	List<PnspUserTQuery> getBeanByPhone(String username);
	
}
