<%--
  Created by IntelliJ IDEA.
  User: 飞扬
  Date: 2022/8/5
  Time: 9:06
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
    <title>我的订单界面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <style>
        .hid{
            display: none;
            float: left;
            margin-left: 30px;
            line-height: 120px;
        }
        .ord:hover .hid{
            display: block;
        }
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min-2.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<div style="overflow: hidden;background-color: lavender;">
    <div style="width: 100%;height: 784px;margin: 0;margin-top: 40px;">
        <c:forEach items="${list}" var="ord">
            <div style="padding-left: 30px;">
                <div>
                    <span style="font-size: 25px;font-weight: 600;">订单号${ord.orderNo}</span>
                    <span style="color: red;margin-left: 30px;font-size: 20px;">状态:
                        <c:if test="${ord.orderStatus == 1}">待付款</c:if>
                        <c:if test="${ord.orderStatus == 2}">待发货</c:if>
                        <c:if test="${ord.orderStatus == 3}">已发货</c:if>
                        <c:if test="${ord.orderStatus == 4}">已签收</c:if>
                        <c:if test="${ord.orderStatus == 5}">退款中</c:if>
                        <c:if test="${ord.orderStatus == 6}">已退款</c:if>
                        <c:if test="${ord.orderStatus == 7}">交易结束</c:if>
                    </span>
                    <c:if test="${ord.orderStatus == 1}">
                        <button style="margin-left: 20px;" class="layui-btn layui-btn-warm" onclick="ording('${ord.orderNo}')">支付</button>
                    </c:if>
                    <c:if test="${ord.orderStatus == 2 || ord.orderStatus == 3}">
                        <button style="margin-left: 20px;" class="layui-btn layui-btn-warm" onclick="get('${ord.orderNo}','4')">签收</button>
                    </c:if>
                </div>
                <c:forEach items="${list1}" var="det">
                    <c:if test="${det.orderNo == ord.orderNo}">
                        <div style="margin-top: 15px;width: 100%;height: 120px;" class="ord">
                            <div style="float: left;">
                                <img src="${det.imgUrl}" style="width: 200px;height: 120px;"/>
                            </div>
                            <div style="float: left;margin-left: 15px;">
                                <div>
                                    <span style="font-size: 25px;font-weight: 600;">${det.gName}</span>
                                    <span style="font-size: 25px;font-weight: 600;">${ord.createTime}</span>
                                </div>
                                <div style="margin-top: 50px;">
                                    <span style="font-size: 20px;">单价 ${det.gPrice}</span>
                                    <span style="font-size: 20px;margin-left: 40px;">购买数量${det.buyCount}</span>
                                    <span style="font-size: 20px;margin-left: 40px;">总价${det.totalMoney}</span>
                                </div>
                            </div>
                            <div class="hid">
                                <c:if test="${ord.orderStatus == 1}">
                                    <button class="layui-btn layui-btn-lg" onclick="get('${ord.orderNo}','7')">关闭交易</button>
                                </c:if>
                                <c:if test="${ord.orderStatus == 2 || ord.orderStatus == 3 || ord.orderStatus == 4}">
                                    <button class="layui-btn layui-btn-lg" onclick="get('${ord.orderNo}','5')">申请退款</button>
                                </c:if>
                                <c:if test="${ord.orderStatus == 5}">
                                    <button class="layui-btn layui-btn-lg" onclick="get('${ord.orderNo}','${ord.preStatus}')">取消退款</button>
                                </c:if>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </c:forEach>
    </div>
</div>
</body>
<script>
    function ording(orderNo){
        layer.open({
            type:2,
            title:"支付",
            content:"geo?orderNo="+orderNo,
            area: ['455px', '250px']
        })
    }

    function get(orderNo,orderStatus){
        var params = "orderNo="+orderNo+"&orderStatus="+orderStatus;
        //提交登录信息
        $.ajax({
            url: "up",
            data: params,
            type: "post",
            success:function(str){
                if(str == "1"){
                    setTimeout(closeLayer(), 500);
                } else {
                    alert("更新失败")
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
