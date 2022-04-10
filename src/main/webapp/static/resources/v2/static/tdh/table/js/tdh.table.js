function table(id,type){
	if(typeof(id) !='object'){
		this.ele = $(document.getElementById(id));
	}else{
		this.ele = id;
	}
	this.header=this.ele.find('.tdh_grid_header');
	this.body=$('<div class="tdh_grid_body"></div>').appendTo(this.ele);
	if(type){
		this.autosize=type;
	}
	this.setInit();
	eventable(this);
}
table.prototype={
	setInit:function(){
		this.setWidth=this.header.find('.tdh_grid_setWidth');
		var clnms=this.setWidth.children();
		for(var i=0;i<clnms.length;i++){
			clnms.eq(i).attr('data-width',clnms.eq(i).attr('width'));
		}
		this.setcolWidth();
		this.setBody();
		this.setFixed();
		this.setAutoResize();
	},
	setcolWidth:function(){
		var that=this;
		that.eleWidth=this.ele.width();
		var clnms=that.setWidth.children(),widths=0,clnm=[];
		if(clnms.attr('data-width').indexOf('%')==-1){
			for(var i=0;i<clnms.length;i++){
				var colWidth=clnms.eq(i).attr('data-width');
				if( colWidth && colWidth!='*'){
					widths+=colWidth*1;
				}else{
					clnm.push(i);
				}
			}
			var remainder=that.eleWidth-widths,clnmLength=clnm.length;
			if(remainder>0){
				that.header.find('table').width(that.eleWidth);
				if(clnmLength){
					for(var i=0;i<clnmLength;i++){
						clnms.eq(clnm[i]).attr('width',(remainder-1)/clnmLength);
					}
				}
			}else{
				if(clnmLength){
					for(var i=0;i<clnmLength;i++){
						clnms.eq(clnm[i]).attr('width','200');
					}
					that.header.find('table').width(widths+200*clnmLength);
				}else{
					that.header.find('table').width(widths);
				}
			}
		}else{
			for(var i=0;i<clnms.length;i++){
				var colWidth=clnms.eq(i).attr('data-width').replace(/%+/, "");
				if( colWidth && colWidth!='*'){
					widths+=colWidth*1;
				}else{
					clnm.push(i);
				}
			}
			var remainder=100-widths,clnmLength=clnm.length;
			if(remainder>0){
				that.header.find('table').css('width','100%');
				if(clnmLength){
					for(var i=0;i<clnmLength;i++){
						clnms.eq(clnm[i]).attr('width',remainder/clnmLength+'%');
					}
				}
			}else{
				if(clnmLength){
					for(var i=0;i<clnmLength;i++){
						clnms.eq(clnm[i]).attr('width','20%');
					}
					that.header.find('table').css('width',widths+20*clnmLength+'%');
				}else{
					that.header.find('table').css('width',widths+'%');
				}
			}
		}
	},
	setBody:function(){
		var that=this;
		var obj=$('<table></table>').appendTo(that.body);
		obj.css('width',that.header.find('table')[0].style.width);
    	obj.append(that.setWidth.clone());
        that.body.scroll(function() {
			that.header.scrollLeft($(this).scrollLeft());
			that.ele.find('.tdh_grid_fixed .tdh_grid_body').scrollTop($(this).scrollTop());
		});
    	if(that.autosize!='autosize'){
			that.body.height(that.ele.height()-that.header.outerHeight());
			that.body.niceScroll({
				cursoropacitymax: 1,
				cursoropacitymin: 1,
				cursorcolor:'#aaa',
				cursorwidth:'8px',
				cursorborder: "0",
				cursorborderradius: "10px",
				autohidemode: false,
				background: "none",
				horizrailenabled: true
			});
			that.body.getNiceScroll().resize();
		}
	},
	setAutoResize:function(){
		var that=this;
		$(window).resize(function(){
			that.setcolWidth();
			that.body.find('table').css('width',that.header.find('table')[0].style.width);
			that.body.find('.tdh_grid_setWidth').html(that.setWidth.html());
			if(that.autosize!='autosize'){
				that.body.height(that.ele.height()-that.header.outerHeight());
	    		that.body.getNiceScroll().resize();
			}
			that.setFixed();
		});
	},
	setSkin:function(name){
		this.skin=name;
		this.ele.addClass('tdh_grid_'+this.skin);
	},
	loadBody:function(data,type){
		var that=this;
		that.data=data;
		var obj=that.body.find('table');
		$.each(data,function(index,item){
        	var tr = $('<tr>');
		    $.each(item,function(name,val){
		    	tr.append('<td title="' + val + '">' + val + '</td>');
		    });
		    obj.append(tr);
		    if(that.skin){
		    	if((index+1)%2>0){
		    		tr.addClass('tdh_grid_ev');
		    	}else{
		    		tr.addClass('tdh_grid_odd');
		    	}
		    }
		});
		that.refreshFixed();
		that.tdEvents(that.body);
		that.sort(data);
	},
	setColType:function(colnum,type){
		this.body.find('tr').not('.tdh_grid_setWidth').each(function(){
			var col=$(this).children().eq(colnum-1),
				colTxt=col.text();
			if(type='link'){
				col.html('<a class="tdh_grid_link">'+colTxt+'</a>');
			}
		});
		this.refreshFixed();
	},
	addCol:function(colnum,collabel){
		var that=this;
		var num=colnum-2;
		that.body.find('tr').not('.tdh_grid_setWidth').each(function(){
			if(num<0){
				$(this).prepend('<td>'+collabel+'</td>');
			}else{
				$(this).children().eq(num).after('<td>'+collabel+'</td>');
			}
		});
		if(collabel.indexOf('inputCheck')!=-1){
			checkboxInit(that.body.find('.inputCheck'));
		}
		that.refreshFixed();
	},
   	sort:function(data){
   		var that=this;
		var sort_direction=1;
		that.header.find('.tdh_grid_sort').removeClass('tdh_grid_sort_asc tdh_grid_sort_desc');
    	that.header.find('.tdh_grid_sort').click(function() {
    		that.header.find('.tdh_grid_sort').not(this).removeClass('tdh_grid_sort_asc tdh_grid_sort_desc');
	        if($(this).hasClass('tdh_grid_sort_asc')) {
	            $(this).removeClass('tdh_grid_sort_asc').addClass('tdh_grid_sort_desc');
	            sort_direction=-1;
	        }
	        else {
	            $(this).removeClass('tdh_grid_sort_desc').addClass('tdh_grid_sort_asc');
	            sort_direction=1;
	        }
	        var trarr=that.body.find('tr').not('.tdh_grid_setWidth').get(),
	        	index=$(this).index();
	        trarr.sort(function(a, b) {
	            var col1=$(a).children('td').eq(index).text().toUpperCase();
	            var col2=$(b).children('td').eq(index).text().toUpperCase();
	            return(col1 < col2) ? -sort_direction: (col1 > col2) ? sort_direction: 0;
	        });
	        $.each(trarr, function(i, row) {
	            that.body.find('table').append(row);
	        });
	    });
	    that.refreshFixed();
   	},
   	setFixed:function(){
   		var that=this;
   		var leftArr=[],rightArr=[];
   		that.header.find('th[data-fixed]').each(function(){
   			var fixed=$(this).attr('data-fixed'),
   				index=$(this).index();
   			if(fixed=='left'){
   				leftArr.push(index);
   			}else{
   				rightArr.push(index);
   			}
   		});
   		that.ele.find('.tdh_grid_fixed').remove();
   		if(leftArr.length){
   			that.leftFixed=$('<div class="tdh_grid_fixed tdh_grid_fixed_l"></div>').appendTo(that.ele);
   			that._setFixed(that.leftFixed,leftArr);
   		}
   		if(rightArr.length){
   			that.rightFixed=$('<div class="tdh_grid_fixed tdh_grid_fixed_r"></div>').appendTo(that.ele);
   			that._setFixed(that.rightFixed,rightArr);
   		}
   	},
   	_setFixed:function(obj,ary){
   		var that=this;
   		var fixedHeader=$('<div class="tdh_grid_header"></div>').appendTo(obj)
   			fixedBody=$('<div class="tdh_grid_body"></div>').appendTo(obj);
   		fixedHeader.html(that.header.html());
   		fixedBody.html(that.body.html());
   		fixedBody.css({ top : that.header.outerHeight()+'px' });
   		if(that.autosize!='autosize'){
   			fixedBody.css({ height : that.body[0].style.height });
   		}
   		var objWidth=0;
   		for(var i=0;i<ary.length;i++){
   			objWidth+=that.header.find('.tdh_grid_setWidth th').eq(ary[i]).width();
   		}
   		obj.css({width:objWidth+'px',height:that.ele.height()+'px'});
   		fixedBody.on('mousewheel', function(event,delta) {
   			event.preventDefault();
          	var scrollTop = $(this).scrollTop();
          	that.body.scrollTop(scrollTop + ((event.deltaY * event.deltaFactor) * -1));
   		});
   		that.tdEvents(fixedBody);
   	},
   	refreshFixed:function(){
   		this.ele.find('.tdh_grid_fixed .tdh_grid_body').html(this.body.html());
   	},
   	tdEvents:function(obj){
   		var that=this;
		obj.on('click','td',function(){
			that.ele.find('.tdh_grid_body').find('tr').removeClass('tdh_grid_active');
			var index=$(this).parent('tr').index()-1,indexData;
			that.ele.find('.tdh_grid_body').each(function(){
				$(this).find('tr').eq(index+1).addClass('tdh_grid_active');
			});
			$.each(that.data, function(i) {
			    if(i==index){
			    	indexData=that.data[i];
			    }
			});
			that.callEvent('onClick',[indexData]);
		}).on('mouseover','td',function(){
			var index=$(this).parent('tr').index();
			that.ele.find('.tdh_grid_body').each(function(){
				$(this).find('tr').eq(index).addClass('tdh_grid_hover');
			});
		}).on('mouseout','td',function(){
			var index=$(this).parent('tr').index();
			that.ele.find('.tdh_grid_body').each(function(){
				$(this).find('tr').eq(index).removeClass('tdh_grid_hover');
			});
		});
   	}
}