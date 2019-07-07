
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

    <title>添加商品 - H-ui.admin v3.1</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
</head>
<body>
<article class="page-container">
    <form action="Goods" class="form form-horizontal" id="objForm">
        <input type="hidden" name="oper" value="insertDeal" />
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商品名：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${gName}"
                       placeholder="" id="gName" name="gName">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>安全存量：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${saveStock}"
                       placeholder="" id="saveStock" name="saveStock">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>现有存量：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${nowStock}"
                       placeholder="" id="nowStock" name="nowStock">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>购入价格：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${buyPrice}"
                       placeholder="" id="buyPrice" name="buyPrice">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>售出价格：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${price}"
                       placeholder="" id="price" name="price">
            </div>
        </div>
        <%--<div class="row cl">--%>
            <%--<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>最后购入时间：</label>--%>
            <%--<div class="formControls col-xs-8 col-sm-9">--%>
                <%--<input type="date" class="input-text" value="${lastBuyTime}"--%>
                       <%--placeholder="" id="lastBuyTime" name="lastBuyTime">--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="row cl">--%>
            <%--<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>最后售出时间：</label>--%>
            <%--<div class="formControls col-xs-8 col-sm-9">--%>
                <%--<input type="date" class="input-text" value="${lastSaleTime}"--%>
                       <%--placeholder="" id="lastSaleTime" name="lastSaleTime">--%>
            <%--</div>--%>
        <%--</div>--%>

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
<script type="text/javascript">
    $("#objForm").validate({
        rules:{
            gName:{
                required:true,
                stringCheck:true,
            },
            saveStock:{
                required:true,
                isFloatGtZero:true,
            },
            nowStock:{
                required:true,
                isFloatGtZero:true,
            },
            buyPrice:{
                required:true,
                isFloatGtZero:true,
            },
            price:{
                required:true,
                isFloatGtZero:true,
            }
            //,
            // lastBuyTime:{
            //     required:true,
            // },
            // lastSaleTime:{
            //     required:true,
            // }
        },
        messages:{
            gName:{
                required:"商品名不能为空",
            },
            saveStock:{
                required:"安全存量不可为空",
            },
            nowStock:{
                required:"现有存量不能为空",
            },
            buyPrice:{
                required:"购入价格不能为空",
            },
            price:{
                required:"售出价格不能为空",
            }
            // ,
            // lastBuyTime:{
            //     required:"最后购入时间不能为空",
            // },
            // lastSaleTime:{
            //     required:"最后售出时间不能为空",
            // }
        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            form.submit();
        }
    });



    $(function(){
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });

        $("#form-member-add").validate({
            rules:{
                username:{
                    required:true,
                    minlength:2,
                    maxlength:16
                },
                sex:{
                    required:true,
                },
                mobile:{
                    required:true,
                    isMobile:true,
                },
                email:{
                    required:true,
                    email:true,
                },
                uploadfile:{
                    required:true,
                },

            },
            onkeyup:false,
            focusCleanup:true,
            success:"valid",
            submitHandler:function(form){
                //$(form).ajaxSubmit();
                var index = parent.layer.getFrameIndex(window.name);
                //parent.$('.btn-refresh').click();
                parent.layer.close(index);
            }
        });
    });
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>

