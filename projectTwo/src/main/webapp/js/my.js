$(document).ready(function(){
    $(".title-l").addClass("layui-icon layui-icon-triangle-r");
    $(".title-d").addClass("layui-icon layui-icon-triangle-d");
    $(".icon-add").addClass("layui-icon layui-icon-add-1");
    $(".icon-sub").addClass("layui-icon layui-icon-subtraction");
});
function getKidEle(id, name){
    var ele = "<div class='kid'><span class='my-font'>";
    ele += "<i class='title-l' onclick='selectKid(this, "+id+");'></i>";
    ele += "<i id='"+name+"' ondblclick='up(this,\""+id+"\")'><span>"+name+"</span></i>";
    ele += "<i class='icon-add' onclick='openAddLayer("+id+",&quot;"+name+"&quot;);'></i>";
    ele += "<i class='icon-sub' onclick='del("+id+");'></i>";
    ele += "</span></div>";
    return ele;
}