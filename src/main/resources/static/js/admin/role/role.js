
$(function () {
    // alert(123);
    getRoleData();
})


$("html").keypress(function (event) {
    if (event.keyCode == 13) {//判断是否enter键
        $("#search").click();
    }
});

//搜索点击
$("#search").click(function () {
    var rName=$("#inputRname").val();
    var pojo={roleName:rName};
    reloadTable(pojo);
})



// function f() {
//     var oCks = tree.GetChecked(); //这是方法
//     var funId = [];
//     for (var i = 0; i < oCks.length; i++) {
//         funId.push(oCks[i].value);
//     }
//     var pojo = RoleObjPojo = {roleid:id,vs:funId};
//     var url = '/userperce/TROLETfunction/insertListRoidFun';
//     $.ajax({
//         url:url,
//         data:JSON.stringify(pojo),
//         method:"POST",
//         contentType: 'application/json',
//         success:function (res) {
//             console.log(res);
//             if(res=="ok"){
//                 msg("添加功能成功！");
//                 setTimeout(function(){
//                     // parent.window.document.getElementById("txtid").value="ok";
//                     var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
//                     parent.layer.close(index); //再执行关闭
//                 },3000)
//
//             }else{
//                 msg("添加功能失败！");
//             }
//         },
//         error:function (res) {
//             layer.msg("无该权限");
//         }
//     })
// }


function loadCheckBoxTree(roleCode){
    //layui 的 form 模块是必须的
    layui.use(['form'], function () {
        var form = layui.form;
        //3、最完整的参数用法
        tree = new layuiXtree({
            elem: 'treeCheckBox'//必填
            , form: form        //必填
            , data: rootPath+'/RoleController/getTree?roleCode='+roleCode//必填
            , isopen: false  //加载完毕后的展开状态，默认值：true
            , ckall: false    //启用全选功能，默认值：false
            , ckallback: function () { } //全选框状态改变后执行的回调函数
            , islinkage:true//是否联动
            , icon: {        //三种图标样式，更改几个都可以，用的是layui的图标
                  open: "&#xe6c9;"       //节点打开的图标
                , close: "&#xe67a;"    //节点关闭的图标
                , end: "&#xe67b;"      //末尾节点的图标
            }
            , color: {       //三种图标颜色，独立配色，更改几个都可以
                open: "rgb(238, 154, 0)"        //节点图标打开的颜色
                , close: "rgb(238, 154, 0)"     //节点图标关闭的颜色
                , end: "rgb(130, 130, 130)"       //末级节点图标的颜色
            }
            , click: function (data) {  //节点选中状态改变事件监听，全选框有自己的监听事件
                //parent.window.document.getElementById("DEPT_NAME").value=data.id;
                //parent.window.document.getElementById("DEPT_CODE").value=data.value;
            },dataBindEnd:function(jsondata){
                // console.log(jsondata);
                var node =   tree.GetParent('530100');//云南省默认展开
                $(node).prev().click();
                console.log(node);

            }
        });

        //获取全部[选中的][末级节点]原checkbox DOM对象，返回Array
        //document.getElementById('btn1').onclick = function () {
        //    var oCks = tree.GetChecked(); //这是方法
        //    for (var i = 0; i < oCks.length; i++) {
        //        console.log(oCks[i].value);
        //    }
        //}
    });
}

