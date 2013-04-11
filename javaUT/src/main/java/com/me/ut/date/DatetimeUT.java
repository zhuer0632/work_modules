package com.me.ut.date;


import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;


public class DatetimeUT
{

    /**
     * 返回 yyyy-MM-dd日期部分
     * 
     * @return
     */
    public static String getDate(Date date)
    {
        DateConverter d = new DateConverter();
        String[] datePattern =
        {
                "yyyy-MM-dd"
        };

        d.setPatterns(datePattern);
        ConvertUtils.register(d,
                              java.util.Date.class);

        Object obj = d.convert(String.class,
                               date);

        return obj.toString();
    }

    /**
     * 返回 yyyy-MM-dd HH:mm:ss
     * 
     * @return
     */
    public static String getDatetime(Date date)
    {
        DateConverter d = new DateConverter();
        String[] datePattern =
        {
            "yyyy-MM-dd HH:mm:ss"
        };

        d.setPatterns(datePattern);
        ConvertUtils.register(d,
                              java.util.Date.class);

        Object obj = d.convert(String.class,
                               date);

        return obj.toString();
    }
    
    /**
     *
     * 返回 yyyy-MM-dd HH:mm:ss.SSS 
     * 
     * @return
     */
    public static String getDatetimeStamp(Date date)
    {
        DateConverter d = new DateConverter();
        String[] datePattern =
        {
            "yyyy-MM-dd HH:mm:ss.SSS"
        };

        d.setPatterns(datePattern);
        ConvertUtils.register(d,
                              java.util.Date.class);

        Object obj = d.convert(String.class,
                               date);

        return obj.toString();
    }

    /**
     * 取得服务器当前时间
     * @return
     */
    public static Date getNow()
    {
            return new Date();
    }

    /**
     * 返回模板 yyyy_MM_dd_HH_mm
     * @return
     */
    public static String getNow_str()
    {
        DateConverter d = new DateConverter();
        String[] datePattern =
                {
                        "yyyy_MM_dd_HH_mm"
                };

        d.setPatterns(datePattern);
        ConvertUtils.register(d,
                java.util.Date.class);

        Object obj = d.convert(String.class,
                new Date());

        return obj.toString();
    }

    /**
     * 返回模板 yyyy_MM_dd
     * @return
     */
    public static String getDate_str()
    {
        DateConverter d = new DateConverter();
        String[] datePattern =
         {
                        "yyyy_MM_dd"
         };

        d.setPatterns(datePattern);
        ConvertUtils.register(d,
                java.util.Date.class);

        Object obj = d.convert(String.class,
                new Date());

        return obj.toString();
    }

    public static String getNow_long()
    {
        return String.valueOf(new Date().getTime());
    }

    /**
     *
     *  字符串转java.util.Date ,字符串格式必须是 yyyy-MM-dd HH:mm:ss
     *
     * @param dateStr
     * @return
     */
    public static Date getDate(String dateStr)
    {
        if (null == dateStr || dateStr.length() <= 0)
        {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = formatter.parse(dateStr,
                new ParsePosition(0));
        return d;
    }
}
