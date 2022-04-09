var capActiveId,current=1;
$(function(){
	var navData = [{
	    "id": "CYGN",
	    "mc": "常用功能",
	    "text": "常用功能",
	    "leaf": true,
	    "lx": "label",
	    "pxh": 0,
	    "yydm": "HOME",
	    "dkfs": null,
	    "cygn": null,
	    "url": null,
	    "open": true,
	    "close": true,
	    "children": [
	      {
	        "id": "SPFK_SPFK01",
	        "mc": "我的案件",
	        "text": "我的案件",
	        "leaf": true,
	        "lx": "node",
	        "pxh": 0,
	        "yydm": "SPFK",
	        "dkfs": "",
	        "cygn": null,
	        "url": "http://192.168.1.31:9090/spfk/webapp/court/ajgl/ourAj2.jsp?pageLx=bt&portalkey=3201000001&RouteID=320100",
	        "close": true,
	        "pid": null
	      }
	    ],
	    "pid": null
	  },
	  {
	    "id": "UIM",
	    "mc": "统一用户管理",
	    "text": "统一用户管理",
	    "leaf": false,
	    "lx": "label",
	    "pxh": 1,
	    "yydm": "UIM",
	    "dkfs": null,
	    "cygn": null,
	    "url": null,
	    "close": true,
	    "children": [
	      {
	        "id": "UIM_UIM96",
	        "mc": "统一用户管理",
	        "text": "统一用户管理",
	        "leaf": false,
	        "lx": "node",
	        "pxh": 0,
	        "yydm": null,
	        "dkfs": "",
	        "cygn": null,
	        "url": "",
	        "close": true,
	        "children": [
	          {
	            "id": "UIM_UIM9610",
	            "mc": "基础定义",
	            "text": "基础定义",
	            "leaf": false,
	            "lx": "node",
	            "pxh": 0,
	            "yydm": null,
	            "dkfs": "",
	            "cygn": null,
	            "url": "",
	            "close": true,
	            "children": [
	              {
	                "id": "UIM_UIM961010",
	                "mc": "机构管理",
	                "text": "机构管理",
	                "leaf": true,
	                "lx": "node",
	                "pxh": 0,
	                "yydm": null,
	                "dkfs": "",
	                "cygn": null,
	                "url": "http://192.168.0.64:8090/uim/frame/xtgl/bmgl/bmgl_main.jsp?portalkey=3201000001&RouteID=320100",
	                "close": true,
	                "children": [],
	                "pid": null
	              },
	              {
	                "id": "UIM_UIM961020",
	                "mc": "用户管理",
	                "text": "用户管理",
	                "leaf": true,
	                "lx": "node",
	                "pxh": 0,
	                "yydm": null,
	                "dkfs": "",
	                "cygn": null,
	                "url": "http://192.168.0.64:8090/uim/frame/xtgl/yhgl/yhgl_main.jsp?portalkey=3201000001&RouteID=320100",
	                "close": true,
	                "children": [],
	                "pid": null
	              },
	              {
	                "id": "UIM_UIM961030",
	                "mc": "角色管理",
	                "text": "角色管理",
	                "leaf": true,
	                "lx": "node",
	                "pxh": 0,
	                "yydm": null,
	                "dkfs": "",
	                "cygn": null,
	                "url": "http://192.168.0.64:8090/uim/frame/xtgl/jsgl/jsgl_main.jsp?portalkey=3201000001&RouteID=320100",
	                "close": true,
	                "children": [],
	                "pid": null
	              }
	            ],
	            "pid": null
	          },
	          {
	            "id": "UIM_UIM9620",
	            "mc": "授权管理",
	            "text": "授权管理",
	            "leaf": false,
	            "lx": "node",
	            "pxh": 0,
	            "yydm": null,
	            "dkfs": "",
	            "cygn": null,
	            "url": "",
	            "close": true,
	            "children": [
	              {
	                "id": "UIM_UIM962010",
	                "mc": "功能分配",
	                "text": "功能分配",
	                "leaf": true,
	                "lx": "node",
	                "pxh": 0,
	                "yydm": null,
	                "dkfs": "",
	                "cygn": null,
	                "url": "http://192.168.0.64:8090/uim/frame/xtgl/sqgl/gnfpgl.jsp?portalkey=3201000001&RouteID=320100",
	                "close": true,
	                "children": [],
	                "pid": null
	              },
	              {
	                "id": "UIM_UIM962020",
	                "mc": "用户授权",
	                "text": "用户授权",
	                "leaf": true,
	                "lx": "node",
	                "pxh": 0,
	                "yydm": null,
	                "dkfs": "",
	                "cygn": null,
	                "url": "http://192.168.0.64:8090/uim/frame/xtgl/sqgl/yhsqgl.jsp?portalkey=3201000001&RouteID=320100",
	                "close": true,
	                "children": [],
	                "pid": null
	              },
	              {
	                "id": "UIM_UIM962030",
	                "mc": "角色授权",
	                "text": "角色授权",
	                "leaf": true,
	                "lx": "node",
	                "pxh": 0,
	                "yydm": null,
	                "dkfs": "",
	                "cygn": null,
	                "url": "http://192.168.0.64:8090/uim/frame/xtgl/sqgl/jssqgl.jsp?portalkey=3201000001&RouteID=320100",
	                "close": true,
	                "children": [],
	                "pid": null
	              }
	            ],
	            "pid": null
	          },
	          {
	            "id": "UIM_UIM9630",
	            "mc": "运行管理",
	            "text": "运行管理",
	            "leaf": false,
	            "lx": "node",
	            "pxh": 0,
	            "yydm": null,
	            "dkfs": "",
	            "cygn": null,
	            "url": "",
	            "close": true,
	            "children": [
	              {
	                "id": "UIM_UIM963010",
	                "mc": "日志查询",
	                "text": "日志查询",
	                "leaf": true,
	                "lx": "node",
	                "pxh": 0,
	                "yydm": null,
	                "dkfs": "",
	                "cygn": null,
	                "url": "http://192.168.0.64:8090/uim/frame/xtgl/yxgl/czrzcx_main.jsp?portalkey=3201000001&RouteID=320100",
	                "close": true,
	                "children": [],
	                "pid": null
	              },
	              {
	                "id": "UIM_UIM963030",
	                "mc": "数据分发",
	                "text": "数据分发",
	                "leaf": true,
	                "lx": "node",
	                "pxh": 0,
	                "yydm": null,
	                "dkfs": "",
	                "cygn": null,
	                "url": "http://192.168.0.64:8090/uim/frame/task/sjff.jsp?portalkey=3201000001&RouteID=320100",
	                "close": true,
	                "children": [],
	                "pid": null
	              },
	              {
	                "id": "UIM_UIM963020",
	                "mc": "缓存管理",
	                "text": "缓存管理",
	                "leaf": true,
	                "lx": "node",
	                "pxh": 7,
	                "yydm": null,
	                "dkfs": "",
	                "cygn": null,
	                "url": "http://192.168.0.64:8090/uim/frame/xtgl/cache/cache_manager.jsp?portalkey=3201000001&RouteID=320100",
	                "close": true,
	                "children": [],
	                "pid": null
	              }
	            ],
	            "pid": null
	          }
	        ],
	        "pid": null
	      }
	    ],
	    "pid": null
	  }
	]
	//导航加载
	var myHomeNav=new homeNav('homenav');
	myHomeNav.init(navData);
	myHomeNav.attachEvent('onClick', function(id,nodes) { //导航点击事件
		addCapnav(id,nodes);
	});
	myHomeNav.attachEvent('ontoolClick', function(name) { //常用设置点击事件
		alert('常用设置');
	});
	//添加默认标签-工作桌面
	var nodeData = new Object();
	nodeData.url='homepage.html';
	nodeData.mc = '首页';
	nodeData.close = false;
	addCapnav('home',nodeData);
	capActiveId='home';
	//标签切换
	$('#capnav').find('a').live('click',function(){
		var indexId=$(this).attr('data-id');
		$('#capnav').find('a[data-id="'+capActiveId+'"]').removeClass('capnav_now');
		$('#capNav_cont').find('div[data-for="'+capActiveId+'"]').hide();
		$(this).addClass('capnav_now');
		$('#capNav_cont').find('div[data-for="'+indexId+'"]').show();
		capActiveId=indexId;
	}).live('mouseover',function(){
		$(this).addClass('capnav_hover')
	}).live('mouseout',function(){
		$(this).removeClass('capnav_hover')
	});
	$('#capnav').find('.capnav_closed').live('mouseover',function(){
		$(this).addClass('capnav_closed_hover');
	}).live('mouseout',function(){
		$(this).removeClass('capnav_closed_hover');
	});
	//标签移动
	$('#capnav').siblings('.cap_prev').click(function(){ scrollCapnav($('#capnav').children(),'prev'); });
	$('#capnav').siblings('.cap_next').click(function(){ scrollCapnav($('#capnav').children(),'next'); });
});
//左侧导航显示隐藏
function hideSideBar(obj){
	var slideBox=$('.sideBar'),
		slideOtherBox=slideBox.siblings('.main');
	slideBox.find('.homenav_scroll').getNiceScroll().hide();
	slideBox.animate({ 
		marginLeft:parseInt(slideBox.css('marginLeft'))==0 ? -slideBox.outerWidth() : 0 
	},function(){
		if(!parseInt(slideBox.css('marginLeft'))==0){
			$(obj).attr('title','显示导航');
		}else{
			$(obj).attr('title','隐藏导航');
			slideBox.find('.homenav_scroll').getNiceScroll().show();
		}
	});
	slideOtherBox.animate({
		marginLeft:parseInt(slideOtherBox.css('marginLeft'))==0 ? slideBox.outerWidth() : 0
	});
}
//添加标签
function addCapnav(objid,objdata){
	if(objdata.url==undefined || $.trim(objdata.url) == '') return;
	var cyggid=$('#homenav').find('.homenav_item').eq(0).attr('id');
	itemCurrentId=objid.replace(cyggid+'_','');
	var	capnav=$('#capnav').children('ul'),
		findNode=capnav.find('a[data-id="'+itemCurrentId+'"]'),
		capnav_cont=$('#capNav_cont');
	capnav.find('a[data-id="'+capActiveId+'"]').removeClass('capnav_now');
	capnav_cont.find('div[data-for="'+capActiveId+'"]').hide();
	capActiveId=itemCurrentId;
	if(findNode.length){//已有该标签
		findNode.addClass('capnav_now');
		capnav_cont.find('div[data-for="'+capActiveId+'"]').show();
	}else{//没有该标签
		var capnavLink='<li><a class="capnav_now" data-id="'+itemCurrentId+'" data-url="'+objdata.url+'" title="'+objdata.mc+'">'+objdata.mc;
		if(objdata.close==true){
			capnavLink += '<span class="capnav_closed" onclick="delCapnav($(this).parent().attr(\'data-id\'))"></span>';
		}
		capnavLink += '</a></li>'
		capnav.append(capnavLink);
		if(objid=='home'){
			capnav.find('a[data-id="home"]').addClass('capnav_spec').prepend('<i class="icon icon_home"></i>');
		}
		capnav_cont.append('<div style="width:100%;height:100%;" data-for="'+itemCurrentId+'"><iframe style="width: 100%; height: 100%; background: none;" frameborder="0" scrolling="no" src="'+objdata.url+'"></iframe></div>');
		//标签样式
		showScrollBtn(capnav,'next');
	}
}
//标签移动按钮显示隐藏
function showScrollBtn(objul,type){
	var	maxWidth=objul.parent().width(),
		childWidth=objul.children().eq(0).outerWidth();
		childLength=objul.children().length,
		capnavWidth=childWidth*childLength;
	var prevbtn=objul.parent().siblings('.cap_prev'),
		nextbtn=objul.parent().siblings('.cap_next');
	objul.width(capnavWidth);
	if(capnavWidth>maxWidth){
		prevbtn.show();nextbtn.show();
		if(type){
			scrollCapnav(objul,type);
		}
	}else{
		prevbtn.hide();nextbtn.hide();
		objul.css('left','0');
	}
}
//标签移动
function scrollCapnav(objul,type){
	var	maxWidth=objul.parent().width(),
		liWidth=objul.children().eq(0).outerWidth();
		liLength=objul.children().length,
		ulWidth=objul.width();
	var odd=(ulWidth-maxWidth)/liWidth+1;
	if(type=='prev'){
		current=current-1;
		if(current<1) { current=1; }
	}else{
		current=current+1;
		if(current>odd){ current=odd; }
	}
	if(current<1 || current>odd) return false;
	objul.animate({left:-liWidth*(current-1)});
}
//删除当前标签
function delCapnav(objid){
	var obj=$('#capnav').find('a[data-id="'+objid+'"]'),
		wrap=obj.parent(),
		prevWrap=wrap.prev(),
		nextWrap=wrap.next();
	if(capActiveId==objid){
		if(nextWrap.length){
			nextWrap.children().addClass('capnav_now');
			capActiveId=nextWrap.children().attr('data-id');
		}else if(prevWrap.length){
			prevWrap.children().addClass('capnav_now');
			capActiveId=prevWrap.children().attr('data-id');
		}else{
			capActiveId='';
		}
	}
	wrap.remove();
	//$('#capNav_cont').find('div[data-for="'+objid+'"]').remove();
	destroyIframe($('#capNav_cont').find('div[data-for="'+objid+'"]'));
	$('#capNav_cont').find('div[data-for="'+capActiveId+'"]').show();
	//标签样式
	showScrollBtn($('#capnav').children(),'prev');
}
//关闭操作显示隐藏
function showToollist(){
	var toollist=$('.cap_toollist');
	toollist.css({
		display:toollist.css('display')=='none' ? 'block' : 'none'
	});
	toollist.find('li').hover(function(){
		$(this).addClass('cap_hover');
	},function(){
		$(this).removeClass('cap_hover');
	}).click(function(){
		toollist.css('display','none');
	});
}
//关闭操作
function delTool(type){
	var capnav=$('#capnav').children(),capnav_cont=$('#capNav_cont');
	switch(type){
		case 'all':
			capnav.find('a[data-id]').not('a[data-id="home"]').parent().remove();
			//capnav_cont.find('div[data-for]').not('div[data-for="home"]').remove();
			destroyIframe(capnav_cont.find('div[data-for]').not('div[data-for="home"]'));
			capActiveId='home';
			break;
		case 'cur':
			if(capActiveId!='home'){ 
				var curWrap=capnav.find('a[data-id="'+capActiveId+'"]').parent();
				//capnav_cont.find('div[data-for="'+capActiveId+'"]').remove();
				destroyIframe(capnav_cont.find('div[data-for="'+capActiveId+'"]'));
				if(curWrap.next().length){
					capActiveId=curWrap.next().children().attr('data-id');
				}else if(curWrap.prev().length){
					capActiveId=curWrap.prev().children().attr('data-id');
				}else{
					capActiveId='';
				}
				curWrap.remove();
			}
			break;
		case 'sib':
			var curWrap=capnav.find('a[data-id="'+capActiveId+'"]').parent();
			curWrap.siblings().children().not('a[data-id="home"]').parent().remove();
			//capnav_cont.find('div[data-for="'+capActiveId+'"]').siblings().not('div[data-for="home"]').remove();
			destroyIframe(capnav_cont.find('div[data-for="'+capActiveId+'"]').siblings().not('div[data-for="home"]'));
	}
	var capActiveObj=capnav.find('a[data-id="'+capActiveId+'"]');
	capActiveObj.addClass('capnav_now');
	capnav_cont.find('div[data-for="'+capActiveId+'"]').show();
	//标签样式
	showScrollBtn($('#capnav').children(),'prev');
}
//销毁iframe
function destroyIframe(iframeParent){
	iframeParent.each(function(){
		var iframe=$(this).children();
		iframe[0].src = 'about:blank';
		//iframe[0].contentWindow.document.write('');
		//iframe[0].contentWindow.close();
		iframe.remove();
	});
	iframeParent.remove();
}