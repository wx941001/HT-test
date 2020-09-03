import Dao.IAccountDao;
import Dao.IUserDao;
import Domain.AccountUser;
import Domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class AccountDaoCrudTest {
    private IUserDao userDao;
    private IAccountDao accountDao;
    InputStream in;
    SqlSession session;
    @Before
    public void setUp() throws Exception {
        // 1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3.使用构建者创建工厂对象SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        //4.使用SqlSessionFactory生产SqlSession对象
        session = factory.openSession();
        //5.使用SqlSession创建dao接口的代理对象
        userDao = session.getMapper(IUserDao.class);
        accountDao = session.getMapper(IAccountDao.class);
    }
    @Test
    public void testFindAll(){
        List<AccountUser> all = accountDao.findAll();
        for(AccountUser au:all)
            System.out.println(au);
        Assert.assertEquals(3,all.size());
    }
    @Test
    public void testFindAll2(){
        List<User> all = accountDao.findAll2();
        for(User au:all)
            System.out.println(au);
        Assert.assertEquals(9,all.size());
    }
    @After
    public void tearDown() throws Exception {
        session.commit();
        session.close();
        in.close();
    }
}
