<%--
  Created by IntelliJ IDEA.
  User: Tzq
  Date: 2019/6/30
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.2</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/X-admin/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/X-admin/css/xadmin.css">
    <script src="https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/X-admin/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/X-admin/js/xadmin.js"></script>

    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link id="layuicss-layer" rel="stylesheet"
          href="http://x.xuebingsi.com/x-admin/v2.2/lib/layui/css/modules/layer/default/layer.css?v=3.1.1" media="all">
    <style>
        body{ text-align:center}
        #div{margin:0 auto;width:300px;height:30px}
    </style>
</head>

<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form class="layui-form layui-form-pane">
            <div class="layui-form-item">
                <label for="userName" class="layui-form-label">
                    <span class="x-red">*</span>用户名
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="userName" name="userName" required="" lay-verify="required"
                           value="${bean.userName}" autocomplete="off"
                           class="layui-input" disabled="true">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">
                    拥有权限
                </label>
                <table class="layui-table layui-input-block">
                    <tbody>
                    <tr>
                        <td>
                            <label class="layui-form-label">
                                资料管理
                            </label>
                        </td>
                        <td>
                            <div class="layui-input-block">
                                <input name="haveSaleMan" lay-skin="primary" type="checkbox"
                                       value="${bean.haveSaleMan}"title="销售员资料" >
                                <div class="layui-unselect layui-form-checkbox" lay-skin="primary"><span>销售员资料</span><i
                                        class="layui-icon layui-icon-ok"></i></div>
                                <input name="haveGoods" lay-skin="primary" type="checkbox"
                                       value="${bean.haveGoods}" title="商品资料">
                                <div class="layui-unselect layui-form-checkbox" lay-skin="primary"><span>商品资料</span><i
                                        class="layui-icon layui-icon-ok"></i></div>
                                <input name="haveCustomer" lay-skin="primary" type="checkbox"
                                       value="${bean.haveCustomer}" title="客户资料">
                                <div class="layui-unselect layui-form-checkbox" lay-skin="primary"><span>客户资料</span><i
                                        class="layui-icon layui-icon-ok"></i></div>
                                <input name="haveSupplier" lay-skin="primary" type="checkbox"
                                       value="${bean.haveSupplier}" title="供应商资料">
                                <div class="layui-unselect layui-form-checkbox" lay-skin="primary"><span>供应商资料</span><i
                                        class="layui-icon layui-icon-ok"></i></div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="layui-form-label">
                                采购管理
                            </label>
                        </td>
                        <td>
                            <div class="layui-input-block">
                                <input name="havePurchaseIn" lay-skin="primary" type="checkbox"
                                       value="${bean.havePurchaseIn}" title="采购入库">
                                <div class="layui-unselect layui-form-checkbox" lay-skin="primary"><span>采购入库</span><i
                                        class="layui-icon layui-icon-ok"></i></div>
                                <input name="havePurchaseOut" lay-skin="primary" type="checkbox"
                                       value="${bean.havePurchaseOut}" title="采购出库">
                                <div class="layui-unselect layui-form-checkbox" lay-skin="primary"><span>采购出库</span><i
                                        class="layui-icon layui-icon-ok"></i></div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="layui-form-label">
                                销售管理
                            </label>
                        </td>
                        <td>
                            <div class="layui-input-block">
                                <input name="haveSaleOut" lay-skin="primary" type="checkbox"
                                       value="${bean.haveSaleOut}" title="销售出库">
                                <div class="layui-unselect layui-form-checkbox" lay-skin="primary"><span>销售出库</span><i
                                        class="layui-icon layui-icon-ok"></i></div>
                                <input name="haveSaleReturn" lay-skin="primary" type="checkbox"
                                       value="${bean.haveSaleReturn}" title="销售退货">
                                <div class="layui-unselect layui-form-checkbox" lay-skin="primary"><span>销售退货</span><i
                                        class="layui-icon layui-icon-ok"></i></div>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="layui-form-item layui-form-text">
                <label for="description" class="layui-form-label">
                    描述
                </label>
                <div class="layui-input-block">
                    <textarea disabled="true" placeholder="请输入内容" id="description" name="description"
                              class="layui-textarea">${bean.description}</textarea>
                </div>
            </div>
            <div class="layui-form-item" id="div">
                <button class="layui-btn" lay-submit="" lay-filter="add">关闭</button>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/jquery/1.9.1/jquery.min.js"></script>
<script>
    /*function Checks(){
        var checkboxs = $('input[type=checkbox]:checked');
        $.each(checkboxs, function () {
            $(this).attr("value", '1');
        });
        var chec = $('input[type=checkbox]:not(:checked)');
        $.each(chec, function () {
            $(this).attr("value", '0');
            $(this).attr("checked", true);
        });
        return true;
    }*/

    $(function () {
        var checkeds = $('input[type=checkbox][value="1"]');
        $.each(checkeds, function () {
            $(this).attr("checked", true);
        });
        var checkboxs = $('input[type=checkbox]');
        $.each(checkboxs, function () {
            $(this).attr("disabled", true);
        });
    });
</script>
<script>

    layui.use(['form', 'layer'], function () {
        $ = layui.jquery;
        var form = layui.form
            , layer = layui.layer;

        //自定义验证规则
        /*form.verify({
            nikename: function (value) {
                if (value.length < 5) {
                    return '昵称至少得5个字符啊';
                }
            }
            , pass: [/(.+){6,12}$/, '密码必须6到12位']
            , repass: function (value) {
                if ($('#L_pass').val() != $('#L_repass').val()) {
                    return '两次密码不一致';
                }
            }
        });*/

        //监听提交
        form.on('submit(add)', function (data) {
            /*console.log(data);
            //发异步，把数据提交给php
            layer.alert("增加成功", {icon: 6}, function () {
                // 获得frame索引
                var index = parent.layer.getFrameIndex(window.name);
                //关闭当前frame
                parent.layer.close(index);
            });*/
            // 获得frame索引
            var index = parent.layer.getFrameIndex(window.name);
            //关闭当前frame
            parent.layer.close(index);
            return true;
        });


        /*form.on('checkbox(father)', function (data) {

            if (data.elem.checked) {
                $(data.elem).parent().siblings('td').find('input').prop("checked", true);
                form.render();
            } else {
                $(data.elem).parent().siblings('td').find('input').prop("checked", false);
                form.render();
            }
        });*/


    });
</script>
</body>
</html>
