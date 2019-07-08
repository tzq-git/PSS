package edu.pss.ui.ctrl00;

import com.liuvei.common.PagerItem;
import com.liuvei.common.SysFun;
import edu.pss.bean.Goods;
import edu.pss.bean.Purchase;
import edu.pss.dao.impl.GoodsDaoImpl;
import edu.pss.service.PurchaseService;
import edu.pss.service.impl.GoodsServiceImpl;
import edu.pss.service.impl.PurchaseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(UIConst.AREAPATH + "/Purchase")
public class PurchaseServlet extends HttpServlet {

    private PurchaseService purchaseService = new PurchaseServiceImpl();
    private Double num1 ;

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
            default:
                //  listView(request, response); // 列表页面 : 默认
                System.out.println("oper不存在。");
                break;
        }



    }

    protected void listView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Purchase> vDataList = null;
        // ------------------------------------------------------------------------
        // 分页步骤1. 创建PagerIter对象, 处理url传过来的pagesize和pageindex
        PagerItem pagerItem = new PagerItem();
        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
        // 分页步骤2.1. 定义记录数变量
        Long rowCount = 0L;
        // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其它方法
        rowCount = purchaseService.count();
        // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
        pagerItem.changeRowCount(rowCount);
        // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
        vDataList = purchaseService.pager(pagerItem.getPageNum(),
                pagerItem.getPageSize());
        // 分页步骤2.5. 设置页面的跳转url
        pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(),
                request.getQueryString()));
        // 分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问
        request.setAttribute("pagerItem", pagerItem);
        request.setAttribute("DataList", vDataList);
        // ------------------------------------------------------------------------
        // 转发到页面
        String toPage = UIConst.VIEWPATH + "/Purchase_list.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);
    }

    protected void listDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数
        String searchName = request.getParameter("searchName");
        // 回显请求数据
        request.setAttribute("searchName", searchName);

        System.out.println("我输入的是："+searchName);

        List<Purchase> vDataList = null;
        // ------------------------------------------------------------------------
        // 分页步骤1. 创建PagerIter对象, 处理url传过来的pagesize和pageindex
        PagerItem pagerItem = new PagerItem();
        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
        // 分页步骤2.1. 定义记录数变量
        Long rowCount = 0L;
        if (SysFun.isNullOrEmpty(searchName)) {
            // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其它方法
            rowCount = purchaseService.count();
            // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
            pagerItem.changeRowCount(rowCount);
            // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
            vDataList = purchaseService.pager(pagerItem.getPageNum(),
                    pagerItem.getPageSize());

            System.out.println("null操作");
        }
        else {
            System.out.println("search操作");

            // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其它方法
            rowCount = purchaseService.countByName(searchName);
            // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
            pagerItem.changeRowCount(rowCount);
            // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
            vDataList = purchaseService.pagerByName(searchName,
                    pagerItem.getPageNum(), pagerItem.getPageSize());
        }
        // 分页步骤2.5. 设置页面的跳转url
        pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(),
                request.getQueryString()));
        // 分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问
        request.setAttribute("pagerItem", pagerItem);
        request.setAttribute("DataList", vDataList);
        // ------------------------------------------------------------------------
        // 转发到页面
        String toPage = UIConst.VIEWPATH + "/Purchase_list.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);
    }

    protected void insertView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 转发到页面
        String toPage = UIConst.VIEWPATH + "/Purchase_insert.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);

    }

    protected void insertDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();
        // 获取请求数据
        String purchaseId = request.getParameter("purchaseId");
//        String pid = request.getParameter("pid");
        String pName = request.getParameter("pName");
        String buyDate = request.getParameter("buyDate");
        String gid = request.getParameter("gid");
        String num = request.getParameter("num");
        String price = request.getParameter("price");
        String totalAmount = request.getParameter("totalAmount");
        //为了在输入页面回显原来的旧值，将旧值放到作用域，页面中进行获取
        request.setAttribute("purchaseId", purchaseId);
 //       request.setAttribute("pid", pid);
        request.setAttribute("pName", pName);
        request.setAttribute("buyDate", buyDate);
        request.setAttribute("gid", gid);
        request.setAttribute("num", num);
        request.setAttribute("price", price);
        request.setAttribute("totalAmount", totalAmount);
        //服务端验证
        String vMsg = "";
