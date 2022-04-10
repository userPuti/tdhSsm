$(function(){
	resizeHeight_c();
	$(window).resize(function(){
		resizeHeight_c();
	})
});
//上中下结构，中间自适应
function resizeHeight_c(){
	var wrap=$('.tdh_layout_tcb'),
		t=wrap.find('.tdh_layout_t'),
		c=wrap.find('.tdh_layout_c'),
		b=wrap.find('.tdh_layout_b');
	c.height($(window).height()-t.outerHeight()-b.outerHeight());
}
