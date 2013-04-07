package com.comdev.db.callbak;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;


public class QueryStringListCallback implements SqlCallback
{
    public Object invoke(Connection conn, ResultSet rs, Sql sql)
            throws SQLException
    {
        List<String> list = new LinkedList<String>();
        while (rs.next())
        {
            // String columnName = meta.getColumnLabel(1);// 取得第一列的列名
            list.add(rs.getString(1));// 取得第一列的值
        }
        return list;
    }

}
