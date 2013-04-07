package test.com.comdev.db.ddl;

import com.comdev.db.ddl.MysqlDBMeta;
import org.apache.log4j.Logger;
import org.junit.Test;

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


}
