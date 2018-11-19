package com.iexiao.pnsp.user.serviceImpl;

import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexiao.pnsp.constants.UserConstant;
import com.iexiao.pnsp.exception.UserException;
import com.iexiao.pnsp.user.dao.PnspUserTDao;
import com.iexiao.pnsp.user.query.PnspUserTQuery;
import com.iexiao.pnsp.user.service.PnspUserTService;
import com.iexiao.pnsp.utils.BeanHelper;
import com.iexiao.pnsp.utils.HashUtil;

@Service
public class PnspUserTServiceImpl implements PnspUserTService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PnspUserTServiceImpl.class);

	@Autowired
	private PnspUserTDao pnspUserTDao;
	
	public List<PnspUserTQuery> selectListByPage(PnspUserTQuery query) {
		return this.pnspUserTDao.selectListByPage(query);
	}
	
	/**
	 * 注册
	 * @author lizhiyong
	 * @date 2018年7月23日
	 * @param query
	 * @return
	 */
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public Integer register(PnspUserTQuery query) {
		//密码加盐
		query.setPassword(HashUtil.encryPasswd(query.getPassword()));
		//填充主键
		BeanHelper.setUUID(query);
		//填充时间
		BeanHelper.onInsert(query);
		BeanHelper.onUpdate(query);
		query.setAddOprid(UserConstant.USER_SYSTEM_OPRID);
		query.setUpdOprid(UserConstant.USER_SYSTEM_OPRID);
		query.setEffStatus(UserConstant.USER_EFF_STATUS_YES);
		query.setRoleId(UserConstant.ROLE_ID_DEFAULT);
		//若数据库设置字段都为非null,则null不能插入数据,且NULL易影响查询数据
		//BeanHelper.setDefaultProp将进行填充,字符串null设置为""，数字null设置为0
		BeanHelper.setDefaultProp(query, PnspUserTQuery.class);
		return this.pnspUserTDao.register(query);
	}

	/**
	 * 根据手机号码获取用户信息
	 * @author lizhiyong
	 * @date 2018年7月23日
	 * @param username
	 * @return
	 */
	public PnspUserTQuery getBeanByPhone(AuthenticationToken authenticationToken) {
		UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
		String username = (String) token.getPrincipal();
		LOGGER.info("token username：" + username);
		String password = new String((char[]) token.getCredentials());
		LOGGER.info("token password：" + password);
		List<PnspUserTQuery> beanByPhone = this.pnspUserTDao.getBeanByPhone(username);
		if(null == beanByPhone || beanByPhone.isEmpty()) {
			throw new UserException(UserException.Type.USER_NOT_FOUND,UserConstant.USER_NOT_FOUND);
		}
		PnspUserTQuery pnspUserTQuery = beanByPhone.get(0);
		if(!password.equals(pnspUserTQuery.getPassword())) {
			throw new UserException(UserException.Type.USER_AUTH_FAIL, UserConstant.PASSWORD_ERROR);
		}
		if(UserConstant.USER_EFF_STATUS_NO.equals(pnspUserTQuery.getEffStatus())) {
			//抛出异常,账号被冻结,请联系管理员
			throw new UserException(UserException.Type.USER_NOT_FOUND,UserConstant.USER_FROZEN);
		}
		return pnspUserTQuery;
	}

	@Override
	public Set<String> getRolesByUsername(String phone) {
		
		return null;
	}
	
	

}
