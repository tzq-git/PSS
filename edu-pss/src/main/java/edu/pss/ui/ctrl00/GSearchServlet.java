package edu.pss.ui.ctrl00;

import com.liuvei.common.PagerItem;
import com.liuvei.common.SysFun;
import edu.pss.bean.Goods;
import edu.pss.service.GoodsService;
import edu.pss.service.impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(UIConst.AREAPATH + "/GSearch")
public class GSearchServlet extends HttpServlet {

    private GoodsService goodsService = new GoodsServiceImpl();

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
        String toURL = checkLogin(request,response);
        if(toURL != null){
            response.sendRedirect(toURL);
            return;
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
            case "form":
                formView(request, response); // 添加页面
                break;
            case "formdeal":
                formDeal(request, response); // 添加处理
                break;

            default:
                //  listView(request, response); // 列表页面 : 默认
                System.out.println("oper不存在。");
                break;
        }



    }
    protected void formView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String toPage = UIConst.VIEWPATH + "/GSearch_form.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);
    }

    protected void formDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        java.io.PrintWriter out = response.getWriter();

        String gid = request.getParameter("gid");
        String gName = request.getParameter("gName");
        String buyDate_min = request.getParameter("buyDate_min");
        String buyDate_max = request.getParameter("buyDate_max");
        String saleDate_min = request.getParameter("saleDate_min");
        String saleDate_max = request.getParameter("saleDate_max");
        System.out.println(buyDate_min);
        System.out.println(buyDate_max);
        System.out.println(buyDate_min.compareTo(buyDate_max)>0);

        if(buyDate_min.compareTo(buyDate_max)>0||saleDate_min.compareTo(saleDate_max)>0){
            out.println("<script>");
            out.println("alert('起始日期不能大于结尾日期');");
            out.println("location.href = \"GSearch?oper=form\";");
            out.println("</script>");
            out.flush();

        }else{
            List<Goods> vDataList = null;
            List<Goods> rDataList = new ArrayList<Goods>();
            // ------------------------------------------------------------------------
            // 分页步骤1. 创建PagerIter对象, 处理url传过来的pagesize和pageindex
            PagerItem pagerItem = new PagerItem();
            pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
            pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
            // 分页步骤2.1. 定义记录数变量
            Long rowCount = 0L;
            // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其它方法
            rowCount = goodsService.countEX(gid,gName);
            // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
            pagerItem.changeRowCount(rowCount);
            // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
            vDataList = goodsService.pagerEX(gid,gName,pagerItem.getPageNum(),
                    pagerItem.getPageSize());
            //需要对查出的数据进行筛选
            Long count = 0L;

            for (Goods goods : vDataList) {
                if(goods.getLastBuyTime().toString().compareTo(buyDate_min)>0&&goods.getLastBuyTime().toString().compareTo(buyDate_max)<0
                        &&goods.getLastSaleTime().toString().compareTo(saleDate_min)>0&&goods.getLastSaleTime().toString().compareTo(saleDate_max)<0){
                    rDataList.add(goods);
                    count++;
                }

            }
            if(count == 0){
                out.println("<script>");
                out.println("alert('未查询到数据，请重新输入！');");
                out.println("location.href = \"GSearch?oper=form\";");
                out.println("</script>");
                out.flush();
            }else{
                pagerItem.setRowCount(count);
                // 分页步骤2.5. 设置页面的跳转url
                pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(),
                        request.getQueryString()));
                // 分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问
                request.setAttribute("pagerItem", pagerItem);
                request.setAttribute("DataList", rDataList);
                // ------------------------------------------------------------------------
                // 转发到页面
                String toPage = UIConst.VIEWPATH + "/GSearch_list.jsp";
                request.getRequestDispatcher(toPage).forward(request, response);
            }

        }

    }


    protected void listView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Goods> vDataList = null;
        // ------------------------------------------------------------------------
        // 分页步骤1. 创建PagerIter对象, 处理url传过来的pagesize和pageindex
        PagerItem pagerItem = new PagerItem();
        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
        // 分页步骤2.1. 定义记录数变量
        Long rowCount = 0L;
        // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其它方法
        rowCount = goodsService.count();
        // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
        pagerItem.changeRowCount(rowCount);
        // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
        vDataList = goodsService.pager(pagerItem.getPageNum(),
                pagerItem.getPageSize());
        // 分页步骤2.5. 设置页面的跳转url
        pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(),
                request.getQueryString()));
        // 分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问
        request.setAttribute("pagerItem", pagerItem);
        request.setAttribute("DataList", vDataList);
        // ------------------------------------------------------------------------
        // 转发到页面
        String toPage = UIConst.VIEWPATH + "/GSearch_list.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);
    }

    protected void listDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数
        String searchName = request.getParameter("searchName");
        // 回显请求数据
        request.setAttribute("searchName", searchName);

        System.out.println("我输入的是："+searchName);

        List<Goods> vDataList = null;
        // ------------------------------------------------------------------------
        // 分页步骤1. 创建PagerIter对象, 处理url传过来的pagesize和pageindex
        PagerItem pagerItem = new PagerItem();
        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
        // 分页步骤2.1. 定义记录数变量
        Long rowCount = 0L;
        if (SysFun.isNullOrEmpty(searchName)) {
            // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其它方法
            rowCount = goodsService.count();
            // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
            pagerItem.changeRowCount(rowCount);
            // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
            vDataList = goodsService.pager(pagerItem.getPageNum(),
                    pagerItem.getPageSize());

            System.out.println("null操作");
        }
        else {
            System.out.println("search操作");

            // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其它方法
            rowCount = goodsService.countByName(searchName);
            // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
            pagerItem.changeRowCount(rowCount);
            // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
            vDataList = goodsService.pagerByName(searchName,
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
        String toPage = UIConst.VIEWPATH + "/GSearch_list.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);
    }

    protected String checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        javax.servlet.http.HttpSession session = req.getSession();
        String toURL = null;
        Object obj = session.getAttribute(UIConst.BG_LOGINUSER_KEY);

        if(obj == null){
            toURL = req.getContextPath() + UIConst.AREAPATH + "/Login";

        }

        return toURL;
    }


}
