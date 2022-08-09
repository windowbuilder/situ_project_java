<%--
  Created by IntelliJ IDEA.
  User: 飞扬
  Date: 2022/8/8
  Time: 20:25
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
    <title>注册界面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <style>
        .layui-container{
            width: 420px;
            height: 440px;
            min-height: 440px;
            max-height: 440px;
            position: absolute;
            top: 0;
            left: 0;
            bottom: 0;
            right: 0;
            margin: auto;
            padding: 20px;
            z-index: 130;
            border-radius: 8px;
            background-color: #fff;
            box-shadow: 0 3px 18px rgba(100, 0, 0, .5);
            font-size: 16px;
        }
        .layui-form-label{
            margin-left: 10px;
        }
        .p{
            height: 35px;
            margin-top: -10px;
            padding-left:130px;
            padding-bottom: 5px;
            font-size: 30px;
            color: #088259;
        }
        .layui-input{
            border-radius: 5px;
            width: 300px;
            height: 40px;
            font-size: 15px;
        }
        .layui-form-item,.d3{
            margin-left: -40px;
        }
        .font-set{
            font-size: 13px;
            text-decoration: none;
            margin-left: 120px;
        }
        a:hover{
            text-decoration: underline;
        }
        .b2{
            margin-left: -70px;
            border-radius: 5px;
            width: 380px;
            height: 40px;
            font-size: 15px;
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
            <p class="p">用户注册</p>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">用 &nbsp;户&nbsp;名</label>
            <div class="layui-input-block">
                <input type="text" id="uName" name="uName" placeholder="请输入用户名" autocomplete="off" class="layui-input" />
            </div>
        </div>
        <h5 style="margin-bottom: 10px;color: darkgray;">用户名开头不能为数字且至少一位</h5>
        <div class="layui-form-item">
            <label class="layui-form-label">手 &nbsp;机&nbsp;号</label>
            <div class="layui-input-block">
                <input type="text" id="uPhone" name="uPhone" placeholder="请输入手机号" autocomplete="off" class="layui-input" />
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密 &nbsp; &nbsp; &nbsp;&nbsp;码</label>
            <div class="layui-input-block">
                <input type="password" id="uPass" name="uPass" placeholder="请输入密码" autocomplete="off" class="layui-input" />
            </div>
        </div>
        <h5 style="margin-bottom: 10px;color: darkgray;">请输入8-16位密码</h5>
        <div class="layui-form-item">
            <label class="layui-form-label">密 &nbsp; &nbsp; &nbsp;&nbsp;码</label>
            <div class="layui-input-block">
                <input type="password" id="again" placeholder="请再次输入密码" autocomplete="off" class="layui-input" />
            </div>
        </div>
    </form>
    <div class="d3">
        <div class="layui-input-block">
            <button class="layui-btn b2" onclick="regist()">立即注册</button>
        </div>
        <div style="height: 15px;"></div>
        <a href="login.html" class="font-set" style="float: left;margin-left: 40px;">已有密码</a>
    </div>
</div>
</body>
<script>
    //注册
    function regist(){
        var uName = $("#uName").val();
        if (uName == ''){
            alert("请输入用户名");
            return;
        }
        if (!(/^[^0-9].*/).test(uName)){
            alert("您输入的用户名不符合格式，请重新输入");
            return;
        }
        var uPhone = $("#uPhone").val();
        if (uPhone == ''){
            alert("请输入手机号");
            return;
        }
        if (!(/\d{11}/).test(uPhone)){
            alert("您输入的手机号不符合格式，请重新输入");
            return;
        }
        var uPass = $("#uPass").val();
        if (uPass == ''){
            alert("请输入密码");
            return;
        }
        if (!(/.{8,16}/).test(uPass)){
            alert("您输入的手机号不符合格式，请重新输入");
            return;
        }
        var again = $("#again").val();
        if (again == ''){
            alert("请再次输入密码");
            return;
        }
        if (again != uPass){
            alert("您第二次输入的密码有误，请重新输入密码");
            return;
        }
        var params = $("form").serialize();
        //提交登录信息
        $.ajax({
            url: "getno",
            data: params,
            type: "post",
            success:function(str){
                if(str == '0'){
                    alert("注册成功");
                    //跳主页
                    window.location.href="loginPage";
                } else if (str == '1'){
                    alert("您输入的用户名已存在，请重新输入");
                }else if (str == '2'){
                    alert("您输入的手机号已存在，请重新输入");
                }
            }
        });
    }
</script>
</html>