function area(id){
	if(typeof(id) != 'object'){
		this.ele = document.getElementById(id);
	}else{
		this.ele = id;
	}
}
area.prototype={
	init:function(data){
		this.$ele=$(this.ele);this.data=data;
		this.$ele.addClass('area').empty();
		this.caption=$('<div class="area_caption"><em>选择地区：</em></div>').appendTo(this.$ele);
		this.cont=$('<div class="area_cont"></div>').appendTo(this.$ele);
		this.creatnodes('1','');
	},
	creatnodes:function(jb,fdm){
		var that=this,nodes=that.data;
		var divhtml='';
		if(jb=='1'){
			for(var i=0;i<nodes.length;i++){
				if(nodes[i].JB=='1'){
					divhtml+='<span title="'+nodes[i].MC+'" class="area_span" data-dm="'+nodes[i].DM+'" data-fdm="'+nodes[i].FDM+'">'+nodes[i].MC+'</span>';
				}
			}
		}else{
			for(var i=0;i<nodes.length;i++){
				if(nodes[i].FDM==fdm){
					divhtml+='<span title="'+nodes[i].MC+'" class="area_span" data-dm="'+nodes[i].DM+'" data-fdm="'+nodes[i].FDM+'">'+nodes[i].MC+'</span>';
				}
			}
		}
		if(divhtml!=''){
			that.cont.children().hide();
			var div=$('<div data-for="'+jb+'"></div>').html(divhtml).appendTo(that.cont);
			initNiceScroll(div);
			that.hover();that.click();
		}
	},
	hover:function(){
		var that=this;
		that.cont.find('.area_span').hover(function(){
			$(this).addClass('area_hover');
		},function(){
			$(this).removeClass('area_hover');
		});
	},
	click:function(){
		var that=this;
		that.cont.find('.area_span').click(function(){
			$(this).addClass('area_active').siblings().removeClass('area_active');
			var pdiv=$(this).parent(),jb=pdiv.attr('data-for'),mc=$(this).text();
			var capdiv=that.caption.find('.area_span[data-jb="'+jb+'"]');
			if(capdiv.length){
				capdiv.attr('title',mc).attr('data-dm',$(this).attr('data-dm')).attr('data-fdm',$(this).attr('data-fdm')).text(mc).nextAll().remove();
				pdiv.nextAll().remove();
				
			}else{
				$(this).clone().appendTo(that.caption).attr('data-jb',jb).attr('class','area_span');
			}
			that.creatnodes((jb*1)+1,$(this).attr('data-dm'));
			that.capClick();
		});
	},
	capClick:function(){
		var that=this;
		that.caption.find('.area_span').click(function(){
			var jb=$(this).attr('data-jb'),mc=$(this).text();
			that.cont.children().hide();
			that.cont.children('div[data-for="'+jb+'"]').show();
		});
	},
	clear:function(){
		this.caption.html('<em>选择地区：</em>');
		this.cont.children().not('div[data-for="1"]').remove();
		this.cont.children().eq(0).show().children().removeClass('area_active');
	},
	getArea:function(){
		var dm=this.caption.find('.area_span').last().attr('data-dm');
		console.log(dm)
		return dm;
	},
	getAreaName:function(dm){
		var mcs=this.getFdm(dm).reverse().join('');
		return mcs;
	},
	getFdm:function(dm,mcs){
		if(!mcs)mcs=[];
		var nodes=this.data;
		for(var i=0;i<nodes.length;i++){
			if(nodes[i].DM==dm){
				mcs.push(nodes[i].MC);
				var fdm=nodes[i].FDM;
				this.getFdm(fdm,mcs);
			}
		}
		return mcs;
	},
	setArea:function(val){
		var that=this,mcs=that.getFdm(val).reverse();
		for(var i=0;i<mcs.length;i++){
			var span=that.cont.find('.area_span[title="'+mcs[i]+'"]'),
				fdm=span.attr('data-dm');
			span.trigger('click');
		}
	}
}