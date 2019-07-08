<%--
  Created by IntelliJ IDEA.
  User: Tzq
  Date: 2019/7/3
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.2</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/X-admin/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/X-admin/css/xadmin.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/X-admin/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/X-admin/js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link id="layuicss-layer" rel="stylesheet" href="http://x.xuebingsi.com/x-admin/v2.2/lib/layui/css/modules/layer/default/layer.css?v=3.1.1" media="all"></head>

<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form action="User" class="layui-form" id="objForm">
            <input type="hidden" name="oper" value="resetDeal" />
            <input type="hidden" name="id" value="${id}" />
            <input type="hidden" name="type" value="reset" />
            <div class="layui-form-item">
                <label for="userName" class="layui-form-label">用户名称</label>
                <div class="layui-input-inline">
                    <input type="text" id="userName" name="userName" disabled="true"
                           value="${userName}" class="layui-input"></div>
            </div>
            <div class="layui-form-item">
                <label for="newpass" class="layui-form-label">
                    <span class="x-red">*</span>新密码</label>
                <div class="layui-input-inline">
                    <input type="password" id="newpass" name="newpass" required="" lay-verify="required" autocomplete="off" class="layui-input"></div>
                <div class="layui-form-mid layui-word-aux">6到16个字符</div></div>
            <div class="layui-form-item">
                <label for="repass" class="layui-form-label">
                    <span class="x-red">*</span>确认密码</label>
                <div class="layui-input-inline">
                    <input type="password" id="repass" name="repass" required="" lay-verify="required" autocomplete="off" class="layui-input"></div>
            </div>
            <div class="row cl">
                <label class="layui-form-item"></label>
                <div class="layui-input-inline">
                    <span style="color:red;font-weight:bold;">${msg}</span>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="repass" class="layui-form-label"></label>
                <button class="layui-btn">确认提交</button></div>
        </form>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/js/H-ui.admin.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/md5.js"></script>
<script>

    function MD5() {
        var newpwd = $('#newpass');
        var repwd = $('#repass');
        var hash1 = hex_md5(newpwd.val());
        var hash2 = hex_md5(repwd.val());
        newpwd.val(hash1);
        repwd.val(hash2);
    }
</script>

<script>
    $("#objForm").validate({
        rules:{
            newpass:{
                required:true,
                minlength:6,
                maxlength:20,
            },
            repass:{
                required:true,
                equalTo:"#newpass"
            }
        },
        messages:{
            newpass:{
                required:"新密码必填",
            },
            repass:{
                required:"确认密码必填",
                equalTo:"两次密码不一致"
            }
        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            MD5();
            form.submit();
        }
    });
</script>
<script>
    // layui.use(['form', 'layer'],
    // function() {
    //     $ = layui.jquery;
    //     var form = layui.form,
    //         layer = layui.layer;
    //
    //     //监听提交
    //     form.on('submit(save)',
    //         function(data) {
    //             console.log(data);
    //             //发异步，把数据提交给php
    //             layer.alert("修改成功", {
    //                     icon: 6
    //                 },
    //                 function() {
    //                     // 获得frame索引
    //                     var index = parent.layer.getFrameIndex(window.name);
    //                     //关闭当前frame
    //                     parent.layer.close(index);
    //                 });
    //             return false;
    //         });
    //
    // });
</script>
</body>
</html>
