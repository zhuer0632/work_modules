package com.comdev.init;

import com.comdev.consts.SysConst;
import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Author: gnoloahs
 * Date: 2013-04-09
 * Time: 上午9:25
 */
public class SysStart extends ContextLoaderListener implements ServletContextListener
{
    private static final Logger logger = Logger.getLogger(SysStart.class);

    @Override
    public void contextDestroyed(ServletContextEvent event)
    {

    }


    @Override
    public void contextInitialized(ServletContextEvent event)
    {
        String syspath = event.getServletContext().getContextPath();
        SysConst.SYS_PATH = syspath;
        System.out.println("项目名称:" + syspath);

    }

}
