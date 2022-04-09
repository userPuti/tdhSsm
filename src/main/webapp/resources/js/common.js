//去掉空格
function trim(value){
	if (value) value = value.replace(/^\s*|\s*$/g,"");
	if (!value) return "";
	else return value;
}
// 如果输入框中有"&"符号，替换掉
function trimstr(value){
	if (value) value = value.replace(/^\s*|\s*$/g,"");
	if (!value) return "";
	else return value = value.replace(/&/g,"");
}
// 对参数编码
function encodeStr(val){
	return encodeURIComponent(encodeURIComponent(trim(val)));
}
// 打开模式窗口
function openModal(url, args,width,height,scroll) {
	var ua = navigator.userAgent.toLowerCase();
	// alert(ua);
	if (window.ActiveXObject && ua.indexOf('msie 6.')>=0) {  // IE6
		height = parseInt(height) + 80;
    // alert('66666');
	}
	if(!scroll)scroll = "no";
	else scroll = "yes";
	var rtn = window.showModalDialog(url,args,'dialogWidth='+width+'px;dialogHeight='+height+'px;resizeable=no;scroll='+scroll+';status=no;help=no;');
	return rtn;
}
// 最大化打开普通窗口
function openMax(url) { 
	var width = window.screen.availWidth - 10;
	var height = window.screen.availHeight - 30;
	var Left_size = 0;// (screen.width) ? (screen.width-width)/2 : 0;
	var Top_size = 0;// (screen.height) ? (screen.height-height)/2 : 0;
	window.open(url, '_blank', 'screenX=0,screenY=0,width=' + width + 'px, height=' + height + 'px, left=' + Left_size + 'px, top=' + Top_size + 'px,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes' );
}
// 打开普通窗口居中，自定义大小，可最大化
function openWindow(url, width, height) {
	var Left_size = (screen.width) ? (screen.width-width)/2 : 0;
	var Top_size = (screen.height) ? (screen.height-height)/2 : 0;
	window.open(url, '_blank', 'width=' + width + 'px, height=' + height + 'px, left=' + Left_size + 'px, top=' + Top_size + 'px,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes' );
}
// 打开普通窗口居中，自定义大小，不可最大化
function openWin(url, width, height) {
	var Left_size = (screen.width) ? (screen.width-width)/2 : 0;
	var Top_size = (screen.height) ? (screen.height-height)/2 : 0;
	window.open(url, '_blank', 'width=' + width + 'px, height=' + height + 'px, left=' + Left_size + 'px, top=' + Top_size + 'px,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no' );
}


// 以下方法用于自适应页面。设置fitHeight值,当页面宽度大于fitHeight时候，主表格宽度为fitHeight，
// 否则为宽度为100%
var fitHeight = 1000;
if(!Array.prototype.map)
	Array.prototype.map = function(fn,scope) {
		var result = [],ri = 0;
		for (var i = 0,n = this.length; i < n; i++){
			if(i in this){
				result[ri++]  = fn.call(scope ,this[i],i,this);
		}
   }
	return result;
};

var getWindowSize = function(){
	return ["Height","Width"].map(function(name){
		return window["inner"+name] || 
		document.compatMode === "CSS1Compat" && document.documentElement[ "client" + name ]
       || document.body[ "client" + name ]
    });
}

window.onload = function(){
	if(!+"\v1" && !document.querySelector) { // for IE6 IE7
		document.body.onresize = resize;
	} else {
		window.onresize = resize;
    }
	function resize(tablename,fitheight) {
		if (document.getElementById("maintable")) {
			if(parseInt(document.body.clientWidth) > fitHeight) {
				maintable.width = fitHeight;
			} else {
				maintable.width = "100%";
			}
		}
	}
}

// 检测val是否在str中.str的格式为"xxx1,xxx2"
function inVal(val, str) {
	if (trim(val) == "") return false;
	var flag = false;
	var arr = str.split(",");
	for (var i = 0; i < arr.length; i++) {
		if (val == arr[i]) {
			flag = true;
			break;
		}
	}
	return flag;
}

