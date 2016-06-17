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
       	$("#myModalLabel").text('添加用户信息');
       	$("#userId").val("0");
       	$("#loginName").val("");
       	$("#userName").val("");
        $("#userPwd").val("");
       	$("#cUserPwd").val("");
        $("#type").val("2");//默认普通用户
        getHospitalList();//获取医院列表
       	$('#Goto').modal('show');
        $(".btn-edit-info").unbind("click");
        $(".btn-edit-info").click(function(){
            saveUser(0);
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

function saveUser(id){
	var loginName = $("#loginName").val();
    var userName = $("#userName").val();
    var type=$("#type").val();
    var hospitalId=$("#hospitalList").val();
    if (null === loginName || "" === $.trim(loginName)){
   		alert("登录名不能为空");
   	    return false;
    } 
    var userPwd = $("#userPwd").val();
    if (null === userPwd || "" === $.trim(userPwd)){
   	    alert("登录密码不能为空");
   	    return false;
    } 
    var cUserPwd = $("#cUserPwd").val();
    if (null === cUserPwd || "" === $.trim(cUserPwd) || ($.trim(userPwd) != $.trim(cUserPwd))){
   	    alert("2次密码输入不一致，请重新输入！");
   	    return false;
    } 
    var json = {
    		 userId : id,
   		 	 loginName : loginName,
   		 	 userName : userName,
   		 	 userPwd : userPwd
               };
    $.ajax({
            url: "checkLoginName",
            dataType: "json",
            data: {loginName : loginName, userId : id},
            type: "POST",
            error : function(){
           	 alert("服务器访问错误");
            },
            success: function (data) {
           	 if (data.status == 1){
           		 $.ajax({
       	             url: "addUser",
       	             dataType: "json",
       	             data: json,
       	             type: "POST",
       	             error : function(){
       	            	 alert("服务器访问错误");
       	             },
       	             success: function (data) {
       	            	 $('#Goto').modal('hide');
       	            	 if (data.status == 1){
       	            		 alert("添加成功");
       	            	 }else{
       	            		 alert(data.msg);
       	            	 }
       	            	 refreshTable();
       	             }
                 });
           	 }else{
           		 alert("登录名已存在，请重新输入！");
           	 }
            }
     });
}

function initButton(){
	//修改
	$("a.btn-success").each(function(){
		$(this).unbind("click");
		$(this).click(function(){
			var userId = $(this).attr("value");
			$.ajax({
	             url: "getUserById",
	             data:{"id":userId},
	             dataType: "json",
	             type: "POST",
	             success: function (data) {
	                 var user = data.data;
	            	 $("#myModalLabel").text('修改用户信息');
	            	 $("#userId").val(user.id);
	            	 $("#loginName").val(user.loginName);
		     	     $("#userName").val(user.userName);
		     	     $("#userPwd").val(user.passwd);
		     	     $("#cUserPwd").val(user.passwd);
		     	     $("#type option[value='"+user.type+"']").attr("selected","selected");
		     	      if(user.type==2){
		     	    	getTeamHospital(userId);
		    			$("#hospitalList option[value='"+user.hopitialId+"']").attr("selected","selected");
		    			$("#hospital_div").show();
		    	    	}else{
		    			$("#hospitalList").html("<option value='0'></option>");
		    			$("#hospital_div").hide();
		    		  }
	                 $('#Goto').modal('show');
	                 $(".btn-edit-info").unbind("click");
	                 $(".btn-edit-info").click(function(){
	                	 saveUser(user.id);
                	 });
	             }
			});
		})
	});
	//删除
	/*$("a.btn-danger").each(function(){
		$(this).unbind("click");
		$(this).click(function(){
			if (confirm("确认删除？删除记录后无法恢复")){
				var userId = $(this).attr("value");
				$.ajax({
		             url: "deleteUserAndUserHospital",
		             data:{"id":,"hospitalId":hospitalId}
		             dataType: "json",
		             type: "POST",
		             success: function (data) {
		                 if (data.status == 1){
		                	 var userHtml = "";
		                	 var userList = data.data;
		                	 if (userList == null || userList.length == 0){
		                		 userHtml = "<tr><td colspan='5' align='center'>无用户记录</td></tr>";
		                	 }
		                	 $(".trValueList").html(userHtml);
		                	 initButton();
		                 }else{
		                	 $(".trValueList").html("<td>无用户信息</td>");
		                 }
		                 refreshTable();
		             }
				});
			}
		});
	});*/
}

function deleteUser(userId,hospitalId){
	if (confirm("确认删除？删除记录后无法恢复")){
		//var userId = $(this).attr("value");
		$.ajax({
             url: "deleteUserAndUserHospital",
             data:{"id":userId,"hospitalId":hospitalId},
             dataType: "json",
             type: "POST",
             success: function (data) {
                 if (data.status == 1){
                	 var userHtml = "";
                	 var userList = data.data;
                	 if (userList == null || userList.length == 0){
                		 userHtml = "<tr><td colspan='5' align='center'>无用户记录</td></tr>";
                	 }
                	 $(".trValueList").html(userHtml);
                	 initButton();
                 }else{
                	 $(".trValueList").html("<td>无用户信息</td>");
                 }
                 refreshTable();
             }
		});
	}
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
			searchUserName : condition,
			pageIndex : pageIndex
        };
	$.ajax({
         url: "user/list",
         dataType: "json",
         data:json,
         type: "POST",
         success: function (data) {
             if (data.status == 1){
            	 var userHtml = "";
            	 var userList = data.data.items;
            	 if (userList == null || userList.length == 0){
            		 userHtml = "<tr><td colspan='5' align='center'>无用户记录</td></tr>";
            	 } else {
            		 for (var index in userList){
                		 var user = userList[index];
                		 var createDate = new Date(user.creationtime).format("yyyy-MM-dd");
                		 var updateDate = new Date(user.updatetime).format("yyyy-MM-dd");
                		 userHtml += "<tr><td>"+user.loginName+"</td><td>"+user.userName+"</td>";
                		 userHtml += "<td>"+createDate+"</td>";
                		 userHtml += "<td>"+updateDate+"</td>";
                		 userHtml += "<td><div class='oper'>";
                		 userHtml += "<a href='javascript:void(0)' class='btn btn-success btn-xs' value="+user.id+"><span class='glyphicon glyphicon-pencil'></span></a>";
                		 userHtml += "<a href='javascript:void(0)' class='btn btn-danger btn-xs' value="+user.id+"><span class='glyphicon glyphicon-remove'></span></a></div></td></tr>";
                	 }
            	 }
            	 $(".trValueList").html(userHtml);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
            	 $("#pageIndex").val(data.data.pageIndex);
        	     $("#pageToal").val(data.data.totalPage);
        	     $("#pageEachNum").val(data.data.limit);
             }else{
            	 $(".trValueList").html("<td colspan='5' align='center'>无用户记录" +
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


function getHospitalList(){
	$.ajax({
		 url: "getHospitalList",
         dataType: "json",
         type: "GET",
         async:false,
         error:function (){
        	alert("服务访问错误!"); 
         },
         success:function (data){
           if(data.status==1){
        	   var hospitals=data.data;
        	   var len=hospitals.length;
        	   var optionHTML="";
        	   for(var i=0;i<len;i++){
        		   optionHTML+="<option value='"+hospitals[i].id+"'>"+hospitals[i].name+"</option>";
        	   }
        	   $("#hospitalList").html(optionHTML);
           }
         }
	});
}

function getTeamHospital(userId){
	$.ajax({
		 url: "getTeamHospital",
		 data:{"userId":userId},
         dataType: "json",
         type: "POST",
         async:false,
         error:function (){
        	alert("服务访问错误!"); 
         },
         success:function (data){
           if(data.status==1){
        	   var hospitals=data.data;
        	   var len=hospitals.length;
        	   var optionHTML="";
        	   for(var i=0;i<len;i++){
        		   if(!(hospitals[i].userId!=null&&hospitals[i].userId>0)){
        			   optionHTML+="<option value='"+hospitals[i].id+"'>"+hospitals[i].name+"</option>";
        		    }
        	   }
        	   $("#hospitalList").html(optionHTML);
           }
         }
	});
}

function getAssignHospitalList(userId){
	$.ajax({
		 url: "getAssginHospital",
		 data:{"userId":userId},
         dataType: "json",
         type: "POST",
         async:false,
         error:function (){
        	alert("服务访问错误!"); 
         },
         success:function (data){
           if(data.status==1){
        	   var hospitals=data.data;
        	   var len=hospitals.length;
        	   var optionHTML="";
        	   var selectedHTML="";
        	   for(var i=0;i<len;i++){
        		   if(hospitals[i].userId!=null&&hospitals[i].userId>0){
        			   selectedHTML+="<li name='"+hospitals[i].name+"'  value='"+hospitals[i].id+"'> <input type='checkbox'  name='checkbox' id='"+hospitals[i].id+"' onclick='selectedHospital(this)' checked='true'></input>&nbsp;"+hospitals[i].name+"</li>";
        		    }else{
        		      optionHTML+="<li  name='"+hospitals[i].name+"' value='"+hospitals[i].id+"'> <input type='checkbox' name='checkbox' id='"+hospitals[i].id+"' onclick='selectedHospital(this)'></input>&nbsp;"+hospitals[i].name+"</li>";  
        		   }
        		  
        	   }
        	   $("#assignHospitalList").html(optionHTML);
        	   $("#selectedHospitalList").html(selectedHTML);
           }
         }
	});
}

//分配医院
function assignHospital(userId){
	$("#hospitalModalLabel").text('分配医院信息');
	getAssignHospitalList(userId);
   	$('#hospital_modal').modal('show');
    $("#saveSelectedHospital").unbind("click");
    $("#saveSelectedHospital").click(function(){
    	saveSelectedHospital(userId);
	    });
}

function selectedHospital(obj){
		if(obj.checked==true){
			$(obj).parent("li").remove();
			$("#selectedHospitalList").append("<li name='"+$(obj).parent("li").attr("name")+"' value='"+$(obj).attr("id")+"'> <input type='checkbox'  name='checkbox' id='"+$(obj).attr("id")+"' onclick='selectedHospital(this)' checked='true'></input>&nbsp;"+$(obj).parent("li").attr("name")+"</li>");
		  }else{
		    $(obj).parent("li").remove();
		    $("#assignHospitalList").append("<li name='"+$(obj).parent("li").attr("name")+"' value='"+$(obj).attr("id")+"'> <input type='checkbox'  name='checkbox' id='"+$(obj).attr("id")+"' onclick='selectedHospital(this)'></input>&nbsp;"+$(obj).parent("li").attr("name")+"</li>");
		}
}

function getSelectedHospitalIds(){
	var objArry=new Array();
	var boxs=$("#selectedHospitalList input[type='checkbox']");
	var len=boxs.length;
	for(var i=0;i<len;i++){
		if(boxs[i].checked==true){
			if($(boxs[i]).attr("id")!=null&&$(boxs[i]).attr("id")!=""){
				objArry.push($(boxs[i]).attr("id"));
				continue;
			}
		  }
	}
	return objArry;
}

function saveSelectedHospital(userId){
	var selectedHospitalList=getSelectedHospitalIds();
	/*if(selectedHospitalList==null||selectedHospitalList.length==0){
		alert("请选择医院!");
		return false;
	}*/
	var hospitalIds=selectedHospitalList.toString();
	$.ajax({
		url: "UserHospitalRelationship/assignUserHospital",
		data:{"userId":userId,"hospitalIds":hospitalIds},
        dataType: "json",
        type: "POST",
        async:false,
        error:function (){
       	alert("服务访问错误!"); 
        },
        success:function (data){
         if(data.status==1){
        	 $('#hospital_modal').modal('hide');
        	 alert(data.msg);
            }else{
             alert(data.msg);	
             window.location.href = "login.html";
          }
          refreshTable();
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
