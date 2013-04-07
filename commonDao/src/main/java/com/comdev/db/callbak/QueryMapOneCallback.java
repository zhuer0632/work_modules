package com.comdev.db.callbak;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;


public class QueryMapOneCallback implements SqlCallback
{

    public Object invoke(Connection conn, ResultSet rs, Sql sql)
            throws SQLException
    {
        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();

        Map<String,Object> map=new HashMap<String, Object>();
        if (rs.next())
        {
          
            for (int i = 1; i <= columnCount; i++)
            {
                String columnName = meta.getColumnLabel(i);
                map.put(columnName, rs.getObject(columnName));
            }
        }
        return map;
    }
}
