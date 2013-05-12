package com.comdev.db.ddl.impl;

import com.comdev.db.DbKit;
import com.comdev.db.dbinfovo.ColumnInfo;
import com.comdev.db.ddl.IDbMeta;
import com.comdev.ut.PropertiesUT;
import com.me.ut.string.StringUT;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: zhu
 * Date: 13-4-7
 * Time: 上午3:14
 */
public class SqlServer2005DBMeta implements IDbMeta
{
    private static final Logger logger = Logger.getLogger(SqlServer2005DBMeta.class);

    @Override
    /**
     * @return 1:成功，2：失败：3：数据库已经存在，没有操作，直接返回
     */
    public int createDb(String dbname)
    {

        if (!checkDbConfig())
        {
            throw new RuntimeException("数据库配置错误");
        }

        //数据库已经存在返回3
        if (checkHasDB(dbname))
        {
            return 3;
        }

        try
        {
            DbKit.execute("CREATE DATABASE " + dbname);
        } catch (Exception e)
        {
            e.printStackTrace();
            return 2;
        }
        return 1;
    }


    @Override
    public List<String> getdbNames()
    {
        List<String> out = new ArrayList<String>();
        Connection conn = null;       //定义变量
        PreparedStatement pstet = null;
        ResultSet rs = null;
        try
        {

            Class.forName(PropertiesUT.get_driverclass());    //装在去动程序

            conn = DriverManager.getConnection(PropertiesUT.get_url(), PropertiesUT.get_username(), PropertiesUT.get_password());   //加载驱动并与数据库连接

            String sql = "use master;select [name] from [sysdatabases] order by [name]";   //sql  语句

            pstet = conn.prepareStatement(sql);   //发送sql语句并得到结果集

            rs = pstet.executeQuery();

            while (rs.next())
            {  // 判断数据集中是否有数据
                out.add(rs.getString(1));
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            clossConnection(conn);
            clossResultSet(rs);
            clossStatement(pstet);
        }
        return out;
    }

    @Override
    public boolean checkDbConfig()
    {
        String url = PropertiesUT.get_url();
        String username = PropertiesUT.get_username();
        String password = PropertiesUT.get_password();
        String driverclass = PropertiesUT.get_driverclass();

        Connection con = null;
        try
        {
            Class.forName(driverclass);
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e)
        {
            return false;
        } finally
        {
            if (con != null)
            {
                try
                {
                    if (con.isClosed())
                    {
                        con.close();
                    }
                } catch (SQLException e)
                {
                }
            }
        }
        return true;
    }

    @Override
    public boolean checkHasDB(String dbName)
    {
        String url = PropertiesUT.get_url();
        String username = PropertiesUT.get_username();
        String password = PropertiesUT.get_password();
        String driverclass = PropertiesUT.get_driverclass();

        Connection con = null;
        try
        {
            Class.forName(driverclass);
            url = StringUT.replace(url, ".*:\\d{4};databaseName=(.*);.*", dbName);
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e)
        {
            return false;
        } finally
        {
            if (con != null)
            {
                try
                {
                    if (con.isClosed())
                    {
                        con.close();
                    }
                } catch (SQLException e)
                {
                }
            }
        }
        return true;
    }

    private void clossConnection(Connection conn)
    {
        try
        {
            if (conn != null && !conn.isClosed())
            {
                conn.close();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void clossStatement(PreparedStatement pstet)
    {
        try
        {
            if (pstet != null)
            {
                pstet.close();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private void clossResultSet(ResultSet rs)
    {
        try
        {
            if (rs != null)
            {
                rs.close();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public List<String> tableNames()
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<String> tableNames(String dbName)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void changeDbConn(String db_properties)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<String> columnNames(String tableName)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<String> columnMeta(String tableName, String columnName)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addTable()
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addColumn()
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delColumn()
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(ColumnInfo columnInfo, boolean delData)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
