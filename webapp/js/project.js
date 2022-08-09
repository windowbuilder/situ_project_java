var pageNo = 1;
var pageCount = 5;
$(document).ready(function(){
	$("#first").click(function() {
		pageNo = 1;
		$("#text").text("第" + pageNo + "页，共5页")
	});
	$("#previous").click(function() {
		pageNo -= 1;
		if (pageNo < 1) {
			pageNo = 1;
		}
		$("#text").text("第" + pageNo + "页，共5页")
	});
	$("#next").click(function() {
		pageNo += 1;
		if (pageNo < 1) {
			pageNo = 1;
		}
		if (pageNo > 5) {
			pageNo = 5;
		}
		$("#text").text("第" + pageNo + "页，共5页")
	});
	$("#last").click(function() {
		pageNo = 5;
		$("#text").text("第" + pageNo + "页，共5页")
	});
	$(".update").on("click",function(){
		layer.open({
			type:2,
			title:"信息",
			content:"popUp.html",
			area: ['640px', '750px']
		})
	});
	$(".submit").on("click",function(){
		layer.open({
			type:2,
			title:"信息",
			content:"popUp.html",
			area: ['640px', '750px']
		})
	});
	$(".update1").on("click",function(){
		layer.open({
			type:2,
			title:"信息",
			content:"popUp1.html",
			area: ['455px', '250px']
		})
	});
	$(".submit1").on("click",function(){
		layer.open({
			type:2,
			title:"信息",
			content:"popUp1.html",
			area: ['455px', '250px']
		})
	});
	$(".b1").on("click",function(){
		parent.layer.closeAll();
	})
});

