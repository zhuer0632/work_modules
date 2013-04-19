package com.me.ut.string;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUT
{

    private static final Logger logger = Logger.getLogger(StringUT.class);

    public static List<String> split(String in)
    {

        List<String> out = new ArrayList<String>();

        String[] arr = StringUtils.splitByWholeSeparatorPreserveAllTokens(in,
                ",");

        out = Arrays.asList(arr);
        return out;
    }


    public static void main(String[] ss)
    {

        List<String> list = StringUT.split(",,,");

        System.out.println(list.size());

    }

    public static boolean isEmpty(Object obj)
    {
        if (obj == null)
            return true;

        if (obj instanceof StringBuilder)
        {
            obj = ((StringBuilder) obj).toString();
        }

        if (obj instanceof String)
        {
            if (obj.equals("null"))
            {
                return true;
            }
            return ((String) obj).trim().length() == 0;
        }

        if (obj.getClass().isArray())
            return Array.getLength(obj) == 0;
        if (obj instanceof Collection<?>)
            return ((Collection<?>) obj).isEmpty();
        if (obj instanceof Map<?, ?>)
            return ((Map<?, ?>) obj).isEmpty();
        return false;
    }

    public static String getBetween(String content, String tag)
    {
        return StringUtils.substringBetween(content, tag);
    }

    public static String getUUID()
    {
        return UUID.randomUUID().toString().replace("-",
                "");
    }

    /**
     * 根据指定的表达式，返回其中的最后一个捕获组
     * <br/>
     * 举例：
     * <br/>
     * jdbc:mysql://127.0.0.1:3306/db_xj_valuation?charachterEncoding=UTF-8;
     * 截取其中的IP，需要的正则就是jdbc:mysql://(.{7,}):\d{4}.+
     *
     * @param src
     * @param rex
     * @return
     */
    public static String cut(String src,
                             String rex)
    {
        String s = "";
        Pattern p = Pattern.compile(rex);
        Matcher m = p.matcher(src);
        while (m.find())
        {
            s = m.group(1);
            //break;
        }
        return s;
    }

    /**
     * 拼接List
     *
     * @param errs
     * @param s
     * @return
     */
    public static String toString(List<String> errs, String s)
    {

        StringBuilder stringBuilder = new StringBuilder();

        if (isEmpty(errs))
        {
            return "";
        }

        for (int i = 0; i < errs.size(); i++)
        {

            if (i == errs.size() - 1)
            {
                stringBuilder.append(errs.get(i));
            } else
            {
                stringBuilder.append(errs.get(i) + s);
            }
        }
        return stringBuilder.toString();

    }

    /**
     * 通过regx查找srcStr中匹配的字符串，然后替换成targetStr
     * <p/>
     * <br/>
     * 比如为了替换"jdbc:mysql://127.0.0.1:3306/cms?charachterEncoding=UTF-8;" 中的cms为abc
     * regx参数就应该为   .*:\d{4}/(.*)\?.*
     * <p/>
     * jdbc:jtds:sqlserver://127.0.0.1:1433;databaseName=bea;SelectMethod=cursor
     *
     * @param srcStr
     * @param regx
     * @param targetStr
     * @return
     */
    public static String replace(String srcStr, String regx, String targetStr)
    {
        String s = StringUT.cut(srcStr, regx);
        srcStr = srcStr.replaceAll(s, targetStr);
        logger.debug("替换后" + srcStr);
        return srcStr;
    }

    /**
     * 取得项目的根目录
     */
    public static String root()
    {

        String s = StringUT.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        if (s.endsWith(".jar"))
        {
            s = s.substring(0, s.lastIndexOf("\\"));
        }
        return s;

    }

    /**
     * 把字符串list转换成sql函数in需要的字符串。不包含最外面的括号
     *
     * @param list
     * @return
     */
    public static String getInStr(List<String> list)
    {
        if (isEmpty(list))
        {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++)
        {
            String s = list.get(i);
            if (i == list.size() - 1)
            {
                stringBuilder.append("\'" + s + "\'");
            } else
            {
                stringBuilder.append("\'" + s + "\',");
            }
        }
        return stringBuilder.toString();
    }


}
