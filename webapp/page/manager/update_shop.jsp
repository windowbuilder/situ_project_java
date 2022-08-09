<%--
  Created by IntelliJ IDEA.
  User: 飞扬
  Date: 2022/7/24
  Time: 15:39
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
    <title>商品更新界面</title>
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
<div style="margin-left: 70px;margin-top: 20px;">
    <form class="layui-form">
        <input type="hidden" value="${goods.id}" name="id">
        <input type="hidden" name="imgUrl" id="imgUrl">
        <div class="layui-form-item">
            <div class="layui-input-inline">
                <input type="text" name="gName" value="${goods.gName}" disabled="disabled" placeholder="商品名称"
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-input-inline" style="width: 120px">
                <button type="button" class="layui-btn" id="imgUpload">
                    <i class="layui-icon">&#xe67c;</i>上传新图片
                </button>
            </div>
            <div class="layui-input-inline layui-upload-list" style="width: 70px;">
                <p id="demoText">
                    <span>未上传</span>
                </p>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-inline">
                <input type="text" name="gCount" value="${goods.gCount}" placeholder="库存"
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-input-inline">
                <input type="text" name="gPrice" value="${goods.gPrice}" placeholder="单价"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
    </form>
    <div class="b3">
        <div class="layui-input-block">
            <button class="layui-btn" onclick="upd()" style="width: 390px;margin-left: -110px;">更新</button>
        </div>
    </div>
</div>
</body>
<script>
    //普通图片上传
    layui.use(['layer','upload'], function(){
        var layer = layui.layer;
        var upload = layui.upload;
        upload.render({
            elem: '#imgUpload' //绑定元素
            ,url: 'upload' //上传接口
            ,accept: 'images' //指定允许上传时校验的文件类型，可选值有：images（图片）、file（所有文件）、video（视频）、audio（音频）
            ,acceptMime: 'image/*',//规定打开文件选择框时，筛选出的文件类型，值为用逗号隔开的 MIME 类型列表
            done: function(res){
                console.info(res)
                //如果上传失败
                if(res.code != 1){
                    return layer.msg('上传失败');
                }
                //上传成功
                var demoText = $('#demoText');
                demoText.html('<span style="color: #4cae4c;">上传成功</span>');
                var imgUrl = $("#imgUrl");
                imgUrl.attr("value",res.imgUrl);
                console.info(res.imgUrl);
            },
            error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span>');
            }
        });
    });

    //更新
    function upd(){
        var params = $("form").serialize();
        $.ajax({
            url:"upda",//指定请求跳转的路径
            data:params,//请求提交的数据
            type:"POST",//请求提交的方式
            success:function(str){
                //请求发送成功回调函数
                if (str) {
                    alert("更新成功");
                    setTimeout(closeLayer(), 500);
                }else {
                    alert("更新失败");
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