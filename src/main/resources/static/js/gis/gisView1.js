
//初始化
$(function () {
    getSectorList();
    drawxdr.map()
})



//获取小区列表数据
function getSectorList(){
    layui.use('table', function(){
        var table = layui.table;
        //第一个实例
        table.render({
            // id:'idTest',
            elem: '#sectorTable'
            ,defaultToolbar: ['filter', 'exports']
            ,height: 600
            ,toolbar: '#toolbarDemo'
            ,url: rootPath+'/GisController/sectorList' //数据接口
            ,limit:50
            ,page: true //开启分页
            ,cols: [[ //表头
                // {type:'numbers'}
                // ,{type: 'checkbox'}
                // ,{type:'radio'}
                 {field: 'CITY', title: '地市',width:70}
                ,{field: 'VERSION_DATE', title: '日期', width:100}
                ,{field: 'SECTOR_ID', title: '小区ID' ,event:'clickSectorId', width:120,style: 'cursor: pointer;color:#85EFFF'}
                ,{field: 'SECTOR_NAME', title: '小区名' , width:180}
                ,{field: 'LONGITUDE', title: '经度' , width:60}
                ,{field: 'LATITUDE', title: '纬度' , width:60}

            ]],
            done: function(res, curr, count){
                var data=res.data[0];
                // drawxdr.cell(data.SECTOR_ID);
            },


        });
        table.on('tool(sectorTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'clickSectorId') {
                drawxdr.cell(data.SECTOR_ID);
            }
        });


    });
}



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
                    m.update.ucl();
                    m.update.cell();
                    m.update.blackGrid("113","530100","2019-05-13");//初始加载
                }
            })
        },
        cell:function (cellid) {
            var m = new UCMap.UCLoctions();
            m.drawLayer.drawSector(cellid);
        },
        cellline:function (cellid) {
            var m = new UCMap.UCLoctions();
            m.drawLayer.drawSectorLine(cellid,lng,lat);
        },
    }