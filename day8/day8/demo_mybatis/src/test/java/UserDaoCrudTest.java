import Dao.IUserDao;
import Domain.QueryVo;
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
import java.util.Date;
import java.util.List;

public class UserDaoCrudTest {
    private IUserDao userDao;
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
    }

    @Test
    public void testFindOne() {
        User user=userDao.findById(41);
        System.out.println(user);
        Assert.assertEquals("张三",user.getUsername());
    }
    @Test
    public void testSave() {
        User user=new User();
        user.setUsername("华泰");
        user.setAddress("南京市建邺区");
        user.setSex("男");
        user.setBirthday(new Date());
        int id = userDao.saveUser(user);
        User queryUser = userDao.findById(user.getId());
        Assert.assertEquals("华泰",queryUser.getUsername());
    }
    @Test
    public void testUpdate() {
        int id=55;
        User user=userDao.findById(id);
        user.setId(id);
        user.setAddress("南京市仙武区");
        user.setSex("女");

        int res = userDao.updateUserById(user);

        User updatedUser=userDao.findById(id);
        Assert.assertEquals("南京市仙武区",updatedUser.getAddress());
    }

    @Test
    public void testDeleteUser() {
        int id = 54;
        int  res=userDao.deleteUerById(id);
        Assert.assertEquals(1, res);
    }
    @Test
    public void testFindByName(){
        List<User> users = userDao.findByName("%王%");
        Assert.assertEquals(2,users.size());
    }
    @Test
    public void testCount(){
        int cnt = userDao.count();
        Assert.assertEquals(9,cnt);
    }
    @Test
    public void testFindByVo(){
        QueryVo queryVo= new QueryVo();
        queryVo.setName("%王%");
        queryVo.setAddress("%南京%");
        List<User> users = userDao.findByVo(queryVo);
        Assert.assertEquals(1,users.size());
    }
    @After
    public void tearDown() throws Exception {
        session.commit();
        session.close();
        in.close();
    }
}
