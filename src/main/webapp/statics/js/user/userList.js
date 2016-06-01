
var isUserUpdate = false;
var pageSize=8;
var updateUserId;
var queryMsg="";

$(function() {
	//初始化加载角色
	userListInit();
	
	//列表查询
	userListSelect(1);
	$("#dialog-form").dialog(
		{
			autoOpen : false,
			appendTo : "#div_pageContent",
			title : "",
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
					userAddOrUpt();
				}
			}, {
				text : "关闭",
				"class" : "btn btn-primary btn-minier",
				click : function() {
					$(this).dialog("close");
				}
			} ]
		});
	
	$('table th input:checkbox').on('click',function() {
		var that = this;
		$(this).closest('table').find(
				'tr > td:first-child input:checkbox').each(function() {
			this.checked = that.checked;
			$(this).closest('tr').toggleClass('selected');
		});
	});
	
	$("#form_userSearch").on("submit",function(){
		queryMsg = $("#userNameQuery").val();
		userListSelect(1);
		return false;
	});
	
	// 弹出框里的表单验证
	$("#myform").validate({
		errorPlacement : function(error, element) {
			error.appendTo(element.parent());
		},
		rules : {
			userName : {
				required : true,
				maxlength : 20
			},
			passWord : {
				required : true,
				maxlength : 20,
				minlength : 6
			}

		},
		messages : {
			userName : {
				required : "必填",
				maxlength : "用户名长度不能超过{0} 位"
			},
			passWord : {
				required : "必填",
				maxlength : "密码长度不能超过{0} 位",
				minlength : "密码长度不能少于{0} 位"
			}

		}
	});
});

/**
 * 用户列表查询
 * 
 * @param pageCurr
 *            当前页
 */
function userListSelect(pageCurr) {
	var senddata = {
		"userName" : queryMsg,
		"size" : pageSize,
		"pageId" : pageCurr
	};
	sendAjax({
		url : "user/page",
		data : senddata,
		success : function(data) {
			var template=$("#tp_userList").html();
			$("#tb_userList tbody").html(doT.template(template)(data.result));
			laypage({
				curr : pageCurr,
				cont : $('#page3'), // 容器。值支持id名、原生dom对象，jquery对象,
				pages : data.totalPage, // 总页数
				skip : true, // 是否开启跳页
				skin : '#1d7ad9',
				groups : data.totalPage < 5 ? data.totalPage : 5, // 连续显示分页数
				jump : function(obj, first) {
					// 得到了当前页，用于向服务端请求对应数据
					if (!first) { // 点击跳页触发函数自身，并传递当前页：obj.curr
						userListSelect(obj.curr);
					}
					$('#countbox').html(
							'目前正在第' + obj.curr + '/' + obj.pages + '页，一共有：'
									+ data.totalCount + '条');
					$("#boxid").prop("checked", false);

				}
			});
		}
	});
}

/**
 * 新增用户
 */
function addUser() {
	document.getElementById("userNameAndPwd").style.display = "block";
	$("#userName").val("");
	$("#passWord").val("");
	$("#sl_rightRoleList").html("");
	$("#sl_leftRoleList").html($("#sl_roleList").html());
	$("#dialog-form").dialog("open");
	$("#dialog-form").dialog("option", "title", "<div class='widget-header'><h4 class='smaller'><i></i>新增用户</h4></div>");
	$("#dialog-form").dialog({
	      height: 470,
	      width: 500
	    });
	isUserUpdate = false;
}

function resetPwd(userId){
	if (confirm("确认要重置用户登录密码吗？")) {
		sendAjax({
			url : "resetPwd",
			data : {
				"userId" : userId
			},
			success : function(data) {
				if(data.status = 200){
					alert("重置成功, 默认密码为 123456_fnd");
				}
			},
			fail:function(data){
	        	alert("重置失败");
	        },
	        error:function (XMLHttpRequest, textStatus, errorThrown){
	            alert("网络异常");
	        }
		});
	}
}

/**
 * 用户角色修改
 * @param userId
 * @param userName
 * @param passWord
 */
