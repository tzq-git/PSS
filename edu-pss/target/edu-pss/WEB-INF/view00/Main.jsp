<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>H-ui.admin v3.1</title>
    <meta name="keywords" content="进销存管理系统 v1.0.0">
    <meta name="description" content="进销存管理系统 v1.0.0">
</head>
<body>
<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
        <div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="Main?oper=list">进销存管理系统</a> <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="Main?oper=list">H-ui</a>
            <span class="logo navbar-slogan f-l mr-10 hidden-xs">v1.0</span>
            <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
            <nav class="nav navbar-nav">
                <ul class="cl">
                    <li class="dropDown dropDown_hover"><a href="javascript:;" class="dropDown_A"><i class="Hui-iconfont">&#xe600;</i> 新增 <i class="Hui-iconfont">&#xe6d5;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="javascript:;" onclick="item_purchase('快捷添加购入单','Purchase?oper=insert','800','500')"><i class="Hui-iconfont">&#xe616;</i> 购入单</a></li>
                            <li><a href="javascript:;" onclick="item_outbound('快捷添加销售单','OutBound?oper=insert','800','700')"><i class="Hui-iconfont">&#xe613;</i> 销售单</a></li>
                            <li><a href="javascript:;" onclick="item_customer('快捷添加客户','Customer?oper=insert','800','600')"><i class="Hui-iconfont">&#xe620;</i> 客户</a></li>
                            <li><a href="javascript:;" onclick="item_provider('快捷添加供应商','Provider?oper=insert','800','600')"><i class="Hui-iconfont">&#xe60d;</i> 供应商</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
            <nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
                <ul class="cl">
                    <li>超级管理员</li>
                    <li class="dropDown dropDown_hover">
                        <a href="#" class="dropDown_A">${user.userName} <i class="Hui-iconfont">&#xe6d5;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="javascript:;"
                                   onclick="myselfinfo('用户信息[id=${user.userId}]',
                                           'User?oper=info&id=${user.userId}', '700', '700')">个人信息</a></li>
                            <li><a href="javascript:;"
                                   onclick="resetPwd('修改密码[id=${user.userId}]',
                                           'User?oper=reset&id=${user.userId}&type=change', '600', '500')">修改密码</a></li>

                            <li><a href="Login?oper=logoutDeal">退出</a></li>
                        </ul>
                    </li>
                    <li id="Hui-msg"> <a href="#" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> </li>
                    <li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="javascript:;" data-val="green" title="默认（绿色）">默认（绿色）</a></li>
                            <li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
                            <li><a href="javascript:;" data-val="default" title="黑色">黑色</a></li>
                            <li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
                            <li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
                            <li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</header>
<aside class="Hui-aside">
    <div class="menu_dropdown bk_2">
        <dl id="menu-admin">
            <dt><i class="Hui-iconfont">&#xe62d;</i>管理员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="User?oper=list" data-title="用户管理"
                           href="javascript:void(0)">用户管理</a></li>
                    <li><a data-href="Authority?oper=list" data-title="权限管理"
                           href="javascript:void(0)">权限管理</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-article">
            <dt>
                <i class="Hui-iconfont">&#xe616;</i> 维护基本资料<i
                    class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
            </dt>
            <dd>
                <ul>
                    <li><a data-href="SalePerson?oper=list" data-title="销售员资料维护"
                           href="javascript:void(0)">销售员资料维护</a></li>
                    <li><a data-href="Goods?oper=list" data-title="商品资料维护"
                           href="javascript:void(0)">商品资料维护</a></li>
                    <li><a data-href="Customer?oper=list" data-title="客户资料维护"
                           href="javascript:void(0)">客户资料维护</a></li>
                    <li><a data-href="Provider?oper=list" data-title="供应商资料维护"
                           href="javascript:void(0)">供应商资料维护</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-purchase">
            <dt><i class="Hui-iconfont">&#xe613;</i> 采购维护<i
                    class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
            </dt>
            <dd>
                <ul>
                    <li><a data-href="Purchase?oper=list" data-title="采购入库"
                           href="javascript:void(0)">采购入库</a></li>
                    <li><a data-href="RPurchase?oper=list" data-title="采购退货"
                           href="javascript:void(0)">采购退货</a></li>
                </ul>
            </dd>
        </dl>

        <dl id="menu-sale">
            <dt><i class="Hui-iconfont">&#xe616;</i> 销售管理<i
                    class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
            </dt>
            <dd>
                <ul>
                    <li><a data-href="OutBound?oper=list" data-title="销售出库"
                           href="javascript:void(0)">销售出库</a></li>
                    <li><a data-href="SaleReturn?oper=list" data-title="销售退货"
                           href="javascript:void(0)">销售退货</a></li>
                </ul>

            </dd>
        </dl>
        <dl id="menu-Stock">
            <dt><i class="Hui-iconfont">&#xe613;</i> 库存查询<i
                    class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
            </dt>
            <dd>
                <ul>
                    <li><a data-href="GSearch?oper=form" data-title="库存查询"
                           href="javascript:void(0)">库存查询</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-download">
            <dt><i class="Hui-iconfont">&#xe613;</i> 数据导出<i
                    class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
            </dt>
            <dd>
                <ul>
                    <li><a data-href="Download?oper=list" data-title="资料下载"
                           href="javascript:void(0)">表格下载</a></li>
                </ul>
            </dd>
        </dl>
    </div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
    <div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
        <div class="Hui-tabNav-wp">
            <ul id="min_title_list" class="acrossTab cl">
                <li class="active">
                    <span title="我的桌面" data-href="Main?oper=Welcome">我的桌面</span>
                    <em></em></li>
            </ul>
        </div>
        <div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
    </div>
    <div id="iframe_box" class="Hui-article">
        <div class="show_iframe">
            <div style="display:none" class="loading"></div>
            <iframe scrolling="yes" frameborder="0" src="Main?oper=Welcome"></iframe>
        </div>
    </div>
</section>

<div class="contextMenu" id="Huiadminmenu">
    <ul>
        <li id="closethis">关闭当前 </li>
        <li id="closeall">关闭全部 </li>
    </ul>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript">
    $(function () {
        if (${user.type != "0"}) {
            var lis = $("#Hui-userbar .cl li:first");
            $('#menu-admin').remove();
            lis.text("普通用户");
        }
    })


    /*修改密码*/
    function resetPwd(title, url, w, h) {
        layer_show(title, url, w, h);
    }

    /*个人信息*/
    function myselfinfo(title, url, w, h) {
        layer_show(title, url, w, h);
        /*layer.open({
            type: 1,
            area: ['600px','500px'],
            fix: false, //不固定
            maxmin: true,
            shade:0.4,
            title: '查看信息',
            content:''
        });*/
    }

    /*采购-添加*/
    function item_purchase(title,url,w,h){
        layer_show(title,url,w,h);
    }
    /*销售-添加*/
    function item_outbound(title,url,w,h){
        layer_show(title,url,w,h);
    }
    /*客户-添加*/
    function item_customer(title,url,w,h){
        layer_show(title,url,w,h);
    }
    /*供应商-添加*/
    function item_provider(title,url,w,h){
        layer_show(title,url,w,h);
    }


</script>

</body>
</html>
