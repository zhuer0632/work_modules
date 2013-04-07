package com.comdev.db.callbak;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;

public class QueryArrayListCallback implements SqlCallback
{

    public Object invoke(Connection conn,
                         ResultSet rs,
                         Sql sql)
                                 throws SQLException
    {
        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();

        List<Object[]> list = new LinkedList<Object[]>();
        while (rs.next())
        {
            Object[] re = new Object[columnCount];
            for (int i = 1; i <=  columnCount; i++) 
            {
                re[i-1]= rs.getObject(i);
            }
            list.add(re);
        }
        return list;
    }

}
