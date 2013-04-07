package com.comdev.db;


import com.alibaba.druid.pool.DruidDataSource;
import com.comdev.db.callbak.*;
import com.comdev.ut.PropertiesUT;
import org.apache.log4j.Logger;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 对NutDao的进一步封装
 *
 * @author zsl
 */
public class DbKit
{
    private static  final Logger logger = Logger.getLogger(DbKit.class);
    private static NutDao dao;


    static
    {
        //初始化dao的配置文件
        dao = new NutDao();

        DruidDataSource druid = new DruidDataSource();
        druid.setUrl(PropertiesUT.get_url());
        druid.setUsername(PropertiesUT.get_username());
        druid.setPassword(PropertiesUT.get_password());
        druid.setMaxWait(PropertiesUT.get_maxconn());// 最多创建多少链接
        druid.setMaxActive(PropertiesUT.get_minconn());// 最少保留多少链接
        druid.setInitialSize(2);// 没有连接的时候一次创建多少
        dao.setDataSource(druid);
    }


    public static Dao getDao()
    {
        return dao;
    }


    public static List<String> getStringList(String sql)
    {
        Sql s = Sqls.create(sql);
        s.setCallback(new QueryStringListCallback());
        dao.execute(s);
        List<String> recs = s.getList(String.class);
        return recs;
    }


    public static List<String> getStringList(String sql,
                                             Pager pager)
    {
        Sql s = Sqls.create(sql);
        s.setCallback(new QueryStringListCallback());
        s.setPager(pager);
        dao.execute(s);
        List<String> recs = s.getList(String.class);
        return recs;
    }


    public static List<Integer> getIntList(String sql)
    {
        Sql s = Sqls.create(sql);
        s.setCallback(new QueryIntListCallback());
        dao.execute(s);
        List<Integer> recs = s.getList(Integer.class);
        return recs;
    }


    public static List<Date> getDateList(String sql)
    {
        Sql s = Sqls.create(sql);
        s.setCallback(new QueryDateListCallback());
        dao.execute(s);
        List<Date> recs = s.getList(Date.class);
        return recs;
    }


    /**
     * 返回任意一条查询sql得到数量
     *
     * @param sql
     * @return
     */
    public static int getCount(String sql)
    {
        Sql s = Sqls.create(sql);
        s.setCallback(new QueryCountCallback());
        dao.execute(s);
        int count = Integer.valueOf(s.getResult().toString());
        return count;
    }


    @SuppressWarnings("unchecked")
    public static List<Map> getMapFormatDateList(String sql,
                                                 Pager pager)
    {
        Sql s = Sqls.create(sql);
        s.setCallback(new QueryMapListFormatDateCallback());
        s.setPager(pager);
        dao.execute(s);
        List<Map> recs = s.getList(Map.class);
        // 设置总共多少条记录
        pager.setRecordCount(getCount(sql));
        return recs;
    }


    @SuppressWarnings("unchecked")
    /**
     * 字段名为key，值为value
     */
    public static List<Map> getMapList(String sql)
    {
        Sql s = Sqls.create(sql);
        s.setCallback(new QueryMapListCallback());
        // s.setPager(pager);
        dao.execute(s);
        List<Map> recs = s.getList(Map.class);
        // 设置总共多少条记录
        // pager.setRecordCount(getCount(sql, dao));
        return recs;
    }


    /**
     * 取得左上角的值 String
     *
     * @param sql
     * @return
     */
    public static String getString(String sql)
    {
        Sql s = Sqls.create(sql);
        s.setCallback(new QueryStringOneCallback());
        dao.execute(s);
        String str = s.getString();
        // 设置总共多少条记录
        return str;
    }


    /**
     * 取得左上角的值 int
     *
     * @param sql
     * @return
     */
    public static int getInt(String sql)
    {
        Sql s = Sqls.create(sql);
        s.setCallback(new QueryIntOneCallback());
        dao.execute(s);
        int i = s.getInt();
        // 设置总共多少条记录
        return i;
    }


    public static Date getDate(String sql)
    {
        Sql s = Sqls.create(sql);
        s.setCallback(new QueryDateOneCallback());
        dao.execute(s);
        Date date = s.getObject(Date.class);
        return date;
    }


    public static Object getObj(String sql)
    {
        Sql s = Sqls.create(sql);
        s.setCallback(new QueryObjOneCallback());
        dao.execute(s);
        Object date = s.getObject(Object.class);
        return date;
    }

