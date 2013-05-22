package com.comdev.init;


import org.nutz.dao.impl.NutDao;


public class CreateTable
{

    @SuppressWarnings("unchecked")
    public static void create(Class clz, boolean delexist)
    {
        NutDao dao = SpringContextHolder.getBean(NutDao.class);
        dao.create(clz, delexist);
    }

}
