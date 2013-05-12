package com.comdev.ut;

import com.comdev.db.DbKit;

/**
 * Author: gnoloahs
 * Date: 2013-04-09
 * Time: 下午1:20
 */
public class ClassMapping
{

    public static void CreateTable(Class clz)
    {
        DbKit.getDao().create(clz, true);
    }

}
