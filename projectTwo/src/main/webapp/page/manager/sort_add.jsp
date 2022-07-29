<%--
  Created by IntelliJ IDEA.
  User: 飞扬
  Date: 2022/7/27
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %> <!-- isELIgnored=true禁用表达式 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min-2.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
    <script type="text/javascript">
        function addObj(pId){
            var newSortName = $("#newSortName").val();
            var params = "sName="+newSortName;
            if(pId != undefined && pId != null){
                params += "&parentId="+pId;
            }else {
                params += "&parentId=-1";
            }
            console.info(params);
            $.ajax({
                url: "addP",
                data: params,
                type: "POST",
                success: function(code){
                    if(code == '1'){
                        alert("分类添加成功");
                    } else {
                        alert("分类添加失败");
                    }
                    //延迟关闭
                    setTimeout(closeLayer(), 500);
                }
            });
        }

        function closeLayer(){
            //关闭弹出层
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
            //刷新父页面
            window.parent.location.reload();
        }
    </script>
</head>
<body>
<div style="width: 80%; margin: 0 auto;">
    <form class="layui-form">
        <table style="width:100%;">
            <c:if test="${pName != ''}">
                <div class="layui-item">
                    <tr>
                        <td>父级分类</td>
                        <td>${pName}</td>
                    </tr>
                </div>
            </c:if>

            <div class="layui-item">
                <tr>
                    <td>新增分类</td>
                    <td><input type="text" value="" class="layui-input" id="newSortName"></td>
                </tr>
            </div>
        </table>
    </form>
    <br>
    <button class="layui-btn layui-btn-fluid" onclick="addObj(${pId});">添加</button>
</div>

</body>
</html>