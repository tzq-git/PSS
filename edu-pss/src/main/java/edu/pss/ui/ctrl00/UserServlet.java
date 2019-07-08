package edu.pss.ui.ctrl00;

import com.liuvei.common.PagerItem;
import com.liuvei.common.SysFun;
import edu.pss.bean.Authority;
import edu.pss.bean.User;
import edu.pss.service.AuthorityService;
import edu.pss.service.UserService;
import edu.pss.service.impl.AuthorityServiceImpl;
import edu.pss.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(UIConst.AREAPATH + "/User")
public class UserServlet extends HttpServlet {

    UserService userService = new UserServiceImpl();
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
        PrintWriter out = response.getWriter();
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
            case "insert":
                insertView(request, response); // 添加页面
                break;
            case "insertdeal":
                insertDeal(request, response); // 添加处理
                break;
            case "update":
                updateView(request, response); // 修改页面
                break;
            case "updatedeal":
                updateDeal(request, response); // 修改处理
                break;
            case "detail":
                detailView(request, response); // 查看页面
                break;
            case "deletedeal":
                deleteDeal(request, response); // 删除处理
                break;
            case "autuority":
                autuorityView(request, response);
                break;
            case "reset":
                resetView(request, response);
                break;
            case "resetdeal":
                resetDeal(request, response);
                break;
            case "info":
                infoView(request, response);
            default:
// listView(request, response); // 列表页面 : 默认loadByName
                System.out.println("oper不存在。");
                break;
        }
    }

    protected void infoView(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String toPage = UIConst.VIEWPATH + "/User_Info.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);

    }

    protected void resetView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String toPage = null;
        //获取操作类型，修改密码，还是管理员重置密码
        String type = request.getParameter("type");
        //获取请求参数
        String id = request.getParameter("id");
        String userName = request.getParameter("userName");
        //将请求参数放入request数据域中，提供给转发页面使用
        request.setAttribute("id", id);
        request.setAttribute("userName", userName);
        //判断操作类型，reset重置密码，change修改密码
        if ( type.equals("change") ){
            //执行密码修改操作
            toPage = UIConst.VIEWPATH + "/User_Pwd_Change.jsp";
        } else if (type.equals("reset")){
            //执行密码重置操作
            toPage = UIConst.VIEWPATH + "/User_Pwd_Reset.jsp";
        }

        // 转发到页面
        request.getRequestDispatcher(toPage).forward(request, response);
        //请求参数 request.getParameter("userName");
        //域 request中数据的一个存储区域， 生命周期：一次请求中
    }


    protected void resetDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //从请求参数中获取修改密码信息
        String userId = request.getParameter("id");
        String type = request.getParameter("type");
        String oldPss = request.getParameter("oldpass");
        String newpss = request.getParameter("newpass");
        String repss = request.getParameter("repass");

        request.setAttribute("id", userId);
        //获取相应输出流
        PrintWriter out = response.getWriter();

        Long result = 0L;
        String vMsg = "";
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面  z
        if ( !newpss.equals(repss) ){
            vMsg = "新密码和确认密码不一致";
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            resetView(request, response);
        }

        //判断操作类型，reset重置密码，change修改密码
        if ( type.equals("change") ){
            //执行密码修改操作
            result = userService.restPwd(Long.parseLong(userId), newpss, oldPss);

        } else if (type.equals("reset")){
            //执行密码重置操作
            result = userService.restPwd(Long.parseLong(userId), newpss, null);
        }

        if (result > 0) {

            out.println("<script>");
            //如果修改密码成功，跳转登录页面，重新登录
            if ( oldPss!=null ){
                //修改成功后让session失效，用户重新登录
                request.getSession().invalidate();
                vMsg = "修改密码成功，请重新登录";
                out.println("window.close();");
                out.println("alert('"+vMsg+"');");
            }
            System.out.println("resetDeal: 修改成功");
            // 如果修改成功，则父窗口页面的地址栏重新加载

            out.println("parent.window.location.reload();");
            out.println("</script>");
            out.flush();
            out.close();
        } else {
            request.setAttribute("msg", vMsg);
            System.out.println("resetDeal: 密码修改失败");
            updateView(request, response);
        }

    }

    protected void autuorityView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //查询id对应用户得权限
        Long id = Long.parseLong(request.getParameter("userId"));
        Authority bean = authorityService.loadById(id);
        request.setAttribute("bean", bean);

        // 转发到页面
        String toPage = UIConst.VIEWPATH + "/Authority_admin.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);
    }

    protected void listView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<User> vDataList = null;
        // ------------------------------------------------------------------------
        // 分页步骤1. 创建PagerIter对象, 处理url传过来的pagesize和pageindex
        PagerItem pagerItem = new PagerItem();
        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
        // 分页步骤2.1. 定义记录数变量
        Long rowCount = 0L;
        // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其它方法
        rowCount = userService.count();
        // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
        pagerItem.changeRowCount(rowCount);
        // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
        vDataList = userService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
        // 分页步骤2.5. 设置页面的跳转url
        pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));
        // 分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问
        request.setAttribute("pagerItem", pagerItem);
        request.setAttribute("DataList", vDataList);
        // ------------------------------------------------------------------------

        // 转发到页面
        String toPage = UIConst.VIEWPATH + "/User_list.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);
    }


    protected void listDeal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取请求参数
        String searchName = request.getParameter("searchName");
        // 回显请求数据
        request.setAttribute("searchName", searchName);
        List<User> vDataList = null;
        // ------------------------------------------------------------------------
        // 分页步骤1. 创建PagerIter对象, 处理url传过来的pagesize和pageindex
        PagerItem pagerItem = new PagerItem();
        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
        // 分页步骤2.1. 定义记录数变量
        Long rowCount = 0L;
        if (SysFun.isNullOrEmpty(searchName)) {
            // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其它方法
            rowCount = userService.count();
            // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
            pagerItem.changeRowCount(rowCount);
            // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
            vDataList = userService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
        } else {
            // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其它方法
            rowCount = userService.countByName(searchName);
            // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
            pagerItem.changeRowCount(rowCount);
            // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
            vDataList = userService.pagerByName(searchName, pagerItem.getPageNum(), pagerItem.getPageSize());
        }
        // 分页步骤2.5. 设置页面的跳转url
        pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));
        // 分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问
        request.setAttribute("pagerItem", pagerItem);
        request.setAttribute("DataList", vDataList);
        // ------------------------------------------------------------------------
        // 转发到页面
        String toPage = UIConst.VIEWPATH + "/User_list.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);

    }


    protected void insertView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 转发到页面
        String toPage = UIConst.VIEWPATH + "/User_insert.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);
    }


    protected void insertDeal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        PrintWriter out = response.getWriter();
        // 获取请求数据
        String userName = request.getParameter("userName");
        String userPwd = request.getParameter("userPwd");
        String userPwd2 = request.getParameter("userPwd2");
        String type = request.getParameter("type");
        String telPhone = request.getParameter("telPhone");
        String email = request.getParameter("email");

        //为了在输入页面回显原来的旧值，将旧值放到作用域，页面中进行获取
        request.setAttribute("userName", userName);
        request.setAttribute("telPhone", telPhone);
        request.setAttribute("type", type);
        request.setAttribute("email", email);
        //服务端验证
        String vMsg = "";
        if (SysFun.isNullOrEmpty(userName)) {
            vMsg += "用户名不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }
        if ( SysFun.isNullOrEmpty(userPwd) || !userPwd.equals(userPwd2)){
            vMsg += "两次密码不一致";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }
        if (SysFun.isNullOrEmpty(telPhone)) {
            vMsg += "联系电话不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }
        if (SysFun.isNullOrEmpty(email)) {
            vMsg += "电子邮箱不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }

        User bean = new User();

        bean.setUserName(userName);
        bean.setUserPwd(userPwd);
        bean.setType(Long.parseLong(type));
        bean.setTelPhone(telPhone);
        bean.setEmail(email);

        Authority authority = new Authority();
        authority.setUserName(userName);

        Long result = 0L;
        try {

            //检查用户名是否存在，存在则向前台传送提示信息
            User user = userService.loadByName(userName);

            if ( user!=null ){
                vMsg += "用户名重复，请重新输入";
                request.setAttribute("msg", vMsg);
                System.out.println(vMsg);
                insertView(request, response);
                return;
            }

            result = userService.insert(bean);

            authority.setUserId(result);
            result = authorityService.insert(authority);

        } catch (Exception e) {
            vMsg = "添加失败." + e.getMessage();
        }
        if (result > 0) {
            System.out.println("添加成功");
            out.println("<script>");
            out.println("parent.window.location.reload();");
            out.println("</script>");
        } else {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
        }

    }


    protected void detailView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        PrintWriter out = response.getWriter();
        // 取得主键，再根据主键，获取记录
        String vId = request.getParameter("id");
        if (!SysFun.isNullOrEmpty(vId)) {
            Long iPK = SysFun.parseLong(vId);
            User bean = userService.loadById(iPK);
            if (bean != null) {
                // 使用对象来回显
                request.setAttribute("bean", bean);
                String toPage = UIConst.VIEWPATH + "/User_detail.jsp";
                request.getRequestDispatcher(toPage).forward(request, response);
                return;
            }
        }
        out.println("<script>");
        out.println("alert('数据不存在.');");
        out.println("parent.window.location.reload();");
        out.println("</script>");
    }


    protected void deleteDeal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        PrintWriter out = response.getWriter();
        // 取得主键，再根据主键，获取记录
        String vId = request.getParameter("id");
        if (vId.equals("1")){
            out.println("nook");
        }
        if (!SysFun.isNullOrEmpty(vId)) {
            Long iId = SysFun.parseLong(vId);
            Long result = 0L;
            result = userService.delete(iId);
            if (result > 0) {
                out.print("ok"); // 不要使用println()
                return;
            }
        }
        out.println("nook");
    }


    protected void updateView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        PrintWriter out = response.getWriter();
        // 取得主键，再根据主键，获取记录
        String vId = request.getParameter("id");
        if (!SysFun.isNullOrEmpty(vId)) {
            Long iId = SysFun.parseLong(vId);
            User bean = userService.loadById(iId);
            if (bean != null) {
                // 使用对象来回显
                // request.setAttribute("bean", bean);
                // 为了在输入页面回显原来的旧值,需要将旧值放到作用域,页面中进行获取
                request.setAttribute("userId", bean.getUserId());
                request.setAttribute("userName", bean.getUserName());
                request.setAttribute("type", bean.getType());
                request.setAttribute("telPhone", bean.getTelPhone());
                request.setAttribute("email", bean.getEmail());

                String toPage = UIConst.VIEWPATH + "/User_update.jsp";
                request.getRequestDispatcher(toPage).forward(request, response);
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
        PrintWriter out = response.getWriter();
        // 获取请求数据
        String userId = request.getParameter("id");
        String userName = request.getParameter("userName");
        String userPwd = request.getParameter("userPwd");
        String type = request.getParameter("type");
        String telPhone = request.getParameter("telPhone");
        String email = request.getParameter("email");
        //为了在输入页面回显原来的旧值，将旧值放到作用域，页面中进行获取
        request.setAttribute("userId", userId);
        request.setAttribute("userName", userName);
//        request.setAttribute("userPwd", userPwd);
        request.setAttribute("type", type);
        request.setAttribute("telPhone", telPhone);
        request.setAttribute("email", email);

        // (1) 服务端验证
        String vMsg = "";
        if (SysFun.isNullOrEmpty(userName)) {
            vMsg += "用户名不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            updateView(request, response);
        }
        if (SysFun.isNullOrEmpty(telPhone)) {
            vMsg += "联系电话不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            updateView(request, response);
        }
        if (SysFun.isNullOrEmpty(email)) {
            vMsg += "电子邮箱不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            updateView(request, response);
        }

        // (3) 真正处理
        User bean = new User();
        bean.setUserId(Long.parseLong(userId));
        bean.setUserName(userName);
        bean.setUserPwd(userPwd);
        bean.setType(Long.parseLong(type));
        bean.setTelPhone(telPhone);
        bean.setEmail(email);

        Long result = 0L;
        try {
            result = userService.update(bean);
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
}
