<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
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


    <%--<script type="text/javascript">--%>
        <%--function myfun(){--%>
     <%--//       var str = ${isnull};--%>
            <%--alert("共查到0条记录");--%>
      <%--/*      if(str == "yes"){--%>
                <%--alert("未查询到记录，清重新查询");--%>
                <%--location.href = "GSearch?oper=form";--%>
            <%--}*/--%>

        <%--}--%>
        <%--/*用window.onload调用myfun()*/--%>

        <%--// 不要括号--%>
        <%--window.onload = myfun;--%>
    <%--</script>--%>


    <title>商品列表</title>
</head>
<body>
<nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en"></span>
    采购维护 <span class="c-gray en">&gt;</span>库存查询 <a
        class="btn btn-success radius r"
        style="..."
        href="GSearch?oper=form" title="刷新" ><i
        class="Hui-iconfont">&#xe68f;</i>重新查询 </a>
</nav>
<div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="r">共有数据：<strong>${pagerItem.rowCount}</strong>
            条
        </span>
    </div>
    <div class="mt-20">
        <table id="datalist"  class="table table-border table-bordered  table-bg ">
            <thead>
            <tr>
                <th scope="col" colspan="10">商品列表</th>
            </tr>

            <tr class="text-c">
                <th width="25"><input type="checkbox" name="" value=""></th>
                <th width="40">ID</th>
                <th width="100">商品名称</th>
                <th width="100">安全存量</th>
                <th width="100">现有存量</th>
                <th width="100">购入价格</th>
                <th width="100">出售价格</th>
                <th width="100">最后购入时间</th>
                <th width="100">最后售出时间</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${DataList }">
                <tr class="text-c">
                    <td><input type="checkbox" value="${item.gid }" name=""></td>
                    <td>${item.gid }</td>
                    <td>${item.gName }</td>
                    <td>${item.saveStock }</td>
                    <td>${item.nowStock }</td>
                    <td>${item.buyPrice }</td>
                    <td>${item.price }</td>
                    <td>${item.lastBuyTime }</td>
                    <td>${item.lastSaleTime }</td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
        <jsp:include page="__pager.jsp" flush="true"/>
    </div>
</div>+
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">

    $(function(){
        $("#clearSearch").click(function(){
            location.href = "Goods?oper=list";
        });
    });

    $(function(){
        $('.table-sort').dataTable({
            "aaSorting": [[ 1, "desc" ]],//默认第几个排序
            "bStateSave": true,//状态保存
            "aoColumnDefs": [
                //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
                {"orderable":false,"aTargets":[0,8,9]}// 制定列不参与排序
            ]
        });

    });
    /*项目-添加*/
    function item_add(title,url,w,h){
        layer_show(title,url,w,h);
    }
    /*用户-查看*/
    function goods_show(title,url,id,w,h){
        layer_show(title,url,w,h);
    }
    /*用户-停用*/
    function goods_stop(obj,id){
        layer.confirm('确认要停用吗？',function(index){
            $.ajax({
                type: 'POST',
                url: '',
                dataType: 'json',
                success: function(data){
                    $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="goods_start(this,id)" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
                    $(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
                    $(obj).remove();
                    layer.msg('已停用!',{icon: 5,time:1000});
                },
                error:function(data) {
                    console.log(data.msg);
                },
            });
        });
    }

    /*用户-启用*/
    function goods_start(obj,id){
        layer.confirm('确认要启用吗？',function(index){
            $.ajax({
                type: 'POST',
                url: '',
                dataType: 'json',
                success: function(data){
                    $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="goods_stop(this,id)" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>');
                    $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
                    $(obj).remove();
                    layer.msg('已启用!',{icon: 6,time:1000});
                },
                error:function(data) {
                    console.log(data.msg);
                },
            });
        });
    }
    /*项目-编辑*/
    function goods_edit(title,url,id,w,h){
        layer_show(title,url,w,h);
    }
    /*密码-修改*/
    function change_password(title,url,id,w,h){
        layer_show(title,url,w,h);
    }
    /*项目-删除*/
    function item_del(obj,id){
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                type: 'POST',
                url: 'Goods?oper=deleteDeal&id=' + id,
                //    dataType: 'json',
                success: function(data){
                    if(data=="ok"){
                        $(obj).parents("tr").remove();
                        layer.msg('已删除!',{icon:1,time:1000});

                    } else{
                        layer.msg('删除失败', {
                            icon :1,
                            tiem :1000
                        });
                    }
                },
                error:function(data) {
                    console.log(data.msg);
                },
            });
        });
    }

    function datadel() {
        layer.confirm('确认要删除选中的数据吗' , function (index) {
            var num = 0;
            var total =0;
            var obj = null;
            var id = 0;
            $("#datalist input[type=checkbox]:checked").each(function () {
                obj = this;
                id = $(this).val();

                if(id != null && id != "" && id != 0){
                    total++;
                    $.ajax({
                        type : 'POST',
                        url : 'Goods',
                        async : false,
                        data :{"oper":"deleteDeal", "id":id},

                        success :function (data) {
                            if(data=="ok"){
                                $(obj).parents("tr").remove();
                                num++;
                            }else{

                            }

                        },
                        error : function (data) {

                        },
                    });
                }
            });
            layer.msg("要删除" + total + "行记录，成功删除" + num + "行记录。",{
                icon :1,
                time : 1000
            })
        });
    }
    function item_edit(title ,url, id, w, h) {
        layer_show(title,url,id,w,h);
    }

    function item_detail(title ,url, id, w, h) {
        layer_show(title,url,id,w,h);
    }

</script>

</body>
</html>
