<%--
  Created by IntelliJ IDEA.
  User: 飞扬
  Date: 2022/8/3
  Time: 20:51
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
    <title>商品详情界面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min-2.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<div style="width: 86%; margin: 80px auto;">
    <input type="hidden" name="gid" id="gid" value="${goods.id}">
    <div style="float: left;">
        <img src="${goods.imgUrl}" style="width: 200px;height: 160px;"/>
    </div>
    <div style="float: left;margin-left: 50px;margin-top: -40px;">
        <div style="margin: 30px auto;font-size: 16px;">
            ${goods.gName}
        </div>
        <div style="margin: 30px auto;font-size: 16px;">
            单价${goods.gPrice}元 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;库存${goods.gCount}件
        </div>
        <div style="margin: 30px auto;font-size: 16px;">
            购买数量
            <div class="layui-input-inline" style="margin-left: 80px;width: 60px;">
                <input type="text" id="buyCount" maxlength="5" class="layui-input" value="1"/>
            </div>
            <div class="layui-input-inline">
                <button type="button" onclick="ad()" class="layui-btn layui-btn-primary layui-btn-sm" style="width: 35px;height: 35px;border-radius: 50%;">
                    <i class="layui-icon" style="position: absolute;top: 2px;font-size: 18px;left: 7px;">&#xe654;</i>
                </button>
            </div>
        </div>
        <div style="margin: 30px auto;font-size: 16px;">
            <div class="layui-input-inline">
                <button class="layui-btn add">加入购物车</button>
            </div>
            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
            <div class="layui-input-inline" style="margin-left: -10px;">
                <button class="layui-btn buy">购买</button>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    //加入购物车
    $(".add").on("click",function(){
        var id = $("#gid").val();
        var buyCount = $("#buyCount").val();
        var params = "id=" + id + "&buyCount=" + buyCount;
        $.ajax({
            url:"${pageContext.request.contextPath}/shop/order/aca",//指定请求跳转的路径
            data:params,//请求提交的数据
            type:"POST",//请求提交的方式
            success:function(str){
                if (str == "0"){
                    alert("加入购物车失败");
                }else {
                    layer.open({
                        type:1,
                        title:"信息",
                        btnAlign:'c',
                        content:"<div style='text-align: center;margin-top: 30px;'>加入购物车成功</div>",
                        area: ['350px', '200px'],
                        btn: ['确定']
                    })
                }
            }
        });
    });

    //购买
    $(".buy").on("click",function(){
        var id = $("#gid").val();
        var buyCount = $("#buyCount").val();
        window.location.href="${pageContext.request.contextPath}/shop/order/rdi?id=" + id + "&buyCount=" + buyCount;
    });

    //加号
    function ad(){
        var cou = Number($("#buyCount").val()) + 1;
        $("#buyCount").val(cou.toString());
    }
</script>
</html>