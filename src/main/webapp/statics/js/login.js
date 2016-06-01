
/**
 * 用户登录
 */
function login(){
	if ($("#password").val()=="" && $("#username").val()==""){
       alert("请输入账户和密码");
    	return;
    }
    if ($("#password").val()==""){
       alert("请输入密码");
    	return;
    }

    $.ajax({
    	url:webAppPath+"/login",
        type:"POST",
        data:{"userName":$("#username").val(), "passWord":$("#password").val()},
        dataType:"json",
        success:function(data){
            var status=data.status;
            if(status==200){
                window.location.href=webAppPath+"html/index.html";
            }else{
                alert(data.msg);
            }                
        },
        error:function (XMLHttpRequest, textStatus, errorThrown){
            alert("网络异常");
        }
  });
}

/**
 * 退出登录
 */
function logout() {
	if (confirm("你确实要退出吗？？")) {
		$.ajax({
			url:webAppPath+"/logout",
			type:"POST",
			success : function(msg) {
				window.location.href = webAppPath+"html/login.html";// 页面转到登录页
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("网络异常");
			}
		});
	}
}

/**
 * 按enter键提交登录
 */
function keyLogin(){  
	if (window.event.keyCode == 13){
		document.getElementById("submit").click(); //注意ID不是login 
	}
}

	
