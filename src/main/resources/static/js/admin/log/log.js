
//初始化
$(function () {
    getLogData();
    getLogData2();
    getLogData3();
});

//点击事件



//获取日志列表数据
function getLogData(){
    layui.use('table', function(){
        var table = layui.table;
        //第一个实例
        table.render({
            // id:'idTest',
             elem: '#logTable1'
            ,defaultToolbar: ['filter', 'exports']
            ,height: 250
            // ,toolbar: '#toolbarDemo'
            ,url: rootPath+'/LogController/listLog' //数据接口
            ,limit:50
            ,page: true //开启分页
            ,cols: [[ //表头
                {type:'numbers'}
                // ,{type: 'checkbox'}
                // ,{type:'radio'}
                ,{field: 'ID', title: '日志编码',sort:true,width:180}
                ,{field: 'ACTION', title: '模块名称',width:130}
                ,{field: 'TITLE', title: '功能名称' ,width:130}
                ,{field: 'METHOD', title: '方法指向',width:150}
                ,{field: 'OPER_NAME', title: '使用人',width:100}
                ,{field: 'OPER_URL', title: '请求地址',width:120}
                ,{field: 'OPER_PARAM', title: '请求参数',width:150}
                ,{field: 'OPER_TIME', title: '操作时间',width:200}
                ,{field: 'ERROR_MSG', title: '错误信息',width:100}
                // ,{fixed: 'right',  title:'操作', toolbar: '#barDemo', width:240}

            ]],
            done: function(res, curr, count){
            },
        });
        /*头工具栏事件
        table.on('toolbar(userTable)', function(obj){
            var checkStatus = table.checkStatus('userTable');
            switch(obj.event){
                case 'getCheckData1':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckData2':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：'+ data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选': '未全选');
                    break;

            };
        });

        //监听行工具事件
        table.on('tool(userTable)', function(obj){
            var data = obj.data;
            // console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('真的删除么', function(index){
                    // obj.del();
                    // layer.close(index);
                    $.ajax({
                        type:'post',
                        url:rootPath+'/UserController/delUser',
                        data:JSON.stringify(data),
                        dataType:"json",
                        contentType:"application/json",
                        success:function (res) {
                            if(res.msg=="删除用户失败"){
                                layer.msg(res.msg);
                            }else{
                                layer.msg(res.msg);
                                setTimeout(function () {
                                    layer.close(index);
                                    reloadTable();
                                },1500);
                            }
                        },
                        error:function (res) {
                            layer.msg("无该权限");
                        }
                    });
                    reloadTable();
                });
            }
            else if(obj.event === 'edit'){
                editUserInfo(data);
            }else if(obj.event==='updateRole'){
                var userId=data.id;
                var checkBox='<form class="layui-form">';
                $.ajax({
                    type:'get',
                    async:true,
                    url:rootPath+'/UserController/listRoleUser',
                    data:{userId:userId},
                    success:function (res) {
                        var checkBoxChild='<div class="layui-form-item"><div class="layui-input-block" style="margin-left: 10px">';
                        var g=0;
                        for(var i=0;i<res.length;i++){
                            g++;
                            if(res[i].SYS_USER_ID !=null ){
                                checkBoxChild+='<input type="checkbox"  title="'+res[i].ROLE_NAME+'" roleId="'+res[i].ID+'" checked  lay-filter="switchTest" lay-text="ON|OFF"> ';
                            }else{
                                checkBoxChild+='<input type="checkbox"  title="'+res[i].ROLE_NAME+'" roleId="'+res[i].ID+'"  lay-filter="switchTest" lay-text="ON|OFF"> ';
                            }
                            if(g==4){
                                checkBoxChild+="</div></div>";
                                checkBox+=checkBoxChild;
                                checkBoxChild='<div class="layui-form-item"><div class="layui-input-block" style="margin-left: 10px">';
                                g=0;
                            }
                            if(i==res.length-1){
                                if(g!=0){
                                    checkBoxChild+="</div></div>";
                                    checkBox+=checkBoxChild;
                                }
                            }

                        }
                        checkBox+="</form>";
                        layer.open({
                            type: 1,
                            title: ["角色选择", "font-size:18px"],
                            // area: ["600px", "370px"],
                            anim: 2,
                            skin: 'layui-layer-lan',
                            maxmin: false,
                            id: 'updateRole',
                            resize: false,
                            moveType: 1,
                            // shade: 0.65,
                            shade: 0,
                            scrollbar: false,
                            content: checkBox,
                            end: function () {

                            }
                        });
                        layui.use(['form'], function() {
                            var form = layui.form;
                            form.render();
                            //监听指定开关
                            form.on('checkbox(switchTest)', function(data){

                                var updateUserRole={
                                    "roleId":$(this).attr("roleId"),
                                    "userId":userId,
                                    "checked":this.checked}
                                $.ajax({
                                    type:'post',
                                    url:rootPath+'/UserController/updateUserRole',
                                    data:JSON.stringify(updateUserRole),
                                    dataType:"json",
                                    contentType:"application/json",
                                    success:function (res) {
                                        layer.msg("成功");
                                    },
                                    error:function (res) {
                                        layer.msg("无该权限");
                                    }
                                });

                                // layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
                                //     offset: '6px'
                                // });


                            });
                        })
                    },
                    error:function (res) {
                        layer.msg("无该权限");
                    }
                });
            }
        });

        //监听行单击事件（单击事件为：rowDouble）
        table.on('row(userTable)', function(obj){
            var data = obj.data;

            layer.alert(JSON.stringify(data), {
                title: '当前行数据：'
            });

            //标注选中样式
            obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
        });


        监听单元格编辑
        table.on('edit(userTable)', function(obj){
            var value = obj.value //得到修改后的值
                ,data = obj.data //得到所在行所有键值
                ,field = obj.field; //得到字段
            layer.msg('[ID: '+ data.id +'] ' + field + ' 字段更改为：'+ value);
        });*/

    });
}


