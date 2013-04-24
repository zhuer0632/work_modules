package com.me.ut.exe;

/**
 * Author: gnoloahs
 * Date: 2013-01-25
 * Time: 下午7:05
 */

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RunExe
{

    private static Logger logger = Logger.getLogger(RunExe.class);

    public static void ec(String command) throws InterruptedException
    {
        try
        {
            System.out.println("正在执行命令：" + command);
            Process pro = Runtime.getRuntime().exec(command);
//            InputStream in = pro.getInputStream();
//            writeStream(in);
//            int i = pro.waitFor();

//            logger.debug("执行结果："+i+">>>0表示成功，其他是错误");
            logger.debug("执行结果：>>0表示成功，其他是错误");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * InputStream转为String
     */
    public static void writeStream(InputStream is)
    {
        StringBuilder sb = new StringBuilder();
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "GBK"), 8 * 1024);
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\\r\\n");
            }
        } catch (IOException e)
        {
            sb.delete(0, sb.length());
            e.printStackTrace();
        } finally
        {
            try
            {
                is.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }

}