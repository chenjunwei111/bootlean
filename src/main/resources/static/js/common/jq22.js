 /* www.jq22.com */
$(function(){
	// 初始化插件
	$("#demo").zyUpload({
		width            :   "650px",                 // 宽度
		height           :   "400px",                 // 宽度
		itemWidth        :   "120px",                 // 文件项的宽度
		itemHeight       :   "100px",                 // 文件项的高度
		url              :   "../../getSitePhoto/ImageUpload?orderNum=20180803133515X515959847",  // 上传文件的路径
		multiple         :   true,                    // 是否可以多个文件上传
		dragDrop         :   false,                    // 是否可以拖动上传文件
		del              :   true,                    // 是否可以删除文件
		finishDel        :   false,  				  // 是否在上传文件完成后删除预览
		/* 外部获得的回调接口 */
		onSelect: function(files, allFiles){                    // 选择文件的回调方法
			console.info("当前选择了以下文件：");
			console.info(files);

			for(var i=0;i<files.length;i++){
				//控制图片存储空间大小
				if(files[i].size>=100000){
					return msgtime("图片："+files[i].name+"大小超过100K",4000);
				}
				if(i>5){
                    return msg("上传图片不能超过5张");
				}
			}
		},
		onDelete: function(file, surplusFiles){                     // 删除一个文件的回调方法
			console.info("当前删除了此文件：");
			console.info(file);
			// console.info("当前剩余的文件：");
			// console.info(surplusFiles);
		},
		onSuccess: function(file){                    // 文件上传成功的回调方法
			console.info("此文件上传成功：");
			console.info(file);
		},
		onFailure: function(file){                    // 文件上传失败的回调方法
			// console.info("此文件上传失败：");
			// console.info(file);
		},
		onComplete: function(responseInfo){           // 上传完成的回调方法
			console.info("文件上传完成");

			console.info(responseInfo);
		}
	});
});

