package com.iexiao.pnsp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 特殊工具类
 * @author lizhiyong
 * @date 2018年4月2日
 */
public class SpecialUtil {
	
	/**
	 * 获取UUID
	 * @author lizhiyong
	 * @date 2018年4月2日
	 * @return
	 */
	public static String getUUID() {
		String uuid[] = UUID.randomUUID().toString().split("-");
		StringBuffer id = new StringBuffer();
		for(String str : uuid) {
			id.append(str);
		}
		return id.toString();
	}
	
	/**
	 * 字符串下划线转驼峰
	 * @author lizhiyong
	 * @date 2018年4月2日
	 * @param str
	 * @return
	 */
	public static String lineToHump(String str) {
		Pattern linePattern = Pattern.compile("_(\\w)");
		str = str.toLowerCase();
		Matcher matcher = linePattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while(matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
	
	/**
	 * 获取length长度0-9组成的随机数字符串
	 * @author lizhiyong
	 * @date 2018年4月2日
	 * @param length
	 * @return
	 */
	public static String getRandomCode(int length) {
		String authCode = "";
		for(int i = 0;i < length;i++) {
			authCode += (new Random()).nextInt(10);//0-9不含10
		}
		return authCode;
	}
	
	/**
	 * 获取系统时间戳(系统时间字符串:yyyyMMddHHmmss)
	 * @author lizhiyong
	 * @date 2018年4月2日
	 * @return
	 */
	public static String getTimestamp(){
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
	
	/**
	 * 获取系统时间13位毫秒戳
	 * @author lizhiyong
	 * @date 2018年4月2日
	 * @return
	 */
	public static String getTimeMillis() {
		String str = System.currentTimeMillis() + "";
		String newStr = str.substring(0,13);
		return newStr;
	}
}
