var customIndex,sums=0,sum=0;
function fixedlayout(id,str,type){
	var obj,arry=str.split(',');
	if(typeof(id) != 'object'){
		obj = $(document.getElementById(id));
	}else{
		obj = id;
	}
	for(var i=0;i<arry.length;i++){
		if(arry[i]=='*'){
			customIndex=i;
		}else{
			sum=sum+arry[i]*1;
		}
		sums=sum;
		obj.children().eq(i).css('height',arry[i]+'px').attr('data-height',arry[i]);
	}
	var customDiv=obj.children().eq(customIndex),
		customChildC=customDiv.children().children();
	if(type=='fixed'){
		customDiv.css('overflow-y','hidden');
		setFixedHeight(obj,customDiv,sums);
		$(window).resize(function(){
			setFixedHeight(obj,customDiv,sums);
		});
	}else{
		customDiv.css('overflow-y','auto');
		if(customChildC[0].tagName=='IFRAME'){
			setFrameHeight(customChildC);
		}
		setCustomHeight(obj,customDiv,sums);
		$(window).resize(function(){
			setCustomHeight(obj,customDiv,sums);
		});
		customDiv.children().bind('resize',function(){});
	}
}
function setCustomHeight(obj,customobj,total){
	var maxHeight=obj.height()-total,
		customHeight=customobj.children().height();
	if(customHeight>=maxHeight){
		customobj.css('height',maxHeight+'px');
	}else{
		customobj.css('height','auto');
	}
}
function setFixedHeight(obj,customobj,total){
	var maxHeight=obj.height()-total;
	customobj.css('height',maxHeight+'px');
	customobj.children().css('overflow','hidden');
}
function setFrameHeight(iframe){
	iframe.load(function(){
		var mainheight = $(this).contents().find('body').height();
		$(this).height(mainheight);
	});
}
function doShowGrid(type){
	$('#grid').parent().show();
	$('#showGrid').parent().hide();
	$('#hideGrid').parent().show();
	sums=sum;
	if(type=='fixed'){
		setFixedHeight($('#fixedlayout'),$('#fixedlayout').children().eq(customIndex),sums);
	}else{
		setCustomHeight($('#fixedlayout'),$('#fixedlayout').children().eq(customIndex),sums);
	}
}
function doHideGrid(type){
	$('#grid').parent().hide();
	$('#showGrid').parent().show();
	$('#hideGrid').parent().hide();
	sums=sum-$('#grid').parent().attr('data-height');
	if(type=='fixed'){
		setFixedHeight($('#fixedlayout'),$('#fixedlayout').children().eq(customIndex),sums);
	}else{
		setCustomHeight($('#fixedlayout'),$('#fixedlayout').children().eq(customIndex),sums);
	}
}
