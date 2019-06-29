//依赖于jquery-3.1.1  bootstrap.js
function addTree(data, selectorid, callback, elem) {
	selectorid = selectorid + (elem ? "-" + elem : '')
	$('#' + selectorid).empty();
	tree(data, selectorid);
	settree(callback, selectorid, elem);
}

function tree(data, selectorid) {
	if ($('#' + selectorid + ' .tree').length == 0) {
		$("#" + selectorid).append("<div class=\"tree\" style='left:2px'>\n" + "\n" + "    <ul id=\"root-tree\">\n" + "\n" + "    </ul>\n" + "</div>");
		// $("#" + selectorid).css("overflow-y", "auto");
		// $("#" + selectorid).css("overflow-x", "auto");
	}

	// if($(".tree:has(ul)").length>0)
	// {
	// $('.tree ul').attr("id","root-tree");
	// console.log($('.tree ul').attr("id")
	// );
	// }
	// else
	// {
	// $('.tree').append("<ul id='root-tree'></ul>");
	// console.log( $('.tree ul'));
	// }
	for (var i = 0; i < data.length; i++) {
		var data2 = data[i];
		if (data2.icon == "tree-icon-level-th") {
			$("#" + selectorid + " #root-tree").append(
					"<li data-name='" + data2.code + "'><span><i class='" + data2.icon + "'  id='" + data2.code + "'></i> " + data2.name + "</span></li>");
		} else {
			var children = $("#" + selectorid + " li[data-name='" + data2.parentCode + "']").children("ul");
			if (children.length == 0) {
				$("#" + selectorid + " li[data-name='" + data2.parentCode + "']").append("<ul></ul>")
			}
			$("#" + selectorid + " li[data-name='" + data2.parentCode + "'] > ul").append(
					"<li data-name='" + data2.code + "'>" + "<span>" + "<i class='" + data2.icon + "' id='" + data2.code + "'></i> " + data2.name + "</span>" + "</li>")
		}
		for (var j = 0; j < data2.child.length; j++) {
			var child = data2.child[j];
			var children = $("#" + selectorid + " li[data-name='" + child.parentCode + "']").children("ul");
			if (children.length == 0) {
				$("#" + selectorid + " li[data-name='" + child.parentCode + "']").append("<ul></ul>")
			}
			$("#" + selectorid + " li[data-name='" + child.parentCode + "'] > ul").append(
					"<li data-name='" + child.code + "'>" + "<span>" + "<i class='" + child.icon + "' id='" + child.code + "'></i> " + child.name + "</span>" + "</li>")
			var child2 = data2.child[j].child;
			tree(child2, selectorid)
		}
		tree(data2, selectorid);
	}
}

function settree(_callback, selectorid, elem) {
	$("#" + selectorid + ' .tree li:has(ul)').addClass('parent_li');
	$("#" + selectorid + " i[class^='tree-icon-level-']").attr("title", "关闭");
	$("#" + selectorid + ' .tree-icon-view-close-sign').attr("title", "显示");
	$("#" + selectorid + ' .tree-icon-view-open-sign').attr("title", "隐藏");
	$("#" + selectorid + ' .tree li.parent_li > span>i').on('click', function(e) {
		var children = $(this).parent('span').parent('li.parent_li').find(' > ul > li');
		if (children.is(":visible")) {
			children.hide('fast');
			$(this).attr('title', '展开').addClass('tree-icon-level-plus-sign').removeClass('tree-icon-level-minus-sign');
		} else {
			children.show('fast');
			$(this).attr('title', '关闭').addClass('tree-icon-level-minus-sign').removeClass('tree-icon-level-plus-sign');
		}
		e.stopPropagation();
	});
	$("#" + selectorid + " i[class^='tree-icon-view-']").on('click', function(e) {
		// var code=$('.'+e.target.id).closest('li').attr('data-name');
		if ($(this).prop("className") == "tree-icon-view-open-sign") {
			$(this).addClass('tree-icon-view-close-sign').removeClass('tree-icon-view-open-sign').attr("title", "显示");
		} else if ($(this).prop("className") == "tree-icon-view-close-sign") {
			$(this).addClass('tree-icon-view-open-sign').removeClass('tree-icon-view-close-sign').attr("title", "隐藏");
		}
		if (_callback && typeof _callback == 'function') {

			_callback(e.target.id, elem);
		}
		e.stopPropagation();
	});
};