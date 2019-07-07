package edu.pss.filter;

import edu.pss.bean.Authority;
import edu.pss.bean.User;
import edu.pss.ui.ctrl00.UIConst;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorityFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        /**
         * 1,doFilter方法的第一个参数为ServletRequest对象。此对象给过滤器提供了对进入的信息（包括 *
         * 表单数据、cookie和HTTP请求头）的完全访问。第二个参数为ServletResponse，通常在简单的过 *
         * 滤器中忽略此参数。最后一个参数为FilterChain，此参数用来调用servlet或JSP页。
         */

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        /**
         * 如果处理HTTP请求，并且需要访问诸如getHeader或getCookies等在ServletRequest中
         * 无法得到的方法，就要把此request对象构造成HttpServletRequest
         */
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 取得根目录所对应的绝对路径:
        String currentURL = request.getRequestURI();
        // 截取到当前文件名用于比较
        int currentURLLength = currentURL.length();
        // 截取到当前文件名用于比较
        String targetURL = currentURL.substring(request.getContextPath().length(), currentURLLength);
        //获取当前会话的session
        HttpSession session = request.getSession(true);

//        System.out.println("getContextPath: " + request.getContextPath());
//        System.out.println("currentURL：" + currentURL );
//        System.out.println("targetURL：" + targetURL );


        //从session中取出user对象，以便判断用户类型
        User user = (User)session.getAttribute("user");

        //管理员访问所有资源，放行
        if ( user!=null && user.getUserId()==1 ){
            // 加入filter链继续向下执行
            filterChain.doFilter(request, response);
            return ;
        }


        /*登录后进行权限验证*/
        //获取session中的权限类
        Authority authority = (Authority)session.getAttribute("authority");
        switch (targetURL){
            case UIConst.AREAPATH + "/Login":
            case UIConst.AREAPATH + "/Main":
            case UIConst.AREAPATH + "/Download":
            case UIConst.AREAPATH + "/ValidateCode":
                filterChain.doFilter(request, response);
                return ;
            case UIConst.AREAPATH + "/User":
                String oper = request.getParameter("oper");
                String type = request.getParameter("type");
                String id = request.getParameter("id");

                //管理员权限，直接放行
                if ( user.getType()==0){
                    break;//退出switch语句，执行下一个Filter
                } else {
                    //普通用户
                    //修改密码操作，放行
                    if ( "change".equals(type) ||
                            "info".equals(oper) && id.equals(user.getUserId()) ) //查看自己的详细信息，放行
                        break;
                }
                //请求转发会主页面
                requestForwad(request, response, "/Reject");
                break;
            case UIConst.AREAPATH + "/Authority":
                //非管理员，无权更改权限
                if ( user.getType()!=0 ){
                    //请求转发会主页面
                    requestForwad(request, response, "/Reject");
                    return ;
                }
                break;
            case UIConst.AREAPATH + "/OutBound":
                if ( authority.getHaveSaleOut()==0 ){
                    //请求转发会主页面
                    requestForwad(request, response, "/Reject");
                    return ;
                }
                break;
            case UIConst.AREAPATH + "/SaleReturn":
                if ( authority.getHaveSaleReturn()==0 ){
                    //请求转发会主页面
                    requestForwad(request, response, "/Reject");
                    return ;
                }
                break;
            case UIConst.AREAPATH + "/Goods":
                if ( authority.getHaveGoods()==0 ){
                    //请求转发会主页面
                    requestForwad(request, response, "/Reject");
                    return ;
                }
                break;
            case UIConst.AREAPATH + "/Customer":
            case UIConst.AREAPATH + "/DealRecord":
                if ( authority.getHaveCustomer()==0 ){
                    //请求转发会主页面
                    requestForwad(request, response, "/Reject");
                    return ;
                }
                break;
            case UIConst.AREAPATH + "/Provider":
                if ( authority.getHaveSupplier()==0 ){
                    //请求转发会主页面
                    requestForwad(request, response, "/Reject");
                    return ;
                }
                break;
            case UIConst.AREAPATH + "/SalePerson":
                if ( authority.getHaveSaleMan()==0 ){
                    //请求转发会主页面
                    requestForwad(request, response, "/Reject");
                    return ;
                }
                break;
            case UIConst.AREAPATH + "/Purchase":
                if ( authority.getHavePurchaseIn()==0 ){
                    //请求转发会主页面
                    requestForwad(request, response, "/Reject");
                    return ;
                }
                break;
            case UIConst.AREAPATH + "/RPurchase":
                if ( authority.getHavePurchaseOut()==0 ){
                    //请求转发会主页面
                    requestForwad(request, response, "/Reject");
                    return ;
                }
                break;
                //库存查询默认放心，每个用户都可以查询库存
            case UIConst.AREAPATH + "/GSearch":
                break;
            default:
                //登录用户请求静态资源文件，放行
                if( targetURL.startsWith("/static") ){
                    filterChain.doFilter(request, response);
                    return ;
                }
                String toURL = UIConst.VIEWPATH + "/404.jsp";
                request.getRequestDispatcher(toURL).forward(request, response);
                return ;
        }

        // 加入filter链继续向下执行
        filterChain.doFilter(request, response);
        /**
         * 调用FilterChain对象的doFilter方法。Filter接口的doFilter方法取一个FilterChain对象作 为它
         * 的一个参数。在调用此对象的doFilter方法时，激活下一个相关的过滤器。如果没有另
         * 一个过滤器与servlet或JSP页面关联，则servlet或JSP页面被激活。
         */
    }

    @Override
    public void destroy() {

    }

    /**
     * 完成请求转发
     * @param request
     * @param response
     * @param page
     */
    public void requestForwad(HttpServletRequest request, HttpServletResponse response, String page){

        //转发页面
        try {
            request.getRequestDispatcher(UIConst.AREAPATH + page).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
