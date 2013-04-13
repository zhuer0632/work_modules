package com.comdev.db.ddl;

import com.comdev.db.DbKit;
import com.comdev.db.dbinfovo.ColumnInfo;
import com.comdev.exceptions.noImplException;
import org.nutz.dao.DB;

import java.util.List;

/**
 * Author: gnoloahs
 * Date: 2013-04-12
 * Time: 上午9:59
 */
public class DbMetaProxy implements IDbMeta
{

    private IDbMeta iDbMeta = DbMetaFac();

    private IDbMeta DbMetaFac()
    {
        if (DbKit.getDao().meta().getType() == DB.MYSQL)
        {
            iDbMeta = new MysqlDBMeta();
        } else if (DbKit.getDao().meta().getType() == DB.SQLSERVER)
        {
            iDbMeta = new SqlServerDBMeta();
        } else
        {
            throw new noImplException();
        }
        return iDbMeta;
    }


    @Override
    /**
     *      * @return  1:成功，2：失败：3：数据库已经存在，没有操作，直接返回
     */
    public int createDb(String dbname)
    {
        return iDbMeta.createDb(dbname);
    }

    @Override
    public List<String> getdbNames()
    {
        return iDbMeta.getdbNames();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean checkDbConfig()
    {
        return iDbMeta.checkDbConfig();
    }

    @Override
    public boolean checkHasDB(String dbName)
    {
        return iDbMeta.checkHasDB(dbName);
    }

    @Override
    public List<String> tableNames()
    {
        return iDbMeta.tableNames();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<String> tableNames(String dbName)
    {
        return iDbMeta.tableNames(dbName);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void changeDbConn(String db_properties)
    {
        iDbMeta.changeDbConn(db_properties);
    }

    @Override
    public List<String> columnNames(String tableName)
    {
        return iDbMeta.columnNames(tableName);
    }

    @Override
    public List<String> columnMeta(String tableName, String columnName)
    {
        return iDbMeta.columnMeta(tableName, columnName);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addTable()
    {
        iDbMeta.addTable();
    }

    @Override
    public void addColumn()
    {

    }

    @Override
    public void delColumn()
    {

    }

    @Override
    public void update(ColumnInfo columnInfo, boolean delData)
    {

    }
}
