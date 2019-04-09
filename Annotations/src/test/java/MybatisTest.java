import itcast.dao.IUserMapper;
import itcast.domain.Account;
import itcast.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class MybatisTest {
    InputStream is;
    SqlSessionFactory factory;
    SqlSession sqlSession;
    IUserMapper mapper;
    @Before
    public void init() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建构造者对象
        SqlSessionFactoryBuilder sfb = new SqlSessionFactoryBuilder();
        //通过构造者对象创建工厂对象
        factory = sfb.build(is);
        //通过工厂对象创建SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //通过sqlsession对象动态代理IUserMapper接口
        mapper = sqlSession.getMapper(IUserMapper.class);
    }
    @After
    public void destory() throws IOException {
        sqlSession.commit();
        is.close();
        sqlSession.close();
    }

    /**
     * 查询所有用户
     */
    @Test
    public void findUser(){
        List<User> users = mapper.findUser();
        for (User user : users) {
            System.out.println(user);
            for (Account account : user.getAccounts()) {
                System.out.println(account);
            }
        }
    }
    /**
     * 通过id查询用户
     */
    @Test
    public void findUserById(){
        List<User> users = mapper.findUserById(41);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
