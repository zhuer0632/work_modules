package com.comdev.db.ddl;

import com.comdev.db.dbinfovo.ColumnInfo;

import java.util.List;

/**
 * Author: gnoloahs
 * Date: 2013-04-03
 * Time: 下午7:48
 */
public interface IDbMeta
{


    /**
     * 创建数据库
     *
     * @return 1:成功，2：失败：3：数据库已经存在，没有操作，直接返回
     */
    public int createDb(String dbname);

    /**
     * 取得当前数据库下的所有数据库的名字
     *
     * @return
     */
    public List<String> getdbNames();

    /**
     * 测试配置文件是否正常
     *
     * @return
     */
    public boolean checkDbConfig();

    /**
     * 检查数据库是否存在-实现方法就是根据dbName放在配置文件中
     * 初始化一下配置文件，没有异常就是存在
     * @param dbName
     * @return
     */
    public boolean checkHasDB(String dbName);


    /**
     * 取得当前连接的数据库中的所有的表
     *
     * @return
     */
    public List<String> tableNames();

    /**
     * 取得指定数据库中的所有的表
     *
     * @return
     */
    public List<String> tableNames(String dbName);


    /**
     * 更改数据库连接
     */
    public void changeDbConn(String db_properties);


    /**
     * 取得指定表名中所有的字段
     *
     * @param tableName
     * @return
     */
    public List<String> columnNames(String tableName);

    /**
     * 取得指定表中的指定字段的常用信息 [长度，类型，是否为空，是否主键，默认值]
     *
     * @param tableName
     * @return
     */
    public List<String> columnMeta(String tableName, String columnName);

    /**
     * 创建一个基础结构的表[objid_,modifydate_,isdeleted_,modifyuser_]
     */
    public void addTable();

    /**
     * 添加一个字段
     */
    public void addColumn();

    /**
     * 删除一个字段
     */
    public void delColumn();

    /**
     * 修改一个字段的配置
     *
     * @param columnInfo
     * @param delData    如果该字段中的数据不能兼容字段的新配置是否删除，默认不删除
     */
    public void update(ColumnInfo columnInfo, boolean delData);


}
