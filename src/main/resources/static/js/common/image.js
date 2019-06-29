$(function () {
    var jsobj = GetRequest();
    var photo = jsobj.photo;
    var photos =[];
    var imgs=[];
    var photocount=0;
    var currentimageid = 0;
    if(photo.indexOf(";") == -1){
        photos.push(photo);
        photocount++;
    }
    else{
        photos = photo.split(';');
    }
    var ss='';
    for(var i=0;i<photos.length;i++){
        if(photos[i]==null||photos[i]=='')continue;
        photocount++;
        imgs.push("<img src='"+photos[i]+"' />");
    }
    $('#divimg').html(imgs[0]);
    $('#divcount').html('共：'+imgs.length+'张图片，当前：'+(currentimageid+1));
    $('#up').click(function(){
        if(currentimageid<=0){
            currentimageid = imgs.length-1;
        }else{
            currentimageid--;
        }
        $('#divimg').html(imgs[currentimageid]);
        $('#divcount').html('共：'+imgs.length+'张图片，当前：'+(currentimageid+1));
    });
    $('#down').click(function(){
        if(currentimageid>=imgs.length-1){
            currentimageid = 0;
        }else{
            currentimageid++;
        }
        $('#divimg').html(imgs[currentimageid]);
        $('#divcount').html('共：'+imgs.length+'张图片，当前：'+(currentimageid+1));
    });
});



//地址获取参数
function GetRequest() {
    var url = location.search;
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        var strs = new Array();
        strs = str.split('&');
        if (strs.length < 1)return null;
        var photo = strs[0].split('=')[1];
        var jsobj = {};
        jsobj.photo = decodeURI(decodeURI(photo));
        return jsobj;
    }
}
