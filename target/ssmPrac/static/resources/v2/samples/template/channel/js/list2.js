var pageSize = 10;
var pageNum = 1;
var mygrid;
$(function(){
	//标签切换
	$('#tab li').click(function () {
		$(this).addClass('active').siblings().removeClass('active');
	});
	$('#tab li').eq(0).click();
	
	mygrid = new dhtmlXGridObject('grid');
	mygrid.setImagePath('../../../../static/plugg/dhtmlx/dhtmlxGrid/codebase/imgs/');
	mygrid.setHeader('序号,案号,案件名称,承办部门,承办人,地址,操作');
	mygrid.setInitWidths('100,200,*,200,200,*,300');
    mygrid.setColumnMinWidth(200, 2);
    mygrid.setColumnMinWidth(200, 5);
	mygrid.setColTypes('ro,ro,ro,ro,ro,address,btns');
	mygrid.setColAlign('center,center,left,center,center,left,left');
	mygrid.enableTooltips('true,true,true,true,true,false,false');
	mygrid.setSkin('tdh_default');
	mygrid.init();
	getList();
});

//设置标签数值
function setTabCount(id, num) {
    var numbox = $('#tab li[data-id="'+ id +'"]').find('.tag_num');
    numbox.text(num).show();
    if(!num || num == '0') numbox.hide();
}

//加载列表
function getList() {
    mygrid.clearAll();
    mygrid.loadXML('grid2.xml',function(){
    	var total = mygrid.getUserData('','totalnumber');
    	pageEvent('page',total,pageSize,pageNum,function(cur){
			pageNum = cur;
			getList();
		});
    });
}

//图片
function eXcell_img(cell){
    if (cell){
        this.cell = cell;
        this.grid = this.cell.parentNode.grid;
    }
    this.setValue = function(val){
        var html = '';
        if(val == '2'){
            html = '<img data-jsbz="'+ val +'" title="未读" src="../images/list/cuib2.png">';
        }else{
            html = '<img data-jsbz="'+ val +'" title="已读" src="../images/list/cuib1.png">';
        }
        this.setCValue(html);
    }
}
eXcell_img.prototype = new eXcell;

//地址
function eXcell_address(cell){
    if (cell){
        this.cell = cell;
        this.grid = this.cell.parentNode.grid;
    }
    this.setValue = function(val){
		var rows = 3; //显示3行地址
		var ary = val?val.split('\\n'):[];
        var html = '<div class="list_tempaddress" data-droppop="address" data-callback="initAddress(\'address\',\''+ val +'\')">';
		for(var i = 0; i < rows; i++){
			if(ary[i]) html += '<p>'+ ary[i] +'</p>';
		}
		html += '</div>'
		var popObj = $('.droppop[data-dropdom="address"]');
		if(!popObj.length) $('body').append('<div data-dropdom="address" class="droppop"><ul class="droppop_ol"></ul></div>');
        this.setCValue(html);
    }
}
eXcell_address.prototype = new eXcell;

//记载更多地址
function initAddress(name,data){
	var popObj = $('[data-dropdom="'+ name +'"]');
	if(data){
		var ary = data.split('\n');
		var liHtml = '';
		for(var i = 0; i < ary.length; i++){
			liHtml += '<li>'+ ary[i] +'</li>';
		}
		popObj.css('width','auto').find('ul').html(liHtml);
	}
}

//按钮组
function eXcell_btns(cell){
    if (cell){ 
		$(cell).css('white-space','normal')
        this.cell = cell;
        this.grid = this.cell.parentNode.grid;
    }
    this.setValue = function(val){
		var cols = 6; //一行6个按钮
		var rows = 2; //显示2行按钮
		var ary = val.split('|');
		var length = ary.length;
		var html = '<div class="list_tempbtn">';
		var cur = 0;
		var moreAry = [];
		for(var i = 0; i < length; i++){
			if(cur%cols == 0){ html += '<p>'; }
			html += (cur%cols==0?'':'<i class="list_link_border"></i>')+'<a class="list_link'+ (ary[i].length>2?' list_link_max':'') +'">'+ ary[i] +'</a>';
			cur = cur + (ary[i].length>2?2:1);
			if(cur%cols == 0){ html += '</p>'; }
			if(cur + 1 > cols * rows){
				if(i + 1 < length) moreAry = ary.slice(i+1,ary.length);
				break;
			}
		}
		if(moreAry.length){
			html += '<a class="list_link more" data-droppop="more" data-callback="initMore(\'more\',\''+ moreAry.toString() +'\')" onclick="showDroppop(this)"></a>';
			var popObj = $('.droppop[data-dropdom="more"]');
			if(!popObj.length) $('body').append('<div data-dropdom="more" class="droppop"><ul class="droppop_list"></ul></div>');
		}
		html += '</div>';
        this.setCValue(html);
    }
}
eXcell_btns.prototype = new eXcell;

//加载更多按钮
function initMore(name,data){
	var popObj = $('[data-dropdom="'+ name +'"]');
	if(data){
		var ary = data.split(',');
		var liHtml = '';
		for(var i = 0; i < ary.length; i++){
			liHtml += '<li>'+ ary[i] +'</li>';
		}
		popObj.find('ul').html(liHtml);
	}
}