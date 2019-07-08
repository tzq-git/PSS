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
		<title>权限管理</title>
	</head>
	<body>
		<nav class="breadcrumb">
			<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
			管理员管理<span class="c-gray en">&gt;</span> 权限管理 <a
				class="btn btn-success radius r"
				style="line-height:1.6em;margin-top:3px"
				href="javascript:location.replace(location.href);" title="刷新" ><i
				class="Hui-iconfont">&#xe68f;</i></a>
		</nav>
		<div class="page-container">
			<div class="text-c">
				<form action="Authority" method="get">
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
			<%--<span class="l">
				<a href="javascript:;" onclick="datadel()"
				   class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
					批量删除</a>
				<a href="javascript:;" onclick="item_add('添加','Authority?oper=insert','800','500')"
				   class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
					添加</a>
			</span>--%>
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
							<th width="50">用户ID</th>
							<th width="80">用户名</th>
							<th width="100">权限描述</th>
							<th width="150">权限管理</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${DataList}">
							<tr class="text-c">
								<td>${item.userId }</td>
								<td>${item.userName }</td>
								<td>${item.description }</td>
								<td>
                                    <input class="btn btn-success radius"
                                           onclick="item_admin('权限查看id=[${item.userId}]',
                                                   'Authority?oper=detail&id=${item.userId}',
                                                   800, 550 )"
                                           type="button" value="权限查看">
                                    <input class="btn radius btn-warning"
										   onclick="item_admin('权限管理id=[${item.userId}]',
												   'Authority?oper=admin&id=${item.userId}',
												   800, 550 )"
										   type="button" value="修改权限">
                                </td>
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
					location.href = "Authority?oper=list";
				});
				$('a[id="0"]').remove();
			});
		</script>
		<script type="text/javascript">

		/*项目-查看*/
		function item_detail(title,url,id,w,h){
			layer_show(title,url,w,h);
		}

		/*权限管理*/
		function item_admin(title,url,w,h) {
			layer_show(title,url,w,h);
		}
		</script>
	</body>
</html>