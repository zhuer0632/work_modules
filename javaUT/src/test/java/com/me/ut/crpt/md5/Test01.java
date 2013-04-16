package com.me.ut.crpt.md5;

import com.me.ut.crpt.Md5;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Author: gnoloahs
 * Date: 2013-04-16
 * Time: 上午9:40
 */
public class Test01
{

    private static final Logger logger = Logger.getLogger(Test01.class);

    @Test
    public void test01()
    {

        String s = Md5.md5("admin");

        logger.debug(s);

    }


}
