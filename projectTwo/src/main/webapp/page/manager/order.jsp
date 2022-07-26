<%--
  Created by IntelliJ IDEA.
  User: 飞扬
  Date: 2022/7/25
  Time: 16:14
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
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min-2.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<div style="width: 97%; margin: 20px auto;">
    <div class="layui-input-block">
        <span style="font-size: 25px;font-weight: bold; margin-left: -110px;">查询</span>
    </div>
    <form class="layui-form">
        <div class="layui-form-item">
            <div class="layui-input-inline">
                <input type="text" name="orderNo" value="${orderNo}" placeholder="订单号"
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-input-inline">
                <select name="orderStatus">
                    <option value="">订单状态</option>
                    <option value="1" <c:if test="${orderStatus == 1}">selected="true"</c:if>>待付款</option>
                    <option value="2" <c:if test="${orderStatus == 2}">selected="true"</c:if>>待发货</option>
                    <option value="3" <c:if test="${orderStatus == 3}">selected="true"</c:if>>已发货</option>
                    <option value="4" <c:if test="${orderStatus == 4}">selected="true"</c:if>>已签收</option>
                    <option value="5" <c:if test="${orderStatus == 5}">selected="true"</c:if>>退款中</option>
                    <option value="6" <c:if test="${orderStatus == 6}">selected="true"</c:if>>已退款</option>
                    <option value="7" <c:if test="${orderStatus == 7}">selected="true"</c:if>>交易结束</option>
                </select>
            </div>
            <div class="layui-input-inline">
                <button class="layui-btn">查询</button>
            </div>
        </div>
    </form>
    <hr class="layui-border-black"/>
    <div class="layui-input-block">
        <span style="font-size: 25px;font-weight: bold; margin-left: -110px;">列表</span>
    </div>
    <div class="layui-input-inline">
        <button class="layui-btn" onclick="set()">一键发货</button>
    </div>
    <table class="layui-table" id="tab1" title="订单信息表单">
        <thead>
        <tr>
            <th><input type="checkbox" onclick="getAll(this)" value="0"/></th>
            <th>订单号</th>
            <th>用户id</th>
            <th>用户名</th>
            <th>交易金额</th>
            <th>交易时间</th>
            <th>操作</th>
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
                <td>${ord.createTime}</td>
                <td><a href="look?orderNo"+${ord.orderNo}>查看</a></td>
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
        window.location.href = "quo?pageNo="+pageNo;
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

    //发货
    function set(){
        var arr = [];	//声明一个数组用来存放遍历出来的checkbox value值
        $("input[name='orderNo']:checked").each(function(i){	//遍历
            arr.push($(this).val());	//将我们遍历出来的值push到数组中
            //最后可以提交arr就可以实现将我们选中的checkbox的value值提交
        })
        var params = "arr=" + arr;
        $.ajax({
            url:"set",//指定请求跳转的路径
            data:params,//请求提交的数据
            type:"POST",//请求提交的方式
            success:function(str){
                if (str == "0"){
                    alert("发货失败");
                }else if (str == "-1"){
                    alert("您没有框选订单，请先框选订单");
                }else {
                    alert("发货成功");
                    window.location.href="quo"
                }
            }
        });
    }

</script>
</html>
