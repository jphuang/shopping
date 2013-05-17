$(document).ready(function() {
	$("#delete").live("click",function(){
		var id = $(this).attr("href");
		if(confirm("确认删除 类别吗？")){
			$.post("/product/delete", {
				"id" : id
			}, function(data, textStatus) {
				if(data=="yes"){
					alert("删除成功");
					$("#delete").parent().parent().remove();
				}else{
					alert("删除不成功，请刷新页面");
				}
			});
		}
		return false;
	});
	
});