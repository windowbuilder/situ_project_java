<%--
  Created by IntelliJ IDEA.
  User: 飞扬
  Date: 2022/8/7
  Time: 16:48
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
    <title>个人界面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <style>
        #left_nav {
            position: relative;
            width: 200px;
            height: 822px;
            float: left;
            margin: 0;
        }
        #left_nav ul li {
            height: 50px;
            line-height: 50px;
            padding-left: 20px;
        }
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min-2.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<div>
    <div class="layui-panel" id="left_nav">
        <ul class="layui-menu" id="docDemoMenu1">
            <li lay-options="{id: 100}">
                <div class="layui-menu-body-title"><a href="per" target="t2">个人信息</a></div>
            </li>
            <li lay-options="{id: 100}">
                <div class="layui-menu-body-title"><a href="bal" target="t2">账户余额</a></div>
            </li>
            <li lay-options="{id: 100}">
                <div class="layui-menu-body-title" onclick="lo()">退出登录</div>
            </li>
        </ul>
    </div>
</div>
<div style="height: 824px;margin: 0;overflow: hidden;background-color: lavender;">
    <iframe name="t2" frameBorder="0" style="width: 100%;height: 824px;" src="per" scrolling="no" id="myiframe">

    </iframe>
</div>
</body>
<script>
    function lo(){
        top.location.href="${pageContext.request.contextPath}/use/quitPage";
    }
</script>
</html>