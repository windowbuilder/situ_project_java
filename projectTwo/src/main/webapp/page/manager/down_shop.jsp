<%--
  Created by IntelliJ IDEA.
  User: 飞扬
  Date: 2022/7/25
  Time: 9:12
  To change this template use File | Settings | File Templates.
--%>
<%@page isELIgnored="false" %>
<%@page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>下架商品界面</title>
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
        <span style="font-size: 25px;font-weight: bold; margin-left: -110px;">查询</span>
    </div>
    <form class="layui-form">
        <div class="layui-form-item">
            <div class="layui-input-inline">
                <input type="text" name="gName" value="${gName}" placeholder="商品名称"
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-input-inline">
                <select name="soName" lay-filter="f1">
                    <option value="">一级分类</option>
                    <c:forEach items="${list}" var="gs">
                        <option <c:if test="${soName == gs.sName}">selected=true</c:if>
                                value="${gs.sName}">${gs.sName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="layui-input-inline">
                <select name="sName" id="sName">
                    <option value="">二级分类</option>
                </select>
            </div>
            <div class="layui-input-inline">
                <button class="layui-btn">查询</button>
            </div>
        </div>
    </form>
    <hr  class="layui-border-black"/>
    <div class="layui-input-block">
        <span style="font-size: 25px;font-weight: bold; margin-left: -110px;">列表</span>
    </div>
    <div class="layui-input-inline">
        <button class="layui-btn" onclick="up()">一键上架</button>
    </div>
    <table class="layui-table" id="tab1" title="上架商品表单">
        <thead>
        <tr>
            <th><input type="checkbox" onclick="getAll(this)" value="0"/></th>
            <th>商品名称</th>
            <th>库存</th>
            <th>单价</th>
            <th>所属分类</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${map.get('list')}" var="goo">
            <tr>
                <td><input type="checkbox" id="name" name="id" value="${goo.id}"></td>
                <td>${goo.gName}</td>
                <td>${goo.gCount}</td>
                <td>${goo.gPrice}</td>
                <td>${goo.sName}</td>
                <td>
                    <button type="button" class="layui-btn" onclick="update(${goo.id})">更新</button>
                </td>
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
        window.location.href = "qud?pageNo="+pageNo;
    }

    //全选，取消全选
    function getAll(obj) {
        if($(obj).val() == "0"){
            $(obj).val("1");
            $(":checkbox[name='id']").prop("checked",true);
        }else {
            $(obj).val("0");
            $(":checkbox[name='id']").prop("checked",false);
        }
    }

    //上架
    function up(){
        var arr = [];	//声明一个数组用来存放遍历出来的checkbox value值
        $("input[name='id']:checked").each(function(i){	//遍历
            arr.push($(this).val());	//将我们遍历出来的值push到数组中
            //最后可以提交arr就可以实现将我们选中的checkbox的value值提交
        })
        var params = "arr=" + arr;
        $.ajax({
            url:"dou",//指定请求跳转的路径
            data:params,//请求提交的数据
            type:"POST",//请求提交的方式
            success:function(str){
                if (str == "0"){
                    alert("下架失败");
                }else if (str == "-1"){
                    alert("您没有框选商品，请先框选商品");
                }else {
                    alert("下架成功");
                    window.location.href="qud"
                }
            }
        });
    }

    //二级联动
    layui.use(['layer','form'], function() {
        var layer = layui.layer;
        var form = layui.form;
        form.on('select(f1)',function(data){

            var params = "sName=" + data.value;
            $.ajax({
                url:"two",//指定请求跳转的路径
                data:params,//请求提交的数据
                type:"POST",//请求提交的方式
                success:function(str){
                    //请求发送成功回调函数
                    var arr = JSON.parse(str);
                    $("#sName").empty();
                    $.each(arr,function(index,item){
                        console.info(item);
                        $("#sName").append("<option value='"+item.sName+"'>"+item.sName+"</option>");
                    });
                    form.render('select');
                }
            });
        });
    });

    //更新
    function update(id){
        layer.open({
            type:2,
            title:"更新管理员",
            content:"updPage?id="+id,
            area: ['520px', '250px']
        });
    }
</script>
</html>