//统计 日志用户使用次数
function getLogData2(){
    layui.use('table', function(){
        var table = layui.table;
        //第一个实例
        table.render({
            // id:'idTest',
            elem: '#logTable2'
            ,defaultToolbar: ['filter', 'exports']
            ,height: 300
            // ,toolbar: '#toolbarDemo'
            ,url: rootPath+'/LogController/listLog2' //数据接口
            ,limit:50
            ,page: true //开启分页
            ,cols: [[ //表头
                {type:'numbers'}
                // ,{type: 'checkbox'}
                // ,{type:'radio'}
                ,{field: 'OPER_NAME', title: '用户',width:120}
                ,{field: 'TOTAL_CNT', title: '总次数',sort:true,width:120}
                ,{field: 'MONTH_CNT', title: '当月次数' ,sort:true,width:120}
                ,{field: 'LAST_TIME', title: '最后操作时间',width:200}
                // ,{fixed: 'right',  title:'操作', toolbar: '#barDemo', width:240}

            ]],
            done: function(res, curr, count){
            },
        });
        /*头工具栏事件
        table.on('toolbar(userTable)', function(obj){
            var checkStatus = table.checkStatus('userTable');
            switch(obj.event){
                case 'getCheckData1':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckData2':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：'+ data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选': '未全选');
                    break;

            };
        });

        //监听行工具事件
        table.on('tool(userTable)', function(obj){
            var data = obj.data;
            // console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('真的删除么', function(index){
                    // obj.del();
                    // layer.close(index);
                    $.ajax({
                        type:'post',
                        url:rootPath+'/UserController/delUser',
                        data:JSON.stringify(data),
                        dataType:"json",
                        contentType:"application/json",
                        success:function (res) {
                            if(res.msg=="删除用户失败"){
                                layer.msg(res.msg);
                            }else{
                                layer.msg(res.msg);
                                setTimeout(function () {
                                    layer.close(index);
                                    reloadTable();
                                },1500);
                            }
                        },
                        error:function (res) {
                            layer.msg("无该权限");
                        }
                    });
                    reloadTable();
                });
            }
            else if(obj.event === 'edit'){
                editUserInfo(data);
            }else if(obj.event==='updateRole'){
                var userId=data.id;
                var checkBox='<form class="layui-form">';
                $.ajax({
                    type:'get',
                    async:true,
                    url:rootPath+'/UserController/listRoleUser',
                    data:{userId:userId},
                    success:function (res) {
                        var checkBoxChild='<div class="layui-form-item"><div class="layui-input-block" style="margin-left: 10px">';
                        var g=0;
                        for(var i=0;i<res.length;i++){
                            g++;
                            if(res[i].SYS_USER_ID !=null ){
                                checkBoxChild+='<input type="checkbox"  title="'+res[i].ROLE_NAME+'" roleId="'+res[i].ID+'" checked  lay-filter="switchTest" lay-text="ON|OFF"> ';
                            }else{
                                checkBoxChild+='<input type="checkbox"  title="'+res[i].ROLE_NAME+'" roleId="'+res[i].ID+'"  lay-filter="switchTest" lay-text="ON|OFF"> ';
                            }
                            if(g==4){
                                checkBoxChild+="</div></div>";
                                checkBox+=checkBoxChild;
                                checkBoxChild='<div class="layui-form-item"><div class="layui-input-block" style="margin-left: 10px">';
                                g=0;
                            }
                            if(i==res.length-1){
                                if(g!=0){
                                    checkBoxChild+="</div></div>";
                                    checkBox+=checkBoxChild;
                                }
                            }

                        }
                        checkBox+="</form>";
                        layer.open({
                            type: 1,
                            title: ["角色选择", "font-size:18px"],
                            // area: ["600px", "370px"],
                            anim: 2,
                            skin: 'layui-layer-lan',
                            maxmin: false,
                            id: 'updateRole',
                            resize: false,
                            moveType: 1,
                            // shade: 0.65,
                            shade: 0,
                            scrollbar: false,
                            content: checkBox,
                            end: function () {

                            }
                        });
                        layui.use(['form'], function() {
                            var form = layui.form;
                            form.render();
                            //监听指定开关
                            form.on('checkbox(switchTest)', function(data){

                                var updateUserRole={
                                    "roleId":$(this).attr("roleId"),
                                    "userId":userId,
                                    "checked":this.checked}
                                $.ajax({
                                    type:'post',
                                    url:rootPath+'/UserController/updateUserRole',
                                    data:JSON.stringify(updateUserRole),
                                    dataType:"json",
                                    contentType:"application/json",
                                    success:function (res) {
                                        layer.msg("成功");
                                    },
                                    error:function (res) {
                                        layer.msg("无该权限");
                                    }
                                });

                                // layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
                                //     offset: '6px'
                                // });


                            });
                        })
                    },
                    error:function (res) {
                        layer.msg("无该权限");
                    }
                });
            }
        });

        //监听行单击事件（单击事件为：rowDouble）
        table.on('row(userTable)', function(obj){
            var data = obj.data;

            layer.alert(JSON.stringify(data), {
                title: '当前行数据：'
            });

            //标注选中样式
            obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
        });


        监听单元格编辑
        table.on('edit(userTable)', function(obj){
            var value = obj.value //得到修改后的值
                ,data = obj.data //得到所在行所有键值
                ,field = obj.field; //得到字段
            layer.msg('[ID: '+ data.id +'] ' + field + ' 字段更改为：'+ value);
        });*/

    });
}


