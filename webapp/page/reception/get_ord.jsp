<%--
  Created by IntelliJ IDEA.
  User: 飞扬
  Date: 2022/8/7
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@page isELIgnored="false" %>
<%@page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>支付界面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <style>
        .b1 {
            margin-left: -85px;
            border-radius: 5px;
            width: 400px;
            height: 40px;
            font-size: 15px;
        }
        .d1{
            width: 400px;
            height: 40px;
            margin-left: 25px;
            margin-top: 15px;
            margin-bottom: 30px;
        }
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min-2.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<div>
    <form class="layui-form" action="">
        <div class="layui-form-item d1">
            <input type="text" id="uPass" required lay-verify="required" placeholder="请输入支付密码"
                   autocomplete="off" class="layui-input">
        </div>
    </form>
    <div class="layui-input-block">
        <button class="layui-btn b1" onclick="ord('${orderNo}')">支付</button>
    </div>
</div>
</body>
<script>
    function ord(orderNo){
        var uPass = $("#uPass").val();
        var params = "orderNo="+orderNo+"&uPass="+uPass;
        console.info(params);
        $.ajax({
            url:"${pageContext.request.contextPath}/shop/order/odin",//指定请求跳转的路径
            data:params,//请求提交的数据
            type:"POST",//请求提交的方式
            success:function(str){
                //请求发送成功回调函数
                if (str) {
                    alert("支付成功");
                    setTimeout(closeLayer(), 500);
                }else {
                    alert("支付失败");
                }
            }
        });
    }

    function closeLayer() {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
        window.location.reload();
    }
</script>
</html>
