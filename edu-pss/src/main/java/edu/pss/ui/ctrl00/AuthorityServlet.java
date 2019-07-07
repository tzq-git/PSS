package edu.pss.ui.ctrl00;

import com.liuvei.common.PagerItem;
import com.liuvei.common.SysFun;
import edu.pss.bean.Authority;
import edu.pss.service.AuthorityService;
import edu.pss.service.impl.AuthorityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(UIConst.AREAPATH + "/Authority")
public class AuthorityServlet extends HttpServlet {

    AuthorityService authorityService = new AuthorityServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* **************************************************************** */
        /* ********** Servlet的doXXX方法中的编码方式和6个标准对象 ********** */
        /* **************************************************************** */
        // ***** Servlet的doXXX方法中的编码方式
        // 设置请求对象的编码方式
        request.setCharacterEncoding("utf-8");
        // 设置响应对象的编码方式
        request.setCharacterEncoding("utf-8");
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

        String toURL = checkLogin(request, response);
        if ( toURL != null){
            response.sendRedirect(toURL);
            return ;
        }

        // 取得操作类型
        String oper = request.getParameter("oper");
        if (oper == null) {
            oper = "";
        } else {
            oper = oper.trim().toLowerCase();
        }
        // 根据不同的操作类型,调用不同的处理方法
        switch (oper) {
            case "list":
                listView(request, response); // 列表页面
                break;
            case "listdeal":
                listDeal(request, response); // 列表处理
                break;
            case "updatedeal":
                updateDeal(request, response); // 修改处理
                break;
            case "detail":
                detailView(request, response); // 查看处理
                break;
            case "admin":
                AuthorityAdmin(request, response);//管理权限
                break;
            default:
// listView(request, response); // 列表页面 : 默认
                System.out.println("oper不存在。");
                break;
        }
    }

    protected void AuthorityAdmin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("id");
        Authority bean = authorityService.loadById(Long.parseLong(userId));
        //计算全选按钮得值
        List list= checkAll(bean);

        request.setAttribute("bean", bean);
        request.setAttribute("Data", list.get(0));
        request.setAttribute("Purchase", list.get(1));
        request.setAttribute("Sale", list.get(2));


        // 转发到页面
        String toPage = UIConst.VIEWPATH + "/Authority_admin.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);
    }

    protected void listView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Authority> vDataList = null;
        // ------------------------------------------------------------------------
        // 分页步骤1. 创建PagerIter对象, 处理url传过来的pagesize和pageindex
        PagerItem pagerItem = new PagerItem();
        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
        // 分页步骤2.1. 定义记录数变量
        Long rowCount = 0L;
        // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其它方法
        rowCount = authorityService.count();
        // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
        pagerItem.changeRowCount(rowCount);
        // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
        vDataList = authorityService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
        // 分页步骤2.5. 设置页面的跳转url
        pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));
        // 分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问
        request.setAttribute("pagerItem", pagerItem);
        request.setAttribute("DataList", vDataList);
        // ------------------------------------------------------------------------

        // 转发到页面
        String toPage = UIConst.VIEWPATH + "/Authority_list.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);
    }


    protected void listDeal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取请求参数
        String searchName = request.getParameter("searchName");
        // 回显请求数据
        request.setAttribute("searchName", searchName);
        List<Authority> vDataList = null;
        // ------------------------------------------------------------------------
        // 分页步骤1. 创建PagerIter对象, 处理url传过来的pagesize和pageindex
        PagerItem pagerItem = new PagerItem();
        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
        // 分页步骤2.1. 定义记录数变量
        Long rowCount = 0L;
        if (SysFun.isNullOrEmpty(searchName)) {
            // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其它方法
            rowCount = authorityService.count();
            // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
            pagerItem.changeRowCount(rowCount);
            // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
            vDataList = authorityService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
        } else {
            // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其它方法
            rowCount = authorityService.countByName(searchName);
            // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
            pagerItem.changeRowCount(rowCount);
            // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
            vDataList = authorityService.pagerByName(searchName, pagerItem.getPageNum(), pagerItem.getPageSize());
        }
        // 分页步骤2.5. 设置页面的跳转url
        pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));
        // 分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问
        request.setAttribute("pagerItem", pagerItem);
        request.setAttribute("DataList", vDataList);
        // ------------------------------------------------------------------------
        // 转发到页面
        String toPage = UIConst.VIEWPATH + "/Authority_list.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);

    }

    protected void detailView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();
        // 取得主键，再根据主键，获取记录
        String vId = request.getParameter("id");
        if (!SysFun.isNullOrEmpty(vId)) {
            Long iPK = SysFun.parseLong(vId);
            Authority bean = authorityService.loadById(iPK);
            if (bean != null) {
                // 使用对象来回显
                // 计算全选按钮得值

                request.setAttribute("bean", bean);

                String toPage = UIConst.VIEWPATH + "/Authority_detail.jsp";
                request.getRequestDispatcher(toPage).forward(request, response);
                return;
            }
        }
        out.println("<script>");
        out.println("alert('数据不存在.');");
        out.println("parent.window.location.reload();");
        out.println("</script>");
    }

    /**
     * 作用与AuthorityAdmin方法相似，暂未使用
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();
        // 取得主键，再根据主键，获取记录
        String vId = request.getParameter("id");
        if (!SysFun.isNullOrEmpty(vId)) {
            Long iId = SysFun.parseLong(vId);
            Authority bean = authorityService.loadById(iId);
            if (bean != null) {
                // 使用对象来回显
                // request.setAttribute("bean", bean);
                // 为了在输入页面回显原来的旧值,需要将旧值放到作用域,页面中进行获取
                request.setAttribute("userId", bean.getUserId());
                request.setAttribute("userName", bean.getUserName());
                request.setAttribute("description", bean.getDescription());
                request.setAttribute("haveSaleMan", bean.getHaveSaleMan());
                request.setAttribute("haveGoods", bean.getHaveGoods());
                request.setAttribute("haveCustomer", bean.getHaveCustomer());
                request.setAttribute("haveSupplier", bean.getHaveSupplier());
                request.setAttribute("havePurchaseIn", bean.getHavePurchaseIn());
                request.setAttribute("havePurchaseOut", bean.getHavePurchaseOut());
                request.setAttribute("haveSaleOut", bean.getHaveSaleOut());
                request.setAttribute("haveSaleReturn", bean.getHaveSaleReturn());

                String toPage = UIConst.VIEWPATH + "/Authority_admin.jsp";
                request.getRequestDispatcher(toPage).forward(request, response);
                out.println("success");
                return;
            }
        }
        out.println("<script>");
        out.println("alert('数据不存在');");
        out.println("parent.window.location.reload();");
        out.println("</script>");

    }
    

    protected void updateDeal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();
        // 获取请求数据
        String userId = request.getParameter("id");
        String userName = request.getParameter("userName");
        String description = request.getParameter("description");
        String haveSaleMan = request.getParameter("haveSaleMan");
        String haveGoods = request.getParameter("haveGoods");
        String haveCustomer = request.getParameter("haveCustomer");
        String haveSupplier = request.getParameter("haveSupplier");
        String havePurchaseIn = request.getParameter("havePurchaseIn");
        String havePurchaseOut = request.getParameter("havePurchaseOut");
        String haveSaleOut = request.getParameter("haveSaleOut");
        String haveSaleReturn = request.getParameter("haveSaleReturn");

        //为了在输入页面回显原来的旧值，将旧值放到作用域，页面中进行获取
        request.setAttribute("userId", userId);
        request.setAttribute("userName", userName);
        request.setAttribute("description", description);
        request.setAttribute("haveSaleMan", haveSaleMan);
        request.setAttribute("haveGoods", haveGoods);
        request.setAttribute("haveCustomer", haveCustomer);
        request.setAttribute("haveSupplier", haveSupplier);
        request.setAttribute("havePurchaseIn", havePurchaseIn);
        request.setAttribute("havePurchaseOut", havePurchaseOut);
        request.setAttribute("haveSaleOut", haveSaleOut);
        request.setAttribute("haveSaleReturn", haveSaleReturn);


        // (3) 真正处理
        Authority bean = new Authority();
        bean.setUserId(Long.parseLong(userId));
        bean.setUserName(userName);
        bean.setDescription(description);
        bean.setHaveSaleMan(Long.parseLong(haveSaleMan==null?"0":"1"));
        bean.setHaveGoods(Long.parseLong(haveGoods==null?"0":"1"));
        bean.setHaveCustomer(Long.parseLong(haveCustomer==null?"0":"1"));
        bean.setHaveSupplier(Long.parseLong(haveSupplier==null?"0":"1"));
        bean.setHavePurchaseIn(Long.parseLong(havePurchaseIn==null?"0":"1"));
        bean.setHavePurchaseOut(Long.parseLong(havePurchaseOut==null?"0":"1"));
        bean.setHaveSaleOut(Long.parseLong(haveSaleOut==null?"0":"1"));
        bean.setHaveSaleReturn(Long.parseLong(haveSaleReturn==null?"0":"1"));

        String vMsg = "";
        Long result = 0L;
        try {
            result = authorityService.update(bean);
        } catch (Exception e) {
            vMsg = "修改失败." + e.getMessage();
        }
        if (result > 0) {
            // System.out.println("修改成功");
            // listView(request, response);
            // 如果修改成功，则父窗口页面的地址栏重新加载
            out.println("<script>");
            out.println("parent.window.location.reload();");
            out.println("</script>");
        } else {
            request.setAttribute("msg", vMsg);
            //System.out.println("修改失败");
            updateView(request, response);
        }

    }

    protected String checkLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        javax.servlet.http.HttpSession session = request.getSession();
        String toURL = null;
        Object obj = session.getAttribute(UIConst.BG_LOGINUSER_KEY);
        if ( obj == null ){
            toURL = request.getContextPath() + UIConst.AREAPATH + "/Login";
        }
        return toURL;
    }

    //计算权限修改页面三个全选按钮是否应该为选中状态
    protected List checkAll(Authority bean){
        List<Long> list = new ArrayList<>();
        Long Data = 0L;
        if ( bean.getHaveCustomer()==1 && bean.getHaveSaleMan()==1
                && bean.getHaveGoods()==1 && bean.getHaveSupplier()==1 ){
            Data = 1L;
        }
        Long Purchase = 0L;
        if ( bean.getHavePurchaseIn()==1 && bean.getHavePurchaseOut()==1 ){
            Purchase = 1L;
        }
        Long Sale = 0L;
        if ( bean.getHaveSaleOut()==1 && bean.getHaveSaleReturn()==1 ){
            Sale = 1L;
        }

        list.add(Data);
        list.add(Purchase);
        list.add(Sale);

        return list;
    }
}
