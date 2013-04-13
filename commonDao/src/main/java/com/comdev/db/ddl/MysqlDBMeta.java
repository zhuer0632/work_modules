package com.comdev.db.ddl;

import com.comdev.db.dbinfovo.ColumnInfo;
import com.comdev.exceptions.noImplException;
import com.comdev.ut.PropertiesUT;
import com.me.ut.string.StringUT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * User: zhu
 * Date: 13-4-7
 * Time: 上午2:05
 */
public class MysqlDBMeta implements IDbMeta
{
    @Override
    public int createDb(String dbname)
    {
           throw new noImplException();
    }

    @Override
    public List<String> getdbNames()
    {
        return null;
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
            //jdbc:mysql://127.0.0.1:3306/cms?charachterEncoding=UTF-8;
            Class.forName(driverclass);
            url= StringUT.replace(url,".*:\\d{4}/(.*)\\?.*",dbName);
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
