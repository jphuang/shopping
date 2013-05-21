$(document).ready(function() {
	$("#cartUpdate").live("click",function(){
		var text=$(this).text();
		if(text=="修改数量"){
			var count = $(this).parent().prev().text();
			$(this).parent().prev().html("<input style='width:35px' type='number' value="+count +" />");
			$(this).text("确定");
		}else if(text=="确定"){
			var count2 = $(this).parent().prev().children().val();
			$(this).parent().prev().text(count2);
			$(this).text("修改数量");
		}
	});
	
	$("#cartdelete").live("click",function(){
		var isdelete = confirm("确定删除");
		if(isdelete){
			$(this).parent().parent().remove();
		}
		return false;
	});
	
});