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
        String type = "sign";
        String name = req.getParameter("sign_username");
        String password = req.getParameter("sign_password");
        if(name==null&&password==null){
            name = req.getParameter("login_username");
            password = req.getParameter("login_password");
            type = "login";
        }

        assert name != null;
        if(name.length()==0||password.length()==0){
            req.setAttribute("msg","信息核对失败，请检查");
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
            return;
        }

        if(type.equals("login")){
            System.out.println("进入登录");
            boolean vaild = login.vaild(req.getParameter("login_username"), req.getParameter("login_password"));

            if (vaild){
                String user = req.getParameter("login_username");
                HttpSession session = req.getSession();
                session.setAttribute("user",user);
                //           System.out.println("登录成功");//登录成功操作
                String contextPath = req.getContextPath();
                resp.sendRedirect(contextPath+"/main_menu/main.jsp");
            }


            else{
                System.out.println("登录失败");//登录失败操作
                req.setAttribute("msg","用户名或密码错误");
                req.getRequestDispatcher("/index.jsp").forward(req,resp);
            }

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get...");
    }
}
