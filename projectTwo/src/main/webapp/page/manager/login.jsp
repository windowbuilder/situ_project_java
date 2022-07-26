<%--
  Created by IntelliJ IDEA.
  User: 飞扬
  Date: 2022/7/22
  Time: 11:12
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
    <title>商城管理系统登录界面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css" />
    <style>
        #img{
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
<body style="width:500px;height:855px;margin:40px auto;background:-webkit-radial-gradient(#AEBAF8,#C973FF,#AEBAF8);">
<div class="layui-container">
    <form class="layui-form" action="">
        <div class="layui-form-mid layui-word-aux">
            <p class="p">用户登录</p>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" id="mName" name="mName" placeholder="请输入用户名" autocomplete="off" class="layui-input" />
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密 &nbsp; &nbsp;码</label>
            <div class="layui-input-block">
                <input type="password" id="mPass" name="mPass" placeholder="请输入密码" autocomplete="off" class="layui-input" />
            </div>
            <%--<img id="img" οnclick="change()" src="${pageContext.request.contextPath}/img/openeye.png">--%>
        </div>
    </form>
    <div class="b3">
        <div class="layui-input-block">
            <button class="layui-btn" onclick="login()">登陆</button>
        </div>
        <div style="height: 20px;"></div>
        <a href="../page/forget.html" class="font-set" style="float: left;margin-left: 50px;">忘记密码?</a>
        <a href="../page/regist.html" class="font-set" style="float: right;">立即注册</a>
    </div>
</div>
</body>
<script>
    //登录参数验证
    function login(){
        var mName = $("#mName").val();
        if(mName == ''){
            alert("请输入用户名");
            return;
        }
        var mPass = $("#mPass").val();
        if(mPass == ''){
            alert("请输入密码");
            return;
        }
        var params = "mName="+mName+"&mPass="+mPass;
        //提交登录信息
        $.ajax({
            url: "login",
            data: params,
            type: "post",
            success:function(str){
                console.info(str);
                if(str != '0'){
                    alert("登录成功");
                    //跳主页
                    window.location.href="ind?id="+str;
                } else {
                    alert("登陆失败")
                }
            }
        });
    }

    //睁眼、闭眼
    function change() {
        var $ = layui.jquery;
        if ($("#mPass").type === "password") {
            $("#mPass").type = "text";
            $("#img").src = "${pageContext.request.contextPath}/img/closeeye.png"; //图片路径（闭眼图片）
        } else {
            $("#mPass").type = "password";
            $("#img").src = "${pageContext.request.contextPath}/img/openeye.png"; // 图片路径（睁眼图片）
        }
    }
</script>
</html>

