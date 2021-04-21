package com.itheima.test;

import com.itheima.dao.UserDao;
import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    private InputStream in;
    private SqlSession sqlSession;
    private UserDao userDao;

    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession();
        userDao = sqlSession.getMapper(UserDao.class);
    }
    @After
    public void destroy() throws Exception {
        //提交事务
        sqlSession.commit();

        sqlSession.close();
        in.close();
    }
    @Test
    public void testFindAll() {
        List<User> users = userDao.findAll();
        for (User user:users) {
            System.out.println(user);
        }
    }
    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("geyx");
        user.setAddress("河南省郑州市");
        user.setSex("男");
        user.setBirthday(new Date());
        userDao.saveUser(user);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(3);
        user.setUsername("geyx");
        user.setAddress("河南省郑州市中原区");
        user.setSex("男");
        user.setBirthday(new Date());
        userDao.updateUser(user);
    }

    @Test
    public void testDelete(){
        userDao.deleteUser(3);
    }

    @Test
    public void testFindOne(){
        User user = userDao.findById(2);
        System.out.println(user);
    }

    @Test
    public void testFindByName(){
        List<User> users = userDao.findByName("%王%");
//        List<User> users = userDao.findByName("王");
        for (User user:users) {
            System.out.println(user);
        }
    }
    @Test
    public void testFindTotal(){
        int count = userDao.findTotal();
        System.out.println(count);
    }
    @Test
    public void testFindByVo(){
        QueryVo queryVo = new QueryVo();
        User user = new User();
        user.setUsername("%王%");
        queryVo.setUser(user);
        List<User> users = userDao.findUserByVo(queryVo);
        for (User u:users) {
            System.out.println(u);
        }
    }
}
