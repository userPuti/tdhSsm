
/**
 * 0次编码
 * @param formSelector
 * @param prefix
 * @returns
 */
function serializeNo(formSelector, prefix) {
    if (!prefix)
        prefix = "";
    return reParamsSentenceNew(searchForPms(formSelector, prefix), false);
}

/**
 * 1次编码
 * @param formSelector
 * @param prefix
 * @returns
 */
function serializeOne(formSelector, prefix) {
    if (!prefix)
        prefix = "";
    return reParamsSentenceNew(searchForPms(formSelector, prefix), true, 1);
}

/**
 * 2次编码
 * @param formSelector
 * @param prefix
 * @returns
 */
function serializeTwo(formSelector, prefix){
    if (!prefix)
        prefix = "";

    return reParamsSentenceNew(searchForPms(formSelector, prefix), true, 2);
}

/**
*将一个表单对象序列化。
*改进：由于JQuery 自带的方法，会将无值的数据部序列化到提交数据内，无法使用自动反射的方式处理数据.
*提交方式需设置 POST,不建议使用GET方式处理.
*@author 施健伟  2012-5-17
*@param  formSelector  表单选择器
*@param  prefix        表单前缀  对于多数据组的情况，方便后台处理Request
*@param  decode        是否编码 --默认编码一次
*/
function serialize(formSelector,prefix,decode){
    if(decode == undefined) decode = false;
    if(prefix == undefined) prefix ="";
    if(decode){
        return reParamsSentence(searchForPms(formSelector,prefix));
    }else{
        return reParamsSentence(searchForPms(formSelector,prefix),null);
    }
}


/**
*通过AJAX方式加载一个指定表的数据，并且动态的绑定到对应的表单对象内.
*@param formSelector  指定某个表单对象
*@param url  数据加载地址
*@callback   回调函数
*返回的JSON 数据结构（兼容EXTJS):
 <BR>
 {
  "success":true,
  "data":{
  	  "clientName": "Fred. Olsen Lines",
      "portOfLoading": "FXT",
      "portOfDischarge": "OSL"
  }
 }
*/
function formLoadData(formSelector,url,callback){
   //cache
   if(url.indexOf('?')>-1){
   	  url = url +'&ect='+new Date().getTime();
   }else{
   	  url = url +'?ect='+new Date().getTime();
   }
   var form = $(formSelector);
   $.getJSON(url,function(json){
      if(json.success){
	   	  $.each(json.data,function(name,value){
	   	  	 form.find("[name='"+name+"']").val($.trim(value));
	   	  });
	   	  //回调函数
	   	  if(callback!=undefined){
	   	      //默认数据传递在后续回调函数内
	   	      //一般在联动的下拉框对象时才定义
	   	      callback(json);
	   	  }
	   }else{
	   	  alert("加载数据失败!");
	   }
   });
}

/**
*动态将JSON对象数据绑定到对应的#form对象
*@param  formSelector  
*@param  jsondata
*@param  prefix
*@param  callback   
*/
function formSetData(formSelector,jsondata,prefix,callback){
     var form = $(formSelector);
     if(prefix == undefined) prefix = "";
	 $.each(jsondata,function(name,value){
	         if(prefix==''){
	   	  	 	form.find("[name='"+name+"']").val(value);
	   	  	 }else{
	   	  	 	form.find("[name='"+prefix+name+"']").val(value);
	   	  	 }
	 });
	 //回调函数
	 if(callback!=undefined){
   	      //默认数据传递在后续回调函数内
   	      //一般在联动的下拉框对象时才定义
	   	  callback(jsondata);
	 }
}

/**
 * @author Rongym
 * @Date Oct 27, 2011
 */
/**
 * pms为Object类型，返回页面请求传递串，参数值都不加引号，形如name=who&age=13
 *
 * 当twice为true时，对value值编码两次
 * 当twice为false或undefined时，对value值编码一次
 * 当twice为null时，对value值不进行编码
 *
 * @param pms
 * @param twice
 */
function reParamsSentence(pms, twice) {
    if (twice == undefined) {
        twice = false;
    }
    var params = new StringBuilder();
    for (var key in pms) {
        params.append("&" + key + "=");
        if (twice != null) {
            if (twice) {
                params.append(encodeURIComponent(encodeURIComponent(pms[key])));
            } else {
                params.append(encodeURIComponent(pms[key]));
            }
        } else {
            params.append(pms[key]);
        }
    }
    if (params.length > 0) {
        return params.toString().substring(1);
    } else {
        return "";
    }
}

/**
 * pms为Object类型，返回页面请求传递串，参数值都不加引号，形如name=who&age=13
 *
 * 当encode为true时，编码
 * 当encodeCnt为编码次数
 *
 * @param pms
 * @param encode
 * @param encodeCnt
 */
function reParamsSentenceNew(pms, encode, encodeCnt) {
    var params = new StringBuilder();
    for (var key in pms) {
        params.append("&" + key + "=");
        if (encode) {
            // 如果转码，默认2次编码
            if (!encodeCnt) {
                encodeCnt == 2;
            }

            if (encodeCnt == 2) {
                params.append(encodeURIComponent(encodeURIComponent(pms[key])));
            } else {
                params.append(encodeURIComponent(pms[key]));
            }
        } else {
            params.append(pms[key]);
        }
    }
    if (params.length > 0) {
        return params.toString().substring(1);
    } else {
        return "";
    }
}

