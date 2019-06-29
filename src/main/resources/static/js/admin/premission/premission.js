
$(function () {
    // alert(123);
    getPremissionData();

})


$("html").keypress(function (event) {
    if (event.keyCode == 13) {//判断是否enter键
        $("#search").click();
    }
});

//点击事件
function btnClickAddPre(){
    var table='<div class="form-flex" style="padding: 10px;text-align: center">' +

        '<div class="layui-form-item" style="margin-bottom: 0px;">\n' +
        '            <div class="layui-inline clearfix">\n' +
        '                <label class="layui-form-label" >权限编码：</label>\n' +
        '                <div class="layui-input-inline" style="margin-top: 9px;">\n' +
        '<input  class="layui-input" field="FUNCTION_CODE" type="text" placeholder="权限编码(一级页面:A,二级页面:A-B,3级按钮:A1-B1-list)">' +
        '                </div>\n' +
        '            </div>\n' +
        '            </div>\n' +

        '<div class="layui-form-item" style="margin-bottom: 0px;">\n' +
        '            <div class="layui-inline clearfix">\n' +
        '                <label class="layui-form-label" >权限名称：</label>\n' +
        '                <div class="layui-input-inline" style="margin-top: 9px;">\n' +
        '<input  class="layui-input" field="FUNCTION_NAME" type="text" placeholder="请输入权限名称" >'+
        '                </div>\n' +
        '            </div>\n' +
        '        </div>'+

        '<div class="layui-form-item" style="margin-bottom: 0px;">\n' +
        '            <div class="layui-inline clearfix">\n' +
        '                <label class="layui-form-label" >父级编码：</label>\n' +
        '                <div class="layui-input-inline" style="margin-top: 9px;">\n' +
        '<input  class="layui-input" field="FUNCTION_PARENT_CODE" type="text"  placeholder="父级编码(页面的上一级页面编码,一级菜单不写)" >' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>'+

        '<div class="layui-form-item" style="margin-bottom: 0px;">\n' +
        '            <div class="layui-inline clearfix">\n' +
        '                <label class="layui-form-label" >权限指向：</label>\n' +
        '                <div class="layui-input-inline" style="margin-top: 9px;">\n' +
        '<input  class="layui-input" field="FUNCTION_HREF" type="text" placeholder="URI指向(view权限需要写，其余不需要写)" >' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>'+

        '<div class="layui-form-item" style="margin-bottom: 0px;">\n' +
        '            <div class="layui-inline clearfix">\n' +
        '                <label class="layui-form-label" >权限图标：</label>\n' +
        '                <div class="layui-input-inline" style="margin-top: 9px;">\n' +
        '<input  class="layui-input" field="FUNCTION_ICON"  type="text" placeholder="icon图标(view权限需要写，其余不需要写)" >'+
        '                </div>\n' +
        '            </div>\n' +
        '        </div>'+

        '<div class="layui-form-item" style="margin-bottom: 0px;">\n' +
        '            <div class="layui-inline layui-inline-2">\n' +
        '                <label class="layui-form-label" >是否可用：</label>\n' +
        '                <div class="layui-input-inline" style="margin-top: 9px;">\n' +
        '<input  class="layui-input"  field="ISVALID" type="text" placeholder="1可用/0禁用" value="1">  '+
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="layui-inline layui-inline-2">\n' +
        '                <label class="layui-form-label" >排序：</label>\n' +
        '                <div class="layui-input-inline" style="margin-top: 9px;">\n' +
        '<input  class="layui-input" field="SEQUENCE" type="text" placeholder="排序" value="111111">'+
        '                </div>\n' +
        '            </div>\n' +
        '        </div>'+

        '<div class="layui-form-item" style="margin-bottom: 0px;">\n' +
        '            <div class="layui-inline clearfix">\n' +
        '                <label class="layui-form-label" >备注：</label>\n' +
        '                <div class="layui-input-inline" style="margin-top: 9px;">\n' +
        '<input  class="layui-input" field="DESCRIPTION" type="text" placeholder="备注说明" >'+
        '                </div>\n' +
        '            </div>\n' +
        '        </div>'+

        '<div class="layui-form-item" style="margin-bottom: 0px;">\n' +
        '            <div class="layui-inline layui-inline-2">\n' +
        '                <label class="layui-form-label" >权限Shiro</label>\n' +
        '                <div class="layui-input-inline" style="margin-top: 9px;">\n' +
        '<input  class="layui-input" field="PERMS"  type="text"  placeholder="Shiro权限设置" >'+
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="layui-inline layui-inline-2">\n' +
        '                <label class="layui-form-label" >权限类型：</label>\n' +
        '                <div class="layui-input-inline" style="margin-top: 9px;">\n' +
        '<input  class="layui-input"  field="PERMS_TYPE" type="text" placeholder="Shiro权限类型" >'+
        '                </div>\n' +
        '            </div>\n' +
        '        </div>'+
        '<button type="button" class="btn btn-bordered" id="btnEnter" >确认</button>'+
        '</div> '
    ;
    layer.open({
        type: 1,
        title: ["新增权限", "font-size:18px"],
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

    //
    $("#btnEnter").click(function () {
        //遍历值
        var premission = {};
        $(".layui-input").each(function () {
            if($(this).attr("field")!=undefined && $(this).val()!="" ){
                console.log($(this).attr("field")+"-"+$(this).val());
                premission[''+$(this).attr("field")+''] = ''+$(this).val()+'';
            }
        })

         $.ajax({
                 type:'post',
                 url:rootPath+'/PremissionController/addPremission',
                 data:JSON.stringify(premission),
                 dataType:"json",
                 contentType:"application/json",
                 success:function (res) {
                     if(res.msg=="新增权限失败"){
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

//搜索按钮点击
$("#search").click(function () {
    var val=$("#preType option:selected").val();
    var valChild=null;
    if(!$("#preTypeChild").hasClass("hide")){
        valChild=$("#preTypeChild option:selected").val();
    }
    var pojo={"preType":val,"preTypeChild":valChild};
    reloadTable(pojo);
})


//权限类型变化事件
$("#preType").change(function () {
    var val=$("#preType option:selected").val();
    var valChild=null;
    if(val==='view'){
        $("#preTypeChild").removeClass("hide");
    }else{
        $("#preTypeChild").addClass("hide");

    }


})



//获取权限列表数据
function getPremissionData(){
    layui.use('table', function(){
        var table = layui.table;
        //第一个实例
        table.render({
             // id:'idTest',
             elem: '#dataTable'
            ,defaultToolbar: ['filter', 'exports']
            ,height: 600
            ,limit:20
            ,toolbar: '#toolbarDemo'
            ,url: rootPath+'/PremissionController/listPremission' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                 {type:'numbers'}
                // ,{type: 'checkbox'}
                // ,{type:'radio'}
                ,{field: 'FUNCTION_CODE', title: '权限编码',sort:true,width:150}
                ,{field: 'FUNCTION_NAME', title: '权限名称', edit: 'text',width:180}
                ,{field: 'FUNCTION_PARENT_CODE', title: '父级权限编码' , edit: 'text',width:180}
                ,{field: 'FUNCTION_HREF', title: '权限指向',width:250, edit: 'text'}
                ,{field: 'PERMS_TYPE', title: '权限类型', edit: 'text',width:100}
                ,{field: 'SEQUENCE', title: '排序', edit: 'text',sort:true,width:120}
                ,{field: 'FUNCTION_ICON', title: '权限图标', edit: 'text',width:250}
                ,{field: 'PERMS', title: '权限shiro',width:220, edit: 'text'}
                ,{field: 'ISVALID', title: '禁用', edit: 'text'}
                ,{field: 'DESCRIPTION', title: '备注', edit: 'text',width:150}
                ,{fixed: 'right',  title:'操作', toolbar: '#barDemo', width:170}
            ]],
            done: function(res, curr, count){

            },
        });
        //头工具栏事件
        table.on('toolbar(dataTable)', function(obj){
            var checkStatus = table.checkStatus('dataTable');
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
        table.on('tool(dataTable)', function(obj){
            var data = obj.data;
            // console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    // obj.del();
                    // layer.close(index);
                     $.ajax({
                             type:'post',
                             url:rootPath+'/PremissionController/delPremission',
                             data:JSON.stringify(data),
                             dataType:"json",
                             contentType:"application/json",
                             success:function (res) {
                                 if(res.msg=="删除权限失败"){
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
                editPreInfo(data);
            }
        });

        // //监听行单击事件（单击事件为：rowDouble）
        // table.on('row(dataTable)', function(obj){
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
        // table.on('edit(dataTable)', function(obj){
        //     var value = obj.value //得到修改后的值
        //         ,data = obj.data //得到所在行所有键值
        //         ,field = obj.field; //得到字段
        //     layer.msg('[ID: '+ data.id +'] ' + field + ' 字段更改为：'+ value);
        // });

    });
}


//修改权限信息
function editPreInfo(preInfo) {
    $.ajax({
        type:'post',
        url:rootPath+'/PremissionController/editPreInfo',
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
        table1.reload('dataTable', {
            url: rootPath+'/PremissionController/listPremission'
            ,where: {pojo:JSON.stringify(pojo)} //设定异步数据接口的额外参数
            //,height: 300
        });
    });
}


// function load() {
//     window.location.href='preview2';
// }