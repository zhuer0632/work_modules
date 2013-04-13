package com.comdev.exceptions;

import org.apache.log4j.Logger;

/**
 * Author: gnoloahs
 * Date: 2013-04-12
 * Time: 上午10:56
 */
public class noImplException extends RuntimeException
{

    private static final Logger logger = Logger.getLogger(noImplException.class);

    public noImplException()
    {
        logger.debug("还没有实现");
    }


}
