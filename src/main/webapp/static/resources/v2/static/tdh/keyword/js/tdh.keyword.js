function keyword(id){
	if(typeof(id) != 'object'){
		this.ele = document.getElementById(id);
	}else{
		this.ele = id;
	}
}
keyword.prototype={
	init:function(data){
		this.$ele=$(this.ele);
		this.$ele.addClass('keyword').empty();
		this.caption=$('<div class="keyword_caption">'+
			'<div class="keyword_right">'+
				'<div class="keyword_caption_title">已选定关键字</div>'+
			'</div>'+
			'<div class="keyword_left">'+
				'<table><tr>'+
					'<td width="56">关键字：</td>'+
					'<td><input class="keyword_search_input" type="text" /></td>'+
					'<td width="54"><a class="keyword_search_btn">查询</a></td>'+
				'</tr></table>'+
			'</div>'+
		'</div>').appendTo(this.$ele);
		this.cont=$('<div class="keyword_cont"></div>').appendTo(this.$ele);
		this.creatnodes(data);
		this.search();
		this.addKeyword();
	},
	creatnodes:function(data){
		data.sort(function(a,b){return a.pxh-b.pxh});
		this.rightCont=$('<div class="keyword_right"></div>').appendTo(this.cont);
		this.leftCont=$('<div class="keyword_left"></div>').appendTo(this.cont);
		var leftHtml='';
		for(var i=0;i<data.length;i++){
			leftHtml+='<span class="keyword_span" title="'+data[i].name+'">'+data[i].name+'</span>';
		}
		this.leftCont.html(leftHtml);
		this.addbtn=$('<span class="keyword_add"></span>').appendTo(this.leftCont);
		this.events();
	},
	search:function(){
		var that=this;
		that.caption.find('.keyword_search_input').on('keydown', function (e) {
    		if(e.keyCode== 13){
				doSearch();
				$(this).blur();
			}
    	});
    	that.caption.on('click','.keyword_search_btn',function(){
    		doSearch();
    	});
    	function doSearch(){
    		var search=that.caption.find('.keyword_search_input').val();
    		that.leftCont.find('.keyword_span').removeClass('keyword_hide');
    		var hiddens = that.leftCont.find('.keyword_span').filter(function (i, span) { 
				return $(span).text().toLowerCase().indexOf(search) < 0; 
			}).addClass('keyword_hide').length;
			console.log(hiddens)
			if(that.leftCont.find('.keyword_span').length == hiddens){
				layer.msg('无匹配关键字！')
			}
    	}
	},
	events:function(){
		var that=this;
		that.cont.on('mouseover','.keyword_span,.keyword_add',function(){
			$(this).addClass('keyword_hover');
		}).on('mouseout','.keyword_span,.keyword_add',function(){
			$(this).removeClass('keyword_hover');
		});
		that.leftCont.on('click','.keyword_span',function(){
			$(this).removeClass('keyword_hover').append('<i class="keyword_del"></i>').appendTo(that.rightCont);
		});
		that.rightCont.on('click','.keyword_del',function(){
			$(this).parent().removeClass('keyword_hover').insertBefore(that.addbtn);
			$(this).remove();
		});
	},
	addKeyword:function(){
		var that=this;
		that.leftCont.find('.keyword_add').click(function(){
			layer.prompt({title: '输入关键字', formType: 0}, function(value, index){
				layer.close(index);
				that.rightCont.append('<span class="keyword_span" title="'+value+'">'+value+'<i class="keyword_del"></i></span>');
			    if(window.doAddKeyword){
					doAddKeyword(value);
				}
			});
			
		});
	},
	getCheckArray:function(){
		var getCheckArray=[];
		this.rightCont.find('.keyword_span').each(function(){
			getCheckArray.push($(this).text());
		})
		return getCheckArray.join(',');
	},
	setKeyword:function(str){
		var that=this;
		var strs=str.split(',');
		that.leftCont.find('.keyword_span').each(function(){
			for(var i=0;i<strs.length;i++){
				if($(this).text()==strs[i]){
					$(this).remove();
				}
			}
		});
		var rightHtml='';
		for(var i=0;i<strs.length;i++){
			rightHtml+='<span class="keyword_span" title="'+strs[i]+'">'+strs[i]+'<i class="keyword_del"></i></span>'
			that.rightCont.html(rightHtml);
		}
	}
}