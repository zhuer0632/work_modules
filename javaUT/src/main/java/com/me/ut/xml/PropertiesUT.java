package com.me.ut.xml;


import java.io.*;
import java.util.Properties;


public class PropertiesUT
{

    public static String getProV(String filePath,
                                 String key)
    {
        Properties props = new Properties();
        try
        {
            InputStream in = new FileInputStream(new File(filePath));
            props.load(in);
            String value = props.getProperty(key);
            return value;
        } catch
        (Exception e)
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

            InputStream fis = PropertiesUT.class.getClassLoader().getResourceAsStream(filePath);
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
