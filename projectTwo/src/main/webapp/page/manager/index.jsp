<%--
  Created by IntelliJ IDEA.
  User: 飞扬
  Date: 2022/7/21
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@page isELIgnored="false" %>
<%@page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>系统主界面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min-2.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header layui-bg-green">
        <div class="layui-logo layui-bg-green">商城后台管理系统</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">用户名：${mName}</li>
        </ul>
    </div>
    <div class="layui-side layui-bg-cyan">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree layui-bg-cyan" lay-filter="test" lay-shrink="all">
                <c:if test="${mLevel == 1}">
                    <li class="layui-nav-item layui-nav-itemed">
                        <a class="" href="javascript:;"><i class="layui-icon">&#xe66f;</i> &nbsp;管理员</a>
                        <dl class="layui-nav-child">
                            <dd><a href="que" target="t1">管理员列表</a></dd>
                        </dl>
                    </li>
                </c:if>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;"><i class="layui-icon">&#xe66e;</i> &nbsp;分类管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="../page/shop_num.html" target="t1">商品分类</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;"><i class="layui-icon">&#xe657;</i> &nbsp;商品管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="queg" target="t1">商品列表</a></dd>
                        <dd><a href="addsh" target="t1">新增商品</a></dd>
                        <dd><a href="qud" target="t1">下架商品</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;"><i class="layui-icon">&#xe63c;</i> &nbsp;订单管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="quo" target="t1">订单列表</a></dd>
                        <dd><a href="../page/refund.html" target="t1">退款</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="quitPage"><i class="layui-icon">&#xe682;</i> &nbsp;退出登录</a>
                </li>
            </ul>
        </div>
    </div>
    <div class="layui-body" style="background-color: lavender;">
        <div style="font-size:0;">
            <iframe name="t1" frameBorder="0" style="width: 100%;height: 834px;" src="queg" scrolling="no" id="myiframe">

            </iframe>
        </div>
    </div>
    <div class="layui-footer" style="text-align: center;">@feiyang</div>
</div>
</body>
</html>
