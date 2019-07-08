package edu.pss.ui.ctrl00;

import edu.pss.dao.OutBoundDao;
import edu.pss.dao.SaleReturnDao;
import edu.pss.dao.impl.OutBoundDaoImpl;
import edu.pss.dao.impl.SaleReturnDaoImpl;
import edu.pss.service.GoodsService;
import edu.pss.service.PurchaseService;
import edu.pss.service.impl.GoodsServiceImpl;
import edu.pss.service.impl.PurchaseServiceImpl;
import edu.pss.util.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(UIConst.AREAPATH + "/Download")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String oper = request.getParameter("oper");
        if (oper!=null && oper.equals("list")){
            listView(request, response);
        } else {
            downloadDeal(request, response);
        }
    }

    private void listView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String toPage = UIConst.VIEWPATH + "/Download.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);
    }

    protected void downloadDeal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //验证是否登录
        /*String toURL = checkLogin(request, response);
        if ( toURL != null){
            response.sendRedirect(toURL);
            return ;
        }*/

        /*
        * 1.根据求参数获取对应得数据
        * 2.将获得的数据写入Execl(XSSFWorkbook)对象中
        * 3.将Execl文件对象内容写入相应流中
        * */

        //获取要下载表格的记录条数
        String number = request.getParameter("number");
        Long num = 0L;
        if ( number!=null )
            num = Long.parseLong(number);
        //获取下载表格的名称
        String name = request.getParameter("name");
        //获取类名，确认下载什么类型的数据
        String type = request.getParameter("type");
        //获取请求页号，如果为0，则表示请求整张表
        String page = request.getParameter("page");
        //获取请求页面的大小，若请求整张表，则此参数无效
        String size = request.getParameter("size");
        //获取年度采购报表年份
        String year = request.getParameter("year");
        //定义POI XSSFWorkbook形式的Excel文件
        XSSFWorkbook workbook = null;
        //定义表格表头名称字符串数组
        String []downloadtableName = null;
        String []goodsTableName = new String[]{"商品ID","商品名称","安全存量","现有存量","购买价格","建议售价","最后购买时间","最后售出时间"};
        String []purchaseTableName = new String[]{"采购单单号","供应商ID","供应商名称","购入日期","商品ID","商品名","购买数量","单价","总金额"};
        List list = null;

        GoodsService goodsService = new  GoodsServiceImpl();
        PurchaseService purchaseService = new PurchaseServiceImpl();

        //根据请求参数page和size获取数据集合
        switch (type){
            case "goods":
                downloadtableName = goodsTableName;
                if ( page!=null && page.equals("0") ){
                    //获取整张商品表
                    list = goodsService.list();
                } else if ( page!=null && size!=null){
                    //获取第page也得size条记录
                    list = goodsService.pager(Long.parseLong(page),Long.parseLong(size));
                } else {
                    list = new ArrayList();
                    List lists = goodsService.list();
                    for(int i=0;i<num;i++){
                        if ( i>=lists.size()) break;
                        list.add(lists.get(i));
                    }
                }
                break;
            case "goodsWarning":
                downloadtableName = goodsTableName;
                //获取库存量低于安全存量的记录
                list = goodsService.lowList();
                break;
            case "purchase":
                downloadtableName = purchaseTableName;
                if ( page!=null && page.equals("0") ){
                    //获取整张采购表
                    list = purchaseService.list();
                } else if ( page!=null){
                    //获取第page也得size条记录
                    list = purchaseService.pager(Long.parseLong(page),Long.parseLong(size));
                } else {
                    list = new ArrayList();
                    List lists = purchaseService.list();
                    for(int i=0;i<num;i++){
                        if ( i>=lists.size()) break;
                        list.add(lists.get(i));
                    }
                }
                break;
            case "yearPurchase":
                downloadtableName = purchaseTableName;
                list = purchaseService.list(year);
                break;
        }

        //表头和记录结合写入Excel文件中
        try {
            //将list中数据写入workbook中
            workbook = ExcelUtil.toWrite(downloadtableName, list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //将文件名加上时间戳，防止文件名重复
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String timestamp = dateFormat.format(date);

        String filename = URLEncoder.encode(name+timestamp+".xlsx", "utf-8");
        //设置相应头和编码方式
        response.setCharacterEncoding("utf-8");
        //设置响应头格式，此格式浏览器默认为下载文件
        response.setHeader("Content-Disposition","attachment; filename=\""+filename+"\"");

        //获取response输出流，像浏览器输出文件内容
        ServletOutputStream out = response.getOutputStream();
        //将workbook写入相应流中
        workbook.write(out);
        //立即刷新相应流
        out.flush();
        //关闭相应流
        out.close();
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
}
