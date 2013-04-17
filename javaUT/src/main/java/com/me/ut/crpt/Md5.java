package com.me.ut.crpt;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Author: gnoloahs
 * Date: 2013-04-16
 * Time: 上午9:32
 */
public class Md5
{

    public static String md5(String s)
    {
        String out = DigestUtils.md5Hex(s);
        return out;
    }


}