/**
 *
 * selector1,selector2,selectorN将每一个选择器匹配到的元素合并后一起返回。你可以指定任意多个选择器，并将匹配到的元素合并到一个结果内。
 * 例：$("div,span,p.myClass")
 *
 * val() 获得第一个匹配元素的当前值，在 jQuery 1.2 中,可以返回任意元素的值了。包括select。如果多选，将返回一个数组，其包含所选的值
 *
 * 查找标签内的input(text、hidden、password、radio、checkbox)、textarea、select等 返回其值的object
 *
 *
 * :input 匹配所有 input, textarea, select 和 button(指的是<button>Button</button>) 元素
 *
 * :checked 匹配所有选中的被选中元素(复选框、单选框等，不包括select中的option)
 *
 * :selected 匹配所有选中的option元素
 *
 * 需要获取其值的表单 :text :password :hidden 需要特殊处理其值的表达 :radio :checkbox
 * 一般不需要获取其值的表单:submit :image :reset :button :file
 *
 * 需要获取其值的其它类别表单 textarea, select
 *
 * @param {}
    *            id 块标签的id 带#，形如"#formid"
 */
function searchForPms(selector,prefix) {
    var pms = new Object();
    if (prefix == undefined) {
        prefix = "";
    }
    var obj = $(selector);
    // 查找块标签内的 input（不包括radio、checkbox等）, textarea, select
    obj.find(":input:not(button,:submit,:image,:reset,:button,:file,:radio,:checkbox)")
        .each(function () {
            if ($.trim($(this).attr("name")) != '' ) {
                if(pms[prefix+ $(this).attr("name")]){ 
                  if(pms[prefix+ $(this).attr("name")]!=''){	
                    //如果name 重复 且不为空，则不覆盖.
                  }
                }else{
                	pms[prefix+ $(this).attr("name")] = $.trim($(this).val());
                }
            }
            
        });
    // 查找块标签内的 checked
    obj.find(":checkbox")
        .each(function () {
            if ($.trim($(this).attr("name")) != '') {
                if($(this).attr('checked')){
                	pms[prefix+$(this).attr("name")] = $.trim($(this).val());
                }else{
                	pms[prefix+$(this).attr("name")] = '';
                }
            }
        });
    // 查找块标签内的 radio
    obj.find(":radio:checked")
        .each(function () {
            if ($.trim($(this).attr("name")) != '') {
                pms[prefix+$(this).attr("name")] = $.trim($(this).val());
            }
        });
    return pms;
}


/**
 * $(":radio[name=sfjy][value=2]").attr("checked", true);
 * 实现功能：将名称为是否禁用，value值为2的radio选中
 */
/**
 * $("#select").val();    选中下拉框的value
 * $("#select").find("option:selected").text();   选中下拉框的text
 * $("#select option:selected").text();   选中下拉框的text
 */
/**
 * $(":input[name=id],[name=password],[name=name]").val('');
 * 将表单name为id、password、name的值清空
 */

/**
 * jQueryjQuery(expression, [context])
 *    expression String用来查找的字符串
 *    context (可选)Element, jQuery作为待查找的 DOM 元素集、文档或 jQuery 对象。
 *    jQuery 的核心功能都是通过这个函数实现的。 jQuery中的一切都基于这个函数，或者说都是在以某种方式使用这个函数。
 * 这个函数最基本的用法就是向它传递一个表达式（通常由 CSS 选择器组成），然后根据这个表达式来查找所有匹配的元素。
 *
 * 清空表单所有元素的值(不包括hidden、submit、button、reset、radio、checkbox)
 * 将radio、checkbox的选中去掉
 * @param obj 表单对象
 */
function clearForm(context) {
    $(':input', context)
        .not(':hidden,button,:button,:submit,:reset,:radio,:checkbox')
        .val('');
    $(':input:radio,:checkbox', context)
        .removeAttr('checked')
        .removeAttr('selected');
}


/**
*
*  COMM  StringBuilder
*/

// 类似java的StringBuilder，有属性方法length,append,appendFormat,size,toString,valueOf
function StringBuilder() {
    // (C) Andrea Giammarchi - Mit Style License
    // http://webreflection.blogspot.com/2008/06/lazy-developers-stack-concept-and.html
    if (arguments.length) {
        this.append.apply(this, arguments);
    }
}
StringBuilder.prototype = function () {
    var join = Array.prototype.join, slice = Array.prototype.slice, RegExp = /\{(\d+)\}/g, toString = function () {
        return join.call(this, "");
    };
    return {
        constructor:StringBuilder,
        length:0,
        append:Array.prototype.push,
        appendFormat:function (String) {
            var i = 0, args = slice.call(arguments, 1);
            this.append(RegExp.test(String) ? String.replace(RegExp, function (String, i) {
                return args[i];
            }) : String.replace(/\?/g, function () {
                return args[i++];
            }));
            return this;
        },
        size:function () {
            return this.toString().length;
        },
        toString:toString,
        valueOf:toString
    };
}();

