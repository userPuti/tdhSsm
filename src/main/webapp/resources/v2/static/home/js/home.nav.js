function homeNav(id,size){
	if(typeof(id) != 'object'){
		this.ele = document.getElementById(id);
	}else{
		this.ele = id;
	}
	this.size=size || 40;
	eventable(this);
}
homeNav.prototype={
	init:function(data){
		this.$ele=$(this.ele);
		this.$ele.addClass('homenav').empty();
		this.length=data.length;
		var _ul=$('<ul></ul>').appendTo(this.$ele);
		var that=this;
		for(var i=0;i<data.length;i++){
			var _li=$('<li><a id="'+data[i].id+'" class="homenav_item"><i class="homenav_icon"></i>'+data[i].mc+'<i class="homenav_iconbtn"></i></a></li>').appendTo(_ul);
			var _a=_li.children().eq(0),
				_icon=_a.find('.homenav_icon'),
				_iconbtn=_a.find('.homenav_iconbtn');
			if(data[i].mc=='常用功能'){
				that.usedid=data[i].id;
				_icon.addClass('icon_used');
				_iconbtn.addClass('icon_setting');
			}else{
				_icon.addClass('icon_apps');
				_iconbtn.addClass('icon_search');
			}
			var childNodes=data[i].children;
			if(typeof childNodes != 'undefined' && childNodes.length != 0){
				that.creatNodes(_a,childNodes,0);
			}
			var open=false;
			if(data[i].open=='true' || data[i].open==true){
				open=true;
				that.indexItem=_a;
				that.showUl(_a,open);
				if(_a.siblings('.homenav_scroll')){ 
					that.scroll(_a.siblings('.homenav_scroll'));
					$(window).resize(function(){
						that.scroll(_a.siblings('.homenav_scroll'));
					});
				}
			}
			that.toolClick(_iconbtn);
		}
		that.$ele.find('.homenav_item').click(function(){
			var that_searchBox=that.$ele.find('.homenav_search'),
				that_scrollBox=that_searchBox.siblings('.homenav_scroll');
			that_searchBox.prev().find('.icon_search').removeClass('active');
			that_searchBox.css('display','none').find('.homenav_searchInput').val('');
			that_scrollBox.css('display','');
			that_scrollBox.find('.homenav_link').show();
			that_scrollBox.find('.homenav_links').removeClass('homenav_open');
			that_scrollBox.find('ul').not(':eq(0)').css('display','none');
			if(that.indexItem){
				that.showUl(that.indexItem,false);
			}
			var curNext=$(this).siblings('.homenav_scroll');
			that.showUl($(this),true);
			if(curNext){ that.scroll(curNext); }
			that.indexItem=$(this);
		});
	},
	creatNodes:function(obj,nodes,num){
		var that=this,obj_id=obj.attr('id');
		var _ul=$('<ul style="display:none;"></ul>').insertAfter(obj);
		if(obj.hasClass('homenav_item')){
			_ul.css('display','').wrap('<div class="homenav_scroll" style="display:none;"></div>');
		}
		for(var n=0;n<nodes.length;n++){
			var id=nodes[n].id;
			var _li=$('<li><a id="'+id+'" class="homenav_link" style="text-indent: '+(42+(num*16))+'px;">'+nodes[n].mc+'</a></li>').appendTo(_ul);
			var _a=_li.children().eq(0);
			if(obj_id==that.usedid){
				_a.attr('id',that.usedid+'_'+id);
			}
			var childNodes=nodes[n].children;
			if(typeof childNodes != 'undefined' && childNodes.length != 0){
				_a.addClass('homenav_links');
				_a.css('background-position-x',(20*(num+1))+'px');
				that.creatNodes(_a,childNodes,num+1);
			}
			_a.hover(function(){
				$(this).addClass('homenav_hover');
			},function(){
				$(this).removeClass('homenav_hover');
			}).click(function(){
				if($(this).hasClass('homenav_links')){
					var display=$(this).next().css('display');
					that.showUl($(this),display=='none'?true:false);
					that.scroll($(this).parents('.homenav_scroll'));
				}else{
					if(that.active){ that.active.removeClass('homenav_active'); }
					$(this).addClass('homenav_active');
					that.active=$(this);
					var indexId=$(this).attr('id'),indexData;
					$.each(nodes, function(i) {
					    if(nodes[i].id==indexId.replace(that.usedid+'_','')){
					    	indexData=nodes[i];
					    }
					});
					that.callEvent('onClick',[indexId,indexData]);
				}
			});
		}
	},
	showUl:function(evobj,state){
		var temp=evobj.next('ul');
		if(evobj.hasClass('homenav_item')){
			temp=evobj.siblings('.homenav_scroll');
		}
		if(temp.length){
			if(state){
				evobj.addClass('homenav_open');
				temp.css('display','');
			}else{
				evobj.removeClass('homenav_open');
				temp.css('display','none');
			}
		}
	},
	scroll:function(scrollObj){
		var wrap=scrollObj,
			ulObj=wrap.children(),
			siblings=wrap.siblings('.homenav_search');
		var obj_height=ulObj.outerHeight(),
			scrollHeight=this.$ele.height()-this.size*this.length;
		if(siblings.length && siblings.css('display')!='none'){
			scrollHeight=scrollHeight-siblings.outerHeight();
		}
		if(obj_height>scrollHeight){
			wrap.height(scrollHeight);
		}else{
			wrap.height(obj_height);
		}
		wrap.niceScroll({
			cursoropacitymax: .85,
			cursorcolor:'#333',
			cursorwidth:'8',
			cursorborder: "0",
			cursorborderradius: "10px",
			autohidemode: false,
			background: "#eee",
			horizrailenabled: false
		});
		wrap.getNiceScroll().resize();
	},
	toolClick:function(toolObj){
		var that=this;
		toolObj.click(function(e){
			e.stopPropagation();
			if($(this).hasClass('icon_setting')){
				var name='icon_setting';
				that.callEvent('ontoolClick',[name]);
			}else{
				var aBox=$(this).parent(),
					searchBox=aBox.siblings('.homenav_search'),
					searchInput=searchBox.find('.homenav_searchInput'),
					scrollBox=aBox.siblings('.homenav_scroll');
				if(that.indexItem){
					that.showUl(that.indexItem,false);
				}
				that.showUl(aBox,true);
				that.indexItem=aBox;
				if($(this).hasClass('active')){
					$(this).removeClass('active');
					searchBox.css('display','none');
					searchInput.val('');
					scrollBox.css('display','');
					scrollBox.find('.homenav_link').show();
					scrollBox.find('.homenav_links').removeClass('homenav_open');
					scrollBox.find('ul').not(':eq(0)').css('display','none');
				}else{
					$(this).addClass('active');
					if(searchBox.length){
						searchBox.css('display','');
					}else{
						var searchHtml='<div class="homenav_search">'+
									'<div class="homenav_searchWrap">'+
										'<input class="homenav_searchInput" type="text" />'+
									'</div>'+
								'</div>';
						aBox.after(searchHtml);
						searchBox=aBox.siblings('.homenav_search');
						searchInput=searchBox.find('.homenav_searchInput');
					}
					scrollBox.css('display','none');
				}
				that.scroll(scrollBox);
				searchInput.on('input keydown propertychange', function (e) {
	        		var inputVal=$(this).val();
	        		scrollBox.find('.homenav_link').hide();
	        		scrollBox.find('ul').not(':eq(0)').css('display','');
					var shows=scrollBox.find('.homenav_link').not('.homenav_links').filter(function (i, a) { 
	        			if(inputVal!=''){
	        				return $(a).text().toLowerCase().indexOf(inputVal) !=-1; 
	        			}
					}).show().addClass('homenav_filter').length;
					if(shows>0){
						scrollBox.css('display','');
						that.scroll(scrollBox);
					}else{
						scrollBox.find('.homenav_link').removeClass('homenav_filter').show();
	    				scrollBox.find('ul').not(':eq(0)').css('display','none');
	    				scrollBox.css('display','none');
					}
	        	});
			}
		});
	}
}