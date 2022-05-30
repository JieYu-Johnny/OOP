import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class loginSystem extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = "sign";//定义一个操作类型 有sign=注册 login=登录
        String name = req.getParameter("sign_username");//得到前端传来请求的sign_username参数
        String password = req.getParameter("sign_password");//得到前端传来请求的sign_password参数

        //如果sign_username这两个变量都是空的 那就是登录 login_username肯定是有值的 并且把type改成login
        if(name==null&&password==null){
            name = req.getParameter("login_username");
            password = req.getParameter("login_password");
            type = "login";
        }

        //断言 防止name还是空的
        assert name != null;
        //判断一下 防止只输入账号不输入密码的情况
        if(name.length()==0||password.length()==0){
            req.setAttribute("msg","信息核对失败，请检查");
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
            return;
        }

        //登录的逻辑
        if(type.equals("login")){
            System.out.println("进入登录");
            //进去login类 验证账号密码
            boolean vaild = login.vaild(req.getParameter("login_username"), req.getParameter("login_password"));

            //如果返回true就验证成功
            if (vaild){
                //这下面没什么讲的
                String user = req.getParameter("login_username");//得到用户名
                HttpSession session = req.getSession();//构建session会话等到主菜单有用
                session.setAttribute("user",user);
                //           System.out.println("登录成功");//登录成功操作
                String contextPath = req.getContextPath();

                //到这。。。。。
                //重定向 进入主菜单
                resp.sendRedirect(contextPath+"/main_menu/main.jsp");
            }


            else{
                //登陆失败操作
                System.out.println("登录失败");//登录失败操作
                req.setAttribute("msg","用户名或密码错误");//这里是传到登陆界面的提示信息，那边用msg作为变量名，后面的是参数
                req.getRequestDispatcher("/index.jsp").forward(req,resp);
            }

            //同上
        }else if(type.equals("sign")){
            int vaild = login.sign(name, password);
            if (vaild==0){
                System.out.println("注册成功");//注册成功操作
                req.setAttribute("msg","注册成功！请登录");
                req.getRequestDispatcher("/index.jsp").forward(req,resp);
            }
            else if(vaild==-1){
                System.out.println("注册失败");//注册成功操作
                req.setAttribute("msg","注册失败请联系管理员");
                req.getRequestDispatcher("/index.jsp").forward(req,resp);
            }else{
                System.out.println("注册失败");//注册成功操作
                req.setAttribute("msg","用户已存在，请登录");
                req.getRequestDispatcher("/index.jsp").forward(req,resp);
            }
        }

    }
    //这个不用看
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get...");
    }
}
