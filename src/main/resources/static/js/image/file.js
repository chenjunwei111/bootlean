$(function () {
    getFileData();
})

var changeImgPojo=null;
//获取文件列表数据
function getFileData() {
    layui.use('table', function () {
        var table = layui.table;
        //第一个实例
        table.render({
            // id:'idTest',
            elem: '#fileTable'
            , defaultToolbar: ['filter', 'exports']
            , height: 400
            , toolbar: '#toolbarDemo'
            , url: rootPath + '/FileController/listFile' //数据接口
            , page: true //开启分页
            , cols: [[ //表头
                {type: 'numbers'}
                // ,{type: 'checkbox'}
                // ,{type:'radio'}
                , {field: 'id', title: '文件编码', sort: true, width: 150}
                , {field: 'fileName', title: '文件名', edit: 'text', width: 100}
                , {field: 'createUserName', title: '创建人', width: 80}
                , {field: 'createTime', title: '创建时间',  width: 120}

                , {field: 'updateUserName', title: '修改人', width:80}
                , {field: 'updateTime', title: '修改时间', width: 120}
                , {field: 'filePath', title: '文件路径', width: 130}
                , {fixed: '', title: '预览', templet: '#showImg', width: 100,style:"cursor: pointer"}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 320}

            ]],
            done: function (res, curr, count) {
            },
        });
        //头工具栏事件
        table.on('toolbar(userTable)', function (obj) {
            var checkStatus = table.checkStatus('userTable');
            switch (obj.event) {
                case 'eventUpload':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckData2':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：' + data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选' : '未全选');
                    break;

            }
            ;
        });

        //监听行工具事件
        table.on('tool(fileTable)', function (obj) {
            var data = obj.data;
            // console.log(obj)
            if (obj.event === 'del') {
                layer.confirm('真的删除么', function (index) {
                    // obj.del();
                    // layer.close(index);
                    $.ajax({
                        type: 'post',
                        url: rootPath + '/FileController/delFile',
                        data: JSON.stringify(data),
                        dataType: "json",
                        contentType: "application/json",
                        success: function (res) {
                            if (res.msg == "删除文件失败") {
                                layer.msg(res.msg);
                            } else {
                                layer.msg(res.msg);
                                setTimeout(function () {
                                    layer.close(index);
                                    reloadTable();
                                }, 1500);
                            }
                        },
                        error: function (res) {
                            layer.msg("无该权限");
                        }
                    });
                    reloadTable();
                });
            }
            else if (obj.event === 'edit') {
                editFileInfo(data);
            }
            else if (obj.event === 'clickImg') {
                // console.log(data);
                // var divImg="<img  th:src=\"'../FileController/viewImg/"+data.id+"'\" >";
                var   divImg=$(this).parent().html();
                divImg=divImg.replace(">"," style='width:600px'> ")//增加宽度
                layer.open({
                    type: 1,
                    title: [data.fileName, "font-size:18px"],
                    // area: ["600px", "440px"],
                    area:"600px",
                    anim:2,
                    skin:'layui-layer-lan',
                    maxmin:false,
                    id:'bigImg',
                    resize:false,
                    moveType:1,
                    shade: 0.65,
                    scrollbar:false,
                    content:divImg ,
                    end: function () {
                        //要判断是提交后还是取消后的操作
                    }
                })
            }
            else if(obj.event==='updateImg'){
                 changeImgPojo=data;
                 $("#uploadImg2").click();
            }

        });

        // //监听行单击事件（单击事件为：rowDouble）
        // table.on('row(userTable)', function(obj){
        //     var data = obj.data;
        //
        //     layer.alert(JSON.stringify(data), {
        //         title: '当前行数据：'
        //     });
        //
        //     //标注选中样式
        //     obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
        // });


        //监听单元格编辑
        // table.on('edit(userTable)', function(obj){
        //     var value = obj.value //得到修改后的值
        //         ,data = obj.data //得到所在行所有键值
        //         ,field = obj.field; //得到字段
        //     layer.msg('[ID: '+ data.id +'] ' + field + ' 字段更改为：'+ value);
        // });

    });
}


//修改文件信息
function editFileInfo(preInfo) {
    $.ajax({
        type: 'post',
        url: rootPath + '/FileController/editFileInfo',
        data: JSON.stringify(preInfo),
        dataType: "json",
        contentType: "application/json",
        success: function (res) {
            if(res.msg==undefined){
                layer.msg("无该权限");
                return;
            }
            layer.msg(res.msg);
            reloadTable();
        },
        error: function (res) {
            layer.msg("无该权限");
        }
    });

}

//表格重载
function reloadTable() {
    layui.use('table', function () {
        var table1 = layui.table;
        table1.reload('fileTable', {
            url: rootPath + '/FileController/listFile'
            // ,where: {} //设定异步数据接口的额外参数
            //,height: 300
        });
    });
}


//图片上传
layui.use('upload', function() {
    var $ = layui.jquery
        , upload = layui.upload;
    //普通图片上传
    var uploadInst1 = upload.render({
        elem: '#uploadImg'
        , url: rootPath + '/FileController/fileUpload'
        , before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#demo1').attr('src', result); //图片链接（base64）
            });
        }
        , done: function (res) {
            reloadTable();
            return layer.msg(res.msg);
        }
        , error: function (res) {
            if(res.msg==undefined){
                return layer.msg('无该权限');
            }else{
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
            }

        }
    });

    //图片重传
    var uploadInst2 = upload.render({
        elem: '#uploadImg2'
        // , data: {file2:JSON.stringify(changeImgPojo)}
        , url: rootPath + '/FileController/changeImage'
        , before: function (obj) {
            this.data={file2:JSON.stringify(changeImgPojo)}
            //预读本地文件示例，不支持ie8
            // obj.preview(function (index, file, result) {
            //     $('#demo1').attr('src', result); //图片链接（base64）
            // });
        }
        , done: function (res) {
            reloadTable();
            return layer.msg(res.msg);
            //上传成功
        }
        , error: function (res) {
            if(res.msg==undefined){
                return layer.msg('无该权限');
            }else{
                //演示失败状态，并实现重传
                // var demoText = $('#demoText');
                // demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                // demoText.find('.demo-reload').on('click', function () {
                //     uploadInst.upload();
                // });
            }

        }
    });

    //多图片上传
    var uploadInst3= upload.render({
        elem: '#uploadImgS'
        ,multiple: true
        // , data: {file2:JSON.stringify(changeImgPojo)}
        , url: rootPath + '/FileController/fileUploadS'
        , before: function (obj) {
            this.data={file2:JSON.stringify(changeImgPojo)}
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#demo2').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
            });
        }
        , done: function (res) {
            //如果上传失败
            reloadTable();
            return layer.msg(res.msg);
            //上传成功
        }
        , error: function (res) {
            if(res.msg==undefined){
                return layer.msg('无该权限');
            }
        }
    });
});


