package com.me.ut.collecttion;

import com.me.ut.string.StringUT;
import org.apache.log4j.Logger;

import java.util.List;

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
}
