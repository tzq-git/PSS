package edu.pss.ui.ctrl00;

import edu.pss.bean.Authority;
import edu.pss.bean.User;
import edu.pss.service.AuthorityService;
import edu.pss.service.UserService;
import edu.pss.service.impl.AuthorityServiceImpl;
import edu.pss.service.impl.UserServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(UIConst.AREAPATH + "/Login")
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();
    private AuthorityService authorityService = new AuthorityServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* **************************************************************** */
        /* ********** Servlet的doXXX方法中的编码方式和6个标准对象 ********** */
        /* **************************************************************** */
        // ***** Servlet的doXXX方法中的编码方式
        // 设置请求对象的编码方式
        request.setCharacterEncoding("utf-8");
        // 设置响应对象的编码方式
        response.setCharacterEncoding("utf-8");
        // 设置响应的内容类型为text/html
        response.setContentType("text/html; charset=utf-8");
        // ***** Servlet的doxxx方法中的6个标准对象（含request和response）
        // 从request里获取session对象和application对象
        javax.servlet.http.HttpSession session = request.getSession();
        javax.servlet.ServletContext application = request.getServletContext();
        // 调用继承的方法来获取config对象
        javax.servlet.ServletConfig config = getServletConfig();
        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();
        /* ----------------------------------------------------------------- */


        String oper = request.getParameter("oper");
        if(oper != null && oper.equalsIgnoreCase("loginDeal")){
            loginDeal(request,response);
        }else if(oper != null && oper.equalsIgnoreCase("logoutDeal")){
            logoutDeal(request,response);
        }else{
            loginView(request,response);
        }

    }

    protected void loginView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        String toPage = UIConst.VIEWPATH + "/Login.jsp";
        request.getRequestDispatcher(toPage).forward(request,response);

    }

    protected void loginDeal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        javax.servlet.http.HttpSession session = request.getSession();
        javax.servlet.ServletContext application = request.getServletContext();


        String userName = request.getParameter("userName");
        String userPwd = request.getParameter("userPwd");
        String validateCode = request.getParameter("validateCode");

        request.setAttribute("userName", userName);


        String toPage = UIConst.VIEWPATH + "/Login.jsp";
        String msg = "";

        /*以下完整性验证均在前端进行过检测，后端再次检测，进行双重校验*/

        //用户名不能为空
        if ( userName == null || userName.isEmpty() ){
            msg = "请填写用户名";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher(toPage).forward(request, response);
            return;
        }
        //用户密码不能为空
        if ( userPwd == null || userPwd.isEmpty() ){
            msg = "请填写密码";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher(toPage).forward(request, response);
            return ;
        }
        //验证码不能为空
        if ( validateCode == null || validateCode.isEmpty() ){
            msg = "请填写验证码";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher(toPage).forward(request, response);
            return ;
        }
        //获取发往前端的验证码的内容，比较验证码是否正确
        String strCode = (String)session.getAttribute(UIConst.BG_VALIDATE_CODE_KEY);
        if ( !validateCode.equals(strCode) ){
            msg = "验证码不正确";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher(toPage).forward(request, response);
            return ;
        }
        //查找对应id的用户是否存在与数据库中
        User bean = userService.loadByName(userName);
        if ( bean == null ){
            request.setAttribute("msg", "用户名不存在");
            request.getRequestDispatcher(toPage).forward(request, response);
            return ;
        }
        //将密码进行MD5加密跟数据库取出的加密字符串对比
        String EncryptedPwd = DigestUtils.md5Hex(userPwd);
        if ( !bean.getUserPwd().equals(EncryptedPwd) ){
            System.out.println(bean.getUserPwd() + "   " + EncryptedPwd);
            request.setAttribute("msg", "密码错误");
            request.getRequestDispatcher(toPage).forward(request, response);
            return ;
        }

        //登录认证成功
        //设置全局变量，标识用户的登录状态
        session.setAttribute(UIConst.BG_LOGINUSER_KEY, bean);
        session.setAttribute("user", bean);
        //将用户权限对象存放在session对象中，进行全局权限的判断
        Authority authority = authorityService.loadById(bean.getUserId());
        session.setAttribute("authority", authority);
        //跳转主页面
        String toURL = application.getContextPath() + UIConst.AREAPATH + "/Main";
        response.sendRedirect(toURL);

    }

    protected void logoutDeal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        javax.servlet.http.HttpSession session = request.getSession();
        session.invalidate();   //会话失效

        //重定向URL;
        String toURL = request.getContextPath() + UIConst.AREAPATH + "/Login";
        response.sendRedirect(toURL);
    }
}

