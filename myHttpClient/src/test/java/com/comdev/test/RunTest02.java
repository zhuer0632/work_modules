package com.comdev.test;

import com.comdev.constvo.CharsetEnum;
import com.me.http.ut.HttpUT;
import com.me.http.ut.TextHttpResponse;
import com.me.ut.file.FileUT;
import org.apache.log4j.Logger;
import org.junit.Test;


/**
 * User: zhu
 * Date: 13-4-6
 * Time: 上午4:54
 */
public class RunTest02
{
    private static final Logger logger = Logger.getLogger(RunTest02.class);

    @Test
    public void test01()
    {
//         http://www.oschina.net/action/tweet/go?obj=272975666&type=16&user=569120
        String url = "http://www.oschina.net/action/tweet/go?obj=452353&type=17&user=569120";
//        url="http://www.baidu.com/";
//        url="http://www.oschina.net/";
        TextHttpResponse response = HttpUT.get_url(url, CharsetEnum.UTF_8);
        logger.debug(response.getCode());

        FileUT.writeAll("c:\\1.html", response.getContent(), CharsetEnum.GB2312);
//        logger.debug(response.getContent());
        logger.debug(response.getCode());
    }
}
