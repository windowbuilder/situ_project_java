<%--
  Created by IntelliJ IDEA.
  User: 飞扬
  Date: 2022/8/7
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@page isELIgnored="false" %>
<%@page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>充值界面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <style>
        .inp{
            width: 300px;
            margin-top: 15px;
            margin-left: 25px;
        }
        .bu1{
            width: 80px;
            margin-right: 2px;
            margin-left: 27px
        }
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min-2.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<div>
    <input id="uMoney" name="uMoney" class="layui-input inp" placeholder="输入充值金额">
    <table>
        <tr>
            <td><button class="layui-btn layui-btn-primary bu1" onclick="bu('100')">100</button></td>
            <td><button class="layui-btn layui-btn-primary bu1" onclick="bu('500')">500</button></td>
            <td><button class="layui-btn layui-btn-primary bu1" onclick="bu('1000')">1000</button></td>
        </tr>
        <tr>
            <td><button class="layui-btn layui-btn-primary bu1" onclick="bu('2000')">2000</button></td>
            <td><button class="layui-btn layui-btn-primary bu1" onclick="bu('5000')">5000</button></td>
            <td><button class="layui-btn layui-btn-primary bu1" onclick="bu('10000')">10000</button></td>
        </tr>
    </table>
    <button class="layui-btn inp add">充值</button>
</div>
</body>
<script>
    function bu(da){
        $("#uMoney").attr("value",da);
    };
    $(".add").on("click",function(){
        var uMoney = $("#uMoney").val();
        var params = "uMoney="+uMoney;
        //提交登录信息
        $.ajax({
            url: "adm",
            data: params,
            type: "post",
            success:function(str){
                if(str == "1"){
                    alert("充值成功");
                    setTimeout(closeLayer(), 500);
                } else {
                    alert("充值失败")
                }
            }
        });
    });

    function closeLayer() {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
        window.parent.location.reload();
    }
</script>
</html>