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
    <style>

        .dropdown {
            position: relative;
            display: inline-block;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            /*background-color: #f9f9f9;*/
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(255, 255, 255, 0);
            padding: 12px 16px;
            z-index: 1;
        }

        .dropdown:hover .dropdown-content {
            display: block;
        }
    </style>
    <title>购入列表</title>
</head>
<body>
<nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en"></span>
    采购维护 <span class="c-gray en">&gt;</span> 购入管理 <a
        class="btn btn-success radius r"
        style="..."
        href="javascript:location.replace(location.href);" title="刷新" ><i
        class="Hui-iconfont">&#xe68f;</i></a>
    </nav>
<div class="page-container">
    <div class="text-c">
        <form action="Purchase" method="get">
            <input type="hidden" name="oper" value="listDeal" />
            <span>查询：</span>
            <input type="text" class="input-text" value="${searchName }"
                   style="width: 250px" placeholder="供应商"
                   id="searchName" name="searchName">
            <button type="submit" class="btn btn-success" id="" name="">
                <i class="Hui-iconfont">&#xe665;</i> 搜索
            </button>
            <button type="button" class="btn btn-success"
                    id="clearSearch" name="">
                <i class="Hui-iconfont">&#xe665;</i> 清空
            </button>
        </form>

    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <a href="javascript:;"
               onclick="datadel()"
               class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
                批量删除</a>

            <a href="javascript:;"
               onclick="item_add('添加','Purchase?oper=insert','800','500')"
               class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
                添加</a>
            <div class="btn btn-primary radius dropdown">
            <i class="Hui-iconfont">&#xe644;</i>
            <span>下载选项</span>
            <div class="dropdown-content">
                <p class="btn btn-primary radius"><a
                        href="Download?type=goods&name=采购清单&page=${pagerItem.pageNum}&size=${pagerItem.pageSize}"
                ><i class="Hui-iconfont">&#xe640;</i>
                    下载当前页面</a></p>
                <p class="btn btn-primary radius"><a
                        href="Download?type=goods&page=0&name=采购清单"
                ><i class="Hui-iconfont">&#xe640;</i>
                    下载所有数据</a></p>
                <br>
            </div>
        </div>
        </span>

        <span class="r">共有数据：<strong>${pagerItem.rowCount}</strong>
            条
        </span>
    </div>
    <div class="mt-20">
        <table id="datalist"  class="table table-border table-bordered  table-bg ">
            <thead>
            <tr>
                <th scope="col" colspan="9">数据列表</th>
            </tr>

            <tr class="text-c">
                <th width="25"><input type="checkbox" name="" value=""></th>
                <th width="80">购入单号</th>
                <th width="100">供应商</th>
                <th width="150">购入日期</th>
                <th width="100">购入货物</th>
                <th width="80">单价</th>
                <th width="80">数量</th>
                <th width="80">总价</th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${DataList }">
                <tr class="text-c">
                    <td><input type="checkbox" value="${item.purchaseId }" name=""></td>
                    <td>${item.purchaseId }</td>
                    <td>${item.pName }</td>
                    <td>${item.buyDate }</td>
                    <td>${item.gName }</td>
                    <td>${item.price }</td>
                    <td>${item.num }</td>
                    <td>${item.totalAmount }</td>
                    <td>
                        <a title="删除" href="javascript:;"
                           onclick="item_del(this,${item.purchaseId})" class="ml-5"
                           style="text-decoration: none"><i class="Hui-iconfont">&#xe6e2;</i>
                        </a>
                        <a title="编辑" href="javascript:;"
                           onclick="item_edit('编辑信息','Purchase?oper=update&id=${item.purchaseId}','600','600','500')"
                           class="ml-5" style="text-decoration: none"> <i
                                class="Hui-iconfont">&#xe6df;</i></a>
                        <a title="查看"
                           href="javascript:;"
                           onclick="item_detail('查看具体信息','Purchase?oper=detail&id=${item.purchaseId }','600','600','500')"
                           class="ml-5" style="text-decoration: none"> <i
                                class="Hui-iconfont">&#xe707;</i></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <jsp:include page="__pager.jsp" flush="true"/>
    </div>
</div>
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
            location.href = "Purchase?oper=list";
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
    function purchase_show(title,url,id,w,h){
        layer_show(title,url,w,h);
    }
    /*用户-停用*/
    function purchase_stop(obj,id){
        layer.confirm('确认要停用吗？',function(index){
            $.ajax({
                type: 'POST',
                url: '',
                dataType: 'json',
                success: function(data){
                    $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="purchase_start(this,id)" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
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
    function purchase_start(obj,id){
        layer.confirm('确认要启用吗？',function(index){
            $.ajax({
                type: 'POST',
                url: '',
                dataType: 'json',
                success: function(data){
                    $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="purchase_stop(this,id)" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>');
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
    function purchase_edit(title,url,id,w,h){
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
                url: 'Purchase?oper=deleteDeal&id=' + id,
            //    dataType: 'json',
                success: function(data){
                    if(data=="refuse"){
                        layer.msg('删除后库存小于0，不可删除!',{icon:1,time:1000});
                    }else if(data=="ok"){
                        $(obj).parents("tr").remove();
                        layer.msg('已删除!',{icon:1,time:2000});

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
                        url : 'Purchase',
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
