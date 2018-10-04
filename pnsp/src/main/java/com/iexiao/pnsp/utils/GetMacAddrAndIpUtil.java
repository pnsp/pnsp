package com.iexiao.pnsp.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取IP和MAC的工具类
 * @author lizhiyong
 * @date 2018年7月23日
 */
public class GetMacAddrAndIpUtil {

	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if(null == ip || ip.length() == 0 || "nuknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(null == ip || ip.length() == 0 || "nuknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(null == ip || ip.length() == 0 || "nuknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	private static String callCmd(String[] cmd) {
		String result = "";
		String line = "";
		try {
			Process proc = Runtime.getRuntime().exec(cmd);
			InputStreamReader is = new InputStreamReader(proc.getInputStream());
			BufferedReader br = new BufferedReader(is);
			while(null != (line = br.readLine())) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * @author lizhiyong
	 * @date 2018年7月23日
	 * @param cmd 第一个命令
	 * @param another 第二个命令
	 * @return
	 */
	private static String callCmd(String[] cmd, String[] another) {
		String result = "";
		String line = "";
		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(cmd);
			//已经执行完第一个命令，准备执行第二个命令
			proc.waitFor();
			proc = rt.exec(another);
			InputStreamReader is = new InputStreamReader(proc.getInputStream());
			BufferedReader br = new BufferedReader(is);
			while(null != (line = br.readLine())) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * IP 目标ip,一般在局域网内
	 * @author lizhiyong
	 * @date 2018年7月23日
	 * @param ip
	 * @param sourceString
	 * @param macSeparator
	 * @return
	 */
	private static String filterMacAddress(final String ip,final String sourceString, final String macSeparator) {
		String result = "";
		String regex = "((([0-9,A-F,a-f]{1,2}" + macSeparator + "){1,5})[0-9,A-F,a-f]{1,2})";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(sourceString);
		while(matcher.find()) {
			result = matcher.group(1);
			if(sourceString.indexOf(ip) <= sourceString.lastIndexOf(matcher.group(1))) {
				break;//如果有多个IP，只匹配本IP对应的MAC
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @author lizhiyong
	 * @date 2018年7月23日
	 * @param ip 目标ip
	 * @return mac address
	 */
	private static String getMacInWindows(final String ip) {
		String result = "";
		String[] cmd = {"cmd","/c","ping " + ip};
		String[] another = {"cmd", "/c", "arp -a"};
		String cmdResult = callCmd(cmd, another);
		result = filterMacAddress(ip, cmdResult, "-");
		return result;
	}
	
	/**
	 * 
	 * @author lizhiyong
	 * @date 2018年7月23日
	 * @param ip 目标ip
	 * @return mac address
	 */
	private static String getMacInLinux(final String ip) {
		String result = "";
		String[] cmd = {"/bin/sh", "-c", "ping " + ip + " -c 2 && arp -a"};
		String cmdResult = callCmd(cmd);
		result = filterMacAddress(ip, cmdResult, ":");
		return result;
	}
	
	/**
	 * 获取MAC地址
	 * @author lizhiyong
	 * @date 2018年7月23日
	 * @param ip
	 * @return MAC
	 */
	public static String getMacAddress(String ip) {
		String macAddress = "";
		macAddress = getMacInWindows(ip).trim();
		if(null == macAddress || "".equals(macAddress)) {
			macAddress = getMacInLinux(ip).trim();
		}
		//没有获取到用00-00-00-00-00-00-00-00
		if(null == macAddress || "".equals(macAddress)) {
			macAddress = "00-00-00-00-00-00-00-00";
		}
		return macAddress;
	}
}