// 页面设置为可读
function setPageRead() {
	var inputs = document.getElementsByTagName("INPUT");
	for (var i=0; i<inputs.length; i++) {
		inputs[i].disabled = true;
	}
	var selects = document.getElementsByTagName("SELECT");
	for (var i=0; i<selects.length; i++) {
		selects[i].disabled = true;
	}
	var imgs = document.getElementsByTagName("IMG");
	for (var i=0; i<imgs.length; i++) {
      imgs[i].disabled = true;
    }
    var textareas = document.getElementsByTagName("TEXTAREA");
    for (var i=0; i<textareas.length; i++) {
      textareas[i].disabled = true;
    }
    var as = document.getElementsByTagName("A");
    for (var i=0; i<as.length; i++) {
      as[i].disabled = true;
      as[i].onclick  = "";
    }
}
  
// 获取文件的真实完整路径
function getPath(obj){
	if(obj){
		if (window.navigator.userAgent.indexOf("MSIE") >= 1){
			obj.select();
			return document.selection.createRange().text;
		} else if(window.navigator.userAgent.indexOf("Firefox")>=1){
			if(obj.files){
				return obj.files.item(0).getAsDataURL();
			}
			return obj.value;
		}
		return obj.value;
	}
}

// 清空对应的input type = file
function clearFile(id){
	var _file = document.getElementById(id);
	if(_file.files) _file.value = "";
	else{
		if (typeof _file != "object")return null;
		var _span = document.createElement("span");
		_span.id = "__tt__";
		_file.parentNode.insertBefore(_span,_file);
		var tf = document.createElement("form");
		tf.appendChild(_file);
		document.getElementsByTagName("body")[0].appendChild(tf);
		tf.reset();
		_span.parentNode.insertBefore(_file,_span);
		_span.parentNode.removeChild(_span);
		_span = null;
		tf.parentNode.removeChild(tf);
	}
}

// 计算两个日期的间隔天数
function DateDiff(sDate1, sDate2){ // sDate1和sDate2是2002-12-18格式
	var aDate, oDate1, oDate2, iDays   
	aDate = sDate1.split("-")   
	oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]) // 转换为12-18-2002格式
	aDate = sDate2.split("-")   
	oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0])   
	iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 /24) // 把相差的毫秒数转换为天数
	return iDays;
}

/**
 * 用layer打开窗口
 * @param url 窗口地址
 * @param args 窗口名称
 * @param width 窗口宽度
 * @param height 窗口高度
 * @param func 回调函数名称（字符串）
 * @param needRtn 需要子页面传返回值
 */
function openLayerModal(url, args, width, height, func, needRtn) {
	//增加传入参数
	var callBackParams = '';
	if (arguments.length > 6) {
		for(var i=6;i<arguments.length;i++) {
			callBackParams += ',"'+arguments[i]+'"'
		}
	}
	if (callBackParams.length > 0) {
		callBackParams = callBackParams.substring(1, callBackParams.length);
	}
	return openLayerBase(this, url, args, width, height, func, needRtn, callBackParams);
}

/**
 * 打开layer窗口，底层方法
 * @param openWin 打开layer弹窗的窗口对象
 * common : 当前页打开 this
 * parent : 父页面打开 parent
 * grand : 祖父页面打开 parent.parent
 * top : 本域最外层页面打开 getTopWindow()
 *
 * 理论上用top就可以了，但是执行的页面会被嵌入在什么页面里还说不准，以及对应页面的jquery版本+layer.js的引入无法保证，还是存在风险
 * @param url 弹窗地址
 * @param args 弹窗名称
 * @param width 宽
 * @param height 高
 * @param func 回调函数
 * @param needRtn 是否需要返回值
 * @param callBackParams 回调参数
 */
