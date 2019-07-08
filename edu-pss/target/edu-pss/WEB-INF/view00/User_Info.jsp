<%--
  Created by IntelliJ IDEA.
  User: Tzq
  Date: 2019/7/6
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>个人详细信息</title>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
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

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/X-admin/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/X-admin/css/xadmin.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/X-admin/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/X-admin/js/xadmin.js"></script>

    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link id="layuicss-layer" rel="stylesheet"
          href="http://x.xuebingsi.com/x-admin/v2.2/lib/layui/css/modules/layer/default/layer.css?v=3.1.1" media="all">
    <style>
        body{ text-align:center}
        #div{margin:0 auto;width:300px;height:30px}
    </style>

</head>
<div  style="align:center">
<table class="layui-table" >
    <colgroup>
        <col width="150">
        <col width="250">
    </colgroup>
    <tbody>
    <tr>
        <th>用户ID:</th>
        <th>${user.userId}</th>
    </tr>
    <tr>
        <th>用户名:</th>
        <th>${user.userName}</th>
    </tr>
    <tr>
        <th>用户类型:</th>
        <th>
            <c:choose>
                <c:when test="${user.type=='0'}" >
                    <span class="label label-success radius" >超级管理员</span>
                </c:when>
                <c:otherwise>
                    <span class="label label-secondary radius">普通用户</span>
                </c:otherwise>
            </c:choose>
        </th>
    </tr>
    <tr>
        <th>联系电话:</th>
        <th>${user.telPhone}</th>
    </tr>
    <tr>
        <th>电子邮箱:</th>
        <th>${user.email}</th>
    </tr>
    </tbody>
</table>
</div>
<div class="layui-fluid">
    <div class="layui-row">
        <form class="layui-form layui-form-pane">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">
                    拥有权限
                </label>
                <table class="layui-table layui-input-block">
                    <tbody>
                    <tr>
                        <td>
                            <label class="layui-form-label">
                                资料管理
                            </label>
                        </td>
                        <td>
                            <div class="layui-input-block">
                                <input name="haveSaleMan" lay-skin="primary" type="checkbox"
                                       value="${authority.haveSaleMan}"title="销售员资料" >
                                <div class="layui-unselect layui-form-checkbox" lay-skin="primary"><span>销售员资料</span><i
                                        class="layui-icon layui-icon-ok"></i></div>
                                <input name="haveGoods" lay-skin="primary" type="checkbox"
                                       value="${authority.haveGoods}" title="商品资料">
                                <div class="layui-unselect layui-form-checkbox" lay-skin="primary"><span>商品资料</span><i
                                        class="layui-icon layui-icon-ok"></i></div>
                                <input name="haveCustomer" lay-skin="primary" type="checkbox"
                                       value="${authority.haveCustomer}" title="客户资料">
                                <div class="layui-unselect layui-form-checkbox" lay-skin="primary"><span>客户资料</span><i
                                        class="layui-icon layui-icon-ok"></i></div>
                                <input name="haveSupplier" lay-skin="primary" type="checkbox"
                                       value="${authority.haveSupplier}" title="供应商资料">
                                <div class="layui-unselect layui-form-checkbox" lay-skin="primary"><span>供应商资料</span><i
                                        class="layui-icon layui-icon-ok"></i></div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="layui-form-label">
                                采购管理
                            </label>
                        </td>
                        <td>
                            <div class="layui-input-block">
                                <input name="havePurchaseIn" lay-skin="primary" type="checkbox"
                                       value="${authority.havePurchaseIn}" title="采购入库">
                                <div class="layui-unselect layui-form-checkbox" lay-skin="primary"><span>采购入库</span><i
                                        class="layui-icon layui-icon-ok"></i></div>
                                <input name="havePurchaseOut" lay-skin="primary" type="checkbox"
                                       value="${authority.havePurchaseOut}" title="采购出库">
                                <div class="layui-unselect layui-form-checkbox" lay-skin="primary"><span>采购出库</span><i
                                        class="layui-icon layui-icon-ok"></i></div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="layui-form-label">
                                销售管理
                            </label>
                        </td>
                        <td>
                            <div class="layui-input-block">
                                <input name="haveSaleOut" lay-skin="primary" type="checkbox"
                                       value="${authority.haveSaleOut}" title="销售出库">
                                <div class="layui-unselect layui-form-checkbox" lay-skin="primary"><span>销售出库</span><i
                                        class="layui-icon layui-icon-ok"></i></div>
                                <input name="haveSaleReturn" lay-skin="primary" type="checkbox"
                                       value="${authority.haveSaleReturn}" title="销售退货">
                                <div class="layui-unselect layui-form-checkbox" lay-skin="primary"><span>销售退货</span><i
                                        class="layui-icon layui-icon-ok"></i></div>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="layui-form-item layui-form-text">
                <label for="description" class="layui-form-label">
                    描述
                </label>
                <div class="layui-input-block">
                    <textarea disabled="true" placeholder="请输入内容" id="description" name="description"
                              class="layui-textarea">${authority.description}</textarea>
                </div>
            </div>
            <div class="layui-form-item" id="div">
                <button class="layui-btn" lay-submit="" lay-filter="add">关闭</button>
            </div>
        </form>
    </div>
</div>


<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/jquery/1.9.1/jquery.min.js"></script>
<script>
    $(function () {
        var checkeds = $('input[type=checkbox][value="1"]');
        $.each(checkeds, function () {
            $(this).attr("checked", true);
        });
        var checkboxs = $('input[type=checkbox]');
        $.each(checkboxs, function () {
            $(this).attr("disabled", true);
        });
    });
    layui.use(['form', 'layer'], function () {
        $ = layui.jquery;
        var form = layui.form
            , layer = layui.layer;

        form.on('submit(add)', function (data) {
            // 获得frame索引
            var index = parent.layer.getFrameIndex(window.name);
            //关闭当前frame
            parent.layer.close(index);
            return true;
        })
    });
</script>
<script type="text/javascript">
    $(function () {
        $("#close").click(function () {
            layer_close();
        });
    })
</script>
</body>
</html>
