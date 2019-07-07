package edu.pss.filter;

import edu.pss.bean.User;
import edu.pss.ui.ctrl00.UIConst;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
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

        System.out.println("进入LoginFilter的doFilter方法中");

        /*资源文件无需过滤*/

        //先获取到当前的一个访问路径
        String uri = request.getRequestURI();//获取到当前的请求路径
        String path = uri.substring(uri.indexOf("/"));//获取到尾部路径
        System.out.println("拦截到的路径:"+path);

//        //不需要过滤的url，资源文件
//        String[] urls = {".js",".css",".ico",".jpg",".png",".ttf",".gif",".woff"};
//        boolean flag = false;
//        //利用foreach进行比较
//        for (String str : urls) {
//            if (path.lastIndexOf(str) != -1) {
//                flag =true;
//                break;
//            }
//        }
//        //如果你的路径请求是登陆的 那就不拦截 直接放行
//        if (flag) {
//            filterChain.doFilter(request, response);
//            return ;
//        }


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
//        System.out.println(request.getRequestURL());


        //如果为登录页面请求，则放行
        if ( targetURL.equals(UIConst.AREAPATH + "/Login")
                || targetURL.equals(UIConst.AREAPATH + "/ValidateCode" )
                || targetURL.startsWith("/static") ) {
            filterChain.doFilter(request, response);
            return ;
        }

        //登录验证，检查session域中是否存在user类
        User user = (User)session.getAttribute("user");
        if ( user==null  ) {
            String eMsg = "会话失效，请重新登录";
            System.out.println(eMsg);
            //返回错误信息
            request.setAttribute("vMsg", eMsg);
            //转发页面
            response.sendRedirect(request.getContextPath() + UIConst.AREAPATH + "/Login");
            //使用Dispatcher服务器内部转发，会出现死循环
//            request.getRequestDispatcher(UIConst.AREAPATH + "/Login").forward(request, response);
            return ;
        }
        //执行下一个过滤器
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
