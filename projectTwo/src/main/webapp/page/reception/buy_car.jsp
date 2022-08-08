<%--
  Created by IntelliJ IDEA.
  User: 飞扬
  Date: 2022/8/4
  Time: 15:41
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
    <title>购物车界面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <style>
        #left_nav ul li {
            height: 50px;
            line-height: 50px;
            padding-left: 20px;
        }
        .hid{
            margin-top: 70px;
            margin-left: 30px;
        }
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min-2.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<div style="overflow: hidden;background-color: lavender;">
    <div style="width: 100%;height: 787px;margin: 0;margin-top: 40px;">
        <c:forEach items="${map.get('list')}" var="car">
            <div style="padding-left: 30px;">
                <div style="margin-top: 15px;width: 100%;height: 120px;" class="ord">
                    <div style="float: left;">
                        <img src="${car.imgUrl}" style="width: 200px;height: 120px;"/>
                    </div>
                    <div style="float: left;margin-left: 15px;">
                        <div>
                            <span style="font-size: 25px;font-weight: 600;">${car.gName}</span>
                        </div>
                        <div style="margin-top: 50px;">
                            <span style="font-size: 20px;">单价 120.00</span>
                            <span style="font-size: 20px;margin-left: 40px;">购买数量${car.buyCount}</span>
                            <span style="font-size: 20px;margin-left: 40px;">总价${car.totalMoney}</span>
                        </div>
                    </div>
                    <div class="hid layui-btn-group">
                        <button class="layui-btn layui-btn-lg">购买</button>
                        <button class="layui-btn layui-btn-lg">删除</button>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>