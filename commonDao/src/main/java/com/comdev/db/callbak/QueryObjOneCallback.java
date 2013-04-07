package com.comdev.db.callbak;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;

public class QueryObjOneCallback implements SqlCallback
{

    @Override
    public Object invoke(Connection arg0,
                         ResultSet rs,
                         Sql arg2)
                                  throws SQLException
    {
        Object o = null;
        if (rs.next())
            o = rs.getObject(1);
        return o;
    }

}
