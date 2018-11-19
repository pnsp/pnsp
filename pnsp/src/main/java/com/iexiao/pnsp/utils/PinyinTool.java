package com.iexiao.pnsp.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 汉字转化拼音工具类
 * @author lizhiyong
 * @date 2018年10月3日
 */
public class PinyinTool {
	
	static HanyuPinyinOutputFormat format = null;
	
	public static enum Type{
		UPPERCASE,			//大写
		LOWERCASE,			//小写
		FIRSTUPPER			//首字母大写
	}
	
	public PinyinTool() {
		format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
	}
	
	public static String toPinYin(String str) throws BadHanyuPinyinOutputFormatCombination{
		return toPinYin(str,"",Type.UPPERCASE);
	}
	
	public static String toPinYin(String str, String spera) throws BadHanyuPinyinOutputFormatCombination{
		return toPinYin(str, spera, Type.UPPERCASE);
	}
	
	public static String toPinYin(String str, String spera, Type type) throws BadHanyuPinyinOutputFormatCombination {
		if(null == str || str.trim().length() == 0) {
			return "";
		}
		if(type == Type.UPPERCASE) {
			format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		}else{
			format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		}
		String py = "";
		String temp = "";
		String[] t;
		for(int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if((int)c <= 128) {
				py += c;
			}else {
				t = PinyinHelper.toHanyuPinyinStringArray(c, format);
				if(null == t)
					py += c;
				else {
					temp = t[0];
					if(type == Type.FIRSTUPPER)
						temp = t[0].toUpperCase().charAt(0)+temp.substring(1);
					py += temp + (i == str.length()-1?"":spera);
				}
			}
		}
		return py.trim();
	}
	
}
