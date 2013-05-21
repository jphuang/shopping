$(document).ready(function() {
	$("#cartUpdate").live("click",function(){
		var text=$(this).text();
		if(text=="修改数量"){
			var count = $(this).parent().prev().text();
			$(this).parent().prev().html("<input style='width:35px' min=1 type='number' value="+count +" />");
			$(this).text("确定");
		}else if(text=="确定"){
			var count2 = $(this).parent().prev().children().val();
			var id = $(this).parent().prev().prev().prev().prev().text();
			$.post("/cart/update", {
				"productId" : id,"count":count2
			}, function(data) {
				if(data=="ok"){
					$("#p" + id).next().next().next().text(count2);
					$("#p" + id).nextAll().find("button").text("修改数量");
				}else{
					alert("修改失败");
				}
			});
			
			
		}
	});
	
	$("#cartdelete").live("click",function(){
		var id = $(this).parent().prev().prev().prev().prev().prev().text();
		var isdelete = confirm("确定删除");
		if(isdelete){
			$.post("/cart/deleteItem", {
				"productId" : id
			}, function(data) {
				if(data=="ok"){
					alert("删除成功");
					$("#p" + id).parent().remove();
				}else{
					alert("删除失败");
				}
			});
		}
		return false;
	});
	
});