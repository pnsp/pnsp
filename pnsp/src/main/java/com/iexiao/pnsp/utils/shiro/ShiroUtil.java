package com.iexiao.pnsp.utils.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iexiao.pnsp.exception.GlobalExceptionHandler;

public final class ShiroUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShiroUtil.class);
	
	/**
	 * 设置默认超时时间的session
	 * @author lizhiyong
	 * @date 2018年7月20日
	 * @param key
	 * @param value
	 */
	public static void setSession(String key,Object value) {
		Subject subject = SecurityUtils.getSubject();
		if(null == subject) {
			return;
		}
		Session session = subject.getSession(true);
		LOGGER.info("shiro sessionId =================" + session.getId());
		if(null != session) {
			session.setAttribute(key, value);
		}
	}
	
	/**
	 * 设置指定超时时间的session
	 * @author lizhiyong
	 * @date 2018年7月20日
	 * @param key
	 * @param value
	 * @param second
	 */
	public static void setSession(String key,Object value, int second) {
		Subject subject = SecurityUtils.getSubject();
		if(null == subject) {
			return;
		}
		Session session = subject.getSession(true);
		LOGGER.info("shiro sessionId =================" + session.getId());
		if(null != session) {
			session.setTimeout(second);
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
	public static Object getSession(String key) {
		Subject subject = SecurityUtils.getSubject();
		if(null == subject) {
			return null;
		}
		Session session = subject.getSession(false);
		LOGGER.info("shiro sessionId =================" + session.getId());
		if(null != session) {
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
	public static void removeSession(String key) {
		Subject subject = SecurityUtils.getSubject();
		if(null == subject) {
			return;
		}
		Session session = subject.getSession(false);
		LOGGER.info("shiro sessionId =================" + session.getId());
		if(null != session) {
			session.removeAttribute(key);
		}
	}
}
