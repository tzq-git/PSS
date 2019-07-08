package edu.pss.ui.ctrl00;

import com.liuvei.common.PagerItem;
import edu.pss.bean.DealRecord;
import edu.pss.service.DealRecordService;
import edu.pss.service.impl.DealRecordServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(UIConst.AREAPATH + "/DealRecord")
public class DealRecordServlet extends HttpServlet {
    private DealRecordService dealRecordService = new DealRecordServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
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
        /*String toURL = checkLogin(request,response);
        if(toURL != null){
            response.sendRedirect(toURL);
            return;
        }*/

        // 取得操作类型
        String oper = request.getParameter("oper");
        if (oper == null) {
            oper = "";
        } else {
            oper = oper.trim().toLowerCase();
        }
        // 根据不同的操作类型,调用不同的处理方法
        switch (oper) {
            case "detail":
                listDeal(request, response); // 列表处理
                break;
            default:
                //  listView(request, response); // 列表页面 : 默认
                System.out.println("oper不存在。");
                break;
        }



    }


    protected void listDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数
        String cid = request.getParameter("id");

        System.out.println("我输入的是："+cid);

        List<DealRecord> vDataList = null;

        vDataList = dealRecordService.list(Long.parseLong(cid));

        request.setAttribute("DataList", vDataList);
        // ------------------------------------------------------------------------
        // 转发到页面
        String toPage = UIConst.VIEWPATH + "/Customer_detail.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);
    }

   /* protected String checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        javax.servlet.http.HttpSession session = req.getSession();
        String toURL = null;
        Object obj = session.getAttribute(UIConst.BG_LOGINUSER_KEY);

        if(obj == null){
            toURL = req.getContextPath() + UIConst.AREAPATH + "/Login";

        }

        return toURL;
    }*/
}
