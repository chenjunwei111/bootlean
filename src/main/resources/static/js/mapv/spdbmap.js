var hasLoaded = false;
$(function() {
    if (jQuery == "undefined") {
        loadScript('../../js/common/jquery-3.1.1.js?ver=1', addJs);
    } else {
        addJs();
    }
});
function waitjs(k, f) {
    var t = setInterval(function() {
        if (jsl && k in jsl && jsl[k] == 'success') {
            clearInterval(t);
            if (f && typeof f == 'function') {
                f();
            }

        }
    }, 1)
}

var jsl = {};
var $map = -1;
function addJs() {
    $("<link>").attr({
        rel : "stylesheet",
        type : "text/css",
        href : "../static/css/mapv/maptools.css"
    }).appendTo("head");

    $("<link>").attr({
        rel : "stylesheet",
        type : "text/css",
        href : "../static/css/mapv/web.contextmenu.css"
    }).appendTo("head");

    $("<link>").attr({
        rel : "stylesheet",
        type : "text/css",
        href : "../static/css/mapv/spdbmap.css"
    }).appendTo("head");
    $("<link>").attr({
        rel : "stylesheet",
        type : "text/css",
        href : "../static/css/mapv/Legend.css"
    }).appendTo("head");
    $("<link>").attr({
        rel : "stylesheet",
        type : "text/css",
        href : "../static/css/mapv/mtool.css"
    }).appendTo("head");
    $("<link>").attr({
        rel : "stylesheet",
        type : "text/css",
        href : "../static/css/mapv/maptree.css"
    }).appendTo("head");

    $
        .getScript(
            "https://api.map.baidu.com/getscript?v=2.0&ak=1XjLLEhZhQNUzd93EjU5nOGQ&services=&t=&",
            function(r, s) {
                if (s == "success") {
                    $map = 0;
                  
                    //$.getScript("../../js/mapv/mapv.js");
                    $.getScript("../static/js/mapv/mapv.js",
                          function(r, s) {
                              jsl["mapv"] = s;// S:"success",
                              // "notmodified",
                              // "error",
                              // "timeout" 或
                          });
                    $.getScript("../static/js/mapv/UCMap.js",
                        function(r, s) {
                            jsl["UCMap"] = s;// S:"success",
                            // "notmodified",
                            // "error",
                            // "timeout" 或
                        });
                }
            }).fail(function() {
        $map = 1;
        $.getScript(" ../static/js/olmap/olm-component.js", function() {
            $.getScript("../static/js/mapv/mapv.js", function() {
                $.getScript("../static/js/mapv/UCMap.js", function(r, s) {
                    jsl["UCMap"] = s;// S:"success", "notmodified",
                    // "error", "timeout" 或
                });
            });
        });
    });
    $.getScript("../static/js/mapv/LMap.js");
    $.getScript("../static/js/mapv/commdiv.js", function(r, s) {
        jsl["commdiv"] = s;// S:"success", "notmodified", "error", "timeout" 或
        // "parsererror"
    });
    $.getScript("../static/js/mapv/basemap.js");
    $.getScript("../static/js/mapv/mapcutter.js");
    $.getScript("../static/js/mapv/coordtransform.js");
    $.getScript("../static/js/mapv/sectormap.js");
    $.getScript("../static/js/mapv/layermanager.js");
    $.getScript("../static/js/common/contextmenu.js");
    $.getScript("../static/js/common/jquery-ui.js");
    $.getScript("../static/js/mapv/MeasureTool.js");
    $.getScript("../static/js/mapv/stylemap.js");
    $.getScript("../static/js/mapv/Legend.js");
    $.getScript("../static/js/mapv/Legends.js");
    $.getScript("../static/js/mapv/maptools.js");
    $.getScript("../static/js/mapv/maptree.js");
    $.getScript("../static/js/mapv/mapLabel.js");

    $.getScript("../static/js/mapv/PjComm.js");

    $.getScript("../static/js/mapv/RectangleZoom_min.js");
    $.getScript("../static/js/mapv/rightkey.js");
    // $.getScript("../static/js/mapv/mapLabel.js");

    $.getScript("../static/js/mapv/geoMapTool.js");
    $.getScript("../static/js/mapv/mapGeometry.js");

    // $.getScript("../static/js/mapv/UCMap.js", function (r, s) {
    // jsl["UCMap"] = s;// S:"success", "notmodified", "error", "timeout" 或
    // });
}

function loadScript(url, callback) {
    var script = document.createElement("script");
    script.type = "text/javascript";
    if (typeof (callback) != "undefined") {
        if (script.readyState) {
            script.onreadystatechange = function() {
                if (script.readyState == "loaded"
                    || script.readyState == "complete") {
                    script.onreadystatechange = null;
                    callback();
                }
            };
        } else {
            script.onload = function() {
                callback();
            };
        }
    }
    script.src = url;
    document.body.appendChild(script);
}

function exismap() {
    var js = "api.map.baidu.com";
    var sr = "services=&t=";
    var es = document.getElementsByTagName('script');
    for (var i = 0; i < es.length; i++)
         var _es = es[i]['src'];
    if (_es.indexOf(js) > 0 && _es.indexOf(sr) > 0)
        return true;
    return false;
}
