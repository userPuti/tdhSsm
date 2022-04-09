function homeNav(id){
	if(typeof(id) != 'object'){
		this.ele = document.getElementById(id);
	}else{
		this.ele = id;
	}
	eventable(this);
}
homeNav.prototype={
	init:function(data){
		this.$ele=$(this.ele);
		this.$ele.empty();
		var _ul=$('<ul></ul>').appendTo(this.$ele);
		var that=this;
		for(var i=0;i<data.length;i++){
			var _li=$('<li><a id="'+data[i].id+'">'+data[i].mc+'</a></li>').appendTo(_ul);
			var _a=_li.children().eq(0);
			var childNodes=data[i].children;
			if(typeof childNodes != 'undefined'){
				var open=false;
				if(data[i].open=='true' || data[i].open==true){
	 				open=true;
	 			}
				that.creatNodes(_a,childNodes);
				that.showItem(_a,open);
				_a.eq(0).click(function(){
					var display=$(this).next().css('display');
					that.showItem($(this),display=='none'?true:false);
				});
			}
		}
	},
	creatNodes:function(obj,nodes,state){
		var that=this,obj_id=obj.attr('id');
		var _ul=$('<ul></ul>').insertAfter(obj);
		for(var n=0;n<nodes.length;n++){
			var id=obj_id+'_'+nodes[n].id;
			var _li=$('<li><a id="'+id+'">'+nodes[n].mc+'</a></li>').appendTo(_ul);
			var _a=_li.children().eq(0);
			_a.click(function(){
				that.callEvent('onClick',[id]);
			});
			var childNodes=nodes[n].children;
			if(typeof childNodes != 'undefined'){
				var open=false;
				if(nodes[n].open=='true' || nodes[n].open==true){
	 				open=true;
	 			}
				that.creatNodes(_a,childNodes);
				that.showItem(_a,open);
				_a.click(function(){
					var display=$(this).next().css('display');
					that.showItem($(this),display=='none'?true:false);
				});
			}
		}
	},
	showItem:function(evobj,state){
		var temp=evobj.next();
		if(state){
			temp.css('display','');
		}else{
			temp.css('display','none');
		}
	}
}