function updateUser(userId, userName, passWord) {
	document.getElementById("userNameAndPwd").style.display = "none";

	$("#sl_leftRoleList").html($("#sl_roleList").html());
	$("#sl_rightRoleList").html("");
	userupdateId = userId;
	sendAjax({
		url : "user/init",
		data : {
			"userId" : userId
		},
		success : function(data) {
			var roles = data.result;
			for(var i=0;i<roles.length;i++){
				var roleId = roles[i].roleId;
				var roleName = roles[i].roleName;
				$("#sl_rightRoleList").append("<option value='" + roleId + "'>" + roleName+ "</option>");
				$("#sl_leftRoleList option[value='" + roleId + "']").remove();
			}
		}
	});
	$("#dialog-form").dialog("open");
	$("#dialog-form").dialog("option", "title", "<div class='widget-header'><h4 class='smaller'><i></i>角色修改</h4></div>");
	$("#dialog-form").dialog({
	      height: 400,
	      width: 500
	    });
	isUserUpdate = true;
	updateUserId = userId;
}

/**
 * 删除用户
 */
function delSingleUser(userId) {
	if (confirm("确认要删除该用户吗？")) {
		sendAjax({
			url : "user/del",
			data : {
				"userId" : userId
			},
			success : function(data) {
				alert("删除成功");
				userListSelect(1);
			},
			fail:function(data){
	        	alert("删除失败");
	        },
	        error:function (XMLHttpRequest, textStatus, errorThrown){
	            alert("网络异常");
	        }
		});
	} else {
		return;
	}
}


/**
 * 确认新增或者修改用户
 */
function userAddOrUpt() {
	if ($("#myform").valid()) {
		var roleIds = "";
		var right = $("#sl_rightRoleList option");
		for (var i = 0; i < right.length; i++) {
			var opt = right.eq(i);
			roleIds = roleIds + opt.val() + ",";
		}
		if (roleIds.length > 0) {
			roleIds = roleIds.substring(0, roleIds.length - 1);
		} else {
			alert("必须选择角色");
			return;
		}
		if(isUserUpdate){
			sendAjax({
				url : "user/upt",
				data : {
					"roleIds" : roleIds,
					"userId" : updateUserId
				},
				success : function(data) {
					$("#dialog-form").dialog("close");
					alert("更新成功");
					userListSelect(1);
				},
				fail:function(data){
		        	alert(data.msg);
		        },
		        error:function (XMLHttpRequest, textStatus, errorThrown){
		            alert("网络异常");
		        }
			});
		} else {
			sendAjax({
				url : "user/add",
				data : {
					"roleIds" : roleIds,
					"userName" : $("#userName").val(),
					"passWord" : $("#passWord").val()
				},
				success : function(data) {
					$("#dialog-form").dialog("close");
					alert("新增成功");
					userListSelect(1);
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
}

/**
 * 用户页面初始化,加载角色数据
 */
function userListInit() {
	sendAjax({
		url : "user/init",
		success : function(data) {
			var roles = data.result;
			for(var i=0;i<roles.length;i++){
				$("#sl_roleList").append("<option value='"+roles[i].roleId+"'>"+roles[i].roleName+"</option>");
				$("#sl_leftRoleList").append("<option value='"+roles[i].roleId+"'>"+roles[i].roleName+"</option>");
			}
		},
		fail:function(data){
        	alert("初始化失败");
        },
        error:function (XMLHttpRequest, textStatus, errorThrown){
            alert("网络异常");
        }
	});
}

/**
 * 修改面板中角色从左移到右
 */
function leftToRight(){
  var left = $("#sl_leftRoleList option");
  for(var i=0;i<left.length;i++){
    var opt = left.eq(i);
    if(opt.prop("selected")){
      $("#sl_rightRoleList").append("<option value='"+opt.val()+"'>"+opt.text()+"</option>");
      $("#sl_leftRoleList option[value='"+opt.val()+"']").remove();
    }
  }
}
/**
 * 修改面板中角色从右移到左
 */
function rightToLeft(){
  var right = $("#sl_rightRoleList option");
  for(var i=0;i<right.length;i++){
    var opt = right.eq(i);
    if(opt.prop("selected")){
      $("#sl_leftRoleList").append("<option value='"+opt.val()+"'>"+opt.text()+"</option>");
      $("#sl_rightRoleList option[value='"+opt.val()+"']").remove();
    }
  }
}