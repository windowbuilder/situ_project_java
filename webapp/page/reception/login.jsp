<%--
  Created by IntelliJ IDEA.
  User: 飞扬
  Date: 2022/8/2
  Time: 8:55
  To change this template use File | Settings | File Templates.
--%>
<%@page isELIgnored="false" %>
<%@page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>登录界面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css" />
    <style>
        #img{
            CURSOR: pointer;
            width: 25px;
            height: 15px;
            position: absolute;
            margin-left: 380px;
            margin-top: -26px;
            text-align: center;
        }
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min-2.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body style="width:500px;height:855px;margin:40px auto;
		background:-webkit-radial-gradient(#AEBAF8,#C973FF,#AEBAF8);">
<div class="layui-container">
    <form class="layui-form" action="">
        <div class="layui-form-mid layui-word-aux">
            <p class="p">用户登录</p>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" id="uName" name="uName" placeholder="请输入用户名或者手机号" autocomplete="off" class="layui-input" />
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密 &nbsp; &nbsp;码</label>
            <div class="layui-input-block">
                <input type="password" id="uPass" name="uPass" placeholder="请输入密码" autocomplete="off" class="layui-input" />
            </div>
            <img id="img" src="${pageContext.request.contextPath}/img/openeye.png">
        </div>
    </form>
    <div class="b3">
        <div class="layui-input-block">
            <button class="layui-btn" onclick="login()">登陆</button>
        </div>
        <div style="height: 20px;"></div>
        <a href="forget.html" class="font-set" style="float: left;margin-left: 50px;">忘记密码?</a>
        <a href="${pageContext.request.contextPath}/use/regPage" class="font-set" style="float: right;">立即注册</a>
    </div>
</div>
</body>
<script>
    //登录参数验证
    function login(){
        var uName = $("#uName").val();
        if(uName == ''){
            alert("请输入用户名或者手机号");
            return;
        }
        var uPass = $("#uPass").val();
        if(uPass == ''){
            alert("请输入密码");
            return;
        }
        var params = $("form").serialize();
        //提交登录信息
        $.ajax({
            url: "login",
            data: params,
            type: "post",
            success:function(str){
                if(str != '0'){
                    alert("登录成功");
                    //跳主页
                    window.location.href="ind";
                } else {
                    alert("您输入的账户信息有误，请重新输入")
                }
            }
        });
    }

    //睁眼、闭眼
    $(document).ready(function(){
        $("#img").click(function(){
            var $ = layui.$;
            if ($("#uPass").attr("type") == "password") {
                $("#uPass").attr("type","text");
                $("#img").attr("src","${pageContext.request.contextPath}/img/closeeye.png");
            } else {
                $("#uPass").attr("type","password");
                $("#img").attr("src","${pageContext.request.contextPath}/img/openeye.png");
            }
        });
    });
</script>
</html>
