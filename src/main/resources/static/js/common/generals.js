/**
 * @description 通用方法或定义(累积)
 * @author yuan
 */
'use strict'
var Gels = window.Gels = Gels || {};
(function () {
    var gels = Gels.gels = function () {

    }
    /**
     *@desc  Check if string is time
     * @param [String] str 格式 hh:mm:ss
     * @returns {boolean}
     */
    String.prototype.isTime = function (str) {
        var a = str.match(/^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})$/);
        if (a == null || a[1] > 24 || a[3] > 60 || a[4] > 60) {
            return false
        }
        return true;
    }

    /**
     *@desc Check if string is shortdate
     * @param [String] str 格式 yyyy-mm-dd
     * @returns {boolean}
     */
    String.prototype.isShortDate = function () {
        var r = this.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
        if (r == null) return false;
        var d = new Date(r[1], r[3] - 1, r[4]);
        return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4]);
    }

    /**
     *@desc  Check if string is longdate
     * @param [String] str yyyy-mm-dd hh:mm:ss
     * @returns {boolean}
     */
     String.prototype.isLongDateTime = function () {
        var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
        var r = this.match(reg);
        if (r == null) return false;
        var d = new Date(r[1], r[3] - 1, r[4], r[5], r[6], r[7]);
        return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4] && d.getHours() == r[5] && d.getMinutes() == r[6] && d.getSeconds() == r[7]);
    }

    /**
     * @desc delete value from array
     * @param value
     */
    Array.prototype.remove = function (value) {
        var idx = this.indexOf(value);
        if (idx >= 0) {
            this.splice(idx, 1);
        }
    }

    /**
     *
     * @param [String] format
     * @returns {string}
     * @constructor
     */
    Date.prototype.Format = function(format)
    {
        var o = {
            "M+" : this.getMonth()+1,
            "d+" : this.getDate(),
            "h+" : this.getHours(),
            "m+" : this.getMinutes(),
            "s+" : this.getSeconds(),
            "q+" : Math.floor((this.getMonth()+3)/3),
            "S"  : this.getMilliseconds()
        };
        if(/(y+)/.test(format))
            format=format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)
            if(new RegExp("("+ k +")").test(format))
                format = format.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        return format;
    }

    /**
     * @desc Check if string is chinese
     * @returns {boolean}
     */
    String.prototype.isChinese = function () {
        var res = /[\u4E00-\u9FA5]/g;
        if (!res.test(this)) {
            return false;
        }
        return true;
    }

    /**
     * @desc Check if string is  number
     * @returns {boolean}
     */
    String.prototype.isNumeric = function () {
        var reg = /^[0-9]+.?[0-9]*$/;
        if (reg.test(this)) {
            return true;
        }
        return false;
    }


    /**
     *@desc Check if string is  float
     * @param [object] val
     * @return {boolean}
     */
    String.prototype.isFloat = function () {
        var regPos = /^\d+(\.\d+)?$/;
        var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/;
        if (regPos.test(this) || regNeg.test(this)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * @desc Check if string is  integer
     * @return {boolean}
     */
    String.prototype.isInt = function () {
        var regPos = / ^\d+$/; // 非负整数
        var regNeg = /^\-[1-9][0-9]*$/; // 负整数
        if (regPos.test(this) || regNeg.test(this)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @desc Check if string is positive integer
     * @return {boolean}
     */
    String.prototype.isPosInt = function () {
        var regPos = /^[0-9]*[1-9][0-9]*$/;
        if (regPos.test(this)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @desc Check if string is negative integer
     * @return {boolean}
     */
    String.prototype.isNegInt = function () {
        var regNeg = /^\-[1-9][0-9]*$/;
        if (regNeg.test(this)) {
            return true;
        } else {
            return false;
        }
    }


})();






