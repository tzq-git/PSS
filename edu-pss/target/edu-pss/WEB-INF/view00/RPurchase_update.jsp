
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

    <title>添加用户 - H-ui.admin v3.1</title>
    <meta name="keywords" content="退货列表">
    <meta name="description" content="退货插入">
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" id="objForm" action="RPurchase"
          method="post">
        <input type="hidden" name="oper" value="updateDeal"/>
        <input type="hidden" name="rPurchaseId" value="${rPurchaseId}" />
        <input type="hidden" name="id" value="${rPurchaseId}" />
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>供应商：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${pName}"
                       placeholder="" id="pName" name="pName">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>退货日期：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${outDate}"
                       placeholder="" id="outDate" name="outDate">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商品：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${gid}"
                       placeholder="" id="gid" name="gid">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>单价：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${price}"
                       onkeyup="sum(this);" placeholder="" name="price" id="price">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>数量：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${num}"
                       onkeyup="sum(this);" placeholder="" id="num" name="num">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">总价：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${totalAmount}"
                       placeholder="" id="totalAmount" name="totalAmount" style="border:0px solid white; width:200px">
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
<script src="${pageContext.request.contextPath}/static/X-admin/lib/layui/layui.js" charset="utf-8"></script>
<script>

    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;

        //日期
        laydate.render({
            elem: '#outDate'
        });


    });
</script>
<script type="text/javascript">

    $("#objForm").validate({
        rules:{
            rPurchaseId:{
                required:true,
                isDigits:true,
            },
            pName:{
                required:true,
                stringCheck:true,
            },
            outDate:{
                required:true,
                //        date:true
            },
            gid:{
                required:true,
                isDigits:true,
            },
            price:{
                required:true,
                isFloatGtZero:true,
            },
            num:{
                required:true,
                isFloatGtZero:true,
            },
            totalAmount:{
                required:true,
                isFloatGtZero:true,
            }
        },
        messages:{

            pName:{
                required:"供应商必填",
            },
            outDate:{
                required:"退货日期必填",
                //        date:true
            },
            gid:{
                required:"商品必填",
            },
            price:{
                required:"退货单价必填",
            },
            num:{
                required:"退货数量必填",
            },
            totalAmount:{
                required:"总价必填",
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

