package com.me.ut.collecttion;

import com.me.ut.string.StringUT;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Author: gnoloahs
 * Date: 2013-04-15
 * Time: 上午10:51
 */
public class ListUT
{
    private static final Logger logger = Logger.getLogger(ListUT.class);

    public static boolean contains(List<String> list, String item)
    {
        if (StringUT.isEmpty(list))
        {
            return false;
        }
        if (StringUT.isEmpty(item))
        {
            logger.debug("第二个参数不能为空");
            throw new RuntimeException("第二个参数不能为空");
        }

        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).toLowerCase().equals(item.toLowerCase()))
            {
                return true;
            }
        }
        return false;
    }

    public static List cutByPage(List list,
                                 int pageNo, int pageSize)
    {

        List listOut = new ArrayList();
        if (list == null || list.size() == 0)
        {
            return list;
        }

        // 依据pageSize的取值情况判断所有异常
        if (pageSize <= 0)
        {
            throw new RuntimeException("没有指定分页大小");
        }
        if (pageSize >= list.size())
        {
            // return list;
            if (pageNo == 1)
            {
                return list;
            }
            if (pageNo > 1)
            {
                return new ArrayList();
            }
        }
        if (pageSize > 0 && pageSize < list.size())
        {
            // 取得开始的index和结束的index ,注：所有关于分页的index都从1开始

            if (pageNo <= 0)
            {
                throw new RuntimeException("没有指定页码");
            }

            if (pageNo > 0)
            {

                // 取得 beginIndex
                int beginIndex = ((pageNo - 1) * pageSize) + 1;//

                if (beginIndex > list.size())
                {
                    return new ArrayList();
                }

                int endIndex = pageNo * pageSize;

                if (endIndex >= list.size())
                {
                    endIndex = list.size();
                }
                listOut = list.subList(beginIndex - 1,
                        endIndex);// 包括end不包括begin
            }
        }
        return listOut;
    }


    public static List<String> getOneColumnFromMap(List<Map> statusLastMap,
                                                   String columnName)
    {
        if (StringUT.isEmpty(columnName))
        {
            return null;
        }
        if (StringUT.isEmpty(statusLastMap))
        {
            return null;
        }

        List<String> out = new ArrayList<String>();
        for (int i = 0; i < statusLastMap.size(); i++)
        {
            Map map = statusLastMap.get(i);
            out.add(map.get(columnName).toString());
        }
        return out;
    }


    /**
     * 根据Map中的某个字段进行排序
     *
     * @param colname
     * @return
     */
    public static List<Map> orderby_mapcol(List<Map> status,
                                           String colname)
    {

        for (int i = 0; i < status.size() - 1; i++)
        {
            for (int j = i + 1; j < status.size(); j++)
            {
                Map map = status.get(i);
                if (map.get(colname) instanceof Long)
                {
                    Long d = (Long) map.get(colname);

                    Map map2 = status.get(j);
                    Long d2 = (Long) map2.get(colname);

                    if (d < d2)
                    {
                        Collections.swap(status,
                                i,
                                j);
                    }
                } else
                {
                    Date d = (Date) map.get(colname);

                    Map map2 = status.get(j);
                    Date d2 = (Date) map2.get(colname);

                    if (d.before(d2))
                    {
                        Collections.swap(status,
                                i,
                                j);
                    }
                }
            }
        }
        return status;
    }


}
