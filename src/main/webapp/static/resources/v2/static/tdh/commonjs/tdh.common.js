/*
 *常用js函数
 *author:yaoting,date:20181212
 */
var regName = new RegExp("^[\u4e00-\u9fa5a-zA-Z- ]*$"); //姓名正则
var regPhone = /^1[34578]\d{9}$/; //手机号正则
var regMail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/; //邮箱正则
var regSfz = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; //身份证证件号码正则
var regYzbm = /^[1-9][0-9]{5}$/; //邮政编码正则
var regGddh = /^0\d{2,3}-\d{7,8}(-\d{1,6})?$/; //座机号码正则
var regBase64 = /^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$/;//base64正则

// 验证手机号
function isPhone(value) {
  return regMobile.test(value);
}
// 验证邮箱
function isMail(value) {
  return regMail.test(value);
}
// 验证身份证证件号码
function isSfz(value) {
  return regSfz.test(value);
}
// 验证邮政编码
function isYzbm(value) {
  return regYzbm.test(value);
}
// 验证座机号码
function isGddh(value) {
  return regGddh.test(value);
}
// 去掉字符串前后所有空格
function trim(value) {
    if (value) {
        value = value + ''; //int类型不支持replace 隐式转换为string
        value = value.replace(/^\s*|\s*$/g, "");
    }
    if (!value)
        return "";
    else
        return value;
}
// 去掉字符串中所有空格(包括中间空格,需要设置第2个参数为:g)
function Trim(str,is_global) {
    var result;
    result = str.replace(/(^\s+)|(\s+$)/g,"");
    if(is_global.toLowerCase()=="g")
    {
        result = result.replace(/\s/g,"");
    }
    return result;
}
// 只能输入整数或者浮点数onkeyup="onlyFloat(this)"
function onlyFloat(athis) {
  athis.value = athis.value.replace(/[^\-?\d.]/g, '');
}
// 只能输入整数onkeyup="onlyInt(this)"
function onlyInt(athis) {
  athis.value = athis.value.replace(/[^\-?\d]/g, '');
}
// 字数限制
function textCounter(obj, maxlimit) {
   var val = obj.value;
   var len = 0;
    for (var i=0; i<val.length; i++) {
    	var c = val.charCodeAt(i);
    	if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) {
    		len++;
    	}else {
    		len+=2;
     	}
    }
	if (len > maxlimit * 2){
		alert("字数超出长度限制！");
		obj.focus();
   }
}
// 日期加减 var newDate = dateAdd("2018-12-12", "dd", -3)
function dateAdd(mydate, type, add) {
	var d = new Date(mydate);
	if (type == "dd") { // 天
		d.setDate(d.getDate() + add);
	}
	if (type == "MM") { // 月
		d.setMonth(d.getMonth() + add);
	}
	if (type == "yyyy") { // 年
		d.setFullYear(d.getFullYear() + add);
	}
  	return d;
}
// 两个日期之间的时间差var time = dateCha("2018-12-12","2019-01-01")
function dateCha(begintime,endtime){
	var btime = Date.parse(new Date(begintime)),
		etime = Date.parse(new Date(endtime));
	var usedTime = etime - btime;  //两个时间戳相差的毫秒数
	var days = Math.floor(usedTime/(24*3600*1000));
	
	console.log(usedTime)
	//计算出小时数
	var leave1 = usedTime%(24*3600*1000);    //计算天数后剩余的毫秒数
	var hours = Math.floor(leave1/(3600*1000));
	//计算相差分钟数
	var leave2 = leave1%(3600*1000);        //计算小时数后剩余的毫秒数
	var minutes = Math.floor(leave2/(60*1000));
	var time = days + "天"+hours + "时" + minutes + "分";
	return time;
}
// 日期转字符串
function dateToStr(datetime) {
	var year = datetime.getFullYear(),
		month = datetime.getMonth() + 1,// js从0开始取
		day = datetime.getDate();
	if (month < 10) { month = "0" + month; }
	if (day < 10) { day = "0" + day; }
	var str = year + "-" + month + "-" + day; // 2018-12-12
	return str;
}
// 时间字符串转换成日期2018-12-12
function strToDate(str) {
  if (!str) return null;
  var datetime = new Date();
  datetime.setFullYear(parseInt(str.split("-")[0], 10));
  datetime.setMonth(parseInt(str.split("-")[1], 10) - 1);
  datetime.setDate(parseInt(str.split("-")[2], 10));
  return datetime;
}

// 对参数编码
function encodeStr(val) {
  return encodeURIComponent(encodeURIComponent(trim(val)));
}

//ie版本
function IEVersion() {
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串  
    var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1; //判断是否IE<11浏览器  
    var isEdge = userAgent.indexOf("Edge") > -1 && !isIE; //判断是否IE的Edge浏览器  
    var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
    if(isIE) {
        var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
        reIE.test(userAgent);
        var fIEVersion = parseFloat(RegExp["$1"]);
        if(fIEVersion == 7) {
            return 7;
        } else if(fIEVersion == 8) {
            return 8;
        } else if(fIEVersion == 9) {
            return 9;
        } else if(fIEVersion == 10) {
            return 10;
        } else {
            return 6;//IE版本<=7
        }   
    } else if(isEdge) {
        return 'edge';//edge
    } else if(isIE11) {
        return 11; //IE11  
    }else{
        return -1;//不是ie浏览器
    }
}