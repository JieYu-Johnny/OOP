import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class sqltest {
    public static void main(String[] args) throws IOException {
        String path = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(path);
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = build.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int i = mapper.insertUser("123", "123");
        System.out.println(i);

        sqlSession.commit();
        sqlSession.close();
    }
}
