/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.4.14.v20181114
 * Generated at: 2019-07-08 07:39:33 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.view00;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Purchase_005finsert_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("jar:file:/D:/javaroot/localRepository_global/taglibs/standard/1.1.2/standard-1.1.2.jar!/META-INF/c.tld", Long.valueOf(1098682290000L));
    _jspx_dependants.put("file:/D:/javaroot/localRepository_global/taglibs/standard/1.1.2/standard-1.1.2.jar", Long.valueOf(1560828620329L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("    <title>layui</title>\r\n");
      out.write("    <meta name=\"renderer\" content=\"webkit\">\r\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/static/X-admin/lib/layui/css/layui.css\"  media=\"all\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <meta name=\"renderer\" content=\"webkit|ie-comp|ie-stand\">\r\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no\" />\r\n");
      out.write("    <meta http-equiv=\"Cache-Control\" content=\"no-siteapp\" />\r\n");
      out.write("    <link rel=\"Bookmark\" href=\"/favicon.ico\" >\r\n");
      out.write("    <link rel=\"Shortcut Icon\" href=\"/favicon.ico\" />\r\n");
      out.write("    <!--[if lt IE 9]>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/static/H-ui.admin/lib/html5shiv.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/static/H-ui.admin/lib/respond.min.js\"></script>\r\n");
      out.write("    <![endif]-->\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/static/H-ui.admin/static/h-ui/css/H-ui.min.css\" />\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/static/H-ui.admin/static/h-ui.admin/css/H-ui.admin.css\" />\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/static/H-ui.admin/lib/Hui-iconfont/1.0.8/iconfont.css\" />\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/static/H-ui.admin/static/h-ui.admin/skin/default/skin.css\" id=\"skin\" />\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/static/H-ui.admin/static/h-ui.admin/css/style.css\" />\r\n");
      out.write("    <!--[if IE 6]>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/static/H-ui.admin/lib/DD_belatedPNG_0.0.8a-min.js\" ></script>\r\n");
      out.write("    <script>DD_belatedPNG.fix('*');</script>\r\n");
      out.write("    <![endif]-->\r\n");
      out.write("    <!--/meta 作为公共模版分离出去-->\r\n");
      out.write("    <!--计算单价数量总额并实时更新-->\r\n");
      out.write("    <script type=\"text/javascript\">\r\n");
      out.write("        function sum(obj) {\r\n");
      out.write("            var a = document.getElementById(\"price\");\r\n");
      out.write("            var b = document.getElementById(\"num\");\r\n");
      out.write("            if(a.value!=''&&b.value!='')\r\n");
      out.write("            {\r\n");
      out.write("                totalAmount.value=(parseFloat(b.value)*parseFloat(a.value)).toFixed(1);\r\n");
      out.write("            }\r\n");
      out.write("\r\n");
      out.write("        }\r\n");
      out.write("    </script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <title>添加用户 - H-ui.admin v3.1</title>\r\n");
      out.write("    <meta name=\"keywords\" content=\"购入管理\">\r\n");
      out.write("    <meta name=\"description\" content=\"购入管理\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<article class=\"page-container\">\r\n");
      out.write("    <form action=\"Purchase\" class=\"form form-horizontal\" id=\"objForm\">\r\n");
      out.write("        <input type=\"hidden\" name=\"oper\" value=\"insertDeal\" />\r\n");
      out.write("        <div class=\"row cl\">\r\n");
      out.write("            <label class=\"form-label col-xs-4 col-sm-3\"><span class=\"c-red\">*</span>购入单号：</label>\r\n");
      out.write("            <div class=\"formControls col-xs-8 col-sm-9\">\r\n");
      out.write("                <input type=\"text\" class=\"input-text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${purchaseId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\"\r\n");
      out.write("                       placeholder=\"\" id=\"purchaseId\" name=\"purchaseId\">\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"row cl\">\r\n");
      out.write("            <label class=\"form-label col-xs-4 col-sm-3\"><span class=\"c-red\">*</span>供应商：</label>\r\n");
      out.write("            <div class=\"formControls col-xs-8 col-sm-9\">\r\n");
      out.write("                <input type=\"text\" class=\"input-text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pName}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\"\r\n");
      out.write("                       placeholder=\"\" id=\"pName\" name=\"pName\">\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"row cl\">\r\n");
      out.write("            <label class=\"form-label col-xs-4 col-sm-3\"><span class=\"c-red\">*</span>购入日期：</label>\r\n");
      out.write("            <div class=\"formControls col-xs-8 col-sm-9\">\r\n");
      out.write("                <input type=\"text\" class=\"input-text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${buyDate}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\"\r\n");
      out.write("                       placeholder=\"\" id=\"buyDate\" name=\"buyDate\">\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"row cl\">\r\n");
      out.write("            <label class=\"form-label col-xs-4 col-sm-3\"><span class=\"c-red\">*</span>商品ID：</label>\r\n");
      out.write("            <div class=\"formControls col-xs-8 col-sm-9\">\r\n");
      out.write("                <input type=\"text\" class=\"input-text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${gid}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\"\r\n");
      out.write("                       placeholder=\"\" id=\"gid\" name=\"gid\">\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"row cl\">\r\n");
      out.write("            <label class=\"form-label col-xs-4 col-sm-3\"><span class=\"c-red\">*</span>单价：</label>\r\n");
      out.write("            <div class=\"formControls col-xs-8 col-sm-9\">\r\n");
      out.write("                <input type=\"text\" class=\"input-text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${price}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\"\r\n");
      out.write("                       onkeyup=\"sum(this);\" placeholder=\"\" name=\"price\" id=\"price\" >\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"row cl\">\r\n");
      out.write("            <label class=\"form-label col-xs-4 col-sm-3\"><span class=\"c-red\">*</span>数量：</label>\r\n");
      out.write("            <div class=\"formControls col-xs-8 col-sm-9\">\r\n");
      out.write("                <input type=\"text\" class=\"input-text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${num}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\"\r\n");
      out.write("                       onkeyup=\"sum(this);\" placeholder=\"\" id=\"num\" name=\"num\">\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"row cl\">\r\n");
      out.write("            <label class=\"form-label col-xs-4 col-sm-3\">总价：</label>\r\n");
      out.write("            <div class=\"formControls col-xs-8 col-sm-9\">\r\n");
      out.write("                <input type=\"text\" class=\"input-text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${totalAmount}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\"\r\n");
      out.write("                       placeholder=\"\" id=\"totalAmount\" name=\"totalAmount\" style=\"border:0px solid white; width:200px\">\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"row cl\">\r\n");
      out.write("            <label class=\"form-label col-xs-4 col-sm-3\"></label>\r\n");
      out.write("            <div class=\"formControls col-xs-8 col-sm-9\">\r\n");
      out.write("                <span style=\"color:red;font-weight:bold;\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${msg}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</span>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"row cl\">\r\n");
      out.write("            <div class=\"col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3\">\r\n");
      out.write("                <input class=\"btn btn-primary radius\" type=\"submit\"\r\n");
      out.write("                       value=\"&nbsp;&nbsp;提交&nbsp;&nbsp;\">\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </form>\r\n");
      out.write("</article>\r\n");
      out.write("\r\n");
      out.write("<!--_footer 作为公共模版分离出去-->\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/static/H-ui.admin/lib/jquery/1.9.1/jquery.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/static/H-ui.admin/lib/layer/2.4/layer.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/static/H-ui.admin/static/h-ui/js/H-ui.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/static/H-ui.admin/static/h-ui.admin/js/H-ui.admin.js\"></script> <!--/_footer 作为公共模版分离出去-->\r\n");
      out.write("\r\n");
      out.write("<!--请在下方写此页面业务相关的脚本-->\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/static/H-ui.admin/lib/My97DatePicker/4.8/WdatePicker.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/static/H-ui.admin/lib/jquery.validation/1.14.0/jquery.validate.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/static/H-ui.admin/lib/jquery.validation/1.14.0/validate-methods.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/static/H-ui.admin/lib/jquery.validation/1.14.0/messages_zh.js\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/static/X-admin/lib/layui/layui.js\" charset=\"utf-8\"></script>\r\n");
      out.write("<script>\r\n");
      out.write("\r\n");
      out.write("    layui.use(['form', 'layedit', 'laydate'], function(){\r\n");
      out.write("        var form = layui.form\r\n");
      out.write("            ,layer = layui.layer\r\n");
      out.write("            ,layedit = layui.layedit\r\n");
      out.write("            ,laydate = layui.laydate;\r\n");
      out.write("\r\n");
      out.write("        //日期\r\n");
      out.write("        laydate.render({\r\n");
      out.write("            elem: '#buyDate'\r\n");
      out.write("        });\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    });\r\n");
      out.write("</script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("    $(\"#objForm\").validate({\r\n");
      out.write("        rules:{\r\n");
      out.write("            purchaseId:{\r\n");
      out.write("                required:true,\r\n");
      out.write("                isDigits:true,\r\n");
      out.write("            },\r\n");
      out.write("            pName:{\r\n");
      out.write("                required:true,\r\n");
      out.write("                stringCheck:true,\r\n");
      out.write("            },\r\n");
      out.write("            buyDate:{\r\n");
      out.write("                required:true,\r\n");
      out.write("                //        date:true\r\n");
      out.write("            },\r\n");
      out.write("            gid:{\r\n");
      out.write("                required:true,\r\n");
      out.write("                isDigits:true,\r\n");
      out.write("            },\r\n");
      out.write("            price:{\r\n");
      out.write("                required:true,\r\n");
      out.write("                isFloatGtZero:true,\r\n");
      out.write("            },\r\n");
      out.write("            num:{\r\n");
      out.write("                required:true,\r\n");
      out.write("                isFloatGtZero:true,\r\n");
      out.write("            },\r\n");
      out.write("            totalAmount:{\r\n");
      out.write("                required:true,\r\n");
      out.write("                isFloatGtZero:true,\r\n");
      out.write("            }\r\n");
      out.write("        },\r\n");
      out.write("        messages:{\r\n");
      out.write("            purchaseId:{\r\n");
      out.write("                required:\"单号必填\",\r\n");
      out.write("            },\r\n");
      out.write("            pName:{\r\n");
      out.write("                required:\"供应商必填\",\r\n");
      out.write("            },\r\n");
      out.write("            buyDate:{\r\n");
      out.write("                required:\"购入日期必填\",\r\n");
      out.write("                //        date:true\r\n");
      out.write("            },\r\n");
      out.write("            gid:{\r\n");
      out.write("                required:\"商品必填\",\r\n");
      out.write("            },\r\n");
      out.write("            price:{\r\n");
      out.write("                required:\"购入单价必填\",\r\n");
      out.write("            },\r\n");
      out.write("            num:{\r\n");
      out.write("                required:\"购入数量必填\",\r\n");
      out.write("            },\r\n");
      out.write("            totalAmount:{\r\n");
      out.write("                required:\"总价必填\",\r\n");
      out.write("            }\r\n");
      out.write("        },\r\n");
      out.write("        onkeyup:false,\r\n");
      out.write("        focusCleanup:true,\r\n");
      out.write("        success:\"valid\",\r\n");
      out.write("        submitHandler:function(form){\r\n");
      out.write("            form.submit();\r\n");
      out.write("        }\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    $(function(){\r\n");
      out.write("        $('.skin-minimal input').iCheck({\r\n");
      out.write("            checkboxClass: 'icheckbox-blue',\r\n");
      out.write("            radioClass: 'iradio-blue',\r\n");
      out.write("            increaseArea: '20%'\r\n");
      out.write("        });\r\n");
      out.write("\r\n");
      out.write("        $(\"#form-purchase-add\").validate({\r\n");
      out.write("            rules:{\r\n");
      out.write("                username:{\r\n");
      out.write("                    required:true,\r\n");
      out.write("                    minlength:2,\r\n");
      out.write("                    maxlength:16\r\n");
      out.write("                },\r\n");
      out.write("                sex:{\r\n");
      out.write("                    required:true,\r\n");
      out.write("                },\r\n");
      out.write("                mobile:{\r\n");
      out.write("                    required:true,\r\n");
      out.write("                    isMobile:true,\r\n");
      out.write("                },\r\n");
      out.write("                email:{\r\n");
      out.write("                    required:true,\r\n");
      out.write("                    email:true,\r\n");
      out.write("                },\r\n");
      out.write("                uploadfile:{\r\n");
      out.write("                    required:true,\r\n");
      out.write("                },\r\n");
      out.write("\r\n");
      out.write("            },\r\n");
      out.write("            onkeyup:false,\r\n");
      out.write("            focusCleanup:true,\r\n");
      out.write("            success:\"valid\",\r\n");
      out.write("            submitHandler:function(form){\r\n");
      out.write("                //$(form).ajaxSubmit();\r\n");
      out.write("                var index = parent.layer.getFrameIndex(window.name);\r\n");
      out.write("                //parent.$('.btn-refresh').click();\r\n");
      out.write("                parent.layer.close(index);\r\n");
      out.write("            }\r\n");
      out.write("        });\r\n");
      out.write("    });\r\n");
      out.write("</script>\r\n");
      out.write("<!--/请在上方写此页面业务相关的脚本-->\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