function openLayerBase(openWin, url, args, width, height, func, needRtn, callBackParams) {
	if (func == undefined) {
		func = "";
	}
	if (needRtn == undefined) {
		needRtn = true;
	}
	if (url.indexOf("?") > -1) {
		url += "&";
	} else {
		url += "?";
	}
	url += "openWay=layer";
	//问题1：shadeClose点击左侧shade无法关闭
	//问题2：IE下如果title为空时，不显示关闭按钮
	//故，折中方法，去除shadeClose，如果传入title为空时，赋予一个定值，暂定为“执行案件流程信息管理系统”
	if (!trim(args)) {
		args = '在线训练系统';
	}

	if (!openWin) {
		openWin = this;
	}
	//width有个最小宽度
	if (width < 270){
		width = 270;
	}
	//调整width,height
	if ('number' === typeof width) {
		width = width + 'px';
	}
	if ('number' === typeof height) {
		height = height + 'px';
	}

	//打开layer前清除之前的返回值
	openWin.layerRtn = '';
	var index = openWin.layer.open({
		type: 2
		,title: args
		,area: [width, height]
		,shade: 0.6
		,maxmin: false
		,offset:'auto'
		,content: url
		,end: function () {
			openWin.layerRtn = handleLayerRtn(openWin.layerRtn);
			if (openWin.layerRtn && func) {
				var funcStr = func + "(\'" + openWin.layerRtn + "\'";
				if (callBackParams) {
					funcStr += ',' + callBackParams;
				}
				funcStr += ')';
				eval(funcStr);
			} else if (func) {
				var funcStr = func + '(';
				//如果需要返回值，第一个值一定要是返回值
				if (needRtn) {
					funcStr += '"'+openWin.layerRtn+'"';
				}
				if (callBackParams) {
					funcStr += ',' + callBackParams;
				}
				funcStr += ')';
				eval(funcStr);
			}
		}
	});
	// 修正 layer iframe 里面 出现滚动条 遮罩高度
	var scrollHeight = document.body.scrollHeight;
	jQuery("#layui-layer-shade"+index).height(scrollHeight);
	var $layuiLayer = jQuery("#layui-layer"+index);
	var layuiStyleVal = $layuiLayer.attr("style");
	var offsetHeigth = scrollHeight/2-(height/2);
	$layuiLayer.attr("style",layuiStyleVal+";top:"+offsetHeigth+"px");
	return index;
}

//layer父子页面传值
var layerRtn;

/**
 * layer打开的子页面关闭时赋值
 * @param res
 * @param needClose 是否自动关闭
 * @param forcelayer 强制layer关闭
 */
function layerReturn(res, needClose, forcelayer) {
	if (forcelayer == undefined) {
		forcelayer = false;
	}
	var openWay = getQueryString('openWay');
	if ('layer' == openWay || forcelayer) {
		//赋返回值
		if (parent) {
			parent.layerRtn = res;
		} else {
			//如果从本域最上层页面打开，就不存在parent了
			this.layerRtn = res;
		}
	} else {
		window.returnValue = res;
	}
	if (needClose == undefined) {
		needClose = true;
	}
	if (needClose) {
		layerClose(forcelayer);
	}
}

/**
 * layer关闭
 * @param forcelayer 强制使用layer关闭
 */
function layerClose(forcelayer) {
	if (forcelayer == undefined) {
		forcelayer = false;
	}
	var openWay = getQueryString('openWay');
	if ('layer' == openWay || forcelayer) {
		//先得到当前iframe层的索引
		if (parent.layer) {
			var index = parent.layer.getFrameIndex(window.name);
			//再执行关闭
			parent.layer.close(index);
		}
	} else {
		//存在工作流打开的页面，window.close不能直接关闭
		closeWebPage();
	}
}

function getQueryString(name) {
	var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
	var r = window.location.search.substr(1).match(reg);
	if (r != null) {
		return unescape(r[2]);
	}
	return null;
}

//处理layer返回值中的\n\r，会影响eval函数执行
function handleLayerRtn(rtn) {
	if (!rtn){
		return '';
	}
	if (typeof rtn == "string") {
		var handledRtn = rtn.replace(/\n/g, "\\n").replace(/\r/g, "\\r").replace(/'/g, '"');
		return handledRtn;
	} else {
		return rtn;
	}
}