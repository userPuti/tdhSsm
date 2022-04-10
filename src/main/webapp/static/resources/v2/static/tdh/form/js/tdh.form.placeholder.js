/**
 * tdh.form.js 扩展
 * 支持IE9以下的 placeholder 属性 
 * 支持IE浏览器，其他浏览器未做支持
 * 目前只支持.inputText、.inputArea元素(text,textarea) 
 * 
 * ie5不支持动态修改placeholder属性值，可通过调用placeholderInit('dom元素')方法重新加载
 * 示例：$('#srk').attr('placeholder','测试')
 * 		placeholderInit('#srk');
 */
jQuery(function() {
	placeholderInit(".inputText[placeholder],.inputArea[placeholder]");
});

function placeholderInit(obj) {
	if(!obj.length || 'placeholder' in document.createElement('input')) { //浏览器支持placeholder属性 不做处理
		return;
	}
	var inputBoxs = ifdom(obj);
	inputBoxs.each(function() {
		var othor = this,
			$this = jQuery(othor),
			$span = $this.next('span.placeholder');
		if($span.length) {
			$span.text($this.attr("placeholder"));
			return true;
		}
		var padding = $this.css("padding-left") || 0,
			width = $this[0].currentStyle.width || $this.outerWidth() + 'px',
			height = $this[0].currentStyle.height || $this.outerHeight() + 'px',
			lineHeight = $this.css('line-height');
		if($this.hasClass('inputText')){
			lineHeight = height;
		}
		$this.wrap("<div style='position:relative;display:inline-block;' class='placeholder-box'></div>");
		var $pdiv = $this.parent().css({
			'width': width,
			'height': height
		});
		$span = jQuery('<span class="placeholder" style="position:absolute;display: inline-block;width:100%;height:' + height + ';padding-left:'+ padding +';box-sizing:border-box;white-space: nowrap;overflow: hidden;line-height:' + lineHeight + '; z-index: 10;color: #acacac;left:0;top:0;">' + $this.attr("placeholder") + '</span>');
		$pdiv.append($span);
		if($this.attr('disabled') == 'disabled') {
			$span.css('cursor', 'default');
		}
		if($this.val() != "") {
			$span.hide();
		} else {
			$span.show();
		}
		if($this.css('display') == 'none' || $this.css('visibility') == 'hidden'){
			$pdiv.hide();
		}else{
			$pdiv.show();
		}

		//$this.addEventListener ('DOMAttrModified', OnAttrModified, false);   //TODO 兼容其他非ie浏览器可在此修改
		othor.attachEvent('onpropertychange', function(event) { // ie
			if(event.propertyName === "value") {
				//if(!event.srcElement.attributes[event.propertyName].value) {
				if(!event.srcElement.value) {
					jQuery(event.srcElement).parent().find("span.placeholder").show();
				} else {
					jQuery(event.srcElement).parent().find("span.placeholder").hide();
				}
			} else if(event.propertyName === "placeholder") {
				jQuery(event.srcElement).parent().find("span.placeholder").text(event.srcElement.attributes[event.propertyName].value);
			}else if(event.propertyName === "style.display") {
				if(event.srcElement.style.display === "none"){
					jQuery(event.srcElement).parent().hide();
				}else{
					jQuery(event.srcElement).parent().show();
				}
			}else if(event.propertyName === "style.visibility") {
				if(event.srcElement.style.visibility === "hidden"){
					jQuery(event.srcElement).parent().hide();
				}else{
					jQuery(event.srcElement).parent().show();
				}
			}
		});
	}).on("focus", function() {
		jQuery(this).next("span.placeholder").hide();
	}).on("blur", function() {
		var $this = jQuery(this);
		if($this.val() != "") {
			$this.next("span.placeholder").hide();
		} else {
			$this.next("span.placeholder").show();
		}
	});

	// 点击表示placeholder的标签相当于触发input
	$("span.placeholder").on("click", function() {
		if(jQuery(this).prev().attr('disabled') == 'disabled') return false;
		jQuery(this).hide();
		jQuery(this).siblings("[placeholder]").trigger("click");
		jQuery(this).siblings("[placeholder]").trigger("focus");
	});
}