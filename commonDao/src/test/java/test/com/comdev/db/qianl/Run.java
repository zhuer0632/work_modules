package test.com.comdev.db.qianl;

import com.comdev.db.DbKit;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Author: gnoloahs
 * Date: 2013-04-12
 * Time: 下午9:28
 */
public class Run
{

    private static final Logger logger = Logger.getLogger(Run.class);

    @Test
    public void test01()
    {

        String tableName="GRTree";
        String sql = "select * from  beaDataConfig\n" +
                "where  classId in\n" +
                "(select secondaryClassId from  beaDataConfigRela\n" +
                "where  masterClassId =\n" +
                " (\n" +
                "	select classId from beaDataconfig where  className='"+tableName+"' \n" +
                " )\n" +
                ")";

        List<Map> list = DbKit.getMapList(sql);

        Map<String, String> fields = new HashMap<String, String>();

        for (int i = 0; i < list.size(); i++)
        {

            Map map = list.get(i);
            String key = map.get("className").toString();
            String v = map.get("storePostion").toString();
            fields.put(key, v);
        }


        String a = "";
        String b = "";
        Iterator<String> it = fields.keySet().iterator();
        while (it.hasNext())
        {
            String key = it.next();
            String value = fields.get(key);
            a = a + " " + value + " as  " + key + ",\r\n  ";
        }

        logger.debug(a);


    }


}
