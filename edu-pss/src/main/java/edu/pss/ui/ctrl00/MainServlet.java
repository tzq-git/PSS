package edu.pss.ui.ctrl00;

import edu.pss.bean.User;
import edu.pss.service.UserService;
import edu.pss.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(UIConst.AREAPATH + "/Main")
public class MainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        //检测是否登录
        /*String toURL = checkLogin(request, response);
        if (toURL != null) {
            response.sendRedirect(toURL);
            return;
        }*/

        String oper = request.getParameter("oper");

        if (oper != null && oper.equalsIgnoreCase("welcome")) {
            welcomeView(request, response);
        } else {
            mainView(request, response);
        }


    }


    protected void mainView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String toPage = UIConst.VIEWPATH + "/Main.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);

    }

    protected void welcomeView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String toPage = UIConst.VIEWPATH + "/Welcome.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);

    }

   /* protected String checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        javax.servlet.http.HttpSession session = req.getSession();
        String toURL = null;
        Object obj = session.getAttribute(UIConst.BG_LOGINUSER_KEY);

        if (obj == null) {
            toURL = req.getContextPath() + UIConst.AREAPATH + "/Login";

        }

        return toURL;
    }*/
}