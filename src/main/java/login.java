import mapper.UserMapper;
import pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class login {
    private static String path = "mybatis-config.xml";
    private static InputStream resourceAsStream;

    static {
        try {
            resourceAsStream = Resources.getResourceAsStream(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static SqlSessionFactory build =new SqlSessionFactoryBuilder().build(resourceAsStream);


    public static boolean vaild(String name, String password){
        SqlSession sqlSession = build.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //取出真实的密码

        User user = mapper.selectByName(name);
        if (user==null){
            System.out.println("用户不存在");
            return false;
        }
        String tPassword = user.getPassword();

        //加密传入的密码
        String vPassword = MD5Utils.MD5Lower(password,MD5Utils.salt);

        sqlSession.close();//关闭

        //验证
        if (vPassword==null){
            System.out.println("用户不存在");
            return false;
        }else {
            return vPassword.equals(tPassword);
        }


    }

    public static int sign(String name,String password){
        //验证用户名是否存在
        SqlSession sqlSession = build.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectByName(name);
        if (user!=null){
            System.out.println("用户存在");
            return 1;
        }

        //加密密码
        String ePassword = MD5Utils.MD5Lower(password,MD5Utils.salt);
//        System.out.println(name+"  "+ePassword);
        int i = mapper.insertUser(name, ePassword);
        sqlSession.commit();
        sqlSession.close();
        if(i==1) {
            return 0;//成功返回
        }else {
            return -1;//失败
        }
    }

}
