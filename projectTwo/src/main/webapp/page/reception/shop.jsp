<%--
  Created by IntelliJ IDEA.
  User: 飞扬
  Date: 2022/8/3
  Time: 11:12
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
    <title>商品界面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min-2.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<div style="width: 80%; margin: 60px auto;">
    <input type="hidden" name="sid" id="sid" value="${sid}">
    <table id="tab1" style="border: 1px;color: black;" title="管理员表单">
        <tbody>
        <c:forEach items="${map.get('list')}" var="goo" varStatus="status">
            <c:if test="${(status.count-1)%5 == 0}"><tr></c:if>
                <td style="width: 272px">
                    <div style="width: 150px;height: 200px;" onclick="det(${goo.id})">
                        <div><img src="${goo.imgUrl}" style="width: 130px;height: 100px;" /></div>
                        <div style="float: left;">${goo.gName}</div>
                        <div style="clear:both;"></div>
                        <div>
                            <div style="float: left;">价格${goo.gPrice}</div>
                            <div style="float: right;">库存${goo.gCount}</div>
                        </div>
                    </div>
                </td>
            <c:if test="${(status.count)%5 == 0 || map.get('list').size() == status.count}"></tr></c:if>
        </c:forEach>
        </tbody>
    </table>
    <div style="margin: 0 auto;width: 30%;">
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
        var $=layui.jquery;
        var sid = $("#sid").val();
        if (id != null && id != ""){
            window.location.href = "ques?pageNo="+pageNo+"&sid="+sid;
        }else {
            window.location.href = "ques?pageNo="+pageNo;
        }
    }

    //点击进入商品详情界面
    function det(id){
        window.parent.location.href = "quo?id="+id;
    }
</script>
</html>

