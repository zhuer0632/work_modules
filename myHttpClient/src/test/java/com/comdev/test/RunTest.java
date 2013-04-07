package com.comdev.test;

import com.comdev.constvo.CharsetEnum;
import com.me.http.ut.HttpUT;
import org.apache.log4j.Logger;

/**
 * User: zhu
 * Date: 13-4-6
 * Time: 上午3:31
 */
public class RunTest
{

    private static final Logger logger = Logger.getLogger(RunTest.class);

    public static void main(String[] args)
    {
        RunTest run = new RunTest();
        run.test01();
    }

    private void test01()
    {

        String out = HttpUT.get_url("http://www.baidu.com", CharsetEnum.GB2312).getContent();
        logger.debug(out);

    }


}
