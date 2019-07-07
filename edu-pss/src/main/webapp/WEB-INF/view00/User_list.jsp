<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
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
		<title>会员列表</title>
	</head>
	<body>
		<nav class="breadcrumb">
			<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
			会员管理<span class="c-gray en">&gt;</span> 会员列表 <a
				class="btn btn-success radius r"
				style="line-height:1.6em;margin-top:3px"
				href="javascript:location.replace(location.href);" title="刷新" ><i
				class="Hui-iconfont">&#xe68f;</i></a>
		</nav>
		<div class="page-container">
			<div class="text-c">
				<form action="User" method="get">
					<input type="hidden" name="oper" value="listDeal" />
					<span> 名称：</span>
					<input type="text" class="input-txet" value="${searchName}"
						   style="..." placeholder="输入名称" id="searchName" name="searchName" >
					<button type="submit" class="btn btn-success radius" id="" name="">
						<i class="Hui-iconfont">&#xe665;</i> 搜索
					</button>
					<button type="button" class="btn btn-success radius"
							id="clearSearch" name="">
						<i class="Hui-iconfont">&#xe665;</i> 清空
					</button>
				</form>
			</div>
			<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l">
				<a href="javascript:;" onclick="datadel()"
				   class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
					批量删除</a>
				<a href="javascript:;" onclick="item_add('添加','User?oper=insert','800','500')"
				   class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
					添加</a>
			</span>
				<span class="r">共有数据：<strong>${pagerItem.rowCount}</strong>
				条
			</span>
			</div>
			<div class="mt-20">
				<table id="datalist" class="table table-border table-bordered table-hover table-bg">
					<thead>
						<tr>
							<th scope="col" colspan="9">数据列表</th>
						</tr>
						<tr class="text-c">
							<th width="25"><input type="checkbox" name="" value=""></th>
							<th width="50">用户ID</th>
							<th width="80">用户名</th>
							<th width="100">用户类型</th>
							<th width="150">联系电话</th>
							<th width="150">电子邮箱</th>
							<th width="100">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${DataList}">
							<tr class="text-c">
								<td><input type="checkbox" value="${item.userId }" name="id"></td>
								<td>${item.userId }</td>
								<td>${item.userName }</td>
								<td class="td-status">
									<c:choose>
										<c:when test="${item.type=='0'}" >
											<span class="label label-primary radius" >超级管理员</span>
										</c:when>
										<c:otherwise>
											<span class="label label-secondary radius">普通用户</span>
										</c:otherwise>
									</c:choose>
								</td>
								<td>${item.telPhone }</td>
								<td>${item.email }</td>
								<td>
			<a title="删除" href="javascript:;" id="${item.type}"
			   onclick="item_del(this,${item.userId})" class="ml-5"
			   style="text-decoration: none"><i class="Hui-iconfont">&#xe6e2;</i></a>

			<a title="编辑" href="javascript:;"
			   onclick="item_edit('编辑[Id=${item.userId}]','User?oper=update&id=${item.userId}','1','800','500')"
			   class="ml-5" style="text-decoration: none"> <i
					class="Hui-iconfont">&#xe6df;</i></a>

            <a title="重置密码" href="javascript:;"
               onclick="item_reset('重置密码[Id=${item.userId}]',
					   'User?oper=reset&id=${item.userId}&userName=${item.userName}&type=reset', '600','400')"
               class="ml-5" style="text-decoration: none"> <i
                    class="Hui-iconfont">&#xe63c</i></a>
			<a title="查看"
			   href="javascript:;"
			   onclick="item_detail('查看[Id=${item.userId}]','User?oper=detail&id=${item.userId }','4','800','500')"
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
					location.href = "User?oper=list";
				});
				$('a[id="0"]').remove();
			});
		</script>
		<script type="text/javascript">
		/*项目-添加*/
		function item_add(title,url,w,h){
			layer_show(title,url,w,h);
		}
		/*项目-查看*/
		function item_detail(title,url,id,w,h){
			layer_show(title,url,w,h);
		}
		/*项目-编辑*/
		function item_edit(title,url,id,w,h){
			layer_show(title,url,w,h);
		}
		/*密码重置*/
        function item_reset(title,url,w,h) {
            layer_show(title,url,w,h);
        }
		/*项目-删除*/
		function item_del(obj,id){
			layer.confirm('确认要删除该用户吗？',function(index){
				$.ajax({
					type: 'POST',
					url: 'User?oper=deleteDeal&id=' + id,
					// dataType: 'json',
					success: function(data){
						if (data=="ok"){
							$(obj).parents("tr").remove();
							layer.msg('已删除!',{
								icon : 1,
								time : 1000
							});
						} else {
							layer.msg('删除失败!',{
								icon : 1,
								time : 1000
							});
						}
					},
					error:function(data) {
						console.log(data.msg);
					},
				});
			});
		}

		/*批量-删除*/
		function datadel() {
			layer.confirm('确认要删除选中用户吗？', function (index) {
				var num = 0;
				var total = 0;
				var obj = null;
				var id = 0;
				$("#datalist input[type=checkbox]:checked").each(function () {
					obj = this;
					id = $(this).val();

					if(id != null && id != "" && id != "0"){
						total++;
						$.ajax({
							type : 'POST',
							url : 'User',
							async : false,
							data : {"oper":"deleteDeal", "id":id},
							success : function (data) {
								if (data=="ok"){
									$(obj).parents("tr").remove();
									// layer.msg('已删除!',{
									// 	icon : 1,
									// 	time : 1000
									// });
								} else {
									// layer.msg('删除失败!',{
									// 	icon : 1,
									// 	time : 1000
									// });
								}

							},
							error : function (data) {
								// console.log(data.msg);
							}
						});
					}
				})
				layer.msg("要删除" + total + "行记录，成功删除" + num + "行记录。", {
					icon : 1,
					time : 1000
				});
			});
		}
		</script>
	</body>
</html>