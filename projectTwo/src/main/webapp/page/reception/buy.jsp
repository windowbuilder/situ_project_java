<%--
  Created by IntelliJ IDEA.
  User: 飞扬
  Date: 2022/8/4
  Time: 14:41
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
    <title>支付界面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min-2.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<div style="width: 86%; margin: 80px auto;">
    <div style="float: left;">
        <img src="${goods.imgUrl}" style="width: 200px;height: 160px;"/>
    </div>
    <div style="float: left;margin-left: 50px;margin-top: -40px;">
        <div style="margin: 30px auto;font-size: 16px;">
            ${goods.gName}
        </div>
        <div style="margin: 30px auto;font-size: 16px;">
            单价${goods.gPrice}元 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;购买数量${buyCount}
        </div>
        <div id="dd">
            <div style="margin: 30px auto;font-size: 23px;color: red;">
                ${orderMoney}
            </div>
            <div style="margin: 30px auto;">
                <button class="layui-btn add">支付</button>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    //支付后弹窗改变提示
    $(".add").on("click",function(){
        $("#dd").html("<div style='width: 230px;height: 110px;background-color: aquamarine;padding-top:20px'><div id='zer'>"+
            "<input type='text' id='uPass' name='uPass' placeholder='请输入支付密码' autocomplete='off' style='width: 200px;margin-left: 15px;' class='layui-input'>"+
            "<button class='layui-btn layui-btn-black geto' style='margin-top:20px;margin-left: 15px;width: 200px'>支付</button></div></div>");
        //提交数据进行支付
        $(".geto").on("click",function(){
            var orderNo = "${orderNo}";
            var uid = "${uid}";
            var $ = layui.jquery;
            var uPass = $("#uPass").val();
            var params = "orderNo=" + orderNo + "&uPass=" + uPass;
            $.ajax({
                url:"odin",//指定请求跳转的路径
                data:params,//请求提交的数据
                type:"POST",//请求提交的方式
                success:function(str){
                    if (str == "1"){
                        $("#zer").html("<div style='text-align: center;'>支付成功</div><button class='layui-btn layui-btn-black gg'"+
                            "style='margin-top:20px;margin-left: 15px;width: 200px'>关闭</button>");
                        $(".gg").on("click",function(){
                            window.location.href = "${pageContext.request.contextPath}/ord/qio?uId="+uid;
                        })
                    }else {
                        $("#zer").html("<div style='text-align: center;'>支付失败</div><button class='layui-btn layui-btn-black gg'"+
                            "style='margin-top:20px;margin-left: 15px;width: 200px'>请重新尝试</button>");
                        $(".gg").on("click",function(){
                            $("#zer").html("<input type='text' id='uPass' name='uPass' placeholder='请输入支付密码' autocomplete='off' style='width: 200px;margin-left: 15px;' class='layui-input'>"+
                                "<button class='layui-btn layui-btn-black geto' style='margin-top:20px;margin-left: 15px;width: 200px'>支付</button>");
                        })
                    }
                }
            });

        })
    });
</script>
</html>