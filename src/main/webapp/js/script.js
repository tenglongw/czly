$(function() {


	// 轮播图

	function lunbo(obj) {
		var obj    = obj;
		var ulC    = obj.find('ul');
		var liC    = ulC.find('li');
		var spC    = obj.find('.spanCn');
		var spanJ  = '';
		var active = 'active';
		var index  = 0;
		var span   = null;
		//alert(5623);
		for (var i = 0; i < liC.length; i++) {
			 var spanZ = '<span></span>';
			 spanJ += spanZ;
		};
		spC.html(spanJ);
		function auto() {
			 time = setTimeout(function(){
				index++;
				if (index >liC.length-1 ) {
					index = 0;
				}else if(index < 0){
					index = liC.length-1;
				}
				auto();
				show()
				console.log(index);
			},3000);
		};
		auto();
		function show() {
		    span   = spC.find('span');
			span.eq(index).addClass(active).siblings().removeClass(active);
			liC.eq(index).addClass(active).siblings().removeClass(active);
		}
		show();
		span.click(function() {
			index = $(this).index();
			show();
		});
		liC.hover(function() {clearTimeout(time)},function() {auto()});




		
	


	

		
	};
	 lunbo($('#banner'));
});