//统计 日志功能使用次数
function getLogData3(){
    layui.use('table', function(){
        var table = layui.table;
        //第一个实例
        table.render({
            // id:'idTest',
            elem: '#logTable3'
            ,defaultToolbar: ['filter', 'exports']
            ,height: 300
            // ,toolbar: '#toolbarDemo'
            ,url: rootPath+'/LogController/listLog3' //数据接口
            ,limit:50
            ,page: true //开启分页
            ,cols: [[ //表头
                {type:'numbers'}
                // ,{type: 'checkbox'}
                // ,{type:'radio'}
                ,{field: 'TITLE', title: '功能名称',width:120}
                ,{field: 'TOTAL_CNT', title: '总次数',sort:true,width:120}
                ,{field: 'MONTH_CNT', title: '当月次数',sort:true ,width:120}
                ,{field: 'LAST_TIME', title: '最后操作时间',width:200}
                // ,{fixed: 'right',  title:'操作', toolbar: '#barDemo', width:240}

            ]],
            done: function(res, curr, count){
            },
        });
        /*头工具栏事件
        table.on('toolbar(userTable)', function(obj){
            var checkStatus = table.checkStatus('userTable');
            switch(obj.event){
                case 'getCheckData1':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckData2':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：'+ data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选': '未全选');
                    break;

            };
        });

        //监听行工具事件
        table.on('tool(userTable)', function(obj){
            var data = obj.data;
            // console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('真的删除么', function(index){
                    // obj.del();
                    // layer.close(index);
                    $.ajax({
                        type:'post',
                        url:rootPath+'/UserController/delUser',
                        data:JSON.stringify(data),
                        dataType:"json",
                        contentType:"application/json",
                        success:function (res) {
                            if(res.msg=="删除用户失败"){
                                layer.msg(res.msg);
                            }else{
                                layer.msg(res.msg);
                                setTimeout(function () {
                                    layer.close(index);
                                    reloadTable();
                                },1500);
                            }
                        },
                        error:function (res) {
                            layer.msg("无该权限");
                        }
                    });
                    reloadTable();
                });
            }
            else if(obj.event === 'edit'){
                editUserInfo(data);
            }else if(obj.event==='updateRole'){
                var userId=data.id;
                var checkBox='<form class="layui-form">';
                $.ajax({
                    type:'get',
                    async:true,
                    url:rootPath+'/UserController/listRoleUser',
                    data:{userId:userId},
                    success:function (res) {
                        var checkBoxChild='<div class="layui-form-item"><div class="layui-input-block" style="margin-left: 10px">';
                        var g=0;
                        for(var i=0;i<res.length;i++){
                            g++;
                            if(res[i].SYS_USER_ID !=null ){
                                checkBoxChild+='<input type="checkbox"  title="'+res[i].ROLE_NAME+'" roleId="'+res[i].ID+'" checked  lay-filter="switchTest" lay-text="ON|OFF"> ';
                            }else{
                                checkBoxChild+='<input type="checkbox"  title="'+res[i].ROLE_NAME+'" roleId="'+res[i].ID+'"  lay-filter="switchTest" lay-text="ON|OFF"> ';
                            }
                            if(g==4){
                                checkBoxChild+="</div></div>";
                                checkBox+=checkBoxChild;
                                checkBoxChild='<div class="layui-form-item"><div class="layui-input-block" style="margin-left: 10px">';
                                g=0;
                            }
                            if(i==res.length-1){
                                if(g!=0){
                                    checkBoxChild+="</div></div>";
                                    checkBox+=checkBoxChild;
                                }
                            }

                        }
                        checkBox+="</form>";
                        layer.open({
                            type: 1,
                            title: ["角色选择", "font-size:18px"],
                            // area: ["600px", "370px"],
                            anim: 2,
                            skin: 'layui-layer-lan',
                            maxmin: false,
                            id: 'updateRole',
                            resize: false,
                            moveType: 1,
                            // shade: 0.65,
                            shade: 0,
                            scrollbar: false,
                            content: checkBox,
                            end: function () {

                            }
                        });
                        layui.use(['form'], function() {
                            var form = layui.form;
                            form.render();
                            //监听指定开关
                            form.on('checkbox(switchTest)', function(data){

                                var updateUserRole={
                                    "roleId":$(this).attr("roleId"),
                                    "userId":userId,
                                    "checked":this.checked}
                                $.ajax({
                                    type:'post',
                                    url:rootPath+'/UserController/updateUserRole',
                                    data:JSON.stringify(updateUserRole),
                                    dataType:"json",
                                    contentType:"application/json",
                                    success:function (res) {
                                        layer.msg("成功");
                                    },
                                    error:function (res) {
                                        layer.msg("无该权限");
                                    }
                                });

                                // layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
                                //     offset: '6px'
                                // });


                            });
                        })
                    },
                    error:function (res) {
                        layer.msg("无该权限");
                    }
                });
            }
        });

        //监听行单击事件（单击事件为：rowDouble）
        table.on('row(userTable)', function(obj){
            var data = obj.data;

            layer.alert(JSON.stringify(data), {
                title: '当前行数据：'
            });

            //标注选中样式
            obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
        });


        监听单元格编辑
        table.on('edit(userTable)', function(obj){
            var value = obj.value //得到修改后的值
                ,data = obj.data //得到所在行所有键值
                ,field = obj.field; //得到字段
            layer.msg('[ID: '+ data.id +'] ' + field + ' 字段更改为：'+ value);
        });*/

    });
}