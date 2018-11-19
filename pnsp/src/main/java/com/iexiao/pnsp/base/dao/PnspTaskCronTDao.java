package com.iexiao.pnsp.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iexiao.pnsp.base.query.PnspTaskCronTQuery;

@Mapper
public interface PnspTaskCronTDao {

	List<PnspTaskCronTQuery> getAllTaskList();

}
