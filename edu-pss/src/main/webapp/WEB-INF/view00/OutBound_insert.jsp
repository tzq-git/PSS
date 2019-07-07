<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
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
		<link rel="Bookmark" href="/favicon.ico" >
		<link rel="Shortcut Icon" href="/favicon.ico" />
		<!--[if lt IE 9]>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/html5shiv.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/respond.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/My97DatePicker/4.8/WdatePicker.js"></script>
		<![endif]-->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui/css/H-ui.min.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/css/H-ui.admin.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/H-ui.admin/lib/Hui-iconfont/1.0.8/iconfont.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/skin/default/skin.css" id="skin" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/css/style.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/layui/css/layui.css"  media="all">
		<!--[if IE 6]>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
		<script>DD_belatedPNG.fix('*');</script>
		<![endif]-->
		<!--计算单价数量总额并实时更新-->
		<script type="text/javascript">
            function sum(obj) {
                var a = document.getElementById("price");
                var b = document.getElementById("num");
                if(a.value!=''&&b.value!='')
                {
                    totalAmount.value=(parseFloat(b.value)*parseFloat(a.value)).toFixed(1);
                }

            }
		</script>
		<!--/meta 作为公共模版分离出去-->

		<title>添加出库单</title>
		<meta name="keywords" content="">
		<meta name="description" content="">
	</head>
	<body>
		<article class="page-container">
			<form action="OutBound" class="form form-horizontal" id="objForm">
				<input type="hidden" name="oper" value="insertDeal" />
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*
			</span>出库单号：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text" value="${orderId}"
							   placeholder="" id="orderId" name="orderId">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*
			</span>客户名：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text" value="${cName}"
							   placeholder="" id="cName" name="cName">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*
			</span>负责销售员：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text" value="${salesMan}"
							   placeholder="" id="salesMan" name="salesMan">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*
			</span>购买商品：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text" value="${gName}"
							   placeholder="" id="gName" name="gName">
					</div>
				</div>

				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*
			</span>单价：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text" value="${price}"
							   onkeyup="sum(this);" placeholder="" id="price" name="price">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*
			</span>数量：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text" value="${num}"
							   onkeyup="sum(this);" placeholder="" id="num" name="num">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*
			</span>总金额：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text" value="${amount}"
							   placeholder="" id="totalAmount" name="amount" style="border:0px solid white; width:200px">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*
			</span>送货地址：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text" value="${address}"
							   placeholder="" id="address" name="address">
					</div>
				</div>

				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*
			</span>交易日期：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text"
							   value='<fmt:formatDate value="${dealDate}" pattern="yyyy-MM-dd hh:mm:ss"/>'
							   onfocus="WdatePicker({ dateFmt:'yyyy-MM-dd HH:mm:ss' })"
							   placeholder="" id="dealDate" name="dealDate">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*
			</span>出库日期：</label>
						<div class="formControls col-xs-8 col-sm-9">
							<input type="text" class="input-text"
								   value='<fmt:formatDate value="${outDate}" pattern="yyyy-MM-dd hh:mm:ss"/>'
								   onfocus="WdatePicker({ dateFmt:'yyyy-MM-dd HH:mm:ss' })"
								   id="outDate" name="outDate" class="input-text">
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
							   value="&nbsp;&nbsp;提交&nbsp;&nbsp;" onclick="check()">
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
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/layui/layui.js" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/static/X-admin/lib/layui/layui.js" charset="utf-8"></script>

		<script>
			$("#objForm").validate({
				rules:{
					orderId:{
						required:true,
                        isDigits:true,
                    },
					cName:{
						required:true,
                        stringCheck:true,
					},
                    salesMan:{
                        required:true,
                        stringCheck:true,
                    },
                    gName:{
                        required:true,
                        stringCheck:true,
                    },

                    price:{
                        required:true,
                        isFloatGtZero:true,
                    },
                    num:{
                        required:true,
                        isFloatGtZero:true,
                    },
                    amount:{
                        required:true,
                        isFloatGtZero:true,
                    },
					address: {
						required: true,
                        stringCheck:true,
					},
                    dealDate:{
                        required:true,
                    },
                    outDate:{
                        required:true,
                    }
				},
				messages:{
					orderId:{
						required:"出库单号必填",
					},
					cName:{
						required:"客户名必填",
					},
					address: {
						required:"送货地址必填",
					}
				},
				onkeyup:false,
				focusCleanup:true,
				success:"valid",
				submitHandler:function(form){
					form.submit();
				}
			});
			// layui.use('laydate', function(){
			// 	var laydate = layui.laydate;
			// 	laydate.render({
			// 		elem: '#outDate'
			// 		,type: 'datetime'
			// 	});
			// });
		</script>
		<!--/请在上方写此页面业务相关的脚本-->
	</body>
</html>