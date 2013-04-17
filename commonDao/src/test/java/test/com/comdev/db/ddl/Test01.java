package test.com.comdev.db.ddl;

import com.comdev.db.ddl.MysqlDBMeta;
import com.comdev.db.ddl.SqlServerDBMeta;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.List;

/**
 * User: zhu
 * Date: 13-4-7
 * Time: 上午2:07
 */
public class Test01
{
    private static final Logger logger = Logger.getLogger(Test01.class);

    @Test
    public void test01()
    {
        MysqlDBMeta db = new MysqlDBMeta();
        boolean flag = db.checkDbConfig();
        logger.debug(flag);
    }

    @Test
    public  void test02()
    {

<<<<<<< HEAD
=======
        MysqlDBMeta db = new MysqlDBMeta();
        List flag = db.getdbNames();
        logger.debug(flag);

>>>>>>> c954ad3a02513463848dcd0ffb1f408412a3bf7c
    }


}
