// 页面加载完成后执行下面
$(document).ready(function() {
	$("#opt").live("click",function(){
		var button = $(this);
		if(button.text()=="订单处理")
		{
			var optObj = button.parent().prev();
			otext = optObj.text();
			optObj.text("");
			if(otext=="未处理"){
			var $sel=$("<select/>")
				.append("<option value=0>未处理</option>")
				.append("<option value=1>已经处理</option>")
				.append("<option value=2>废单</option>");
			}
			if(otext=="已经处理"){
				var $sel=$("<select/>")
				.append("<option value=1>已经处理</option>")
				.append("<option value=2>废单</option>");
				button.removeClass();
				button.addClass("btn btn-success");
			}
			if(otext=="废单"){
				var $sel=$("<select/>")
				.append("<option value=2>废单</option>");
			}
			optObj.append($sel);
			button.text("确定");
			text = optObj.find("option:selected").text();
		}else if(button.text()=="确定"){
			var optObj = button.parent().prev();
			var text = optObj.find("option:selected").text();
			var val =  optObj.find("option:selected").val();
			var id = optObj.prev().prev().prev().text();
			$.post("/order/update",{"status":val,"orderid":id},function(data){
				if(data=="ok"){
					optObj.html(text);
					if(text=="未处理"){
						button.removeClass();
						button.addClass("btn btn-primary");
						button.text("订单处理");
					}
					if(text=="已经处理"){
						button.removeClass();
						button.addClass("btn btn-success");
						button.text("订单处理");
					}
					if(text=="废单"){
						button.removeClass();
						button.addClass("btn btn-danger");
						button.text("处理完成");
					}
				}else{
					alert("处理失败");
					window.location.reload();
				}
			})
			
		}
		
	});
});