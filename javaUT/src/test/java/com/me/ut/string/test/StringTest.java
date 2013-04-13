package com.me.ut.string.test;

import com.me.ut.string.ConnUrlAnalyse;
import com.me.ut.string.StringUT;
import org.apache.log4j.Logger;
import org.junit.Test;


/**
 * Author: gnoloahs
 * Date: 2013-04-03
 * Time: 下午8:36
 */
public class StringTest
{

    private Logger logger = Logger.getLogger(StringTest.class);

    @Test
    public void test01()
    {

        String out = ConnUrlAnalyse.getIP("jdbc:jtds:sqlserver://127.0.0.1:1433;databaseName=bea;SelectMethod=cursor");
        logger.debug(out);

    }

    @Test
    /**
     * 截取字符串
     */
    public void test02()
    {
        String s = "javascript:delete_tweet(1830039)";

        String out = StringUT.cut(s, "javascript:delete_tweet\\((\\d*)\\)");

        logger.debug(out);

    }

    @Test
    /**
     *  字符串替换
     */
    public void test03()
    {
        //.+:\\d{4}/(.)?*
        //
        //jdbc:mysql://127.0.0.1:3306/cms?charachterEncoding=UTF-8;
        // jdbc:jtds:sqlserver://127.0.0.1:1433;databaseName=bea;SelectMethod=cursor
        String url = "jdbc:jtds:sqlserver://127.0.0.1:1433;databaseName=bea;SelectMethod=cursor";
        url = StringUT.replace(url, ".*:\\d{4};databaseName=(.*);.*", "haha");

        //url=url.replaceAll("cms","xxx");
        logger.debug(url);

    }
}
