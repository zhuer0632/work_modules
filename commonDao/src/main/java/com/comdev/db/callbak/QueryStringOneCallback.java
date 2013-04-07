package com.comdev.db.callbak;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;


public class QueryStringOneCallback implements SqlCallback
{

    @Override
    public Object invoke(Connection conn, ResultSet rs, Sql sql)
            throws SQLException
    {
        String str = "";
        if (rs.next())
            str = rs.getString(1);
        return str;
    }

}
