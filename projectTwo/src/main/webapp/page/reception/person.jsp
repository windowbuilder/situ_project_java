<%--
  Created by IntelliJ IDEA.
  User: 飞扬
  Date: 2022/8/7
  Time: 16:55
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min-2.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<div style="width: 97%; margin: 20px auto;">
    <div style="border: 1px solid black;width: 210px;height: 220px;padding-top: 20px;padding-left: 20px;background-color: aliceblue;">
        <form class="layui-form" action="">
            <input type="hidden" name="id" value="${uId}">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="text" name="uName" placeholder="用户名" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="text" name="uPhone" placeholder="手机号" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="text" name="uPass" placeholder="密码" autocomplete="off" class="layui-input">
                </div>
            </div>
        </form>
        <div class="b3">
            <div class="layui-input-block">
                <button class="layui-btn" onclick="upt()" style="width: 190px;margin-left: -110px;">更改</button>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function upt(){
        var params = $("form").serialize();
        //提交登录信息
        $.ajax({
            url: "upt",
            data: params,
            type: "post",
            success:function(str){
                if(str == "1"){
                    alert("更改成功");
                    //跳主页
                    top.location.href="${pageContext.request.contextPath}/use/quitPage";
                } else {
                    alert("更改失败")
                }
            }
        });
    }
</script>
</html>