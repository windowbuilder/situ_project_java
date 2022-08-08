<%--
  Created by IntelliJ IDEA.
  User: 飞扬
  Date: 2022/8/2
  Time: 20:03
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
    <style>
        .child{
            font-size: 17px;
            CURSOR: pointer;
        }
        .glo{
            CURSOR: pointer;
        }
        .child:hover{
            text-decoration: underline;
        }
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min-2.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<div style="width: 100%;height: 60px;background-color: #009688;">
    <div style="line-height: 60px;color: white;text-align: center;width: 200px;float: left;font-size: 20px;" class="glo" onclick="ind()">B2C购物商城</div>
    <div style="float: right;margin-right: 700px;height: 40px;line-height: 60px;">
        <input type="text" id="gName" placeholder="商品名称" onfocus="ind()" style="width: 182px;height: 40px;padding-left: 10px;outline: none;"/>
        <button style="height: 40px;width: 67px;background-color: #1e9fff;border: 0;color: white;cursor: pointer" onclick="que()">查询</button>
    </div>
    <ul class="layui-nav layui-layout-right layui-bg-green">
        <li class="layui-nav-item">
            <a href="${pageContext.request.contextPath}/ord/qio?uId=${uid}" target="t1" class="child" onclick="ord(this)">我的订单</a>
            <dl class="layui-nav-child">
                <dd><a href="">待付款</a></dd>
                <dd><a href="">待发货</a></dd>
                <dd><a href="">已发货</a></dd>
                <dd><a href="">已签收</a></dd>
                <dd><a href="">退款中</a></dd>/
                <dd><a href="">交易结束</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item layui-hide-xs">
            <a href="${pageContext.request.contextPath}/car/quc?uid=${uid}" target="t1" class="child" onclick="buy(this)">购物车</a>
        </li>
        <li class="layui-nav-item layui-hide-xs">
            <a href="${pageContext.request.contextPath}/use/pri" target="t1" class="child" onclick="pri(this)">用户名：${uName}</a>
        </li>
    </ul>
</div>
<div>
    <iframe name="t1" frameBorder="0" style="width: 100%;height: 824px;" src="${pageContext.request.contextPath}/goo/cla" id="myiframe">

    </iframe>
</div>
<div style="width: 100%;background-color: #fafafa;height: 48px;text-align: center;margin: 0;">
    <span style="line-height:50px;">@feiyang</span>
</div>
</body>
<script>
    function pri(da){
        $(".child").css("color","white");
        $("#gName").val("");
        da.style.color="red";
    }
    function buy(da){
        $(".child").css("color","white");
        $("#gName").val("");
        da.style.color="red";
    }
    function ord(da){
        $(".child").css("color","white");
        $("#gName").val("");
        da.style.color="red";
    }
    function ind(){
        $(".child").css("color","white");
        $("#gName").val("");
        t1.location.href="${pageContext.request.contextPath}/goo/cla";
    }
    function que(){
        $(".child").css("color","white");
        var gName = $("#gName").val();
        t1.location.href="${pageContext.request.contextPath}/goo/cla?gName="+gName;
    }
</script>
</html>