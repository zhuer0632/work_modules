package com.comdev.ut;

/**
 * Author: gnoloahs
 * Date: 2013-04-03
 * Time: 下午5:10
 */

import com.comdev.db.DbKit;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

/**
 * 取得db.properties中的配置
 */
public class PropertiesUT
{

    private static final HashMap<String, String> cache = new HashMap<String, String>();

    private static String DBCONFIG = DbKit.class.getResource("/").getPath()+"db.properties";

    public static String get_driverclass()
    {
        if (cache.get("driverclass") != null)
        {
            return cache.get("driverclass");
        }
        return getProV(DBCONFIG, "jdbc.driverclass");
    }

    public static String get_url()
    {
        if (cache.get("jdbc.url") != null)
        {
            return cache.get("jdbc.url");
        }
        String s = getProV(DBCONFIG, "jdbc.url");
        cache.put("jdbc.url", s);
        return s;
    }

    public static String get_username()
    {
        if (cache.get("jdbc.username") != null)
        {
            return cache.get("jdbc.username");
        }
        String s = getProV(DBCONFIG, "jdbc.username");
        cache.put("jdbc.username", s);
        return s;
    }

    public static String get_password()
    {
        if (cache.get("jdbc.password") != null)
        {
            return cache.get("jdbc.password");
        }
        String s = getProV(DBCONFIG, "jdbc.password");
        cache.put("jdbc.password", s);
        return s;
    }

    public static int   get_maxconn()
    {
        if (cache.get("jdbc.maxconn") != null)
        {
            return Integer.valueOf(cache.get("jdbc.maxcon"));
        }
        String s = getProV(DBCONFIG, "jdbc.maxconn");
        cache.put("jdbc.maxconn", s);
        return Integer.valueOf(s);
    }

    public static int   get_minconn()
    {
        if (cache.get("jdbc.minconn") != null)
        {
            return Integer.valueOf(cache.get("jdbc.minconn"));
        }
        String s = getProV(DBCONFIG, "jdbc.minconn");
        cache.put("jdbc.minconn", s);
        return Integer.valueOf(s);
    }

    public static String getProV(String filePath,
                                 String key)
    {
        Properties props = new Properties();
        try
        {
            InputStream in = new FileInputStream(filePath);
            props.load(in);
            String value = props.getProperty(key);
            return value;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


    public static void setProV(String filePath,
                               String parameterName,
                               String parameterValue)
    {
        Properties prop = new Properties();
        try
        {

            InputStream fis = new FileInputStream(filePath);
            // 从输入流中读取属性列表（键和元素对）
            prop.load(fis);
            // 调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
            // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
            OutputStream fos = new FileOutputStream(filePath);
            prop.setProperty(parameterName,
                    parameterValue);
            // 以适合使用 load 方法加载到 Properties 表中的格式，
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            prop.store(fos,
                    "Update '" + parameterName + "' value");
        } catch (IOException e)
        {
            System.err.println("Visit " + filePath + " for updating "
                    + parameterName + " value error");
        }

    }


}
