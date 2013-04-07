package com.me.ut.string.test;

import com.me.ut.string.ConnUrlAnalyse;
import com.me.ut.xml.PropertiesUT;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * User: zhu
 * Date: 13-4-7
 * Time: 上午1:41
 */
public class ConnTest
{
    private static final Logger logger = Logger.getLogger(ConnTest.class);

    @Test
    public void test01()
    {
        String ip = ConnUrlAnalyse.getIP(PropertiesUT.getProV("db.properties", "jdbc.url"));
        logger.debug(ip);

        String port = ConnUrlAnalyse.getPort(PropertiesUT.getProV("db.properties", "jdbc.url"));
        logger.debug(port);

        String dbname = ConnUrlAnalyse.getDbName(PropertiesUT.getProV("db.properties", "jdbc.url"));
        logger.debug(dbname);

    }

}
