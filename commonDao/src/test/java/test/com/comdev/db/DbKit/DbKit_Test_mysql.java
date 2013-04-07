package test.com.comdev.db.DbKit;


import com.comdev.db.DbKit;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.nutz.dao.pager.Pager;
import org.nutz.lang.Dumps;

import java.util.List;


/**
 * Author: gnoloahs
 * Date: 2013-04-03
 * Time: 下午5:25
 */
public class DbKit_Test_mysql
{

    private static final Logger logger = Logger.getLogger(DbKit_Test_mysql.class);

    @Test
    public void test01() throws Exception
    {
        int i = DbKit.getCount("select * from t_zhaobiao");
        logger.debug(i);
        
    }

    @Test
    public void test02() throws Exception
    {
        String sql = "select * from t_zhaobiao";

        Pager pager = new Pager();
        pager.setPageNumber(1);
        pager.setPageSize(5);
        List<String> list = DbKit.getStringList(sql, pager);
        logger.debug(Dumps.obj(list));

    }


}
