require([ 'jquery', 'ge-bootstrap', 'datagrids', 'trays', 'hds', 'modules' ],
		function($) {
			$(function() {
				$.ajax({
					url : "getLoginUser",
					dataType : "json",
					data : {},
					type : "GET",
					error : function() {
						alert("服务器访问错误");
					},
					success : function(data) {
						if (data.status == 1) {
							$(".user-name").html("欢迎!&nbsp;&nbsp;"+ data.data.loginName);
						} else {
							alert(data.msg);
						}
					}
				});
				$("#logout").click(function(){
					if(confirm("您确定要退出系统?")){
						$.ajax({
							url : "logout",
							dataType : "json",
							data : {},
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
		});