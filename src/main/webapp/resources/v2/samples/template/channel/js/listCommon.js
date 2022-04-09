$(function(){
	resetListHeight();
	$(window).resize(function(){
		resetListHeight();
	});
	//鼠标碰触样式变化
	$('[data-hover]').hover(function(){
		var _hover = $(this).attr('data-hover');
		$(this).addClass(_hover);
	},function(){
		var _hover = $(this).attr('data-hover');
		$(this).removeClass(_hover);
	});
	//悬停显示tips提示
	$('[data-tips]').live('mouseenter',function(){
		var that = this;
		var tips = $(that).attr('data-tips');
		if(tips){
			layer.tips(tips, that,{tips: [3,'#1D2D44'],time:0});
		}
	}).live('mouseleave',function(){
		layer.closeAll('tips');
	});
	//显示下拉浮动层
	$('[data-droppop]').off('mouseover').live('mouseover',function(){
		if($(this).attr('data-dropevent') == 'click') return false;
		showDroppop(this);
	});
	$('[data-droppop][data-dropevent="click"]').off('click').live('click',function(){
		showDroppop(this);
	});
});

//设置列表高度
function resetListHeight(){
	$('.listwrapper').each(function(){
		var height = $(this).height() - $(this).find('.searchbar').outerHeight() - $(this).find('.toolbar').outerHeight() - $(this).find('.pagebar').outerHeight();
		$(this).find('.list').height(height);
	});
}

//显示下拉浮动层
function showDroppop(ele){
	var obj = $(ele),
		name = obj.attr('data-droppop'),
		callback = obj.attr('data-callback'),
		popObj = $('[data-dropdom="'+ name +'"]'),
		arrowObj = popObj.find('.poparrow');
	obj.addClass('hover');
	$('.droppop').not(popObj).hide();
	if(callback) eval(callback);
	var windowWidth = $(window).width(),
		windowHeight = $(window).height(),
		width = obj.outerWidth(),
		height = obj.outerHeight(),
		offsetTop = obj.offset().top,
		offsetLeft = obj.offset().left;
	var popWidth = popObj.outerWidth(),
		popHeight = popObj.outerHeight();
	var top = offsetTop + height,
		left = offsetLeft;
	var maxHeight = 0;
	if(arrowObj.length){
		arrowObj.css('left', ((width-arrowObj.outerWidth())/2) +'px');
	}
	if(popHeight > windowHeight - offsetTop - height){
		var newtop = offsetTop - popHeight;
		if(newtop < 0){
			var maxHeight1 = windowHeight - top,
				maxHeight2 = newtop;
			if(maxHeight2 > maxHeight1){
				maxHeight = maxHeight2;
				top = offsetTop + height - maxHeight;
				if(arrowObj.length){
					arrowObj.addClass('poparrow_t');
				}
			}else{
				maxHeight = maxHeight1;
			}
		}else{
			top = newtop;
			if(arrowObj.length){
				arrowObj.addClass('poparrow_t');
			}
		}
	}else{
		if(arrowObj.length){
			arrowObj.removeClass('poparrow_t');
		}
	}
	if(popWidth > windowWidth - offsetLeft){
		left = offsetLeft - popWidth + width;
		if(arrowObj.length){
			arrowObj.css({'left':'auto','right': ((width-arrowObj.outerWidth())/2) +'px'});
		}
	}
	popObj.show().css({
		top: top + 'px',
		left:left + 'px',
		height: (maxHeight?maxHeight+'px':'auto'),
		overflow: (maxHeight?'auto':'hidden')
	});
	var moveoutFunc = obj.attr('data-moveout');
	$(document).bind('mousemove',function(e){
		var target = $(e.target);
		if(target.attr('data-droppop')!=name && target.parent().attr('data-droppop')!=name && !target.hasClass('droppop') && !target.parent('.droppop').length && !target.parents('.droppop').length){
			obj.removeClass('hover');
			$('.droppop').hide();
			if(moveoutFunc) eval(moveoutFunc);
			$(document).unbind('mousemove');
		}
	});
	$('iframe').contents().find('body').bind('mousemove',function(e){
		$('.droppop').hide();
	});
}

//分页事件
/*
 * id:分页元素id
 * total:数据总数
 * size:每页显示条数
 * cur:当前页数
 * callback:回调函数，返回参数：当前页数
 */
function pageEvent(id,total,size,cur,callback) {
    $('.list_none').hide();
    var pageObj = $('#'+ id),
        selObj = pageObj.find('select'),
        prevObj = pageObj.find('.btn_prev'),
        nextObj = pageObj.find('.btn_next');
    if(!total){
        pageObj.hide();
        pageObj.after('<div class="list_none">暂无数据</div>');
		selDisable(selObj,true);
        // return false;
    }else{
		selDisable(selObj,false);
	}
    pageObj.show();
    pageObj.find('.pagebar_total').text('共'+ total +'条');
    prevObj.removeClass('btn_prev_disable');
    nextObj.removeClass('btn_next_disable');
    var pageTotal = Math.ceil(total/size);
	if(pageTotal==0){
		pageTotal = 1;
	}
    if(cur > pageTotal){
        cur = pageTotal;
		typeof(callback)==='function'&&callback(cur);
        return false;
    }
    if(cur == 1) prevObj.addClass('btn_prev_disable');
    if(cur == pageTotal) nextObj.addClass('btn_next_disable');
    var options = '';
    for(var i = 1; i <= pageTotal; i++){
        options += '<option value="'+ i +'"'+ (i==cur?' selected':'') +'>'+ i +'/'+ pageTotal +'</option>';
    }
    selObj.html(options);
    selectInit(selObj);
    pageObj.find('.select_box').unbind().bind('mouseover',function(){
        $(this).addClass('select_hover');
    }).bind('mouseout',function(){
        $(this).removeClass('select_hover');
    });
    selObj.unbind().bind('change',function(){
        cur = $(this).val();
        prevObj.removeClass('btn_prev_disable');
        nextObj.removeClass('btn_next_disable');
        if(cur == 1) prevObj.addClass('btn_prev_disable');
        if(cur == pageTotal) nextObj.addClass('btn_next_disable');
		typeof(callback)==='function'&&callback(cur);
    });
    prevObj.unbind().bind('click',function(){
        if($(this).hasClass('btn_prev_disable')) return false;
        cur--;
        if(cur <= 1){
            cur = 1;
            $(this).addClass('btn_prev_disable');
        }
        nextObj.removeClass('btn_next_disable');
        setSelVal(selObj, cur, false);
		typeof(callback)==='function'&&callback(cur);
    });
    nextObj.unbind().bind('click',function(){
        if($(this).hasClass('btn_next_disable')) return false;
        cur++;
        if(cur >= pageTotal){
            cur = pageTotal;
            $(this).addClass('btn_next_disable');
        }
        prevObj.removeClass('btn_prev_disable');
        setSelVal(selObj, cur, false);
		typeof(callback)==='function'&&callback(cur);
    });
}