//        //判断插入数据后是否超过安全存量
//        Goods goods = new GoodsDaoImpl().load(Long.parseLong(gid));
//        if ((goods.getNowStock()+Double.parseDouble(num))>goods.getSaveStock()){
//            vMsg += "增加商品数量后超过将安全存量";
//        }
//        if (!SysFun.isNullOrEmpty(vMsg)) {
//            request.setAttribute("msg", vMsg);
//            System.out.println(vMsg);
//            insertView(request, response);
//            return;
//        }
        if (SysFun.isNullOrEmpty(purchaseId)) {
            vMsg += "单号不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }

        if (SysFun.isNullOrEmpty(pName)) {
            vMsg += "供应商不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }
        if (SysFun.isNullOrEmpty(buyDate)) {
            vMsg += "购入日期不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }
        if (SysFun.isNullOrEmpty(gid)) {
            vMsg += "货物id不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }
        if (SysFun.isNullOrEmpty(num)) {
            vMsg += "购买数量不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }
        if (SysFun.isNullOrEmpty(price)) {
            vMsg += "单价不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }

        //判断是否能够更新最后购买时间
        Goods goods = new GoodsDaoImpl().load(Long.parseLong(gid));
        if (goods.getLastBuyTime().toString().compareTo(buyDate)<0){
            SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
            try {
                //更新最后购买时间
                goods.setLastBuyTime(simpleDateFormat.parse(buyDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        //更新商品存量
   //     goods.setNowStock(goods.getNowStock()+Double.parseDouble(num));
    //    new GoodsServiceImpl().update(goods);

        //真正的处理
        Purchase bean = new Purchase();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        bean.setPurchaseId(Long.parseLong(purchaseId));
 //       bean.setPid(Long.parseLong(pid));
        bean.setpName(pName);
        try {
            bean.setBuyDate(simpleDateFormat.parse(buyDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        bean.setGid(Long.parseLong(gid));
        bean.setNum(Double.parseDouble(num));
        bean.setPrice(Double.parseDouble(price));
        bean.setTotalAmount(Double.parseDouble(totalAmount));
        Long result = 0L;
        try {
            result = purchaseService.insert(bean);
        } catch (Exception e) {
            vMsg = "添加失败." + e.getMessage();
            // TODO: handle exception
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

    protected void updateView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();
        // 取得主键，再根据主键，获取记录
        String vId = request.getParameter("id");
        if (!SysFun.isNullOrEmpty(vId)) {
            Long iId = SysFun.parseLong(vId);
            Purchase bean = purchaseService.load(iId);
            if (bean != null) {
                // 使用对象来回显
                // request.setAttribute("bean", bean);
                // 为了在输入页面回显原来的旧值,需要将旧值放到作用域,页面中进行获取
                num1 = bean.getNum();
                request.setAttribute("purchaseId", bean.getPurchaseId());
 //             request.setAttribute("pid", bean.getPid());
                request.setAttribute("pName", bean.getpName());
                request.setAttribute("buyDate", bean.getBuyDate());
                request.setAttribute("gid", bean.getGid());
                request.setAttribute("num", bean.getNum());
                request.setAttribute("price", bean.getPrice());
                request.setAttribute("totalAmount", bean.getTotalAmount());
                String toPage = UIConst.VIEWPATH +
                        "/Purchase_update.jsp";
                request.getRequestDispatcher(toPage).forward(request,
                        response);
                return;
            }
        }
        out.println("<script>");
        out.println("alert('数据不存在');");
        out.println("parent.window.location.reload();");
        out.println("</script>");

    }

    protected void updateDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();
        // (0) 获取请求数据
        String vId = request.getParameter("purchaseId");
        String pid = request.getParameter("pid");
        String pName = request.getParameter("pName");
        String buyDate = request.getParameter("buyDate");
        String gid = request.getParameter("gid");
        String num = request.getParameter("num");
        String price = request.getParameter("price");
        String totalAmount = request.getParameter("totalAmount");
        // 为了在输入页面回显原来的旧值,需要将旧值放到作用域,页面中进行获取
        request.setAttribute("purchaseId", vId);
    //    request.setAttribute("pid", pid);
        request.setAttribute("pName", pName);
        request.setAttribute("buyDate", buyDate);
        request.setAttribute("gid", gid);
        request.setAttribute("num", num);
        request.setAttribute("price", price);
        request.setAttribute("totalAmount", totalAmount);

        //服务端验证
        //判断更新后库存数量是否会低于0
        Goods goods = new GoodsDaoImpl().load(Long.parseLong(gid));
        String vMsg = "";
        if ((goods.getNowStock()+(Double.parseDouble(num)-num1))<0){
            vMsg += "商品剩余数量不足！，无法修改";
        }
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }
        if (SysFun.isNullOrEmpty(vId)) {
            vMsg += "单号不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }

        if (SysFun.isNullOrEmpty(pName)) {
            vMsg += "供应商不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }
        if (SysFun.isNullOrEmpty(buyDate)) {
            vMsg += "购入日期不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }
        if (SysFun.isNullOrEmpty(gid)) {
            vMsg += "货物id不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }
        if (SysFun.isNullOrEmpty(num)) {
            vMsg += "购买数量不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }
        if (SysFun.isNullOrEmpty(price)) {
            vMsg += "单价不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }

        // 数据库验证
        Long iId = SysFun.parseLong(vId);
        Purchase bean = purchaseService.load(iId);
        if (bean == null) {
            vMsg = "数据不存在";
        }
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            updateView(request, response);
            return;
        }
        //更新商品存量
        //  goods.setNowStock(goods.getNowStock()+(Double.parseDouble(num)-num1));
        //  new GoodsServiceImpl().update(goods);

        //判断是否能够更新最后购买时间
        if (goods.getLastBuyTime().toString().compareTo(buyDate)<0){
            SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
            try {
                //更新最后购买时间
                goods.setLastBuyTime(simpleDateFormat.parse(buyDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //真正出处理
        bean.setPurchaseId(iId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
 //       bean.setPid(Long.parseLong(pid));
        bean.setpName(pName);
        try {
            bean.setBuyDate(simpleDateFormat.parse(buyDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        bean.setGid(Long.parseLong(gid));
        bean.setNum(Double.parseDouble(num));
        bean.setPrice(Double.parseDouble(price));
        bean.setTotalAmount(Double.parseDouble(totalAmount));
        Long result = 0L;
        try {
            result = purchaseService.update(bean);
        } catch (Exception e) {
            vMsg = "修改失败." + e.getMessage();
            // TODO: handle exception
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

    protected void detailView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();
        // 取得主键，再根据主键，获取记录
        String vId = request.getParameter("id");
        if (!SysFun.isNullOrEmpty(vId)) {
            Long iPK = SysFun.parseLong(vId);
            Purchase bean = purchaseService.load(iPK);
            if (bean != null) {
                // 使用对象来回显
                request.setAttribute("bean", bean);
                String toPage = UIConst.VIEWPATH + "/Purchase_detail.jsp";
                request.getRequestDispatcher(toPage).forward(request, response);
                return;
            }
        }
        out.println("<script>");
        out.println("alert('数据不存在.');");
        out.println("parent.window.location.reload();");
        out.println("</script>");
    }

    protected void deleteDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();
        // 取得主键，再根据主键，获取记录
        String vId = request.getParameter("id");
        // 取得当前要删除的单号实体类
        Purchase purchase = new PurchaseServiceImpl().load(Long.parseLong(vId));
        // 获取删除单号商品的实体类
        Goods goods = new GoodsDaoImpl().load(purchase.getGid());
        //判断删除购入单号之后库存是否大于0
        if ((goods.getNowStock()-purchase.getNum())<0){
            //不允许删除
            out.print("refuse"); // 不要使用println()
            return ;
        }


        if (!SysFun.isNullOrEmpty(vId)) {
            Long iId = SysFun.parseLong(vId);
            //删除更新商品数量
            goods.setNowStock(goods.getNowStock()-purchase.getNum());
            new GoodsServiceImpl().update(goods);
            //删除操作
            Long result = 0L;
            result = purchaseService.delete(iId);
            if (result > 0) {
                out.print("ok"); // 不要使用println()
                return ;
            }
        }
        out.println("nook");

    }

    /*protected String checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        javax.servlet.http.HttpSession session = req.getSession();
        String toURL = null;
        Object obj = session.getAttribute(UIConst.BG_LOGINUSER_KEY);

        if(obj == null){
            toURL = req.getContextPath() + UIConst.AREAPATH + "/Login";

        }

        return toURL;
    }*/


}
