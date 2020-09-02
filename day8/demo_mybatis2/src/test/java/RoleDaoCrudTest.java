import Dao.IAccountDao;
import Dao.IRoleDao;
import Dao.IUserDao;
import Domain.Role;
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

public class RoleDaoCrudTest {
    private IUserDao userDao;
    private IAccountDao accountDao;
    private IRoleDao roleDao;
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
        roleDao=session.getMapper(IRoleDao.class);
    }
    @Test
    public void testFindAll (){
    List<Role> roles = roleDao.findAll();
        Assert.assertEquals(3,roles.size());
        for(Role role:roles){
            System.out.println("---每个角色信息---");
            System.out.println(role);
            System.out.println(role.getUsers());
        }
    }
    @After
    public void tearDown() throws Exception {
        session.commit();
        session.close();
        in.close();
    }
}
