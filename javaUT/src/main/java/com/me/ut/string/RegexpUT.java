package com.me.ut.string;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpUT {

    /**
     * 捕获所有匹配的字符串
     * 
     * @param str
     * @param regex
     * @return
     */
    public static List<String> match(String str, String regex) {
	List<String> list = new ArrayList<String>();

	Pattern p = Pattern.compile(regex);
	Matcher mat = p.matcher(str);
	while (mat.find()) {
	    list.add(mat.group());
	}

	return list;
    }

    /**
     * 捕获第一个匹配的字符串
     * 
     * @param str
     * @param regex
     * @return
     */
    public static String matchOne(String str, String regex) {
	String out = "";
	Pattern p = Pattern.compile(regex);
	Matcher mat = p.matcher(str);
	if (mat.find()) {
	    out = mat.group();
	}
	return out;
    }

    /**
     * 根据regex查找，把str中的字符串替换成replacStr
     */
    public static String replace(String str, String regex, String replacStr) {
	
	return str.replaceAll(regex, replacStr);
	
    }

}
