<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<link rel="Bookmark" href="/favicon.ico" >
		<link rel="Shortcut Icon" href="/favicon.ico" />
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
		<!--/meta 作为公共模版分离出去-->

		<title>添加用户</title>
		<meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
		<meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
	</head>
	<body>
		<article class="page-container">
			<form action="User" class="form form-horizontal" id="objForm" method="post">
				<input type="hidden" name="oper" value="insertDeal" />
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*
			</span>用户名：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text" value="${userName}"
							   placeholder="" id="userName" name="userName">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*
			</span>密码：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="password" class="input-text" value="${userPwd}"
							   placeholder="" id="userPwd" name="userPwd">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*
			</span>确认密码：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="password" class="input-text" value="${userPwd2}"
							   placeholder="" id="userPwd2" name="userPwd2">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*
			</span>用户类型：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<select id="type" name="type" >
							<option value="1">普通用户</option>
							<option value="0">超级管理员</option>
						</select>
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*
			</span>联系电话：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text" value="${telPhone}"
							   placeholder="" id="telPhone" name="telPhone">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*
			</span>电子邮箱：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text" value="${email}"
							   placeholder="" id="email" name="email">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"></label>
					<div class="formControls col-xs-8 col-sm-9">
						<span style="color:red;font-weight:bold;">${msg}</span>
					</div>
				</div>
				<div class="row cl">
					<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
						<input class="btn btn-primary radius" type="submit"
							   value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
					</div>
				</div>
			</form>
		</article>

		<!--_footer 作为公共模版分离出去-->
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/layer/2.4/layer.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui/js/H-ui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

		<!--请在下方写此页面业务相关的脚本-->
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/My97DatePicker/4.8/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/jquery.validation/1.14.0/validate-methods.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/jquery.validation/1.14.0/messages_zh.js"></script>
		<script>
			$("#objForm").validate({
				rules:{
					userName:{
						required:true,
						minlength:3,
						maxlength:16,
					},
					userPwd:{
						required:true,
						minlength:6,
						maxlength:20,
					},
					userPwd2:{
						required:true,
						equalTo:"#userPwd"
					},
					type:{
						required:true,
					},
					telPhone:{
						required:true,
						isMobile:true,
					},
					email:{
						required:true,
						email:true,
					}
				},
				messages:{
					userName:{
						required:"用户名必填",
					},
					userPwd:{
						required:"密码必填",
					},
					userPwd2:{
						required:"确认密码必填",
						equalTo:"两次密码不一致"
					},
					type:{
						required:"权限必填",
					},
					telPhone:{
						required:"联系电话必填",
						isMobile:"号码格式不正确",
					},
					email:{
						required:"电子邮箱必填",
						email:"邮箱格式不正确",
					}
				},
				onkeyup:false,
				focusCleanup:true,
				success:"valid",
				submitHandler:function(form){
					form.submit();
				}
			});
		</script>
		<!--/请在上方写此页面业务相关的脚本-->
	</body>
</html>