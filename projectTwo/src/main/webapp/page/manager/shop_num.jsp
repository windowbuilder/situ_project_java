<%--
  Created by IntelliJ IDEA.
  User: 飞扬
  Date: 2022/7/26
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@page isELIgnored="false" %>
<%@page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <style>
        .kid{
            margin-left: 20px;
        }
        .my-font{
            font-size: 16px;
            font-weight: bold;
        }
        .icon-add{
            color: blue;
        }
        .icon-sub{
            color: red;
        }
        .title{
            border: none;
            width: 70px;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min-2.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/my.js"></script>
</head>
<body>
<div style="width: 97%; margin: 20px auto;">
    <span class="my-font">
        <i class="title-d" onclick="test(this)"></i>
        root
        <i class="icon-add" onclick="openAddLayer('','')"></i>
    </span>
    <c:forEach items="${list}" var="s">
        <div class="kid">
            <span class="my-font">
                <i class="title-l" onclick="selectKid(this, ${s.id});"></i>
                <i id="${s.sName}" ondblclick="up(this,'${s.id}')"><span>${s.sName}</span></i>
                <i class="icon-add" onclick="openAddLayer(${s.id},'${s.sName}')"></i>
                <i class="icon-sub" onclick="del(${s.id})"></i>
            </span>
        </div>
    </c:forEach>
</div>
<script>
    //标签变换
    function test(obj){
        var bol = $(obj).hasClass("layui-icon-triangle-r");
        if(bol){
            $(obj).removeClass("layui-icon-triangle-r");
            $(obj).addClass("layui-icon-triangle-d");
            $(obj).parent().nextAll().show();
        } else {
            $(obj).removeClass("layui-icon-triangle-d");
            $(obj).addClass("layui-icon-triangle-r");
            $(obj).parent().nextAll().hide();
        }
    }
    //查询子分类
    function selectKid(obj, id){
        test(obj);
        var bol = $(obj).hasClass("hasQuery");
        if(!bol){
            $.ajax({
                url:"two",
                data: "id="+id,
                dataType:"json",
                type: "POST",
                success: function(d){
                    $.each(d,function(index, item){
                        $(obj).parent().parent().append(getKidEle(item.id, item.sName));
                    });
                    $(obj).addClass("hasQuery")
                    //重新加载js文件
                    $.getScript('${pageContext.request.contextPath}/js/my.js');
                }
            });
        }
    }
    layui.use("layer", function(){
        var layer = layui.layer;
    });

    //添加子分类弹层
    function openAddLayer(pId,pName){
        layer.open({
            type: 2,
            area: ['450px', '250px'],
            content: 'addPage?pId='+pId+"&pName="+pName
        });
    }

    //删除分类
    function del(id){
        $.ajax({
            url:"dels",
            data: "id="+id,
            type: "POST",
            success: function(d){
                if (d != "0"){
                    alert("删除成功");
                    window.location.href = "num";
                }else {
                    alert("删除失败");
                }
            }
        });
    }

    //更新事件
    function up(data,id){
        console.info(data);
        console.info(data.id);
        data.innerHTML = '<input type="text" class="'+id+'" onblur="ch(this,'+id+')" value="'+data.id+'">';

    }
    function ch(da,id){
        console.info(id);
        console.info(da.value);
        $.ajax({
            url:"upsn",
            data: "id="+id+"&sName="+da.value,
            type: "POST",
            success: function(d){
                if (d != "0"){
                    alert("更新成功");
                    window.location.href = "num";
                }else {
                    alert("更新失败");
                }
            }
        });
    }
</script>
</body>
</html>