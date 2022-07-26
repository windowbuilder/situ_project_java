<%--
  Created by IntelliJ IDEA.
  User: 飞扬
  Date: 2022/7/25
  Time: 9:36
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
    <title>商品添加界面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <style>
        table{
            border-spacing: 0;
        }
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min-2.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<div style="width: 97%; margin: 20px auto;">
    <div class="layui-container">
        <div style="border: 1px solid black;width: 410px;height: 220px;padding-top: 20px;padding-left: 20px;background-color: aliceblue;">
            <form class="layui-form" action="">
                <div class="layui-form-item">
                    <div class="layui-input-inline">
                        <input type="text" name="gName" placeholder="商品名称"
                               autocomplete="off" class="layui-input">
                    </div>
                    <input type="hidden" name="images" class="image">
                    <div class="layui-input-inline" style="width: 100px;">
                        <button class="layui-btn layui-btn-radius" id="test1">上传图片</button>
                    </div>
                    <div class="layui-input-inline layui-upload-list" style="width: 70px;">
                        <p id="demoText">
                            <span>未上传图片</span>
                        </p>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-inline">
                        <input type="text" name="gCount" placeholder="库存"
                               autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-input-inline">
                        <input type="text" name="gPrice" placeholder="单价"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
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
                </div>
            </form>
            <div class="b3">
                <div class="layui-input-block">
                    <button class="layui-btn" onclick="add()" style="width: 390px;margin-left: -110px;">添加</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    //二级联动
    layui.use(['layer','form'], function() {
        var layer = layui.layer;
        var form = layui.form;
        form.on('select(f1)',function(data){
            console.info(data.elem);
            console.info(data.value);

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
                        $("#sName").append("<option value='"+item.id+"'>"+item.sName+"</option>");
                    });
                    form.render('select');
                }
            });
        });
    });

    //普通图片上传
    layui.use('upload', function(){
        var $ = layui.jquery,upload = layui.upload;
        var uploadInst = upload.render({
            elem: '#test1',
            url: 'upload',//改成您自己的上传接口
            done: function(res){
                //如果上传失败
                if(res.code > 0){
                    return layer.msg('上传失败');
                }
                //上传成功
                var demoText = $('#demoText');
                demoText.html('<span style="color: #4cae4c;">上传成功</span>');

                var fileupload = $(".image");
                fileupload.attr("value",res.data.src);
                console.log(fileupload.attr("value"));
            },
            error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        });
    });

    //添加
    function add(){
        var params = $("form").serialize();
        $.ajax({
            url:"adds",//指定请求跳转的路径
            data:params,//请求提交的数据
            type:"POST",//请求提交的方式
            success:function(str){
                if (str == 1){
                    alert("添加成功");
                    setTimeout(closeLayer(), 500);
                }else {
                    alert("该用户名已存在请重新尝试");
                }
            }
        });
    }
    function closeLayer() {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
        window.parent.location.reload();
    }
</script>
</html>