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
       	$("#myModalLabel").text('添加党组织信息');
       	$('#Goto').modal('show');
        $(".btn-edit-info").unbind("click");
        $(".btn-edit-info").click(function(){
        	saveUserCase();
   	    });       	
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

function checkPartyBranchName(){
	var flag = false;
	var json = {
			"partyBranchName" : $("#partyBranchName").val(),
		};
		$.ajax({
			url : "party/checkPartyBranchName",
			dataType : "json",
			async: false,
			data : json,
			type : "GET",
			error : function() {
				alert("服务器访问错误");
			},
			success : function(data) {
				if (data.isRepeat == "true") {
					alert("党组织名称已经存在，请重新输入...");
					//$("#partyBranchName").val("");
					flag = true;
				}
			}
		});
		return flag;
}

function saveUserCase(id) {
	//党支部名称
	var partyBranchName = $("#partyBranchName").val();
	//所属社区党委
	var communityPartyCommittee = $("#communityPartyCommittee").val();
	//党员人数
	var isNonPublicPartyBranch = $("#isNonPublicPartyBranch").val();
	//支部书记
	var branchSecretary = $("#branchSecretary").val();
	//备注
	var remark = $("#remark").val();
	var keyword='';
	$("#keyword input").each(function(n,obj){
		if(""!=$(obj).val()){
			keyword = keyword +$(obj).val()+',';
		}
	});
	if (null == partyBranchName || "" == $.trim(partyBranchName)) {
		alert("党组织名称不能为空");
		return false;
	}
	if (null == communityPartyCommittee || "" == $.trim(communityPartyCommittee)) {
		alert("所属社区党委不能为空");
		return false;
	}
	if (null == isNonPublicPartyBranch || "" == $.trim(isNonPublicPartyBranch)) {
		alert("是否非公企业党支部不能为空");
		return false;
	}
	if (null == branchSecretary || "" == $.trim(branchSecretary)) {
		alert("支部书记不能为空");
		return false;
	}
	if(!id && checkPartyBranchName()){
		return false;
	}
	var json = {
		"id":id,
		"partyBranchName" : partyBranchName,
		"communityPartyCommittee" : communityPartyCommittee,
		"isNonPublicPartyBranch" : isNonPublicPartyBranch,
		"branchSecretary" :branchSecretary,
		"remark":remark
	};
	$.ajax({
		url : "party/addPartyOrganization",
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
			var caseid = $(this).attr("value");
			$.ajax({
	             url: "party/getPartyOrganizationById",
	             data:{"id":caseid},
	             dataType: "json",
	             type: "POST",
	             success: function (data) {
	                 var userCase = data.data.partyOrganization;
	            	 $("#myModalLabel").text('修改党组织信息');
		           	 $("#partyBranchName").val(userCase.partyBranchName);
		           	 $("#communityPartyCommittee").val(userCase.communityPartyCommittee);
		           	 $("#isNonPublicPartyBranch option[value='"+userCase.isNonPublicPartyBranch+"']").attr("selected","selected");
		           	 $("#branchSecretary").val(userCase.branchSecretary);
		           	 $("#remark").val(userCase.remark);
	                 $('#Goto').modal('show');
	                 $(".btn-edit-info").unbind("click");
	                 $(".btn-edit-info").click(function(){
	                	 saveUserCase(userCase.id);
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
				var userCaseId = $(this).attr("value");
				$.ajax({
		             url: "party/deletePartyOrganizationById",
		             data:{"id":userCaseId},
		             dataType: "json",
		             type: "POST",
		             success: function (data) {
		                 if (data.status == 1){
		                	 var userHtml = "";
		                	 var userCaseList = data.data;
		                	 if (userCaseList == null || userCaseList.length == 0){
		                		 userCaseHtml = "<tr><td colspan='5' align='center'>无党组织记录</td></tr>";
		                	 }
		                	 $(".trValueList").html(userCaseHtml);
		                	 initButton();
		                 }else{
		                	 $(".trValueList").html("<td>无党组织信息</td>");
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
         url: "party/queryPartyOrganizationList",
         dataType: "json",
         data:json,
         type: "POST",
         success: function (data) {
             if (data.status == 1){
            	 var userCaseHtml = "";
            	 var userCaseList = data.data.items;
            	 if (userCaseList == null || userCaseList.length == 0){
            		 userCaseHtml = "<tr><td colspan='5' align='center'>无党组织记录</td></tr>";
            	 } else {
            		 for (var index in userCaseList){
                		 var userCase = userCaseList[index];
                		 var createDate = new Date(userCase.creationtime).format("yyyy-MM-dd");
                		 var updateDate = new Date(userCase.updatetime).format("yyyy-MM-dd");
                		 userCaseHtml += "<tr><td>"+userCase.partyBranchName+"</td>" +
                		 		"<td>"+userCase.communityPartyCommittee+"</td>" +
                		 		"<td>"+userCase.partyMemberNum+"</td>" +
                		 		"<td>"+userCase.branchSecretary+"</td>";
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
