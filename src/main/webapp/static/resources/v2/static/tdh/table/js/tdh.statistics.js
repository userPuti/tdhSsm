;(function($) {
	var table = function(ele, opt) {
        this.$element = ele,
        this.defaults = {
            data: '',
            dataType:'',
	    	fixed:false, //开启固定列
	    	fixedAlign:'', //三种赋值方式--fixedAlign:'left'; fixedAlign:'right'; fixedAlign:['left','right'];
	    	fixedNumber:'',//两种赋值方式--fixedNumber:'2'; fixedNumber:['2','1'](对应fixedAlign必须为['left','right']);
	    	sort:false,
	    	openAll:false,
	    	height:0
        },
        this.options = $.extend({}, this.defaults, opt)
    }
	table.prototype = {
		cell: function(){
			this.$element.empty();
			if(this.options.data.length){ this.init(this.options.data); }
			if(this.options.sort){ this.sort(); }
		},
        init: function(data) {
        	this.$element.empty();
        	var dataType=this.options.dataType,
        		obj=$('<table></table>').appendTo(this.$element),
        		col=this.$element.prev().find('.statis_setWidth'),
        		openAll=this.options.openAll;
        	var _this=this;
			var colWidths = 0;
			col.children().each(function(){
				colWidths += parseInt($(this).attr('width'));
			});
			col.parents('table').css('width',colWidths+'px');
        	obj.css('width',colWidths+'px').append(col.clone());
            $.each(data,function(index,item){
            	var tr = $('<tr>'),open,children,total;
			    $.each(item,function(name,val){
			    	if(name=="id"){
			    		return;
			    	}else if(name=="open"){
			    		open = val; return open;
			    	}else if(name=="z_children"){
			    		children = val; return children;
			    	}else if(name=="total"){
			    		total = val; tr.attr('data-total',val); return total;
			    	}else if(name=="lock"){
			    		return;
			    	}else{
                        if (typeof val === 'object') {
                            tr.append('<td title="' + val.text + '" style="cursor:pointer" onclick="' + (val.onclick ? val.onclick : '') + '">' +val.text +'</td>')
                        } else {
                            tr.append('<td title="' + val + '">' + val + '</td>');
                        }
			    	}
			    });
			    obj.append(tr);
			    if(openAll){open=true;}
            	if(dataType=="multirow"){
            		tr.addClass('statis_ev');
            		tr.find('td').eq(0).css('text-align', 'left').attr('colspan','3');
            		if(total){ tr.find('td').eq(0).attr('colspan','2'); }
            		if(children){
            			var arrowTd=tr.find('td').eq(0).addClass('statis_arrow');
            			if(open){
            				_this.creatNodes(arrowTd,children,1);
            			}
            			arrowTd.click(function(){
            				_this.creatNodes($(this),children,1);
            			});
            		}
            	}
			});
			var tTrs=obj.find('tr[data-total]');
			if(tTrs.length){
				obj.find('tr[data-total]').eq(0).prepend('<td rowspan="'+tTrs.length+'">'+tTrs.attr('data-total')+'</td>');
			}
			_this.scroll();
			if(this.options.fixed){ _this.addFixed(); }
			_this.tdEvents(obj);
      	},
      	creatNodes: function(obj,nodes,num){
      		var _this=this;
  			var ptr=obj.parent(),
  				ptrNext=ptr.next(),
  				e=ptr.attr('class'),
  				o=e=='statis_ev'?'statis_odd':'statis_ev',
  				cur=obj.parent().index()+1,
        		openAll=_this.options.openAll;
			if(obj.hasClass('statis_arrow_up')){
				obj.removeClass('statis_arrow_up');
				var nodeLength=nodes.length;
				for(var i=0;i<nodes.length;i++){
					if(ptr.nextAll('.'+o).eq(i).children().eq(0).hasClass('statis_arrow_up')){
						nodeLength+=nodes[i].z_children.length;
					}
				}
				_this.$element.find('tr').slice(cur, cur+nodeLength).remove();
				
			}else{
				obj.addClass('statis_arrow_up');
				//nodes.sort(function(a,b){return 1-2});
				//nodes.sort(function(a,b){return b.id-a.id});
				var ctrHtml=$('<div></div>');
				$.each(nodes,function(index,odd){
					var tr = $('<tr class="'+o+'">'),open,children,total;
				    $.each(odd,function(name,val){
				    	if(name=="id"){
				    		return;
				    	}else if(name=="open"){
				    		open = val; return open;
				    	}else if(name=="z_children"){
				    		children = val;
				        	return children;
				    	}else if(name=="lock"){
			    			return;
			    		}else{
				    	   if (typeof val === 'object') {
                            tr.append('<td title="' + val.text + '" style="cursor:pointer" onclick="' + (val.onclick ? val.onclick : '') + '">' +val.text +'</td>')
                           } else {
                            tr.append('<td title="' + val + '">' + val + '</td>');
                           }
				    	}
				    });
				    //ptr.after(tr);
				    if(ptrNext.length){
				    	ptrNext.before(tr);
				    }else{
				    	ptr.parents('table').append(tr);
				   	}
        			tr.find('td').eq(0).css({'text-align': 'left','text-indent': num*14+'px'}).attr('colspan','3');
				    if(openAll){open=true;}
				    if(children){
						var arrowTd=tr.find('td').eq(0).addClass('statis_arrow');
            			if(open){
            				_this.creatNodes(arrowTd,children,num+1);
            			}
						arrowTd.click(function(){
            				_this.creatNodes($(this),children,num+1);
            		});
					}
				});
			}
			_this.scroll();
      	},
	    scroll:function(){
	    	var _this=this;
	    	var height=_this.options.height,
	    		eleHeader= _this.$element.prev().children();
	        _this.$element.scroll(function() {
				$(this).prev().scrollLeft($(this).scrollLeft());
			});
			_this.$element.height(height);
			_this.$element.niceScroll({
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
			_this.$element.getNiceScroll().resize();
	    },
	    addFixed:function(){
			this.$element.siblings('.statis_table_fixed').remove();
	    	var _this=this;
	    	var fixed=_this.options.fixed,
				fixedAlign=_this.options.fixedAlign,
				fixedNumber=_this.options.fixedNumber;
			if($.isArray(fixedAlign)){
				if($.isArray(fixedNumber)){
					_this._addFixed('statis_table_fixed_l',fixedNumber[0]);
					_this._addFixed('statis_table_fixed_r',fixedNumber[1]);
				}else{
					_this._addFixed('statis_table_fixed_l',fixedNumber);
					_this._addFixed('statis_table_fixed_r',fixedNumber);
				}
			}else{
				fixedAlign = fixedAlign == 'right'?'statis_table_fixed_r':'statis_table_fixed_l';
				_this._addFixed(fixedAlign,fixedNumber);
			}
	   	},
		_addFixed:function(fixedAlign,fixedNumber){
			var _this=this;
			var fixedBox = $('<div class="statis_table_fixed '+ fixedAlign +'"></div>').insertAfter(_this.$element);
			fixedBox.append(_this.$element.prev().clone());
			fixedBox.append(_this.$element.clone().removeAttr('id'));
			var fixedHead = fixedBox.find('.statis_table_header'),
				fixedBody = fixedBox.find('.statis_table_body');
			var colLength = _this.$element.prev().find('.statis_setWidth').children().length;
			fixedBox.find('table tr').each(function(){
				if(fixedAlign == 'statis_table_fixed_l'){
					$(this).children().eq(fixedNumber-1).nextAll().remove();
				}else if(fixedAlign == 'statis_table_fixed_r'){
					$(this).children().eq(colLength-fixedNumber).prevAll().remove();
				}
			});
			var col=fixedHead.find('.statis_setWidth'),
				fixedColWidths = 0;
			col.children().each(function(i){
				fixedColWidths += parseInt($(this).attr('width'));
			});
			fixedBox.width(fixedColWidths);
			fixedBox.find('table').width(fixedColWidths);
			_this.$element.scroll(function() {
				fixedBody.scrollTop($(this).scrollTop());
			});
			fixedBody.on('mousewheel', function(event,delta) {
				event.preventDefault();
			  	var scrollTop = $(this).scrollTop();
			  	_this.$element.scrollTop(scrollTop + ((event.deltaY * event.deltaFactor) * -1));
			});
			_this.tdEvents(fixedBody);
		},
	   	sort:function(){
	   		var _this=this;
	   		var data=_this.options.data,
	   			header= _this.$element.siblings('.statis_table_header');
	   		header.find('.statis_sort').removeClass('statis_sort_asc statis_sort_desc');
	   		header.find('.statis_sort').unbind('click').click(function(){
	   			if(data.length){
	   				var data1=[],data2=[];
	   				for(var i=0;i<data.length;i++){
	   					if(data[i].lock){ data2.push(data[i]); }else{ data1.push(data[i]); }
	   				}
			  		var index=$(this).attr('data-index');
			  		var str=[];
			  		$.each(data1[0],function(name,val){
			  			if(name=="id"){return;}else{str.push(name);}
			  		});
	                var sortKey=str[index];
	                header.find('.statis_sort').not(this).removeClass('statis_sort_asc statis_sort_desc');
	                if ($(this).hasClass('statis_sort_asc')) {
	                    $(this).removeClass('statis_sort_asc').addClass('statis_sort_desc');
	                    data1.sort(function (a, b) {
	                    	if(!a.lock){
	                        	var a1 = typeof a[sortKey] === 'object' ? (a[sortKey].text || '') : a[sortKey];
	                        	var b1 = typeof b[sortKey] === 'object' ? (b[sortKey].text || '') : b[sortKey];
	                        	return b1 - a1;
	                       }
	                    });
	                } else {
	                    $(this).addClass('statis_sort_asc').removeClass('statis_sort_desc');
						data1.sort(function (a, b) {
	                    	if(!a.lock){
		                        var a1 = typeof a[sortKey] === 'object' ? (a[sortKey].text || '') : a[sortKey];
		                        var b1 = typeof b[sortKey] === 'object' ? (b[sortKey].text || '') : b[sortKey];
								return a1 - b1 ;
	                       	}
	                    });
	                }
	                $.merge(data1,data2);
			  		_this.init(data1);
			  	}
		  	});
	   	},
		tdEvents:function(obj){
			var _this = this;
			obj.find('td').click(function(){
				var index=$(this).parent('tr').index();
				_this.$element.parent().find('.statis_active').removeClass('statis_active');
				_this.$element.parent().find('.statis_table_body').each(function(i){
					$(this).find('tr').eq(index).addClass('statis_active');
				});
			});
		}
    }
	$.fn.statistics = function(options) {
        var newTable = new table(this, options);
        return newTable.cell();
    }
})(jQuery);