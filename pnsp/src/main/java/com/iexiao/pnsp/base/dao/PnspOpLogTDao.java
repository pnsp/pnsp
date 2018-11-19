package com.iexiao.pnsp.base.dao;

import org.apache.ibatis.annotations.Mapper;

import com.iexiao.pnsp.base.query.PnspOpLogTQuery;

@Mapper
public interface PnspOpLogTDao {

	Integer addOpLog(PnspOpLogTQuery pnspOpLogTQuery);

}
