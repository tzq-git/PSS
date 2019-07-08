<%--
  Created by IntelliJ IDEA.
  User: Tzq
  Date: 2019/7/6
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>信息下载</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/X-admin/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/X-admin/css/xadmin.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/X-admin/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/X-admin/js/xadmin.js"></script>

    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/html5shiv.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/H-ui.admin/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <style>
    body{
        text-align:center;
        background:url(${pageContext.request.contextPath}/static/background.jpg)  no-repeat center center;
        background-size:cover;
        background-attachment:fixed;
        background-color:#CCCCCC;
    }
    </style>
</head>
<body>
<table id="datalist"  class="table table-border table-bordered  table-bg ">
    <thead>
        <tr>
            <div class="div"><h1>数据下载界面</h1></div>
        </tr>
    </thead>
    <tbody>
        <tr class="text-c">
        <td>
            <h4>商品资料下载</h4>
        </td>
            <td>
                <p class="btn btn-primary radius"><a
                        href="Download?type=goods&page=0&name=商品信息"
                ><i class="Hui-iconfont">&#xe640;</i>
                    下载所有商品信息</a></p>
            </td>
            <td>
                <label>按数量下载：</label>
                <span class="select-box inline">
                    <select name="number" id="goods" class="select">
                        <option value="500">前500条</option>
                        <option value="1000">前1000条</option>
                        <option value="3000">前3000条</option>
                    </select>
                </span>
                <p class="btn btn-primary radius"><a
                        href="javascript:;"
                        onclick="goods()"
                ><i class="Hui-iconfont">&#xe640;</i>
                    下载</a></p>
            </td>
            <td>
                <p class="btn btn-primary radius"><a
                        href="Download?type=goodsWarning&name=库存预警"
                ><i class="Hui-iconfont">&#xe640;</i>
                    商品预警信息下载</a></p>
            </td>
        </tr>

        <tr class="text-c">
        <td>
            <h4>采购报表下载</h4>
        </td>
            <td>
                <p class="btn btn-primary radius"><a
                        href="Download?type=purchase&page=0&name=采购报表"
                ><i class="Hui-iconfont">&#xe640;</i>
                    下载所有采购报表</a></p>
            </td>
            <td>
                <label>按数量下载：</label>
                <span class="select-box inline">
                    <select name="number" id="purchase" class="select">
                        <option value="500">前500条</option>
                        <option value="1000">前1000条</option>
                        <option value="3000">前3000条</option>
                    </select>
                </span>
                <p class="btn btn-primary radius"><a
                        href="javascript:;"
                        onclick="purchase()"
                ><i class="Hui-iconfont">&#xe640;</i>
                    下载</a></p>
            </td>
            <td>
                <label>按年份下载：</label>
                <span class="select-box inline">
                    <select name="year" id='year' class="select">
                        <option value="2019">2019</option>
                        <option value="2018">2018</option>
                        <option value="2017">2017</option>
                    </select>
                </span>
                <p class="btn btn-primary radius"><a
                        href="javascript:;"
                        onclick="year()"
                ><i class="Hui-iconfont">&#xe640;</i>
                    下载</a></p>
            </td>
        </tr>
    </tbody>
</table>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/X-admin/js/jquery.min.js"></script>
<script>
    function goods() {
        window.open('Download?type=goods&name=商品资料&number='+$('#goods').val());
    }
    function purchase() {
        window.open('Download?type=purchase&name=采购报表&number='+$('#purchase').val());
    }
    function year() {
        window.open('Download?type=yearPurchase&name=年度采购报表&year='+$('#year').val());
    }
</script>
</body>
</html>
