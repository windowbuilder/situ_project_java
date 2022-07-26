<%--
  Created by IntelliJ IDEA.
  User: 飞扬
  Date: 2022/7/21
  Time: 19:46
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
    <title>管理员列表界面</title>
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
    <div class="layui-input-inline">
        <button class="layui-btn" onclick="add()">添加</button>
    </div>
    <table class="layui-table" id="tab1" title="管理员表单">
        <thead>
        <tr>
            <th>序号</th>
            <th>id</th>
            <th>名称</th>
            <th>密码</th>
            <th>权限</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${map.get('list')}" var="man">
            <tr>
                <td>${map.get('list').indexOf(man)+1}</td>
                <td>${man.id}</td>
                <td>${man.mName}</td>
                <td>${man.mPass}</td>
                <td>
                    <c:if test="${man.mLevel == 1}">超级管理员</c:if>
                    <c:if test="${man.mLevel == 0}">管理员</c:if>
                </td>
                <c:if test="${man.mLevel == 1}">
                    <td> </td>
                    <td> </td>
                </c:if>
                <c:if test="${man.mLevel == 0}">
                    <td>
                        <c:if test="${man.mStatus == 1}"><span style="color: green">启用</span></c:if>
                        <c:if test="${man.mStatus == 0}"><span style="color: red">禁用</span></c:if>
                    </td>
                    <td>
                        <button type="button" class="layui-btn <c:if test='${man.mStatus == 1}'>layui-bg-orange</c:if>"
                                onclick="update(${man.id},${man.mStatus})">
                            <c:if test="${man.mStatus == 1}">禁用</c:if>
                            <c:if test="${man.mStatus == 0}">启用</c:if>
                        </button>
                    </td>
                </c:if>
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
    //添加管理员
    function add(){
        layer.open({
            type:2,
            title:"添加管理员",
            content:"adPage",
            area: ['455px', '250px']
        });
    }

    //分页
    function queryByPage(pageNo){
        window.location.href = "que?pageNo="+pageNo;
    }

    //启用、禁用
    function update(id,mStatus){
        var st = "禁用";
        var params = "id="+id+"&mStatus="+mStatus;
        $.ajax({
            url:"update",//指定请求跳转的路径
            data:params,//请求提交的数据
            type:"POST",//请求提交的方式
            success:function(str){
                if (mStatus == 0){
                    st = "启用";
                }
                if (str == "1"){
                    alert(st + "成功");
                    window.location.href="que";
                }else {
                    alert(st + "失败");
                }
            }
        });
    }

</script>
</html>