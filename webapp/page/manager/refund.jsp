<%--
  Created by IntelliJ IDEA.
  User: 飞扬
  Date: 2022/7/26
  Time: 9:55
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
    <title>退款界面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <style>
        table td{
            text-align: center;
        }
        .layui-table th{
            text-align: center;
        }
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min-2.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<div style="width: 97%; margin: 20px auto;">
    <div class="layui-input-block">
        <span style="font-size: 25px;font-weight: bold; margin-left: -110px;">列表</span>
    </div>
    <div class="layui-input-inline">
        <button class="layui-btn" onclick="refu()">一键退款</button>
    </div>
    <table class="layui-table" id="tab1" title="上架商品表单">
        <thead>
        <tr>
            <th><input type="checkbox" onclick="getAll(this)" value="0"/></th>
            <th>订单号</th>
            <th>用户id</th>
            <th>用户名</th>
            <th>交易金额</th>
            <th>交易时间</th>
            <th>订单状态</th>
            <th>退款时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${map.get('list')}" var="ord">
            <tr>
                <td><input type="checkbox" id="name" name="orderNo" value="${ord.orderNo}"></td>
                <td>${ord.orderNo}</td>
                <td>${ord.uId}</td>
                <td>${ord.uName}</td>
                <td>${ord.orderMoney}</td>
                <td><f:formatDate value="${ord.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>
                    <c:if test="${ord.orderStatus == 1}">待付款</c:if>
                    <c:if test="${ord.orderStatus == 2}">待发货</c:if>
                    <c:if test="${ord.orderStatus == 3}">已发货</c:if>
                    <c:if test="${ord.orderStatus == 4}">已签收</c:if>
                    <c:if test="${ord.orderStatus == 5}">退款中</c:if>
                    <c:if test="${ord.orderStatus == 6}">已退款</c:if>
                    <c:if test="${ord.orderStatus == 7}">交易结束</c:if>
                </td>
                <td><f:formatDate value="${ord.deleteTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
        window.location.href = "ref?pageNo="+pageNo;
    }

    //全选，取消全选
    function getAll(obj) {
        if($(obj).val() == "0"){
            $(obj).val("1");
            $(":checkbox[name='orderNo']").prop("checked",true);
        }else {
            $(obj).val("0");
            $(":checkbox[name='orderNo']").prop("checked",false);
        }
    }

    //退款
    function refu(){
        var arr = [];	//声明一个数组用来存放遍历出来的checkbox value值
        $("input[name='orderNo']:checked").each(function(i){	//遍历
            arr.push($(this).val());	//将我们遍历出来的值push到数组中
            //最后可以提交arr就可以实现将我们选中的checkbox的value值提交
        })
        var params = "arr=" + arr;
        $.ajax({
            url:"refu",//指定请求跳转的路径
            data:params,//请求提交的数据
            type:"POST",//请求提交的方式
            success:function(str){
                if (str == "0"){
                    alert("退款失败");
                }else if (str == "-1"){
                    alert("您没有框选订单，请先框选订单");
                }else {
                    alert("退款成功");
                    window.location.href="ref"
                }
            }
        });
    }

</script>
</html>