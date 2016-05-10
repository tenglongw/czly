require.config({
	baseUrl: 'admin/libs/hdx/'
});
require(['signin'], function() {
	$('.signin').signinconstruct();
	$('.signin .signin-btn').html("登 录");
	
	$('#password').bind('keypress',function(event){
	    if(event.keyCode == "13")    
	    {
	    	loginfun();
	    }
	});
	
	$('.signin .signin-btn').click(function(){
		loginfun();
    });
	
	function loginfun(){
		var un = $('#username').val();
        var pd = $('#password').val();
        if(null != un && '' != un && null != pd && '' != pd) {
            $.ajax({
                url: "checkUser",
                dataType: "json",
                data: {username : un, password : pd},
                type: "POST",
                error : function(){
                    alert("服务器访问错误");
                },
                success: function (data) {debugger;
                    if (data.status == 1){
                		window.location.href = "userInfo.html";
                    }else{
                        alert(data.msg);
                    }
                }
            });
        } else {
            if(null === un || '' === $.trim(un)){
            	$('#username').focus();
                alert("请输入用户名!");
                return;
            }
            if(null === pd || '' === $.trim(pd)){
            	$('#password').focus();
                alert("请输入密码!");
                return;
            }
        }
	}
});