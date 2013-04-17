package test.com.comdev.db.temp;

import com.comdev.db.DbKit;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.nutz.dao.entity.Record;

import java.util.List;

/**
 * Author: gnoloahs
 * Date: 2013-04-17
 * Time: 下午5:50
 */
public class Run
{
    private static final Logger logger = Logger.getLogger(Run.class);


    @Test
    public void test01()
    {
        List<Record> list = DbKit.getDao().query("beapath", null);


        System.out.println(list.size());


    }


}
