
//设备序列号 详情展示 全局变量
var deviceSerialGlobal;

function showHTMLinDIV(element){
	
	var urlstr = $(element).attr("url");
	var text = $(element).text();
	var level = $(element).attr("level");
	deviceSerialGlobal = $(element).attr("deviceSerialGlobal");
	
	$.ajax({ 
		 url: urlstr,
		 type:"get",
		 dataType:"html",
		 success:function(msg){
			if(msg.indexOf("ThisIsLoginPage;")>0){
				top.location.href = 'login.html';
				return;
			}
			var html="<li><i class='icon-home home-icon'></i><a href='#'>主页</a></li>";
			if(level==2){
				var $topElement=$(element).parent().parent().prev();
				html+="<li><a href='#'>"+$topElement.text()+"</a></li>";
				html+="<li class='active'>"+text+"</li>";
			}else{
				html+="<li class='active'>"+text+"</li>";
			}
			$("#ul_breadcrumb").html(html);
		    $("#div_pageContent").html(msg);
		 },
	 	complete:function(){}
	});
}

function getUrlParam(name) {  
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象  
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数  
    if (r != null) return unescape(r[2]); return null; //返回参数值  
}

/**
 * 让jqueryUi dialog支持html title
 */
$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
	_title : function(title) {
		var $title = this.options.title || '&nbsp;';
		if (("title_html" in this.options)&& this.options.title_html == true)
			title.html($title);
		else
			title.text($title);
	}
}));

$(function() {
	
	//加载左侧菜单
	sendAjax({
		url: "index/init",
		success : function(data) {
			var funcList = data.result.funcList;
			var loginUserName = data.result.loginUser.userName;
			$("#lb_loginUserName").html(loginUserName);
			for(var i=0;i<funcList.length;i++){
	 			var func = funcList[i];
	 			/*if(func.funcId==1){
	 				var html=$("#funcs").html();
	 				$("#funcs").html(html+"<li><a href="+func.funUrl+"><i class='icon-dashboard'></i><span class='menu-text'>"+func.funName+" </span></a></li>");
	 			}else{*/
	 				if(func.funLevel==1){
		 				var html = $("#funcs").html();
		 				$("#funcs").html(html+"<li><a href='#' class='dropdown-toggle' level='1'><i class='icon-text-width'></i><span class='menu-text'> "+func.funName+" </span><b class='arrow icon-angle-down'></b></a><ul class='submenu' id='submenu_"+func.funcId+"'></ul></li>");	
		 			}else if(func.funLevel==2){
		 				var html = $("#submenu_"+func.funPid).html();
		 				$("#submenu_"+func.funPid).html(html+"<li><a href='#' onclick='showHTMLinDIV(this);' url='"+func.funUrl+"' level='2'><i class='icon-double-angle-right'></i> "+func.funName+" </a></li>");
		 			}
	 			//}
	 		}
		},
		fail:function(data){
        	alert("初始化失败");
        },
        error:function (XMLHttpRequest, textStatus, errorThrown){
            alert("网络异常");
        }
	});
	
});

/**
 * 密码修改弹出框显示
 */
function changePwdView(){
	$("#dialog-changePwdForm").dialog({
		autoOpen : false,
		height : 270,
		width : 500,
		appendTo : "#div_pageContent",
		title : "<div class='widget-header'><h4 class='smaller'><i></i>密码修改</h4></div>",
		title_html : true,
		modal : true,
		resizable : true,
		open : function() {
		},
		close : function() {
		},
		buttons : [ {
			text : "保存",
			"class" : "btn btn-primary btn-minier",
			click : function() {
				changePwd();
			}
		}, {
			text : "关闭",
			"class" : "btn btn-primary btn-minier",
			click : function() {
				$(this).dialog("close");
			}
		} ]
	});
	$("#oldPwd").val("");
	$("#newPwd").val("");
	$("#surePwd").val("");
	$("#dialog-changePwdForm").dialog("open");
	
}

/**
 * 密码修改表单验证
 */
$("#changePwdForm").validate({
	errorPlacement : function(error, element) {
		error.appendTo(element.parent());
	},
	rules : {
		oldPwd : {
			required : true,
			maxlength : 20,
			minlength : 6
		},
		newPwd : {
			required : true,
			maxlength : 20,
			minlength : 6
		},
		surePwd : {
			required : true,
			maxlength : 20,
			minlength : 6,
			equalTo : "#newPwd"
		}
	},
	messages : {
		oldPwd : {
			required : "请输入旧的密码！",
			maxlength : "密码长度不能超过{0} 位",
			minlength : "密码长度不能少于{0} 位"
		},
		newPwd : {
			required : "请输入旧的密码！",
			maxlength : "密码长度不能超过{0} 位",
			minlength : "密码长度不能少于{0} 位"

		},
		surePwd : {
			required : "请输入旧的密码！",
			maxlength : "密码长度不能超过{0} 位",
			minlength : "密码长度不能少于{0} 位",
			equalTo : "新密码必须与确认密码一致"
		}
	}
});

/**
 * 修改密码
 */
function changePwd(){
	if ($("#changePwdForm").valid()) {
		sendAjax({
			url: "changePwd",
			data : {
				"oldPwd" : $("#oldPwd").val(),
				"newPwd" : $("#newPwd").val()
			},
			success : function(data) {
				if(data.status == 200){
					alert("修改成功");
					window.location.href=webAppPath+"html/login.html";
				}
			},
			fail:function(data){
	        	alert(data.msg);
	        },
	        error:function (XMLHttpRequest, textStatus, errorThrown){
	            alert("网络异常");
	        }
		});
	}
}


