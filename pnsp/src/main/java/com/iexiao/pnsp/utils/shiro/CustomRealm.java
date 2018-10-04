package com.iexiao.pnsp.utils.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iexiao.pnsp.base.constants.UserConstant;
import com.iexiao.pnsp.user.query.PnspUserTQuery;
import com.iexiao.pnsp.user.service.PnspUserTService;

/**
 * 自定义Realm
 * @author lizhiyong
 * @date 2018年7月23日
 */
public class CustomRealm extends AuthorizingRealm {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomRealm.class);
	
	@Autowired
	private PnspUserTService pnspUserTService;
	
	/**
	 * 授权
	 * @author lizhiyong
	 * @date 2018年7月23日
	 * @param principalCollection 权限集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		Set<String> roles = (Set<String>) ShiroUtil.getSession(UserConstant.ROLES_SESSION);
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		LOGGER.info("roles ==================== " + ((null == roles) ? null : roles.size()));
		if(null != roles && !roles.isEmpty()) {
			//若还有redis存储缓存，则此处取权限缓存可先本地再redis再数据库
			Set<String> permissions = (Set<String>) ShiroUtil.getSession(UserConstant.PERMISSIONS_SESSION);
			if(null == permissions || permissions.isEmpty()) {
				permissions = getPermissionsByRoles(roles);
				LOGGER.info("permissions ==================== " + ((null == permissions) ? null : permissions.size()));
				ShiroUtil.setSession(UserConstant.PERMISSIONS_SESSION, permissions, 60 * 60 * 24);
			}
			simpleAuthorizationInfo.addStringPermissions(permissions);
		}
		return simpleAuthorizationInfo;
	}
	
	/**
	 * 根据角色集合获取权限集合
	 * @author lizhiyong
	 * @date 2018年7月23日
	 * @param username
	 * @return
	 */
	private Set<String> getPermissionsByRoles(Set<String> roles) {
		//数据库中取权限信息
		//*****************************
		Set<String> permissions = new HashSet<>();
		return permissions;
	}

	/**
	 * 认证
	 * @author lizhiyong
	 * @date 2018年7月23日
	 * @param authenticationToken 主体认证信息
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		PnspUserTQuery query = this.pnspUserTService.getBeanByPhone(authenticationToken);
/*		Set<String> roles = getRolesByUsername(query.getPhone());
		if(null == roles || roles.isEmpty()) {
			roles = new HashSet<>();
			roles.add(Constants.DEFAULT_ROLE);
		}
		ShiroUtil.setSession(Constants.ROLES_SESSION, roles, 60 * 60 * 24);*/
		return new SimpleAuthenticationInfo(query.getPhone(),query.getPassword(),this.getName());
	}

	/**
	 * 根据账号获取角色集合
	 * @author lizhiyong
	 * @date 2018年7月23日
	 * @param username
	 * @return
	 */
	private Set<String> getRolesByUsername(String username) {
		//数据库中取角色信息
		//*****************************
		Set<String> roles = new HashSet<>();
		return roles;
	}
	

}
