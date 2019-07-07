
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html  class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>用户登录-X-admin2.2</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/X-admin/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/X-admin/css/login.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/X-admin/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/X-admin/lib/layui/layui.js" charset="utf-8"></script>
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="login-bg">

<div class="login layui-anim layui-anim-up">
    <div class="message">进销存管理登录</div>
    <div id="darkbannerwrap"></div>

    <form action="Login" method="post" class="layui-form" >
        <input type="hidden" name="oper" value="logindeal"/>
<%--        <div class="row cl">${msg}</div>--%>
        <input id="userName" name="userName" value="${userName}" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" >
        <br>
        <input id="userPwd" name="userPwd" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
        <br>
        <input id="validateCode" name="validateCode" class="input-text size-L" type="text" placeholder="验证码"
               onblur="if(this.value==''){this.value='验证码:'}"
               onclick="if(this.value=='验证码:'){this.value='';}"
               value="验证码:" style="width:150px;">
        <img id="validateCodeImg" src="">
        <a id="kanbuq" href="javascript:changeValidateCode();">看不清，换一张</a>
        <br>
        <br>
        <input name="" type="submit" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
        <br>

    </form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/X-admin/lib/layui/lay/modules/form.js"></script>
<script>
    if ( new String("${msg}").length>0 ){
        alert("${msg}");
    }
    $(function  () {
        layui.use('form', function(){
            var form = layui.form;
            // layer.msg('玩命卖萌中', function(){
            //   //关闭后的操作
            //   });
            //监听提交
            form.on('submit(login)', function(data){
                // alert(888)
                layer.msg(JSON.stringify(data.field),function(){
                    location.href='Main.jsp'
                });
                return false;
            });
        });
    })
</script>
<!-- 底部结束 -->

<script type="text/javascript">
    function changeValidateCode(){

        var timenow = new Date().getTime();

        $("#validateCodeImg").attr("src", "ValidateCode?d=" + timenow);

    }

    $(function() {
        changeValidateCode();
    });
</script>
<script type="text/javascript">
    if(window!=top){
        top.location.href=location.href;
    }
</script>
</body>
</html>
