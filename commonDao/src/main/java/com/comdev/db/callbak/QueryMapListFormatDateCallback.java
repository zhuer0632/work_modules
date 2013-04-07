package com.comdev.db.callbak;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.sql.*;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;

public class QueryMapListFormatDateCallback implements SqlCallback
{

    @Override
    public Object invoke( Connection conn, ResultSet rs, Sql sql )
            throws SQLException
    {
        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();

        List<Map<String,Object>> list = new LinkedList<Map<String,Object>>();
        while (rs.next())
        {
            Map<String,Object> map=new HashMap<String, Object>();
            for (int i = 1; i <= columnCount; i++)
            {
                String columnName = meta.getColumnLabel(i);
                
                if( meta.getColumnType( i ) == Types.DATE ||
                        meta.getColumnType( i ) == Types.TIME ||
                        meta.getColumnType( i ) == Types.TIMESTAMP )
                {
                    if( rs.getObject( columnName ) != null )
                    {
                        map.put( columnName, rs.getObject( columnName ).toString().substring( 0, 10 ) );
                    }
                }
                else
                {
                    map.put(columnName, rs.getObject(columnName));  
                }
            }
            list.add(map);
        }
        return list;
    }

}

