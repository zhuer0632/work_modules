package com.comdev.db.callbak;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;


public class QueryDateOneCallback implements SqlCallback
{

    @Override
    public Object invoke(Connection conn,
                         ResultSet rs,
                         Sql sql)
                                 throws SQLException
    {
        Date date = null;
        if (rs.next())
        {
            Timestamp d = rs.getTimestamp(1);
            date = new Date(d.getTime());
        }
        return date;
    }

}
