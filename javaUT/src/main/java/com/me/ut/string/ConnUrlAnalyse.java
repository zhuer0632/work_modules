package com.me.ut.string;

/**
 * Author: gnoloahs
 * Date: 2013-04-03
 * Time: 下午8:25
 */



/**
 * 对jdbc驱动的解析
 */
public class ConnUrlAnalyse
{

    /**
 * 截取数据库的连接字符串取得IP
 * @param connstr
 * @return
 */
public static String getIP(String connstr)
{
    String out = "";

    if(connstr.contains("mysql"))
    {

        out= StringUT.cut(connstr,"jdbc:mysql://(.{7,}):\\d{4}.+");
        return out;
    }

    if(connstr.contains("sqlserver"))
    {
        out= StringUT.cut(connstr,".+sqlserver://(.{7,}):\\d{4}.+");
        return out;
    }

    return out;
}

    /**
     * 截取数据库的连接字符串取得端口
     * @param connstr
     * @return
     */
    public static String getPort(String connstr)
    {
        String out = "";

        if(connstr.contains("mysql"))//jdbc:mysql://127.0.0.1:3306/db_xj_valuation
        {

            out= StringUT.cut(connstr,"jdbc:mysql://.+:(\\d{4}).+");
            return out;
        }

        //jdbc:jtds:sqlserver://127.0.0.1:1433;databaseName=bea;SelectMethod=cursor
        if(connstr.contains("sqlserver"))
        {
            out= StringUT.cut(connstr,".+sqlserver://.+:(\\d{4}).+");
            return out;
        }

        return out;
    }

    /**
     * 截取数据库的连接字符串取得DbName
     * @param connstr
     * @return
     */
    public static String getDbName(String connstr)
    {

        String out = "";
        if(connstr.contains("mysql"))// jdbc:mysql://127.0.0.1:3306/db_xj_valuation
        {
            out= StringUT.cut(connstr,"jdbc:mysql://.+/(.+).*");
            return out;
        }

        //jdbc:jtds:sqlserver://127.0.0.1:1433;databaseName=bea;SelectMethod=cursor
        if(connstr.contains("sqlserver"))
        {
            out= StringUT.cut(connstr,".+sqlserver://.+:\\d{4}.+=(.+);.*");
            return out;
        }
        return out;
    }


}
