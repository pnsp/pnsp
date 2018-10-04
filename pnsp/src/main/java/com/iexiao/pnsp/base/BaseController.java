package com.iexiao.pnsp.base;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 * @author lizhiyong
 */
public class BaseController {
	protected Logger logger = Logger.getLogger(this.getClass().getName());
	
	private static ThreadLocal<HttpServletRequest> currentRequest = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> currentResponse = new ThreadLocal<HttpServletResponse>();
	
	//设置cookie的默认生命周期
	private static final int COOKIE_MAX_AGE = 7 * 24 * 3600;
	
	@ModelAttribute
	public void initReqAndRep(HttpServletRequest request,HttpServletResponse response) {
		currentRequest.set(request);
		currentResponse.set(response);
	}
	
	public HttpServletRequest getRequest() {
		return (HttpServletRequest) currentRequest.get();
	}
	
	public HttpServletResponse getResponse() {
		return (HttpServletResponse) currentResponse.get();
	}
	
	/**
	 * 获取session对象
	 * @author lizhiyong
	 * @return
	 */
	private HttpSession getSession() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
		if(request == null) {
			return null;
		}
		HttpSession session = request.getSession(false);
		if(session == null) {
			throw new RuntimeException("session已失效");
		}
		return session;
	}
	
	/**
	 * 添加session
	 * @author lizhiyong
	 * @param key
	 * @param value
	 */
	public void addSession(String key,Object value) {
		HttpSession session = this.getSession();
		session.setAttribute(key, value);
	}
	
	/**
	 * 根据key获取session
	 * @author lizhiyong
	 * @param key
	 * @return
	 */
	public Object getSession(String key) {
		HttpSession session = this.getSession();
		Object obj = null;
		if(!StringUtils.isEmpty(key)) {
			obj = session.getAttribute(key);
		}
		return obj;
	}
	
	/**
	 * 根据key删除session
	 * @author lizhiyong
	 * @param key
	 */
	public void removeSession(String key) {
		HttpSession session = this.getSession();
		if(!StringUtils.isEmpty(key)) {
			session.removeAttribute(key);
		}
	}
	
	/**
	 * 添加cookie
	 * @author lizhiyong
	 * @param key
	 * @param value
	 * @param maxAge
	 */
	public void addCookie(String key,String value,int maxAge) {
		HttpServletResponse response = this.getResponse();
		String encodeValue = null;
		try {
			//设置cookie编码
			encodeValue = URLEncoder.encode(value,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.info("编码转换错误，cookie失败");
			e.printStackTrace();
		}
		if(null != encodeValue) {
			Cookie cookie = new Cookie(key,encodeValue);
			//设置cookie共享
			cookie.setPath("/");
			if(maxAge > 0) {
				cookie.setMaxAge(maxAge);
			}
			response.addCookie(cookie);
		}
	}
	
	/**
	 * 添加cookie
	 * @author lizhiyong
	 * @param key
	 * @param value
	 */
	public void addCookie(String key,String value) {
		this.addCookie(key,value,COOKIE_MAX_AGE);
	}
	
	/**
	 * 根据key删除cookie
	 * @author lizhiyong
	 * @param key
	 */
	public void removeCookie(String key) {
		HttpServletResponse response = this.getResponse();
		Cookie uid = new Cookie(key,null);
		//设置共享
		uid.setPath("/");
		uid.setMaxAge(0);
		response.addCookie(uid);
	}
	
	/**
	 * 根据key获取cookie
	 * @author lizhiyong
	 * @param key
	 * @return
	 */
	public String getCookie(String key) {
		HttpServletRequest request = this.getRequest();
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals(key)) {
				return cookie.getValue();
			}
		}
		return null;
	}
}
