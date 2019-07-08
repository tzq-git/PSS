package edu.pss.ui.ctrl00;

import com.liuvei.common.PagerItem;
import com.liuvei.common.SysFun;
import edu.pss.bean.Goods;
import edu.pss.bean.OutBound;
import edu.pss.bean.SaleReturn;
import edu.pss.service.OutBoundService;
import edu.pss.service.impl.GoodsServiceImpl;
import edu.pss.service.impl.OutBoundServiceImpl;
import edu.pss.service.impl.SaleReturnServiceImpl;
import edu.pss.service.SaleReturnService;
import org.apache.poi.hssf.record.SSTRecord;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(UIConst.AREAPATH + "/SaleReturn")
public class SaleReturnServlet extends HttpServlet {
    
    private SaleReturnService saleReturnService = new SaleReturnServiceImpl();
    
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

//        String toURL = checkLogin(request, response);
//        if ( toURL != null){
//            response.sendRedirect(toURL);
//            return ;
//        }

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
            case "dataload":
                dataLoad(request, response);
                break;
            default:
// listView(request, response); // 列表页面 : 默认
                System.out.println("oper不存在。");

                String toPage = UIConst.VIEWPATH + "/404.jsp";
                request.getRequestDispatcher(toPage).forward(request, response);

                break;
        }
    }

    private void dataLoad(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String toPage = UIConst.AREAPATH + "/Download?class=salereturn";
        request.getRequestDispatcher(toPage).forward(request, response);
    }

    protected void listView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<SaleReturn> vDataList = null;
        // ------------------------------------------------------------------------
        // 分页步骤1. 创建PagerIter对象, 处理url传过来的pagesize和pageindex
        PagerItem pagerItem = new PagerItem();
        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
        // 分页步骤2.1. 定义记录数变量
        Long rowCount = 0L;
        // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其它方法
        rowCount = saleReturnService.count();
        // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
        pagerItem.changeRowCount(rowCount);
        // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
        vDataList = saleReturnService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
        // 分页步骤2.5. 设置页面的跳转url
        pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));
        // 分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问
        request.setAttribute("pagerItem", pagerItem);
        request.setAttribute("DataList", vDataList);
        // ------------------------------------------------------------------------

        // 转发到页面
        String toPage = UIConst.VIEWPATH + "/SaleReturn_list.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);
    }


    protected void listDeal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取请求参数
        String searchName = request.getParameter("searchName");
        // 回显请求数据
        request.setAttribute("searchName", searchName);
        List<SaleReturn> vDataList = null;
        // ------------------------------------------------------------------------
        // 分页步骤1. 创建PagerIter对象, 处理url传过来的pagesize和pageindex
        PagerItem pagerItem = new PagerItem();
        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
        // 分页步骤2.1. 定义记录数变量
        Long rowCount = 0L;
        if (SysFun.isNullOrEmpty(searchName)) {
            // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其它方法
            rowCount = saleReturnService.count();
            // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
            pagerItem.changeRowCount(rowCount);
            // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
            vDataList = saleReturnService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
        } else {
            // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其它方法
            rowCount = saleReturnService.countByName(searchName);
            // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
            pagerItem.changeRowCount(rowCount);
            // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
            vDataList = saleReturnService.pagerByName(searchName, pagerItem.getPageNum(), pagerItem.getPageSize());
        }
        // 分页步骤2.5. 设置页面的跳转url
        pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));
        // 分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问
        request.setAttribute("pagerItem", pagerItem);
        request.setAttribute("DataList", vDataList);
        // ------------------------------------------------------------------------
        // 转发到页面
        String toPage = UIConst.VIEWPATH + "/SaleReturn_list.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);

    }


    protected void insertView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 转发到页面
        String toPage = UIConst.VIEWPATH + "/SaleReturn_insert.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);
    }


    protected void insertDeal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();
        // 获取请求数据
        String returnId = request.getParameter("returnId");
        String orderId = request.getParameter("orderId");
        String cName = request.getParameter("cName");
        String price = request.getParameter("price");
        String returnDateStr = request.getParameter("returnDate");
        Date returnDate = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            returnDate = formatter.parse(returnDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //为了在输入页面回显原来的旧值，将旧值放到作用域，页面中进行获取
        request.setAttribute("returnId", returnId);
        request.setAttribute("orderId", orderId);
        request.setAttribute("cName", cName);
        request.setAttribute("price", price);
        request.setAttribute("returnDate", returnDate);
        //服务端验证
        String vMsg = "";
        if (SysFun.isNullOrEmpty(returnId)) {
            vMsg += "退货单号不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }
        if (SysFun.isNullOrEmpty(orderId)) {
            vMsg += "出库单号不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }
        if (SysFun.isNullOrEmpty(cName)) {
            vMsg += "客户名不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }
        //增加一条退货记录后改变售出表中相应数据的状态（已成交->已退货）
        OutBoundService outBoundService = new OutBoundServiceImpl();
        OutBound outBound = outBoundService.loadById(Long.parseLong(orderId));
        outBound.setStatus(0L);
        outBoundService.update(outBound);

        //退货后增加对应商品的库存量
        GoodsServiceImpl goodsService = new GoodsServiceImpl();
        Goods goods = goodsService.loadByName(outBound.getgName());
        goods.setNowStock(goods.getNowStock()+outBound.getNum());
        goodsService.update(goods);

        //插入数据
        SaleReturn bean = new SaleReturn();
        bean.setReturnId(Long.parseLong(returnId));
        bean.setOrderId(Long.parseLong(orderId));
        bean.setPrice(price);
        bean.setcName(cName);
        bean.setReturnDate(returnDate);

        Long result = 0L;
        try {
            result = saleReturnService.insert(bean);
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
        java.io.PrintWriter out = response.getWriter();
        // 取得主键，再根据主键，获取记录
        String vId = request.getParameter("id");
        if (!SysFun.isNullOrEmpty(vId)) {
            Long iPK = SysFun.parseLong(vId);
            SaleReturn bean = saleReturnService.loadById(iPK);
            if (bean != null) {
                // 使用对象来回显
                request.setAttribute("bean", bean);
                String toPage = UIConst.VIEWPATH + "/SaleReturn_detail.jsp";
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
        java.io.PrintWriter out = response.getWriter();
        // 取得主键，再根据主键，获取记录
        String vId = request.getParameter("id");
        if (!SysFun.isNullOrEmpty(vId)) {
            Long iId = SysFun.parseLong(vId);
            //删除一条退货记录后要恢复出库记录中的状态
            OutBoundService outBoundService = new OutBoundServiceImpl();
            OutBound outBound = outBoundService.loadById(saleReturnService.loadById(iId).getOrderId());
            outBound.setStatus(1L);
            outBoundService.update(outBound);
            Long result = 0L;

            //退货删除后减少对应商品的库存量
            GoodsServiceImpl goodsService = new GoodsServiceImpl();
            Goods goods = goodsService.loadByName(outBound.getgName());
            goods.setNowStock(goods.getNowStock()-outBound.getNum());
            goodsService.update(goods);

            //删除退货记录
            result = saleReturnService.delete(iId);
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
        java.io.PrintWriter out = response.getWriter();
        // 取得主键，再根据主键，获取记录
        String vId = request.getParameter("id");
        if (!SysFun.isNullOrEmpty(vId)) {
            Long iId = SysFun.parseLong(vId);
            SaleReturn bean = saleReturnService.loadById(iId);
            if (bean != null) {
                // 使用对象来回显
                // request.setAttribute("bean", bean);
                // 为了在输入页面回显原来的旧值,需要将旧值放到作用域,页面中进行获取
                request.setAttribute("returnId", bean.getReturnId());
                request.setAttribute("orderId", bean.getOrderId());
                request.setAttribute("cName", bean.getcName());
                request.setAttribute("price", bean.getPrice());
                request.setAttribute("returnDate", bean.getReturnDate());
                String toPage = UIConst.VIEWPATH + "/SaleReturn_update.jsp";
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
        java.io.PrintWriter out = response.getWriter();
        // 获取请求数据
        String returnId = request.getParameter("id");
        String orderId = request.getParameter("orderId");
        String cName = request.getParameter("cName");
        String price = request.getParameter("price");
        String returnDateStr = request.getParameter("returnDate");
        Date returnDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            returnDate = formatter.parse(returnDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //为了在输入页面回显原来的旧值，将旧值放到作用域，页面中进行获取
        request.setAttribute("returnId", returnId);
        request.setAttribute("orderId", orderId);
        request.setAttribute("cName", cName);
        request.setAttribute("price", price);
        request.setAttribute("returnDate", returnDate);
        // (1) 服务端验证
        String vMsg = "";
        if (SysFun.isNullOrEmpty(returnId)) {
            vMsg += "退货单号不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            updateView(request, response);
        }
        if (SysFun.isNullOrEmpty(orderId)) {
            vMsg += "出库单号不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            updateView(request, response);
        }
        if (SysFun.isNullOrEmpty(cName)) {
            vMsg += "客户名不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            updateView(request, response);
        }


        // (3) 真正处理
        SaleReturn bean = new SaleReturn();
        bean.setReturnId(Long.parseLong(returnId));
        bean.setOrderId(Long.parseLong(orderId));
        bean.setcName(cName);
        bean.setPrice(price);
        bean.setReturnDate(returnDate);
        Long result = 0L;
        try {
            result = saleReturnService.update(bean);
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
