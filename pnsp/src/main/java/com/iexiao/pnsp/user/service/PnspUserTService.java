package com.iexiao.pnsp.user.service;

import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationToken;
import org.springframework.stereotype.Service;

import com.iexiao.pnsp.user.query.PnspUserTQuery;

public interface PnspUserTService {
	
	/**
	 * 根据分页查询用户
	 * @author lizhiyong
	 * @date 2018年7月15日
	 * @param query
	 * @return
	 */
	List<PnspUserTQuery> selectListByPage(PnspUserTQuery query);
	
	/**
	 * 注册
	 * @author lizhiyong
	 * @date 2018年7月17日
	 * @param query
	 * @return
	 */
	Integer register(PnspUserTQuery query);
	
	/**
	 * 根据手机号码获取用户信息
	 * @author lizhiyong
	 * @date 2018年7月23日
	 * @param username
	 * @return
	 */
	PnspUserTQuery getBeanByPhone(AuthenticationToken authenticationToken);
	
	/**
	 * 根据账号获取角色集合
	 * @author lizhiyong
	 * @date 2018年7月23日
	 * @param phone
	 * @return
	 */
	Set<String> getRolesByUsername(String phone);
	
}
