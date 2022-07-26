<%--
  Created by IntelliJ IDEA.
  User: 飞扬
  Date: 2022/7/22
  Time: 14:14
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
    <title>管理员添加界面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <style>
        .lable1 {
            text-align: left;
        }

        .b1 {
            margin-left: -100px;
            border-radius: 5px;
            width: 290px;
            height: 40px;
            font-size: 15px;
        }
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min-2.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<div style="margin-left: 70px;margin-top: 20px;">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <label class="layui-form-label lable1">姓名：</label>
            <div class="layui-input-inline">
                <input type="text" name="mName" placeholder="请输入姓名" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label lable1">密码：</label>
            <div class="layui-input-inline">
                <input type="password" name="mPass" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
        </div>
    </form>
    <div class="layui-input-block">
        <button class="layui-btn b1" onclick="add()">确认添加</button>
    </div>
</div>
</body>
<script>
    function add(){
        var params = $("form").serialize();
        $.ajax({
            url:"add",//指定请求跳转的路径
            data:params,//请求提交的数据
            type:"POST",//请求提交的方式
            success:function(str){
                if (str == 1){
                    alert("添加成功");
                    setTimeout(closeLayer(), 500);
                }else {
                    alert("该用户名已存在请重新尝试");
                }
            }
        });
    }
    function closeLayer() {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
        window.parent.location.reload();
    }
</script>
</html>