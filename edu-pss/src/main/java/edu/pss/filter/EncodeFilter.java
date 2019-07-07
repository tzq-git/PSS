package edu.pss.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class EncodeFilter implements Filter {

    private String encode = "UTF-8";
    private String contentType = "text/html;charset=UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String paramEncode = filterConfig.getServletContext().getInitParameter("encode");
        if (paramEncode != null) {
            encode = paramEncode;
        }
        String paramContentType = filterConfig.getServletContext().getInitParameter("contentType");
        if (paramContentType != null) {
            contentType = paramContentType;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入EncodeFilterd的doFilter方法中");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        request.setCharacterEncoding(encode);
        response.setCharacterEncoding(encode);
        response.setContentType(contentType);

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
