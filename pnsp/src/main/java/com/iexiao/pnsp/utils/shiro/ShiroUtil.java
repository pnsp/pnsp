package com.iexiao.pnsp.utils.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ShiroUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShiroUtil.class);
	
	/**
	 * 设置默认超时时间的session
	 * @author lizhiyong
	 * @date 2018年7月20日
	 * @param key
	 * @param value
	 */
	public static void setSession(String key,Object value,Subject subject) {
		if(null == subject) {
			return;
		}
		Session session = subject.getSession(true);
		if(null != session) {
//			LOGGER.info("shiro sessionId：" + session.getId());
			session.setAttribute(key, value);
		}
	}
	
	/**
	 * 设置指定超时时间的session
	 * @author lizhiyong
	 * @date 2018年7月20日
	 * @param key
	 * @param value
	 * @param ms
	 */
	public static void setSession(String key,Object value, int ms,Subject subject) {
		if(null == subject) {
			return;
		}
		Session session = subject.getSession(true);
		if(null != session) {
//			LOGGER.info("shiro sessionId：" + session.getId());
			session.setTimeout(ms);
			session.setAttribute(key, value);
		}
	}
	
	/**
	 * 根据key获取session
	 * @author lizhiyong
	 * @date 2018年7月20日
	 * @param key
	 * @return
	 */
	public static Object getSession(String key,Subject subject) {
		if(null == subject) {
			return null;
		}
		Session session = subject.getSession(false);
		if(null != session) {
//			LOGGER.info("shiro sessionId：" + session.getId());
			return session.getAttribute(key);
		}
		return null;
	}
	
	/**
	 * 移除session
	 * @author lizhiyong
	 * @date 2018年7月23日
	 * @param key
	 */
	public static void removeSession(String key,Subject subject) {
		if(null == subject) {
			return;
		}
		Session session = subject.getSession(false);
		if(null != session) {
//			LOGGER.info("shiro sessionId：" + session.getId());
			session.removeAttribute(key);
		}
	}
}
