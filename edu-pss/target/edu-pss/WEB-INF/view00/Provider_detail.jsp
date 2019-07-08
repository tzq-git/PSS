<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/X-admin/lib/layui/css/layui.css"  media="all">


    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
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
    <title>供应商查看</title>
</head>
<body>


<div class="layui-form">
    <table class="layui-table">
        <colgroup>
            <col width="150">
            <col width="250">

        </colgroup>
        <thead>
        <tr>
            <th>信息</th>
            <th>内容</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th>ID:</th>
            <th>${bean.pid}</th>
        </tr>
        <tr>
            <th>供应商:</th>
            <th>${bean.pName}</th>
        </tr>
        <tr>
            <th>法人:</th>
            <th>${bean.legalRepresentative}</th>
        </tr>
        <tr>
            <th>负责人:</th>
            <th>${bean.contactPerson}</th>
        </tr>
        <tr>
            <th>公司传真:</th>
            <th>${bean.companyFax}</th>
        </tr>
        <tr>
            <th>公司联系电话:</th>
            <th>${bean.companyTel}</th>
        </tr>
        <tr>
            <th>公司地址:</th>
            <th>${bean.providerAddress}</th>
        </tr>
        <tr>
            <th>备注:</th>
            <th>${bean.notes}</th>
        </tr>

        </tbody>
    </table>
    <div align="center" class="row cl">
        <input id="close" type="button"class="btn btn-primary radius"
               value="关闭&nbsp;&nbsp;"></div>
    </div>

</div>


<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->
<script src="${pageContext.request.contextPath}/static/X-admin/lib/layui/layui.js" charset="utf-8"></script>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
    $(function(){
        $("#close").click(function(){
            layer_close();
        });
    });

</script>
</body>
</html>
