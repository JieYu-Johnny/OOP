import mapper.UserMapper;
import pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class login {
    private static String path = "mybatis-config.xml";//mybatis的文件路径
    private static InputStream resourceAsStream;

    static {
        try {
            resourceAsStream = Resources.getResourceAsStream(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }//和上面的private static InputStream resourceAsStream 一起将mybatis的文件加载成文件流

    //建立mysql数据库的连接
    private static SqlSessionFactory build =new SqlSessionFactoryBuilder().build(resourceAsStream);

    /**
     * 验证类
     * @param name 传入的账号
     * @param password 传入的密码
     */
    public static boolean vaild(String name, String password){
        SqlSession sqlSession = build.openSession();//打开会话
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);//mybatis加载映射文件

        //取出真实的密码
        User user = mapper.selectByName(name);//sql查询name的信息包括账号和密码 存入user对象里面
        //如果数据库没有这个对象，user就是空指针
        if (user==null){
            System.out.println("用户不存在");
            return false;
        }

        //如果有这个对象，就取出数据库里面对象的密码
        String tPassword = user.getPassword();

        //加密传入的密码
        String vPassword = MD5Utils.MD5Lower(password,MD5Utils.salt);

        sqlSession.close();//关闭sql会话

        //第二次验证 是否是空字符串
        if (vPassword==null){
            System.out.println("用户不存在");
            return false;
        }else {
            //验证 如果数据库对象的密码与传入密码相等则返回true 否则就是false
            return vPassword.equals(tPassword);
        }


    }

    public static int sign(String name,String password){
        //验证用户名是否存在
        SqlSession sqlSession = build.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectByName(name);
        //同上
        if (user!=null){
            System.out.println("用户存在");
            return 1;
        }

        //加密密码
        String ePassword = MD5Utils.MD5Lower(password,MD5Utils.salt);
//        System.out.println(name+"  "+ePassword);
        //i就是执行插入sql后影响的对象数目，如果插入了一个对象i就等于1 如果两个就是2
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
