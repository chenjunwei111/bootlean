//初始化
$(function () {
    selmap();

})

//点击事件~（1个）
layui.use('table', function() {
    var table = layui.table;
    table.on('tool(tableData)', function (obj) {
        var data = obj.data;
        poiid = data.id;
        if (obj.event === 'clkname'){
            var c=coordtransform.wgs84tobd09(data.lng,data.lat);
            $Layer.panToFlag(data,{
                coord:'wgs',distimer:3000
            })
            $Layer.drawSceneComplaintLayer(poiid,'20190501','20190510');
        }
    })
});



//地图核心方法
function  selmap() {




    waitjs("UCMap",function () {
        var ucm=new UCMap.UCLoctions();
        ucm.setparameters({
            mcity:'昆明',
            mcitycode:'530100',
            mdate:'20190510',
            mselector:'maps',
        })
        ucm.map();
        // ucm.update.cell();
        var  top=  ucm.drawLayer.drawPoiSceneLayer(530100,530103,'20190501','20190510',"",0,function (top) {
            showTable(top);
            // console.log(top)
        });
        //  ucm.drawLayer.drawPoiSceneHotLayer();
        //  showTable(top);
    })
}

function  showTable(_top) {
    var w = $('#tableData').width();
    var header = [
        // { checkbox: true,fixed: false,width:w/14,title:'勾选场景，可以加入监控'},
        {'field': 'name', 'title': '场景名', 'width':120,event:'clkname', style: 'cursor: pointer;color:#85EFFF;'},
        {'field': 'areaname', 'width': 90, 'title': '区县'},
        {'field': 'tag', 'width': 120, 'title': '场景类型'},
        {'field': 'cnt', 'width': 90, 'title': '工单量'},
        // {'field': 'upgrade_cnt', 'width': w/8, 'title': '投诉升级<br>用户数'},
        // {'field': 'id', 'title': '明细','width': w/12,toolbar : '#toolopt', style: 'cursor: pointer;'}
    ]
    commdiv.$worker({
        elem: 'tableData',
        fields: header,
        data: _top,
        page: {show: true, limits:'[20, 50, 100]', limit:20},
        //clkevt: {
        //    idx: 1, name: 'clkname', cbfun: function (rsl) {
        //        var c=coordtransform.wgs84tobd09(rsl.lng,rsl.lat);
        //         $Layer.panToFlag(rsl,{
        //             coord:'wgs',distimer:3000
        //         })
        //    }
        //}        ,
        height: 680,
        done:function (rsl) {
            if(rsl.data.length>0)
                $Layer.drawSceneComplaintLayer(rsl.data[0].id,'20190501','20190510');
            $('#tableData').next().css('margin','0')
        }
    })
}

