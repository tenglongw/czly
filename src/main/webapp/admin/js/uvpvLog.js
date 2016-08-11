$(".trValueList").html("");

$(function(){
	refreshTable();
	$(".glyphicon-search").click(function(){
		refreshTable();
	});
	
	$('#searchUserName').bind('keypress',function(event){
        if(event.keyCode == "13")    
        {
        	refreshTable();
        }
    });
	
	
	//退出登录
	$("#logout").click(function(){
		if(confirm("您确定要退出系统?")){
			$.ajax({
				url : "logout",
				dataType : "json",
				data : {},
				async:false,
				type : "POST",
				error : function() {
					alert("服务器访问错误");
				},
				success : function(data) {
					document.location.href="login.html";
				}
			});
		}
		
	});
	
});

function getModuleName(obj){
	var name;
	if('tyjx' == obj){
		name = '糖医精选';
	}if('tygj' == obj){
		name = '糖医工具';
	}if('tyxt' == obj){
		name = '糖医学堂';
	}if('wde' == obj){
		name = '我';
	}
	return name;
}
function refreshTable(){
	var searchUserName = $("#searchUserName").val();
	
	searchUserName = $.trim(searchUserName);
	
	var pageIndex = $("#pageIndex").val();
	if (pageIndex == null||pageIndex==""){
		pageIndex = 1;
	}
	var condition = "";
	if(searchUserName != null && searchUserName != "" ){
		condition = searchUserName;
	}
	var json = {
			module : condition,
			pageIndex : pageIndex
        };
	$.ajax({
         url: "queryWebLog",
         dataType: "json",
         data:json,
         type: "POST",
         success: function (data) {
             if (data.status == 1){
            	 var userCaseHtml = "";
            	 var userCaseList = data.data.items;
            	 if (userCaseList == null || userCaseList.length == 0){
            		 userCaseHtml = "<tr><td colspan='5' align='center'>无统计记录</td></tr>";
            	 } else {
            		 for (var index in userCaseList){
                		 var userCase = userCaseList[index];
                		 var module = getModuleName(userCase.module);
                		 var createDate = new Date(userCase.creationtime).format("yyyy-MM-dd");
                		 userCaseHtml += "<tr><td>"+userCase.uv+"</td><td>"+userCase.pv+"</td><td>"+module+"</td>";
                		 userCaseHtml += "<td>"+createDate+"</td></tr>";
                	 }
            	 }
            	 $(".trValueList").html(userCaseHtml);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
            	 $("#pageIndex").val(data.data.pageIndex);
        	     $("#pageToal").val(data.data.totalPage);
        	     $("#pageEachNum").val(data.data.limit);
             }else{
            	 $(".trValueList").html("<td colspan='5' align='center'>无用例记录" +
            	 		"</td>");
             }
             //分页
         	 var page=$("#page");
         	 var options = {
            	     currentPage :  $("#pageIndex").val(),
            	     totalPages : $("#pageToal").val(),
            	     numberOfPages : 5,
            	     onPageClicked : function(event, originalEvent, type, page){
            	    	 $("#page").hide();
             	    	$("#pageIndex").val(page);
         	    		$("#page").find("ul").addClass("pagination ").addClass("pagination-sm");
         	    		refreshTable();
             	     }
             }
         	 $("#page").bootstrapPaginator(options);
         	 $("#page").find("ul").addClass("pagination").addClass("pagination-sm");
         	$("#page").show();
         }
	});
}


Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
} 
