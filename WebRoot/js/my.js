function isDelete() {
	$(".alert").alert("hehe");
	// return confirm('确定删除?');
}

function chkUsername() {
	var username = $.trim($("#username").val());
	if (username == "") {
		return 0;
	} else if (/^\d.*$/.test(username)) {
		// 用户名不能以数字开头
		return -1;
	} else if (username.length < 6 || username.length > 18) {
		// 合法长度为6-18个字符
		return -2;
	} else if (!/^\w+$/.test(username)) {
		// 用户名只能包含_,英文字母，数字
		return -3;
	} else if (!/^([a-z]|[A-Z])[0-9a-zA-Z_]+$/.test(username)) {
		// 用户名只能英文字母开头
		return -4;
	} else if (!(/[0-9a-zA-Z]+$/.test(username))) {
		// 用户名只能英文字母或数字结尾
		return -5;
	}
	return 1;
}

function chkPassword() {
	var password = $.trim($("#password").val());
	var password1 = $.trim($("#password1").val());
	
	if (password == "" || password1 == "") {
		return 0;
	} else if (username.length < 6 || username.length > 18) {
		// 合法长度为6-18个字符
		return -1;
	} else if (password != password1){
		//重复密码要一致
		return -2;
	}
	   return 1;
}

$(document).ready(function() {
	
	/** ----------- 用户名输入框事件 ----------- */
	// 当文本框成为焦点时
	$("#div_uname_rule").hide();
	$("#username").bind("focus", function() {

		var ret = chkUsername();
		if (ret == 0) {
			// 用户名输入框为空,显示规则
			$("#div_uname_err_info").hide();
			$("#div_uname_rule").show();
		}
		return false;
	});

	// 当文本框失去焦点时
	$("#username").bind("blur",function() {
			var ret = chkUsername();
			$("#div_uname_rule").hide();
			$("#div_uname_err_info").show();
			if (ret > 0) {
				var url = "verify?name="
						+ $("#username").val();
				$.get(url, null, callback);
			} else if (ret == 0) {
				// 用户名输入框为空,显示规则
				$("#div_uname_err_info")
						.html(
								"<br/><span class='alert  alert-error'>用户名不能为空<spn>");
			} else {

				if (ret == -1) {
					// 显示具体的错误内容
					$("#div_uname_err_info")
							.html(
									"<br/><span class='alert  alert-error'>用户名不能以数字开头<spn>");
				} else if (ret == -2) {
					$("#div_uname_err_info")
							.html(
									"<br/><span class='alert  alert-error'>合法长度为6-18个字符<spn>");
				} else if (ret == -3) {
					$("#div_uname_err_info")
							.html(
									"<br/><span class='alert  alert-error'>用户名只能包含_,英文字母,数字<spn>");
				} else if (ret == -4) {
					$("#div_uname_err_info")
							.html(
									"<br/><span class='alert  alert-error'>用户名只能英文字母开头<spn>");
				} else if (ret == -5) {
					$("#div_uname_err_info")
							.html(
									"<br/><span class='alert  alert-error'>用户名只能英文字母或数字结尾<spn>");
				}
			}

			return false;
		});
	});
function verify() {
	var ret = chkUsername();
	if (ret > 0) {
		var url = "verify?name=" + $("#username").val();
		$.get(url, null, callback); // 将文本框 中的数据发送到页面上
	}
}
// 接收服务器返回的数值，将服务器返回的数据动态的显示在页面上
function callback(data) {
	var resultObj = $("#div_uname_err_info");
	var str;
	if (data == "yes") {
		str = "<br/><span class='alert alert-success'>可以使用这个用户名</span>";
	} else {
		str = "<br/><span class='alert alert-error'>用户名已存在</span>";
	}
	resultObj.html(str);
}