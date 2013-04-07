package com.comdev.db.callbak;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;


public class QueryIntOneCallback implements SqlCallback
{

    @Override
    public Object invoke(Connection conn, ResultSet rs, Sql sql)
            throws SQLException
    {
        int i = 0;
        if (rs.next())
            i = rs.getInt(1);
        return i;
    }

}
