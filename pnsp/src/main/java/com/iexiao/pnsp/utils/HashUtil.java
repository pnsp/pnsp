package com.iexiao.pnsp.utils;

import java.nio.charset.Charset;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class HashUtil {
	
	private static final HashFunction FUNCTION = Hashing.md5(); //guava的md5函数
	
	private static final String SALT = "www.iexiao.cn";
	
	//加盐（作用：防止密码过于简单被暴力破解，可用字符串与密码进行拼接）
	public static String encryPasswd(String passwd) {
		HashCode hashCode = FUNCTION.hashString(passwd+SALT,Charset.forName("UTF-8"));
		return hashCode.toString();
	}
	
}
