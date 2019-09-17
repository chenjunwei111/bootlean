//region =================全局变量=================

//endregion =================全局变量=================

//region =================初始化=================
$(function () {
    // alert(123);
    getUserData();

})
//endregion =================初始化=================

//region =================Dome操作=================
$("html").keypress(function (event) {
    if (event.keyCode == 13) {//判断是否enter键
        $("#search").click();
    }
});

//搜索点击
$("#search").click(function () {
    var uName=$("#inputUname").val();
    var pojo={username:uName};
    reloadTable(pojo);
})
//endregion =================Dome操作=================

//region =================表格方法=================
//获取用户列表数据
function getUserData(){
    layui.use('table', function(){
        var table = layui.table;
        //第一个实例
        table.render({
            // id:'idTest',
            elem: '#userTable'
            ,defaultToolbar: ['filter', 'exports']
            ,height: 400
            ,toolbar: '#toolbarDemo'
            ,url: rootPath+'/UserController/listUser' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                {type:'numbers'}
                // ,{type: 'checkbox'}
                // ,{type:'radio'}
                ,{field: 'id', title: '用户编码',sort:true,width:150}
                ,{field: 'username', title: '用户名', edit: 'text',width:180}
                ,{field: 'password', title: '密码' ,width:180}
                ,{field: 'email', title: '邮箱',width:250, edit: 'text'}
                ,{field: 'isvalid', title: '可用', edit: 'text',width:100}
                ,{field: 'discription', title: '备注', edit: 'text',width:120}
                ,{field: 'lasttime', title: '登录时间', edit: 'text',width:150}
                ,{fixed: 'right',  title:'操作', toolbar: '#barDemo', width:240}

            ]],
            done: function(res, curr, count){
            },
        });
        //头工具栏事件
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
                                        layer.msg(res.msg);
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


//表格重载
function reloadTable(pojo) {
    layui.use('table', function() {
        var table1 = layui.table;
        table1.reload('userTable', {
            url: rootPath+'/UserController/listUser'
            ,where: {pojo:JSON.stringify(pojo)} //设定异步数据接口的额外参数
            //,height: 300
        });
    });
}
//endregion =================表格方法=================

//region =================其余方法=================
//点击添加用户
function btnClickAddPre(){
    var checkBox='<form class="layui-form"  id="checkboxForm">';
    $.ajax({
        type:'get',
        async:'false',
        url:rootPath+'/UserController/listRoleUser',
        data:{userId:000},
        success:function (res) {
            var checkBoxChild='<div class="layui-form-item"><div class="layui-input-block" style="margin-left: 0px">';
            var g=0;
            for(var i=0;i<res.length;i++){
                g++;
                checkBoxChild+='<input type="checkbox"  title="'+res[i].ROLE_NAME+'" roleId="'+res[i].ID+'"  lay-filter="switchTest" lay-text="ON|OFF"> ';
                if(g==4){
                    checkBoxChild+="</div></div>";
                    checkBox+=checkBoxChild;
                    checkBoxChild='<div class="layui-form-item"><div class="layui-input-block" style="margin-left: 0px">';
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

            var table='<div class="form-flex" style="padding: 10px;text-align: center">' +
                '<div class="layui-form-item" style="margin-bottom: 0px;">\n' +
                '            <div class="layui-inline clearfix">\n' +
                '                <label class="layui-form-label" >用户名：</label>\n' +
                '                <div class="layui-input-inline" style="margin-top: 9px;">\n' +
                '<input  class="layui-input" field="username" type="text" placeholder="请输入用户名">' +
                '                </div>\n' +
                '            </div>\n' +
                '            </div>\n' +

                '<div class="layui-form-item" style="margin-bottom: 0px;">\n' +
                '            <div class="layui-inline clearfix">\n' +
                '                <label class="layui-form-label" >密码：</label>\n' +
                '                <div class="layui-input-inline" style="margin-top: 9px;">\n' +
                '<input  class="layui-input" field="password" type="text" placeholder="请输入密码" >'+
                '                </div>\n' +
                '            </div>\n' +
                '        </div>'+

                '<div class="layui-form-item" style="margin-bottom: 0px;">\n' +
                '            <div class="layui-inline clearfix">\n' +
                '                <label class="layui-form-label" >邮箱：</label>\n' +
                '                <div class="layui-input-inline" style="margin-top: 9px;">\n' +
                '<input  class="layui-input" field="email" type="text"  placeholder="请输入邮箱" >' +
                '                </div>\n' +
                '            </div>\n' +
                '        </div>'+

                '<div class="layui-form-item" style="margin-bottom: 0px;">\n' +
                '            <div class="layui-inline clearfix">\n' +
                '                <label class="layui-form-label" >是否可用：</label>\n' +
                '                <div class="layui-input-inline" style="margin-top: 9px;">\n' +
                '<input  class="layui-input" field="isvalid" type="text" placeholder="可用1/禁用0" >' +
                '                </div>\n' +
                '            </div>\n' +
                '        </div>'+

                '<div class="layui-form-item" style="margin-bottom: 0px;">\n' +
                '            <div class="layui-inline clearfix">\n' +
                '                <label class="layui-form-label" >备注：</label>\n' +
                '                <div class="layui-input-inline" style="margin-top: 9px;">\n' +
                '<input  class="layui-input" field="discription"  type="text" placeholder="备注" >'+
                '                </div>\n' +
                '            </div>\n' +
                '        </div>'+

                '<div class="layui-form-item" style="margin-bottom: 0px;">\n' +
                '            <div class="layui-inline clearfix">\n' +
                '                <label class="layui-form-label" >角色分配：</label>\n' +
                '                <div class="layui-input-inline" style="margin-top: 9px;">\n' +
                checkBox+
                '                </div>\n' +
                '            </div>\n' +
                '        </div>'+


                '<button type="button" class="btn btn-bordered" id="btnEnter" >确认</button>'+
                '</div> '
            ;
            layer.open({
                type: 1,
                title: ["新增用户", "font-size:18px"],
                area: ["600px", "370px"],
                anim: 2,
                skin: 'layui-layer-lan',
                maxmin: true,
                id: 'addPremssion',
                resize: false,
                moveType: 1,
                // shade: 0.65,
                shade: 0,
                scrollbar: false,
                content: table,
                end: function () {

                }
            });

            layui.use(['form'], function() {
                var form = layui.form;
                form.render();
            })

            $("#btnEnter").click(function () {
                //遍历值
                var user = {};
                $(".layui-input").each(function () {
                    if($(this).attr("field")!=undefined && $(this).val()!="" ){
                        // console.log($(this).attr("field")+"-"+$(this).val());
                        user[''+$(this).attr("field")+''] = ''+$(this).val()+'';
                    }
                });

                var formDom=$("#checkboxForm").find(".layui-form-checkbox");
                var arr="";
                //获取复选框数据
                formDom.each(function () {
                    if($(this).hasClass("layui-form-checked")){
                        // arr.push($(this).prev().attr("roleId"));
                        arr+=$(this).prev().attr("roleId")+","
                    }
                })
                user.roleList=arr;
                $.ajax({
                    type:'post',
                    url:rootPath+'/UserController/addUser',
                    data:JSON.stringify(user),
                    dataType:"json",
                    contentType:"application/json",
                    success:function (res) {
                        if(res.msg=="新增用户失败"){
                            layer.msg(res.msg);
                        }else{
                            layer.msg(res.msg);
                            setTimeout(function () {
                                layer.closeAll();
                                reloadTable();
                            },1500);
                        }
                    },
                    error:function (res) {
                        layer.msg("无该权限");
                    }
                });

            })

        },
        error:function (res) {
            layer.msg("无该权限");
        }
    });
}

//修改用户信息
function editUserInfo(preInfo) {
    $.ajax({
        type:'post',
        url:rootPath+'/UserController/editUserInfo',
        data:JSON.stringify(preInfo),
        dataType:"json",
        contentType:"application/json",
        success:function (res) {
            layer.msg(res.msg);
        },
        error:function (res) {
            layer.msg("无该权限");
        }
    });

}


//endregion =================其余方法=================

//region =================图表方法=================

//endregion =================图表方法=================

//region =================弃用方法====================

//endregion =================弃用方法=================












