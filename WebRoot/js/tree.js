var zTreeObj;
// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
var setting = {
	view : {
		showIcon : showIconForTree,
		addHoverDom : addHoverDom,
		removeHoverDom : removeHoverDom,
		selectedMulti : false
	},
	edit : {
		enable : true,
		editNameSelectAll : true,
		showRemoveBtn : showRemoveBtn,
		showRenameBtn : showRenameBtn
	},
	data : {
		simpleData : {
			enable : true,
			idKey : "id",
			pIdKey : "pid"
		}
	},
	callback : {
		beforeDrag : beforeDrag,
		beforeEditName : beforeEditName,
		beforeRemove : beforeRemove,
		beforeRename : beforeRename,
		onRemove : onRemove,
		onRename : onRename
	}
};
// zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
$(document).ready(function() {
	$.post("/category/ajax", {
		"ajax" : "ok"
	}, function(data, textStatus) {
		// data 可以是 xmlDoc, jsonObj, html, text, 等等.
		// this; // 这个Ajax请求的选项配置信息，请参考jQuery.get()说到的this
		zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, data);
	}, "json");
	$("#selectAll").bind("click", selectAll);
});

function showIconForTree(treeId, treeNode) {
	if (treeNode.isParent) {
		return false;
	}
};

var className = "dark";
function beforeDrag(treeId, treeNodes) {
	return false;
}
function beforeEditName(treeId, treeNode) {
	className = (className === "dark" ? "" : "dark");
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.selectNode(treeNode);
	return confirm("要修改类别（ " + treeNode.name + "）的名字吗？");
}
function beforeRemove(treeId, treeNode) {
	className = (className === "dark" ? "" : "dark");
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.selectNode(treeNode);
	return confirm("确认删除 类别( " + treeNode.name + ")吗？");
}
function onRemove(e, treeId, treeNode) {
	$.post("/category/delete", {
		"id" : treeNode.id
	}, function(data, textStatus) {
		if(data=="yes"){
			alert("删除成功");
		}else{
			alert("删除不成功，请刷新页面");
		}
	});
}
function beforeRename(treeId, treeNode, newName) {
	className = (className === "dark" ? "" : "dark");
	if (newName.length == 0) {
		alert("节点名称不能为空.");
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		setTimeout(function() {
			zTree.editName(treeNode)
		}, 10);
		return false;
	}
	return true;
}
function onRename(e, treeId, treeNode) {
	$.post("/category/update", {
		"id" : treeNode.id,"name":treeNode.name
	}, function(data, textStatus) {
		if(data=="yes"){
			alert("修改成功");
		}else{
			alert("修改不成功，请刷新页面");
		}
	});
	
}
function showRemoveBtn(treeId, treeNode) {
	return !treeNode.isParent;
}
function showRenameBtn(treeId, treeNode) {
	return true;
}
function getTime() {
	var now = new Date(), h = now.getHours(), m = now.getMinutes(), s = now
			.getSeconds(), ms = now.getMilliseconds();
	return (h + ":" + m + ":" + s + " " + ms);
}

var newCount = 1;
function addHoverDom(treeId, treeNode) {
	var sObj = $("#" + treeNode.tId + "_span");
	if (treeNode.editNameFlag || $("#addBtn_" + treeNode.id).length > 0)
		return;
	var addStr = "<span class='button add' id='addBtn_" + treeNode.id
			+ "' title='添加类别' onfocus='this.blur();'></span>";
	sObj.after(addStr);
	var btn = $("#addBtn_" + treeNode.id);
	if (btn)
		btn.bind("click", function() {
			name = prompt("请输入类别名称");
			descr = prompt("请输入类别描述信息");
			$.post("/category/add", {
				"pid" : treeNode.id,"name":name,"descr":descr
			}, function(data, textStatus) {
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.addNodes(treeNode, {
					id : data,
					pId : treeNode.id,
					name : name,
					descr:descr
				});
			});
			return false;
		});
};
function removeHoverDom(treeId, treeNode) {
	$("#addBtn_" + treeNode.id).unbind().remove();
};
function selectAll() {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.setting.edit.editNameSelectAll = $("#selectAll").attr("checked");
};