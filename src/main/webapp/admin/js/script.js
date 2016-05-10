
// 计算高度
$(document).ready(function() {
    $(".main").height($(window).height() - 72 +'px');
    $(".submenuList").height($(window).height() - 237 +'px');
    $(".table_bd").height($(window).height() - 229 +'px');
    $(".patient_images_wrap").height($(window).height() - 172 +'px');
    $(".progress_list").height($(window).height() - 123 +'px');
    
});

$(document).ready(function(){
	$(".menuList li h3").click(function(){
		$(".menuList").find(".submenuList").css("display","none");
		$(this).parent("li").find(".submenuList").css("display","block");
		//.toggleClass("active");
	})
})

/* 当鼠标移到表格上时，当前一行背景变色 */
$(document).ready(function(){
	$(".table tr td").mouseover(function(){
		$(this).parent().find("td").css("background-color","#d5f0f9");
	});
})
/* 当鼠标在表格上移动时，离开的那一行背景恢复 */
$(document).ready(function(){ 
	$(".table tr td").mouseout(function(){
		var bgc = $(this).parent().attr("bg");
		$(this).parent().find("td").css("background-color",bgc);
	});
})
// 表格隔行变色
$(document).ready(function(){ 
	var color="#f3f3f3"
	$(".table tr:odd td").css("background-color",color);  //改变偶数行背景色
	/* 把背景色保存到属性中 */
	$(".table tr:odd").attr("bg",color);
	$(".table tr:even").attr("bg","#fff");
})



