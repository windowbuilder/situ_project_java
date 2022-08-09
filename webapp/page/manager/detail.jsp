<%--
  Created by IntelliJ IDEA.
  User: 飞扬
  Date: 2022/7/25
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@page isELIgnored="false" %>
<%@page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>商品列表界面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <style>
        table td{
            text-align: center;
        }
        .layui-table th{
            text-align: center;
        }
        a:hover{
            text-decoration: underline;
        }
        span{
            margin-top: 10px;
            font-size: 18px;
            height: 40px;
        }
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min-2.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<div style="width: 97%; margin: 20px auto;">
    <div class="layui-input-block">
        <span style="font-size: 25px;font-weight: bold; margin-left: -110px;">订单详情</span>
    </div>
    <form class="layui-form">
        <div class="layui-form-item">
            <div class="layui-input-inline" style="width: 290px;">
                <span>订单号：${list1.get(0).orderNo}</span>
            </div>
            <div class="layui-input-inline">
                <span>用户ID：${list1.get(0).uId}</span>
            </div>
            <div class="layui-input-inline">
                <span>用户名：${list1.get(0).uName}</span>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-inline" style="width: 290px;">
                <span>交易金额：${list1.get(0).orderMoney}</span>
            </div>
            <div class="layui-input-inline">
                <span>订单状态：
                    <c:if test="${list1.get(0).orderStatus == 1}">待付款</c:if>
                    <c:if test="${list1.get(0).orderStatus == 2}">待发货</c:if>
                    <c:if test="${list1.get(0).orderStatus == 3}">已发货</c:if>
                    <c:if test="${list1.get(0).orderStatus == 4}">已签收</c:if>
                    <c:if test="${list1.get(0).orderStatus == 5}">退款中</c:if>
                    <c:if test="${list1.get(0).orderStatus == 6}">已退款</c:if>
                    <c:if test="${list1.get(0).orderStatus == 7}">交易结束</c:if>
                </span>
            </div>
            <div class="layui-input-inline" style="width: 290px;">
                <span>交易时间：<f:formatDate value="${list1.get(0).createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
            </div>
        </div>
    </form>
    <hr class="layui-border-black"/>
    <div class="layui-input-block">
        <span style="font-size: 25px;font-weight: bold; margin-left: -110px;">商品列表</span>
    </div>
    <table class="layui-table" id="tab1" title="订单详情表单">
        <thead>
        <tr>
            <th>序号</th>
            <th>商品id</th>
            <th>商品名</th>
            <th>单价</th>
            <th>购买数量</th>
            <th>金额</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="det">
            <tr>
                <td>${list.indexOf(det)+1}</td>
                <td>${det.gId}</td>
                <td>${det.gName}</td>
                <td>${det.gPrice}</td>
                <td>${det.buyCount}</td>
                <td>${det.totalMoney}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div style="margin: 0 auto;width: 24%;">
        <div class="layui-input-inline "><button type="button" class="layui-btn layui-btn-primary layui-border-green" onclick="queryByPage(1)">首页</button></div>
        <div class="layui-input-inline"><button type="button" class="layui-btn" onclick="queryByPage(${map.get("pageNo")-1})">上一页</button></div>
        <a id="text">第${map.get("pageNo")}页，共${map.get("totalPage")}页</a>
        <div class="layui-input-inline"><button type="button" class="layui-btn" onclick="queryByPage(${map.get("pageNo")+1})">下一页</button></div>
        <div class="layui-input-inline"><button type="button" class="layui-btn layui-btn-primary layui-border-green" onclick="queryByPage(${map.get("totalPage")})">尾页</button></div>
    </div>
</div>
</body>
<script>
    //分页
    function queryByPage(pageNo){
        var orderNo = "${list1.get(0).orderNo}";
        window.location.href = "look?pageNo="+pageNo+"&orderNo="+orderNo;
    }
</script>
</html>
