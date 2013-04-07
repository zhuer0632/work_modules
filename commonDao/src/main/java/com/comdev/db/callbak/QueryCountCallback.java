package com.comdev.db.callbak;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;


public class QueryCountCallback implements SqlCallback
{

    public Object invoke(Connection conn,
                         ResultSet rs,
                         Sql sql)
                                 throws SQLException
    {
        int i = 0;
        while (rs.next())
        {
            Object s = rs.getObject(1);
            i++;
        }
        return i;
    }

}
