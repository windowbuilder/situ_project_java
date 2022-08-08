<%--
  Created by IntelliJ IDEA.
  User: 飞扬
  Date: 2022/8/2
  Time: 20:31
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
    <title>商品分类界面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <style>
        table td{
            text-align: center;
        }
        table tr{
            height: 210px;
        }
        #left_nav {
            position: relative;
            width: 200px;
            height: 822px;
            float: left;
            margin: 0;
        }
        #left_nav ul li {
            height: 50px;
            line-height: 50px;
            padding-left: 20px;
        }
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min-2.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<div class="layui-panel" id="left_nav">
    <ul class="layui-menu" id="docDemoMenu1">
        <c:forEach items="${list}" var="gs">
            <li class="layui-menu-item-parent" lay-options="{type: 'parent'}">
                <div class="layui-menu-body-title">
                    <a class="child1" target="t3" href="ques?sid=${gs.id}" onclick="spa(this)" onmouseover="di(this,${gs.id})">${gs.sName}</a>
                    <i class="layui-icon layui-icon-right"></i>
                </div>
                <div class="layui-panel layui-menu-body-panel"><ul></ul></div>
            </li>
        </c:forEach>
    </ul>
</div>
<div style="height: 824px;margin: 0;overflow: hidden;background-color: lavender;">
    <iframe name="t3" frameBorder="0" style="width: 100%;height: 824px;"
            <c:if test="${gName != null && gName != ''}">src="ques?gName=${gName}" </c:if>
            <c:if test="${gName == null || gName == ''}">src="ques" </c:if>
            scrolling="no" id="myiframe">

    </iframe>
</div>
</body>
<script>
    //点击变色
    function spa(da){
        $(".child1").css("color","black");
        $(da).css("color","green");
    }

    //二级联动
    function di(da,soId){
        var di = $(da).parent().next().find("ul");
        var params = "id=" + soId;
        var bol = di.hasClass("hasQuery");
        if(!bol){
            $.ajax({
                url:"two",//指定请求跳转的路径
                data:params,//请求提交的数据
                type:"POST",//请求提交的方式
                success:function(str){
                    //请求发送成功回调函数
                    var arr = JSON.parse(str);
                    di.empty();
                    $.each(arr,function(index,item){
                        di.append("<li lay-options='{id: 1051}'><div class='layui-menu-body-title'>"+
                            "<a class='child1' target='t3' href='ques?sid="+item.id+"' onclick='spa(this)' onmouseover='di(this,"+item.id+")'>"+
                            item.sName+"</a>"+ "</div></li>");
                    });
                    di.addClass("hasQuery")
                }
            });
        }
    }
</script>
</html>