$(function(){
	$('.tdh_btn').each(function(){
		var func=$(this).attr('onclick');
		if (func != '') {
            $(this).removeAttr('onclick').attr('data-onclick',func);
       }
	});
	$('.tdh_btn').hover(function(){
		if($(this).hasClass('tdh_btn_disable')){
			return;
		}else{
			if($(this).hasClass('tdh_btn_yellow')){
				$(this).addClass('tdh_btn_yellow_hover');
			}else if($(this).hasClass('tdh_btn_blue')){
				$(this).addClass('tdh_btn_blue_hover');
			}else if($(this).hasClass('tdh_btn_white')){
				$(this).addClass('tdh_btn_white_hover');
			}else{
				$(this).addClass('tdh_btn_hover');
			}
		}
	},function(){
		$(this).removeClass('tdh_btn_white_hover');
		$(this).removeClass('tdh_btn_yellow_hover');
		$(this).removeClass('tdh_btn_blue_hover');
		$(this).removeClass('tdh_btn_hover');
	}).click(function(){
		if(!$(this).hasClass('tdh_btn_disable')){
			var func=$(this).data('onclick');
			if (func != '') {
                eval(func);
            }
		}
	});
	$('.tdh_btnMore').click(function(e){
		e.stopPropagation();
		var xLeft=$(this).offset().left,
			yTop=$(this).offset().top+$(this).outerHeight();
		var role=$(this).attr('id');
		var listBox=$('.tdh_btn_listBox[data-role="'+role+'"]');
		$('.tdh_btn_listBox').not(listBox).css('display','none');
		listBox.css({
			left:xLeft,
			top:yTop
		});
		if(listBox.css('display')=='none'){
			listBox.css('display','block');
		}else{
			listBox.css('display','none');
		}
	});
	$('.tdh_btn_listBox').find('li').hover(function(){
		var aBtn=$(this).find('.tdh_btnHover'),
			ulBox=$(this).find('ul');
		if(ulBox.length){
			aBtn.addClass('tdh_btnHover_active');
			ulBox.css('display','block');
		}
	},function(){
		var aBtn=$(this).find('.tdh_btnHover'),
			ulBox=$(this).find('ul');
		if(ulBox.length){
			aBtn.removeClass('tdh_btnHover_active');
			ulBox.css('display','none');
		}
	});
	$('.tdh_btn_listBox').find('a').click(function(){
		if(!$(this).hasClass('tdh_btnHover')){
			$(this).parents('ul').css('display','none');
		}else{
			return;
		}
	});
	$(document).click(function(e){
		if(!$(e.target).parents('.tdh_btn_listBox').length){
	    	$('.tdh_btn_listBox').css('display','none');
	    	$('.tdh_btnHover').removeClass('tdh_btnHover_active');
	    	$('.tdh_btn_listBox').find('ul').css('display','none');
	  	}

	});
});
