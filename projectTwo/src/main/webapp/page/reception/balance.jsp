<%--
  Created by IntelliJ IDEA.
  User: 飞扬
  Date: 2022/8/7
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@page isELIgnored="false" %>
<%@page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>余额界面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min-2.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
    <div style="width: 80%; margin: 20px auto;">
        <span style="font-size: 20px;">
            账户余额:${uMoney}
            <button class="layui-btn add">充值</button>
        </span>
    </div>
</body>
<script>
    $(".add").on("click",function(){
        layer.open({
            type:2,
            title:"充值",
            content:"rea",
            area: ['350px', '250px']
        })
    });
</script>
</html>
