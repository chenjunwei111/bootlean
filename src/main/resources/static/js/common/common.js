
$(function () {
    var roleId=$('.roleId', window.parent.document).val();
    if(roleId==""||roleId==undefined||roleId==" "){
        return;
    }
    getSelectCity(roleId);//获取地市下拉列表
    $("input").attr("autocomplete","off");
})


//传入时间 2018-01-24 23:32:12  计算距离当前时间（jeuery）
function secondToDate(orderSendTime) {
    if(orderSendTime==null||orderSendTime==""){
        return "";
    }
    var sendDate=new Date(orderSendTime).getTime();//字符串转时间戳
    var nowDate=new Date().getTime();//当前时间
    var limitDate=nowDate-sendDate;
    var time= limitDate / 1000;
    if (null != time && "" != time) {
        if (time > 60 && time < 60 * 60) {
            // console.log("分："+time)
            time = parseInt(time / 60.0) + "分钟" + parseInt((parseFloat(time / 60.0) -
                parseInt(time / 60.0)) * 60) + "秒";
        }
        else if (time >= 60 * 60 && time < 60 * 60 * 24) {
            // console.log("时："+time)
            time = parseInt(time / 3600.0) + "小时" + parseInt((parseFloat(time / 3600.0) -
                parseInt(time / 3600.0)) * 60) + "分钟" +
                parseInt((parseFloat((parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60) -
                    parseInt((parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60)) * 60) + "秒";
        } else if (time >= 60 * 60 * 24) {
            // console.log("天："+parseInt(time / 3600.0/24) + "天" );
            // console.log("小时："+ parseInt((parseFloat(time / 3600.0/24)- parseInt(time / 3600.0/24))*24) + "小时")
            // console.log("分钟："   + parseInt((parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60) + "分钟")
            var day=parseInt(time / 3600.0/24);
            var hour=parseInt((parseFloat(time / 3600.0/24)- parseInt(time / 3600.0/24))*24);
            var minute=parseInt((parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60);
            time=day+"天"+hour+"时"+minute+"分";
            // console.log("时间："+time);
            // time = parseInt(time / 3600.0/24) + "天" +
            //     parseInt((parseFloat(time / 3600.0/24)- parseInt(time / 3600.0/24))*24) + "小时"
            //     + parseInt((parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60) + "分钟"
            //     // +
            //     // parseInt((parseFloat((parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60) -
            //     //     parseInt((parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60)) * 60) + "秒";
        }
        else {
            time = parseInt(time) + "秒";
        }
    }
    return time;
}

/*返回 xxxx天xxxx分钟，传入分钟*/
function getMinuteTime(Minute) {
    var Minute2=Minute;
    Minute=Math.abs(Minute);

    if(Minute==0){
        return "0";
    }
    if(Minute==null){
        return "";
    }
    var time=Minute
    if (null != time && "" != time) {
        if ( time < 60 ) {
            time = parseInt(time) + "分钟"
        }
        else if (time >= 60 && time < 60 * 24) {
            time = parseInt(time / 60.0) + "小时" + parseInt((parseFloat(time / 60.0) -
                parseInt(time / 60.0)) * 60) + "分钟"
        } else if (time >=  60 * 24) {
            var day=parseInt(time / 60.0/24);
            var hour=parseInt((parseFloat(time / 60.0/24)- parseInt(time / 60.0/24))*24);
            var minute=parseInt((parseFloat(time / 60.0) - parseInt(time / 60.0)) * 60);
            time=day+"天"+hour+"时"+minute+"分";
        }
        else {
            time = parseInt(time) + "分钟";
        }
    }
    if(Minute2<0){
        return "-"+time;
    }else {
        return time;
    }
}

/*
* 获取两个时间的间隔分钟时间
* */
function getTwoDateValue(date1,date2) {
    if(date1==null||date2==null){
        return null;
    }
    if(date1.indexOf("-")>0){
        date1=new Date(date1).getTime();//字符串转时间戳
    }
    if(date2.indexOf("-")>0){
        date2=new Date(date2).getTime();//字符串转时间戳
    }
    var limitDate=date2-date1;
    var time= limitDate / 1000;
    time=getMinuteTime(parseInt(time / 60.0));
    return time;
}




//时间戳转日期格式 1060000000 --》2018-01-24 23:32:12
function timestampToTime(timestamp) {
    // console.log(timestamp)
    if(timestamp==null){
        return "";
    }
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    Y = date.getFullYear() + '-';
    M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    D = (date.getDate() <10? '0'+(date.getDate()):date.getDate()) + ' ';
    h = (date.getHours()<10? '0'+(date.getHours()):date.getHours() )+ ':';
    m = (date.getMinutes()<10?'0'+(date.getMinutes()):date.getMinutes())+ ':';
    s = date.getSeconds()<10? '0'+(date.getSeconds()):date.getSeconds();
    return Y+M+D+h+m+s;
}

layui.use(['layer','jquery','laydate','element','form'], function() {
    var $ = layui.jquery //重点处
        ,layer = layui.layer;
    var laydate=layui.laydate
    $(function () {
        // laydate.render({
        //     elem: '#dateRange', //指定元素
        //     range: true
        // });

        laydate.render({
            elem: '#date' //指定元素
        });

        function isIE() { //ie?
            if (!!window.ActiveXObject || "ActiveXObject" in window)
                return true;
            else
                return false;
        }

        if(isIE()){
            console.log("IE")
            $(".SlectBox").css("display","inline-table");
            $(".SlectBox").css("width","150px");
            $(".SumoSelect").css("display","inline-table");
            $(".misangles").css("line-height","inherit");
        }
    })
});

//消息弹出框
function msg(message){
    layui.use('layer', function() {
        layer = layui.layer;
        layer.msg( '<span style="color: black">'+message+'<span>', {
            icon: 16,
            time: 1500, //1秒关闭（如果不配置，默认是3秒）
            shade:0.5
        });
    });
}

//消息弹出框
function msgtime(message,time){
    layui.use('layer', function() {
        layer = layui.layer;
        layer.msg('<span style="color: black">'+message+'<span>', {
            icon: 1,
            time: time, //1秒关闭（如果不配置，默认是3秒）
            shade:0.5
        });
    });
}

// /*
// yuan 提示
// pos:显示的位置，默认右  1 上，3 下，4 左
//  */
// function tip(str,sid,pos) {
//     layui.use('layer', function() {
//         layui.layer.tips(str,sid, {
//             tips: pos,
//             time: 3000
//         });
//     });
// }

var thing=null;
function loadStar(message) {
    setTimeout( function () {
        console.log("开始加载")
        layui.use(['layer','jquery'], function() {
            layer = layui.layer;
            if(message==null){
                thing=layer.msg( "<span >数据处理中，请稍候....</span>", {
                    icon: 16,
                    time: 0, //1秒关闭（如果不配置，默认是3秒）
                });
            }else {
                thing=layer.msg("<span>"+message+"</span>", {
                    icon: 16,
                    time: 0, //1秒关闭（如果不配置，默认是3秒）
                });
            }
        });
    }, 100)
}

function loadStop() {
    setTimeout( function () {
        layui.use(['layer','jquery'], function() {
            layer = layui.layer;
            layer.closeAll();
        })
    }, 100)
}

function  reload() {
    location.reload();
}

//获取当前时间，格式YYYY-MM-DD
function getNowFormatDate(time) {
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    if(time){
        var hour=date.getHours();
        var minutes=date.getMinutes();
        var seconds=date.getSeconds()
        return date.getFullYear() + '-' + getFormatDate(month) + '-' + getFormatDate(strDate)+" "+getFormatDate(hour)
            +":"+getFormatDate(minutes)+":"+getFormatDate(seconds);
    }else{
        var currentdate = year + seperator1 + month + seperator1 + strDate;
        return currentdate;
    }

}
/*
* 验证邮件地址 xxxxx.xxx.xxx@xxx.xxx
* */
function isEmail(str){
    var regex = /^([0-9A-Za-z\-_\.]+)@([0-9A-Za-z\-_\.]+)$/g;
    if(regex.test( str )){
        return true;
    }
    return false;
}

/**
 * 验证手机号码
 *
 * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
 * 联通号码段:130、131、132、136、185、186、145
 * 电信号码段:133、153、180、189
 *
 */
function isCellphone( str) {
    var regex = /^\d{11}$/g;
    if(regex.test( str )){
        return true;
    }
    return false;
}

/**
 * 验证固话号码
 *
 */
function isTelephone( str) {
    var regex = /^(0\d{2}-\d{8}(-\d{1,4})?)|(0\d{3}-\d{7,8}(-\d{1,4})?)$/g;
    if(regex.test( str )){
        return true;
    }
    return false;
}



//获取下拉城市
function getSelectCity(roleId) {
    $.ajax({
        url:'/contra/TROLEDepartment/getSelectCity',
        type:'post',
        async:false,
        data:{roleId:roleId},
        success:function (res) {
            var option="";
            $("#selectCity").empty();
            for(var i=0;i<res.length;i++){
                option="<option value='"+res[i].DEPT_CODE+"'>"+res[i].DEPT_NAME+"</option>"
                $("#selectCity").append(option);
            }
        }
    })
}

//获取下拉城市
function getCity(id,selected) {
    $.ajax({
        url:'/contra/TROLEDepartment/getSelectAllCity',
        type:'post',
        async:false,
        data:{},
        success:function (res) {
            var option="";
            $("#"+id).empty();
            for(var i=0;i<res.length;i++){
                if(selected==res[i].AREA_CODE){
                    option="<option value='"+res[i].AREA_CODE+"' selected>"+res[i].DEPT_NAME+"</option>"
                }else{
                    option="<option value='"+res[i].AREA_CODE+"'>"+res[i].DEPT_NAME+"</option>"

                }
                $("#"+id).append(option);
            }
        }
    })
}




//传cityCode判断地市级别  返回当前级别，城市名称，CODE， 父级code
function decideCityLevel(cityCode) {
    var level=""
    $.ajax({
        url:'/contra/TROLEDepartment/decideCityLevel',
        type:'post',
        async:false,
        data:{cityCode:cityCode},
        success:function (res) {
            if(res.data!=null && res.data.length!=0 ){
                level=res.data[0];
            }
        }
    })
    return level;
}



//select下拉选择，ID、选择的值
function set_select_checked(selectId, checkValue){
    var select = document.getElementById(selectId);
    if(checkValue==null){
        checkValue="请选择"
    }
    for (var i = 0; i < select.options.length; i++){
        if (select.options[i].text == checkValue){
            select.options[i].selected = true;
            break;
        }
    }
}


/*
*
 * 倒计时传入分钟 返回xx分钟xx秒
  * */
// var  timer=null;
// function getLimitTime(times) {
//     clearInterval(timer);
//     var times=parseInt(times)*60;
//     var  maxtime=times;
//       timer = setInterval(function () {
//         if (maxtime >= 0) {
//             minutes = Math.floor(maxtime / 60);
//             seconds = Math.floor(maxtime % 60);
//             msg =  minutes + "分" + seconds + "秒";
//             $("#showTime").text(msg);
//             --maxtime;
//         } else{
//             clearInterval(timer);
//         }
//     }, 1000);
// }

/*电话号码屏蔽*/
function replacePhone(phone) {
    var phoneNum=phone.replace(/(\d{3})(\d{4})(\d{4})/,"$1****$3")
    return phoneNum;
}



//传入时间、天数   当前时间 （天数加减）
function addDate(date, days) {
    if (days == undefined || days == '') {
        days = 1;
    }
    var date = new Date(date);
    date.setDate(date.getDate() + days);
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hour=date.getHours();
    var minutes=date.getMinutes();
    var seconds=date.getSeconds()
    return date.getFullYear() + '-' + getFormatDate(month) + '-' + getFormatDate(day)+" "+getFormatDate(hour)
        +":"+getFormatDate(minutes)+":"+getFormatDate(seconds);
}


/*
* 传入分钟 当前时间 （分钟加减）
* */
function getAddDate(minute) {
    minute=parseFloat(minute).toFixed();
    var date = new Date();
    date.setMinutes(date.getMinutes()+parseInt(minute));
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hour=date.getHours();
    var minutes=date.getMinutes();
    var seconds=date.getSeconds()
    return date.getFullYear() + '-' + getFormatDate(month) + '-' + getFormatDate(day)+" "+getFormatDate(hour)
        +":"+getFormatDate(minutes)+":"+getFormatDate(seconds);
}

//个位数前面加零
function getFormatDate(arg) {
    if (arg =="0") {
        return '00';
    }
    if (arg == undefined || arg == '') {
        return '';
    }
    var re = arg + '';
    if (re.length < 2) {
        re = '0' + re;
    }
    return re;
}


var highlightindex2 =-1;   //高亮
var keyNum=null;
var map2=null;
var MapUrl=null;
var MapautDiv = null;
var MapsearchInput = null;
//下拉预显列表方法
/*
 * auto  下拉显示ID
 * search 输入框ID
 * map   传入OBJ对象
 * url   传入接口地址
 * */
function AutoCompleteMapAll(auto,search,map,url) {
    MapautDiv = auto;
    MapsearchInput = search;
    map2=map;
    MapUrl=url;
    if ($("#" + search).val() != "") {
        var autoNode = $("#" + auto); //缓存对象（弹出框）
        var carlist = new Array();
        var n = 0;
        var mylist = [];
        var maxTipsCounts = 8 // 最大显示条数
        var highlightindex = -1;   //高亮
        var aj = $.ajax({
            url: url , // 跳转到后台
            contentType: 'application/json',
            data: JSON.stringify(map),
            type: 'post',
            cache: false,
            dataType: 'json',
            success: function(data) {

                mylist = data;
                if (mylist == null) {
                    autoNode.hide();
                    return;
                }
                autoNode.empty(); //清空上次的记录

                var keyName=null;
                for(key in mylist[0])
                {
                    keyName=key;
                }
                for (i in mylist) {
                    if (i < maxTipsCounts) {
                        var wordNode = mylist[i][keyName]; //弹出框里的每一条内容

                        var newDivNode = $("<div>").attr("id", i); //设置每个节点的id值

                        // document.querySelector("#auto_div").style.width = $("#search_text").outerWidth(true)+10+ 'px'; //设置提示框与输入框宽度一致
                        document.querySelector("#"+auto).style.width ='auto';
                        document.querySelector("#"+auto).style.backgroundColor ='#0a4554';
                        document.querySelector("#"+auto).style.position ='absolute';
                        document.querySelector("#"+auto).style.zIndex='99999';
                        newDivNode.attr("style", "font:14px/25px arial;height:28px;padding:2px 20px;cursor: pointer;");
                        newDivNode.html(wordNode).appendTo(autoNode); //追加到弹出框
                        //鼠标移入高亮，移开不高亮
                        newDivNode.mouseover(function() {
                            if (highlightindex != -1) { //原来高亮的节点要取消高亮（是-1就不需要了）
                                autoNode.children("div").eq(highlightindex).css("background-color", "#0a4554");
                            }
                            //记录新的高亮节点索引
                            highlightindex = $(this).attr("id");
                            $(this).css("background-color", "#183851");
                        });
                        newDivNode.mouseout(function() {
                            $(this).css("background-color", "#0a4554");
                        });
                        //鼠标点击文字上屏
                        newDivNode.click(function() {
                            //取出高亮节点的文本内容
                            var comText = autoNode.hide().children("div").eq(highlightindex).text();
                            highlightindex = -1;
                            //文本框中的内容变成高亮节点的内容
                            $("#" + search).val(comText);
                            $("#search-form").submit();
                        })
                        if (mylist.length > 0) { //如果返回值有内容就显示出来
                            autoNode.show();
                        } else { //服务器端无内容返回 那么隐藏弹出框

                            autoNode.hide();
                            //弹出框隐藏的同时，高亮节点索引值也变成-1
                            highlightindex = -1;
                        }
                    }
                }
            }
        });
    }


    $("#"+MapsearchInput).keyup(function(event) {
        //处理键盘操作
        var myEvent = event || window.event;
        var keyCode = myEvent.keyCode;
        if (keyCode == 38 || keyCode == 40) {
            if (keyCode == 38) { //向上
                var autoNodes = $("#"+MapautDiv).children("div");
                if (highlightindex2 != -1) {
                    autoNodes.eq(highlightindex2).css("background-color", "#0a4554");
                    highlightindex2--;
                } else {
                    highlightindex2 = autoNodes.length - 1;
                }
                if (highlightindex2 == -1) {
                    highlightindex2 = autoNodes.length - 1;
                }
                autoNodes.eq(highlightindex2).css("background-color", "#183851");
            }
            if (keyCode == 40) { //向下
                var autoNodes = $("#"+MapautDiv).children("div");
                if (highlightindex2 != -1) {
                    autoNodes.eq(highlightindex2).css("background-color", "#0a4554");
                }
                highlightindex2++;
                if (highlightindex2 == autoNodes.length) {
                    highlightindex2 = 0;
                }
                autoNodes.eq(highlightindex2).css("background-color", "#183851");
            }
        } else if (keyCode == 13) { //回车键
            if (highlightindex2 != -1) {
                var comText = $("#"+MapautDiv).hide().children("div").eq(highlightindex2).text();

                highlightindex2 = -1;
                $("#"+MapsearchInput).val(comText);
                if ($("#"+MapautDiv).is(":visible")) {
                    $("#"+MapautDiv).css("display", "none");
                }
            }

        } else {
            if ($("#"+MapsearchInput).val() == "") {
                $("#auto_div").hide();
            } else {   //有文字输入时获取提示词
                if(map2!=""||map2!=null){
                    var numValue="";
                    for(key in map2){
                        numValue=key;
                        break;
                    }
                    map2[numValue]=$("#"+MapsearchInput).val();
                    if(keyNum==null || keyNum!=$("#"+MapsearchInput).val()){
                        keyNum=$("#"+MapsearchInput).val();
                        AutoCompleteMapAll(MapautDiv,MapsearchInput,map2,MapUrl);
                    }
                }
            }
        }
    });

};



//点击屏幕 -框消失
document.onclick = function(e) {
    var e = e ? e : window.event;
    var tar = e.srcElement || e.target;
    if (tar.id != MapsearchInput) {
        if ($("#"+MapautDiv).is(":visible")) {
            $("#"+MapautDiv).css("display", "none");
        }
    }
}



//判断数字
// function isNumber(val){
//
//     var regPos = /^\d+(\.\d+)?$/; //非负浮点数
//     var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
//     if(regPos.test(val) || regNeg.test(val)){
//         var y = String(val).indexOf(".") + 1;//获取小数点的位置
//         var count = String(val).length - y;//获取小数点后的个数
//         if(y > 2) {
//             return true;
//         } else {
//             return false;
//         }
//     }else{
//         return false;
//     }
//
// }

//----------------------------勿注释，小廖----------------------------//
//获取日期
function getDay(day){
    var today = new Date();
    var targetday_milliseconds=today.getTime() + 1000*60*60*24*day;
    today.setTime(targetday_milliseconds); //注意，这行是关键代码
    var tYear = today.getFullYear();
    var tMonth = today.getMonth();
    var tDate = today.getDate();
    tMonth = doHandleMonth(tMonth + 1);
    tDate = doHandleMonth(tDate);
    return tYear+"-"+tMonth+"-"+tDate;
}
//指定当天为今天，获取日期
function getDay(s, day){
    var today = parseDate(s);
    var targetday_milliseconds=today.getTime() + 1000*60*60*24*day;
    today.setTime(targetday_milliseconds); //注意，这行是关键代码
    var tYear = today.getFullYear();
    var tMonth = today.getMonth();
    var tDate = today.getDate();
    tMonth = doHandleMonth(tMonth + 1);
    tDate = doHandleMonth(tDate);
    return tYear+"-"+tMonth+"-"+tDate;
}
function doHandleMonth(month){
    var m = month;
    if(month.toString().length == 1){
        m = "0" + month;
    }
    return m;
}
//解析日期
function parseDate(str){
    if(typeof str == 'string'){
        var results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) *$/);
        if(results && results.length>3)
            return new Date(results[1],results[2] -1,results[3]);
        results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2}) *$/);
        if(results && results.length>6)
            return new Date(results[1],results[2] -1,results[3],results[4],results[5],results[6]);
        results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2})\.(\d{1,9}) *$/);
        if(results && results.length>7)
            return new Date(results[1],results[2] -1,results[3],results[4],results[5],results[6],results[7]);
    }
    return null;
}
//----------------------------勿注释，小廖----------------------------//

/*
yuan 提示
pos:显示的位置，默认右  1 上，3 下，4 左
 */
function tip(str,sid,pos) {
    layer.tips(str,sid, {
        tips: pos,
        time: 3000
    });
}
