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


public class QueryMapListCallback implements SqlCallback
{

    public Object invoke(Connection conn, ResultSet rs, Sql sql)
            throws SQLException
    {
        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();

        List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
        while (rs.next())
        {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 1; i <= columnCount; i++)
            {

                String columnName = meta.getColumnLabel(i);
                Object columnValue = rs.getObject(i);

                if (map.get(columnName) != null)
                {
                    columnName = getNewColumnName(columnName, meta, map);
                }
                map.put(columnName, columnValue);
            }
            list.add(map);
        }
        return list;
    }


    public static int i = 0;


    // 如果查询是多表联合查询，那么可能存在列重复现象。导致最终返回结果map中丢失一列。做法是对第二个相同名字的列加一个编号。
    private String getNewColumnName(String columnName, ResultSetMetaData meta,
            Map columnMap)
    {
        String out = "";
        out = columnName + i;
        if (columnMap.containsKey(out))
        {
            return getNewColumnName(out, meta, columnMap);
        }
        return out;
    }

}
