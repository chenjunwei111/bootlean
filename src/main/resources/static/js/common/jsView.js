$(function () {
    getUserData();
    drawxdr.map();
    initChart();
})



//获取用户列表数据
function getUserData(){
    layui.use('table', function(){
        var table = layui.table;
        //第一个实例
        table.render({
            // id:'idTest',
            elem: '#userTable'
            ,defaultToolbar: ['filter', 'exports']
            ,height: 350
            ,toolbar: '#toolbarDemo'
            ,url: rootPath+'/UserController/listUser' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                {type:'numbers'}
                ,{type: 'checkbox'}
                ,{type:'radio'}
                ,{field: 'id', title: '用户编码',sort:true,width:150}
                ,{field: 'username', title: '用户名', edit: 'text',width:180}
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
                    obj.del();
                    layer.close(index);
                });
            }
            else if(obj.event === 'edit'){
                editUserInfo(data);
            }else if(obj.event==='updateRole'){
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


        // 监听单元格编辑
        table.on('edit(userTable)', function(obj){
            var value = obj.value //得到修改后的值
                ,data = obj.data //得到所在行所有键值
                ,field = obj.field; //得到字段
            layer.msg('[ID: '+ data.id +'] ' + field + ' 字段更改为：'+ value);
        });
    });
}


layui.use('laydate', function() {
    var laydate=layui.laydate;
    laydate.render({
        elem: '#dateTime', //指定元素
        // range:true
    });
});



//地图代码
var drawxdr=
    {
        map: function () {
            waitjs("UCMap",function () {
                var m = new UCMap.UCLoctions();
                if (!m.mapover()) {
                    m.setparameters({
                            mcity: '昆明',
                            mcitycode: 530100,
                            mdate: '20190214',
                            mselector: 'maps',
                            mType: basemap.MapType.mrRsrp,
                        }, 'base'
                    );
                    m.map();
                }
            })
        },

    }



function initChart() {
    var myChart1 = echarts.init(document.getElementById('rightTopChart1'));
    option1 = {
        title: {text: '数据分布', textStyle: {color: '#ffffff', fontSize: 18}, x: 'center'},
        tooltip: {trigger: 'axis'},
        //           legend: {data:['低覆盖满意度采样'], textStyle:{ color:'#000',fontSize:12},y:'bottom'},
        //         label: {normal: {show: true, position: 'top', textStyle: {color: '#cdcdcd'}}},
        grid: {left: '2%', right: '2%', bottom: '5%', top: '18%', containLabel: true},
        xAxis: {
            type: 'category', axisLine: {lineStyle: {color: '#28446a', width: 1}},
            data: ['昆明', '昭通', '曲靖', '怒江', '大理', '普洱', '丽江', '临沧'],
            axisLabel: {
                show: true, textStyle: {color: '#5393a2'}, interval: 0,
            }
        },
        yAxis: [{
            type: 'value', axisLabel: {show: true, textStyle: {color: '#5393a2'}},
            axisLine: {lineStyle: {color: '#28446a', width: 1}},
            splitLine: {
                lineStyle: {
                    color: '#26456a'
                }
            }
        }],
        series: [{
            name: '质量',
            data: [70, 48, 45, 45, 60, 60, 30, 40, 50, 60, 55, 22, 80, 90, 95],
            type: 'bar', itemStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: '#06bde6'
                    }, {
                        offset: 1,
                        color: '#3976cc'
                    }])
                }
            }, barWidth: 25
        }
        ],
    };
    myChart1.setOption(option1);
}