    @SuppressWarnings("unchecked")
    public static Map getMap(String sql)
    {
        Sql s = Sqls.create(sql);
        s.setCallback(new QueryMapOneCallback());
        dao.execute(s);
        Map map = (Map) s.getResult();
        return map;
    }


    /**
     * 务必使用通用的sql语句
     *
     * @param sql
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<Object[]> queryArrayList(String sql)
    {
        Sql sql_run = Sqls.create(sql);
        sql_run.setCallback(new QueryArrayListCallback());
        dao.execute(sql_run);
        List list = sql_run.getList(Object.class);
        return list;
    }

    /**
     * 分页查询，每条记录用map表示 <br>
     * 可以使用as,map中的key区分大小写 <br>
     * <br>
     * pager-需要设置pageSize和pageNum。总数量通过pageCount取得
     *
     * @param sql
     * @param pager
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<Map> getMapList(String sql,
                                       Pager pager)
    {
        Sql s = Sqls.create(sql);
        s.setCallback(new QueryMapListCallback());
        s.setPager(pager);
        dao.execute(s);
        List<Map> recs = s.getList(Map.class);
        // 设置总共多少条记录
        pager.setRecordCount(getCount(sql));
        return recs;
    }


    public static List<Object[]> queryArrayList(String sql,
                                                int pageNo,
                                                int pageSize)
    {

        Pager pager = new Pager();
        pager.setPageNumber(pageNo);// 第几页
        pager.setPageSize(pageSize);// 每页几条记录
        Sql s = Sqls.create(sql);
        s.setCallback(new QueryArrayListCallback());
        s.setPager(pager);
        dao.execute(s);
        List<Object[]> recs = s.getList(Object[].class);
        // 设置总共多少条记录
        pager.setRecordCount(getCount(sql,
                dao));
        return recs;
    }

    /**
     * 返回任意一条查询sql得到数量
     *
     * @param sql
     * @param dao
     * @return
     */
    private static int getCount(String sql,
                                NutDao dao)
    {
        Sql s = Sqls.create(sql);
        s.setCallback(new QueryCountCallback());
        dao.execute(s);
        int count = Integer.valueOf(s.getResult().toString());
        return count;
    }


    /**
     * 返回List<Clz> ；根据指定的泛型
     *
     * @param clz
     * @param sql
     * @return
     */
    public static <T> List<T> queryBeanList(Class<T> clz,
                                            String sql)
    {
        Sql sql_run = Sqls.create(sql);
        sql_run.setCallback(Sqls.callback.entities());
        Entity<T> entity = dao.getEntity(clz);
        sql_run.setEntity(entity);
        dao.execute(sql_run);
        List<T> list = (List<T>) sql_run.getList(clz);
        return list;
    }


    /**
     * 返回一个对象；根据指定的泛型T
     *
     * @param <T>
     * @param clz
     * @param sql
     * @return
     */
    public static <T> T queryBean(Class<T> clz,
                                  String sql)
    {
        Sql sql_run = Sqls.create(sql);
        sql_run.setCallback(Sqls.callback.entity());
        Entity<T> entity = dao.getEntity(clz);
        sql_run.setEntity(entity);
        dao.execute(sql_run);
        T t = (T) sql_run.getObject(clz);
        return t;
    }


    /**
     * @param sql
     * @return
     */
    public static void execute(String sql)
    {
        Sql sql_r = Sqls.create(sql);
        dao.execute(sql_r);
    }

    /**
     * 执行特殊的本地sql [尽量少用]
     * @param sql
     */
    public static void executeNaviteSQL(String sql)
    {

        Connection conn = null;       //定义变量
        PreparedStatement pstet =null;
        try {

            Class.forName(PropertiesUT.get_driverclass());    //装在去动程序

            conn = DriverManager.getConnection(PropertiesUT.get_url(), PropertiesUT.get_username(), PropertiesUT.get_password());   //加载驱动并与数据库连接

            pstet = conn.prepareStatement(sql);   //发送sql语句并得到结果集

              pstet.execute();

//            while (rs.next()){  // 判断数据集中是否有数据
//                out.add(rs.getString(1));
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            clossConnection(conn);
            clossStatement(pstet);
        }

    }

    private static   void clossConnection(Connection conn ){
        try {
            if(conn != null && !conn.isClosed()){
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private   static  void clossStatement(PreparedStatement pstet ){
        try {
            if(pstet != null) {
                pstet.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private static void clossResultSet(ResultSet rs ){
        try {
            if(rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
