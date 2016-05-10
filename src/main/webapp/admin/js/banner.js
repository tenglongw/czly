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
	
	$(".btn-add-record").click(function(){
       	$("#myModalLabel").text('添加用例信息');
       	$('#Goto').modal('show');
        $(".btn-edit-info").unbind("click");
        $(".btn-edit-info").click(function(){
        	saveUserCase();
   	    });       	
	});
	
	$("#type").change(function (){
		var value=$(this).val();
		if(value==2){
			getHospitalList();
			$("#hospital_div").show();
		}else{
			$("#hospitalList").html("<option value='0'></option>");
			$("#hospital_div").hide();
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

function doUpload() {
	// 上传方法
	$.upload({
			// 上传地址
			url: 'banner/uploadFile', 
			// 文件域名字
			fileName: 'file', 
			// 其他表单数据
			params: {formData: 'pxblog'},
			// 上传完成后, 返回json, text
			dataType: 'json',
			// 上传之前回调,return true表示可继续上传
			onSend: function() {
					return true;
			},
			// 上传之后回调
			onComplate: function(data) {
				$("#icon").attr("src",data.data);
			}
	});
}

function saveUserCase(id) {
	var displayFlag = $("#displayFlag").val();
	var icon = $("#icon").attr("src");
	var type = $("#bannerType").val();
	if (null === icon || "" === $.trim(icon)) {
		alert("ICON不能为空");
		return false;
	}
	var title = $("#title").val();
	if (null === title || "" === $.trim(title)) {
		alert("标题不能为空");
		return false;
	}
	var description = $("#description").val();
	if (null === description || "" === $.trim(description)) {
		alert("描述不能为空");
		return false;
	}
	var json = {
		"id":id,
		"path" : icon,
		"title" : title,
		"description" : description,
		"displayFlag" : displayFlag,
		"type"	: type
	};
	$.ajax({
		url : "banner/addBanner",
		dataType : "json",
		data : json,
		type : "POST",
		error : function() {
			alert("服务器访问错误");
		},
		success : function(data) {
			$('#Goto').modal('hide');
			if (data.status == 1) {
				alert("添加成功");
			} else {
				alert(data.msg);
				 window.location.href = "login.html";
			}
			refreshTable();
			location.reload() ;
		}
	});
}

function initButton(){
	//修改
	$("a.btn-success").each(function(){
		$(this).unbind("click");
		$(this).click(function(){
			var bannerid = $(this).attr("value");
			$.ajax({
	             url: "banner/getBannerById",
	             data:{"id":bannerid},
	             dataType: "json",
	             type: "POST",
	             success: function (data) {
	                 var banner = data.data;
	            	 $("#myModalLabel").text('修改Banner信息');
		     	     $("#icon").attr("src",banner.path);
		           	 $("#title").val(banner.title);
		           	 $("#description").val(banner.description);
		           	 $("#displayFlag option[value='"+banner.displayFlag+"']").attr("selected","selected");
	                 $('#Goto').modal('show');
	                 $(".btn-edit-info").unbind("click");
	                 $(".btn-edit-info").click(function(){
	                	 saveUserCase(banner.id);
                	 });
	             }
			});
		})
	});
	// 删除
	$("a.btn-danger").each(function(){
		$(this).unbind("click");
		$(this).click(function(){
			if (confirm("确认删除？删除记录后无法恢复")){
				var bannerId = $(this).attr("value");
				$.ajax({
		             url: "banner/deleteBannerByid",
		             data:{"id":bannerId},
		             dataType: "json",
		             type: "POST",
		             success: function (data) {
		                 if (data.status == 1){
		                	 var userHtml = "";
		                	 var userCaseList = data.data;
		                	 if (userCaseList == null || userCaseList.length == 0){
		                		 userCaseHtml = "<tr><td colspan='5' align='center'>无用例记录</td></tr>";
		                	 }
		                	 $(".trValueList").html(userCaseHtml);
		                	 initButton();
		                 }else{
		                	 $(".trValueList").html("<td>无用例信息</td>");
		                 }
		                 refreshTable();
		             }
				});
			}
		});
	});
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
			searcTitle : condition,
			pageIndex : pageIndex
        };
	$.ajax({
         url: "banner/queryBannerList",
         dataType: "json",
         data:json,
         type: "POST",
         success: function (data) {
             if (data.status == 1){
            	 var userCaseHtml = "";
            	 var userCaseList = data.data.items;
            	 if (userCaseList == null || userCaseList.length == 0){
            		 userCaseHtml = "<tr><td colspan='5' align='center'>无用例记录</td></tr>";
            	 } else {
            		 for (var index in userCaseList){
                		 var userCase = userCaseList[index];
                		 var createDate = new Date(userCase.creationtime).format("yyyy-MM-dd");
                		 var updateDate = new Date(userCase.updatetime).format("yyyy-MM-dd");
                		 userCaseHtml += "<tr><td>"+userCase.title+"</td><td>"+userCase.description+"</td>";
                		 userCaseHtml += "<td>"+createDate+"</td>";
                		 userCaseHtml += "<td>"+updateDate+"</td>";
                		 userCaseHtml += "<td><div class='oper'>";
                		 userCaseHtml += "<a href='javascript:void(0)' class='btn btn-success btn-xs' value="+userCase.id+"><span class='glyphicon glyphicon-pencil'></span></a>";
                		 userCaseHtml += "<a href='javascript:void(0)' class='btn btn-danger btn-xs' value="+userCase.id+"><span class='glyphicon glyphicon-remove'></span></a></div></td></tr>";
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
             initButton();
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