//点击事件
function btnClickAddPre(){


    var table='<div class="form-flex" style="padding: 10px;text-align: center">' +
        '<div class="layui-form-item" style="margin-bottom: 0px;">\n' +
        '            <div class="layui-inline clearfix">\n' +
        '                <label class="layui-form-label" >角色名：</label>\n' +
        '                <div class="layui-input-inline" style="margin-top: 9px;">\n' +
        '<input  class="layui-input" field="roleName" type="text" placeholder="请输入角色名">' +
        '                </div>\n' +
        '            </div>\n' +
        '            </div>\n' +

        '<div class="layui-form-item" style="margin-bottom: 0px;">\n' +
        '            <div class="layui-inline clearfix">\n' +
        '                <label class="layui-form-label" >备注：</label>\n' +
        '                <div class="layui-input-inline" style="margin-top: 9px;">\n' +
        '<input  class="layui-input" field="description" type="text" placeholder="请输备注" >'+
        '                </div>\n' +
        '            </div>\n' +
        '        </div>'+

        '<div class="layui-form-item" style="margin-bottom: 0px;">\n' +
        '            <div class="layui-inline clearfix">\n' +
        '                <label class="layui-form-label" >权限分配：</label>\n' +
        '                <div class="layui-input-inline" style="margin-top: 9px;">\n' +
        '<form class="layui-form"><div id="treeCheckBox" class="usertree"></div></form>'+
        '                </div>\n' +
        '            </div>\n' +
        '        </div>'+

        '<button type="button" class="btn btn-bordered" id="btnEnter" >确认</button>'+
        '</div> ';

    layer.open({
        type: 1,
        title: ["新增角色", "font-size:18px"],
        area: ["600px", "370px"],
        anim: 2,
        skin: 'layui-layer-lan',
        maxmin: true,
        id: 'addRole',
        resize: false,
        moveType: 1,
        // shade: 0.65,
        shade: 0,
        scrollbar: false,
        content: table,
        end: function () {

        }
    });

    loadCheckBoxTree('000');

            // layui.use(['form'], function() {
            //     var form = layui.form;
            //     form.render();
            // })
     $("#btnEnter").click(function () {
                //遍历值
                var role = {};
                $(".layui-input").each(function () {
                    if($(this).attr("field")!=undefined && $(this).val()!="" ){
                        // console.log($(this).attr("field")+"-"+$(this).val());
                        role[''+$(this).attr("field")+''] = ''+$(this).val()+'';
                    }
                });

                var oCks = tree.GetChecked(); //这是方法
                var funId = [];
                for (var i = 0; i < oCks.length; i++) {
                    funId.push(oCks[i].value);
                }
                role.premissionS=funId;

                $.ajax({
                    type:'post',
                    url:rootPath+'/RoleController/addRole',
                    data:JSON.stringify(role),
                    dataType:"json",
                    contentType:"application/json",
                    success:function (res) {
                        if(res.msg=="新增角色失败"){
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


}





//获取角色列表数据
function getRoleData(){
    layui.use('table', function(){
        var table = layui.table;
        //第一个实例
        table.render({
            // id:'idTest',
            elem: '#roleTable'
            ,defaultToolbar: ['filter', 'exports']
            ,height: 400
            ,toolbar: '#toolbarDemo'
            ,url: rootPath+'/RoleController/listRole' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                {type:'numbers'}
                // ,{type: 'checkbox'}
                // ,{type:'radio'}
                ,{field: 'id', title: '角色编码',sort:true,width:150}
                ,{field: 'roleName', title: '角色', edit: 'text',width:180}
                ,{field: 'description', title: '备注' , edit: 'text',width:180}
                ,{fixed: 'right',  title:'操作', toolbar: '#barDemo', width:320}

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
        table.on('tool(roleTable)', function(obj){
            var data = obj.data;
            // console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('真的删除么', function(index){
                    // obj.del();
                    // layer.close(index);
                    $.ajax({
                        type:'post',
                        url:rootPath+'/RoleController/delRole',
                        data:JSON.stringify(data),
                        dataType:"json",
                        contentType:"application/json",
                        success:function (res) {
                            if(res.msg=="删除角色失败"){
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

                editRoleInfo(data);
            }
            else if(obj.event==='updatePremission'){

                var table='<div class="form-flex" style="padding: 10px;text-align: center">' +

                    '<div class="layui-form-item" style="margin-bottom: 0px;">\n' +
                    '            <div class="layui-inline clearfix">\n' +
                    '                <label class="layui-form-label" >权限分配：</label>\n' +
                    '                <div class="layui-input-inline" style="margin-top: 9px;">\n' +
                    '<form class="layui-form"><div id="treeCheckBox" class="usertree"></div></form>'+
                    '                </div>\n' +
                    '            </div>\n' +
                    '        </div>'+
                    '<button type="button" class="btn btn-bordered" id="btnUpdateEnter" >确认</button>'+
                    '</div> ';

                layer.open({
                    type: 1,
                    title: ["修改权限", "font-size:18px"],
                    area: ["600px", "370px"],
                    anim: 2,
                    skin: 'layui-layer-lan',
                    maxmin: true,
                    id: 'updatePermission',
                    resize: false,
                    moveType: 1,
                    // shade: 0.65,
                    shade: 0,
                    scrollbar: false,
                    content: table,
                    end: function () {

                    }
                });

                loadCheckBoxTree(data.id);

                // layui.use(['form'], function() {
                //     var form = layui.form;
                //     form.render();
                // })
                $("#btnUpdateEnter").click(function () {
                    //遍历值
                    var role={};
                    var oCks = tree.GetChecked(); //这是方法
                    var funId = [];
                    for (var i = 0; i < oCks.length; i++) {
                        funId.push(oCks[i].value);
                    }
                    role.premissionS=funId;
                    role.id=data.id;
                    $.ajax({
                        type:'post',
                        url:rootPath+'/RoleController/editRolePremission',
                        data:JSON.stringify(role),
                        dataType:"json",
                        contentType:"application/json",
                        success:function (res) {
                            if(res.msg=="修改权限失败"){
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
            }else if(obj.event==='updateUser'){
                layer.open({
                    type:2,
                    title:["添加用户","font-size:16px"],
                    area:["1000px","426px"],
                    anim:2,
                    skin:'layui-layer-lan',
                    maxmin:false,
                    id:'roleuser',
                    resize:false,
                    moveType:1,
                    shade: 0.65,
                    scrollbar:false,
                    content:rootPath+'/RoleController/roleAddUser?id='+data.id+'',
                    end:function(){

                    }
                })
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


//修改角色信息
function editRoleInfo(preInfo) {
    $.ajax({
        type:'post',
        url:rootPath+'/RoleController/editRoleInfo',
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

//表格重载
function reloadTable(pojo) {
    layui.use('table', function() {
        var table1 = layui.table;
        table1.reload('roleTable', {
            url: rootPath+'/RoleController/listRole'
            ,where: {pojo:JSON.stringify(pojo)} //设定异步数据接口的额外参数
            //,height: 300
        });
    });
}


// function load() {
//     window.location.href='preview2';
// }