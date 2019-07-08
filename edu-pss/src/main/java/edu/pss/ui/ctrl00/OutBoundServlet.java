package edu.pss.ui.ctrl00;

import com.liuvei.common.PagerItem;
import com.liuvei.common.SysFun;
import edu.pss.bean.DealRecord;
import edu.pss.bean.Goods;
import edu.pss.bean.OutBound;
import edu.pss.dao.impl.CustomerDaoImpl;
import edu.pss.dao.impl.GoodsDaoImpl;
import edu.pss.service.DealRecordService;
import edu.pss.service.GoodsService;
import edu.pss.service.impl.DealRecordServiceImpl;
import edu.pss.service.impl.GoodsServiceImpl;
import edu.pss.service.impl.OutBoundServiceImpl;
import edu.pss.service.OutBoundService;

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
import java.util.regex.Pattern;

@WebServlet(UIConst.AREAPATH + "/OutBound")
public class OutBoundServlet extends HttpServlet {
    
    private OutBoundService outBoundService = new OutBoundServiceImpl();
    private Double num1 ;
    private Long insertId = null;
    
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
            case "checkdeal":
                checkDeal(request,response);
                break;
            default:
// listView(request, response); // 列表页面 : 默认
                System.out.println("oper不存在。");
                break;
        }

    }



    private void dataLoad(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String toPage = UIConst.AREAPATH + "/Download?class=outbound";
        request.getRequestDispatcher(toPage).forward(request, response);
    }


    protected void listView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<OutBound> vDataList = null;
        // ------------------------------------------------------------------------
        // 分页步骤1. 创建PagerIter对象, 处理url传过来的pagesize和pageindex
        PagerItem pagerItem = new PagerItem();
        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
        // 分页步骤2.1. 定义记录数变量
        Long rowCount = 0L;
        // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其它方法
        rowCount = outBoundService.count();
        // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
        pagerItem.changeRowCount(rowCount);
        // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
        vDataList = outBoundService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
        // 分页步骤2.5. 设置页面的跳转url
        pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));
        // 分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问
        request.setAttribute("pagerItem", pagerItem);
        request.setAttribute("DataList", vDataList);
        // ------------------------------------------------------------------------

        // 转发到页面
        String toPage = UIConst.VIEWPATH + "/OutBound_list.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);
    }


    protected void listDeal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取请求参数
        String searchName = request.getParameter("searchName");
        // 回显请求数据
        request.setAttribute("searchName", searchName);
        List<OutBound> vDataList = null;
        // ------------------------------------------------------------------------
        // 分页步骤1. 创建PagerIter对象, 处理url传过来的pagesize和pageindex
        PagerItem pagerItem = new PagerItem();
        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
        // 分页步骤2.1. 定义记录数变量
        Long rowCount = 0L;
        if (SysFun.isNullOrEmpty(searchName)) {
            // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其它方法
            rowCount = outBoundService.count();
            // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
            pagerItem.changeRowCount(rowCount);
            // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
            vDataList = outBoundService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
        } else {
            // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其它方法
            rowCount = outBoundService.countByName(searchName);
            // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
            pagerItem.changeRowCount(rowCount);
            // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
            vDataList = outBoundService.pagerByName(searchName, pagerItem.getPageNum(), pagerItem.getPageSize());
        }
        // 分页步骤2.5. 设置页面的跳转url
        pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));
        // 分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问
        request.setAttribute("pagerItem", pagerItem);
        request.setAttribute("DataList", vDataList);
        // ------------------------------------------------------------------------
        // 转发到页面
        String toPage = UIConst.VIEWPATH + "/OutBound_list.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);

    }


    protected void insertView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 转发到页面
        String toPage = UIConst.VIEWPATH + "/OutBound_insert.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);
    }


    protected void insertDeal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();
        // 获取请求数据
        String orderId = request.getParameter("orderId");
        String cName = request.getParameter("cName");
        String salesMan = request.getParameter("salesMan");
        String gName = request.getParameter("gName");
        String address = request.getParameter("address");
        String num = request.getParameter("num");
        String price = request.getParameter("price");
        String amount = request.getParameter("amount");
        String dealDateStr = request.getParameter("dealDate");
        String outDateStr = request.getParameter("outDate");

        Date dealDate = new Date();
        Date outDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            dealDate = formatter.parse(dealDateStr);
            outDate = formatter.parse(outDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //存储id
        insertId = Long.parseLong(orderId);

        //为了在输入页面回显原来的旧值，将旧值放到作用域，页面中进行获取
        request.setAttribute("orderId", orderId);
        request.setAttribute("cName", cName);
        request.setAttribute("salesMan",salesMan);
        request.setAttribute("gName",gName);
        request.setAttribute("address", address);
        request.setAttribute("num",num);
        request.setAttribute("price", price);
        request.setAttribute("amount",amount);
        request.setAttribute("dealDate", dealDate);
        request.setAttribute("outDate", outDate);



        //服务端验证
        String vMsg = "";

        //判断增加出库单后商品库存是否低于0
        Goods goods = new GoodsServiceImpl().loadByName(gName);
        if ((goods.getNowStock()-Double.parseDouble(num))<0){
            vMsg += "商品存量不足！请尽快补充";
        }
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
        if (SysFun.isNullOrEmpty(gName)) {
            vMsg += "商品名不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }
        if (SysFun.isNullOrEmpty(num)) {
            vMsg += "数量不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }

        if (SysFun.isNullOrEmpty(price) && isNumeric(price) ) {
            vMsg += "单价格式不正确，请重新输入";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }
        if (SysFun.isNullOrEmpty(amount)) {
            vMsg += "总价不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }

        //更新最后出库时间
        String lastDate = outDateStr.substring(0,10);
        System.out.println(lastDate);
        if(goods.getLastSaleTime()==null||goods.getLastSaleTime().toString().compareTo(lastDate)<0){
            SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
            try {
                goods.setLastSaleTime(simpleDateFormat.parse(lastDate));
                new GoodsServiceImpl().update(goods);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        //更新商品存量
        goods.setNowStock(goods.getNowStock()-Double.parseDouble(num));
        new GoodsServiceImpl().update(goods);
        //更新交易记录表，添加一条新的交易记录
        DealRecordService dealRecordService = new DealRecordServiceImpl();
        DealRecord dealRecord = new DealRecord();
        dealRecord.setOrderId(Long.parseLong(orderId));
        dealRecord.setCid(new CustomerDaoImpl().loadByName(cName).getCid());
        dealRecord.setDealDate(dealDate);
        dealRecord.setSalesMan(salesMan);
        dealRecord.setReceiveAddress(address);
        dealRecord.setBillId(Long.parseLong(orderId+dealRecord.getCid()));
        dealRecord.setAmount(Double.parseDouble(amount));
        dealRecordService.insert(dealRecord);

        //更新购入表
        OutBound bean = new OutBound();
        bean.setOrderId(Long.parseLong(orderId));
        bean.setcName(cName);
        bean.setSalesMan(salesMan);
        bean.setgName(gName);
        bean.setAddress(address);
        bean.setNum(Double.parseDouble(num));
        bean.setPrice(Double.parseDouble(price));
        bean.setAmount(Double.parseDouble(amount));
        bean.setDealDate(dealDate);
        bean.setOutDate(outDate);

        Long result = 0L;
        try {
            result = outBoundService.insert(bean);
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
            OutBound bean = outBoundService.loadById(iPK);
            if (bean != null) {
                // 使用对象来回显
                request.setAttribute("bean", bean);
                String toPage = UIConst.VIEWPATH + "/OutBound_detail.jsp";
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
            Long result = 0L;
            //删除交易记录
            DealRecordService dealRecordService = new DealRecordServiceImpl();
            DealRecord dealRecord = new DealRecord();
            dealRecordService.delete(iId);

            //删除出库单更新商品库存
            OutBound outBound = outBoundService.loadById(iId);
            GoodsService goodsService = new GoodsServiceImpl();
            System.out.println("id:"+iId);
            Goods goods = goodsService.loadByName(outBound.getgName());
            goods.setNowStock(goods.getNowStock()+outBound.getNum());
            goodsService.update(goods);
            result = outBoundService.delete(iId);
            if (result > 0) {
                out.print("ok"); // 不要使用println()
                return;
            }
        }
        out.println("nook");
    }

    private void checkDeal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();
        // 取得主键，再根据主键，获取记录
        if(insertId!=null){
            OutBound outBound = new OutBoundServiceImpl().loadById(insertId);
            Goods goods = new GoodsServiceImpl().loadByName(outBound.getgName());
            System.out.println("test到这里了");
            if(goods.getNowStock()<=goods.getSaveStock()){
                out.print(goods.getgName());
                insertId = null;
                return;
            }
        }

        out.print("nook");
    }


    protected void updateView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();
        // 取得主键，再根据主键，获取记录
        String vId = request.getParameter("id");
        if (!SysFun.isNullOrEmpty(vId)) {
            Long iId = SysFun.parseLong(vId);
            OutBound bean = outBoundService.loadById(iId);
            num1 = bean.getNum();
            if (bean != null) {
                // 使用对象来回显
                // request.setAttribute("bean", bean);
                // 为了在输入页面回显原来的旧值,需要将旧值放到作用域,页面中进行获取
                request.setAttribute("orderId", bean.getOrderId());
                request.setAttribute("cName", bean.getcName());
                request.setAttribute("salesMan",bean.getSalesMan());
                request.setAttribute("gName",bean.getgName());
                request.setAttribute("address", bean.getAddress());
                request.setAttribute("num",bean.getNum());
                request.setAttribute("price", bean.getPrice());
                request.setAttribute("amount",bean.getAmount());
                request.setAttribute("status",bean.getStatus());
                request.setAttribute("dealDate", bean.getDealDate());
                request.setAttribute("outDate", bean.getOutDate());
                String toPage = UIConst.VIEWPATH + "/OutBound_update.jsp";
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
        String orderId = request.getParameter("id");
        String cName = request.getParameter("cName");
        String salesMan = request.getParameter("salesMan");
        String gName = request.getParameter("gName");
        String address = request.getParameter("address");
        String num = request.getParameter("num");
        String price = request.getParameter("price");
        String amount = request.getParameter("amount");
    //    String status = request.getParameter("status");
        String dealDateStr = request.getParameter("dealDate");
        String outDateStr = request.getParameter("outDate");

        Date dealDate = new Date();
        Date outDate = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            dealDate = formatter.parse(dealDateStr);
            outDate = formatter.parse(outDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //为了在输入页面回显原来的旧值，将旧值放到作用域，页面中进行获取
        request.setAttribute("orderId", orderId);
        request.setAttribute("cName", cName);
        request.setAttribute("salesMan",salesMan);
        request.setAttribute("gName",gName);
        request.setAttribute("address", address);
        request.setAttribute("num",num);
        request.setAttribute("price", price);
        request.setAttribute("amount",amount);
        request.setAttribute("dealDate", dealDateStr);
        request.setAttribute("outDate", outDateStr);


        //服务端验证
        String vMsg = "";
        //判断改变出库商品数量后，商品数量是否足够
        Goods goods = new GoodsServiceImpl().loadByName(gName);
        if ((goods.getNowStock()+(num1-Double.parseDouble(num)))<0){
            vMsg += "商品存量不足！请尽快补充";
        }
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
        if (SysFun.isNullOrEmpty(gName)) {
            vMsg += "商品名不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }
        if (SysFun.isNullOrEmpty(num)) {
            vMsg += "数量不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }

        if (SysFun.isNullOrEmpty(price) && isNumeric(price) ) {
            vMsg += "单价格式不正确，请重新输入";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }
        if (SysFun.isNullOrEmpty(amount)) {
            vMsg += "总价不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }

        //更新最后出库时间
        String lastDate = outDateStr.substring(0,10);
        System.out.println(lastDate);
        if(goods.getLastSaleTime().toString().compareTo(lastDate)<0){
            SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
            try {
                goods.setLastSaleTime(simpleDateFormat.parse(lastDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        //更新商品存量
        goods.setNowStock(goods.getNowStock()+(num1-Double.parseDouble(num)));
        new GoodsServiceImpl().update(goods);

        //更新交易记录表，更新一条新的交易记录
        DealRecordService dealRecordService = new DealRecordServiceImpl();
        DealRecord dealRecord = new DealRecord();
        dealRecord.setOrderId(Long.parseLong(orderId));
        dealRecord.setCid(new CustomerDaoImpl().loadByName(cName).getCid());
        dealRecord.setDealDate(dealDate);
        dealRecord.setSalesMan(salesMan);
        dealRecord.setReceiveAddress(address);
        dealRecord.setBillId(Long.parseLong(orderId+dealRecord.getCid()));
        dealRecord.setAmount(Double.parseDouble(amount));
        dealRecordService.update(dealRecord);

        // 真正处理
        OutBound bean = new OutBound();
        bean.setOrderId(Long.parseLong(orderId));
        bean.setcName(cName);
        bean.setSalesMan(salesMan);
        bean.setgName(gName);
        bean.setAddress(address);
        bean.setNum(Double.parseDouble(num));
        bean.setPrice(Double.parseDouble(price));
        bean.setAmount(Double.parseDouble(amount));
     //   bean.setStatus(Long.parseLong(status));
        bean.setDealDate(dealDate);
        bean.setOutDate(outDate);
        Long result = 0L;
        try {
            result = outBoundService.update(bean);
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


    /*protected String checkLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        javax.servlet.http.HttpSession session = request.getSession();
        String toURL = null;
        Object obj = session.getAttribute(UIConst.BG_LOGINUSER_KEY);
        if ( obj == null ){
            toURL = request.getContextPath() + UIConst.AREAPATH + "/Login";
        }
        return toURL;
    }*/

    /**
     * 判读字符串中存储的是否为浮点数
     * @param str  一个数字字符串
     * @return
     */
    public static boolean isNumeric(String str){

        Pattern pattern = Pattern.compile("^((0-9)+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*)$");
        return pattern.matcher(str).matches();
    }

}
