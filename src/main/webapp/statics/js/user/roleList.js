var isRoleNameUpdate = false;
var pageSize=8;
var roleList=",";
var queryMsg="";

$(function(){
	
	//初始化加载功能
	functionListInit();
	
	roleListSelect(1);
	
	$("#dialog-form").dialog(
		{
			autoOpen : false,
			height : 180,
			width : 500,
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
					roleAddOrUpt();
				}
			}, {
				text : "关闭",
				"class" : "btn btn-primary btn-minier",
				click : function() {
					$(this).dialog("close");
				}
			} ]
		});
	
	$("#dialog-roleFuncForm").dialog(
			{
				autoOpen : false,
				appendTo : "#div_pageContent",
				title : "<div class='widget-header'><h4 class='smaller'><i></i>权限列表</h4></div>",
				title_html : true,
				modal : true,
				resizable : true
			});
	
	$("#form_roleSearch").on("submit",function(){
		queryMsg=$("#roleNameQuery").val();
		roleListSelect(1);
		return false;
	});
	
	// 弹出框里的表单验证
	$("#myform").validate({
		errorPlacement : function(error, element) {
			error.appendTo(element.parent());
		},
		rules : {
			roleName : {
				required : true,
				maxlength : 50
			}
		},
		messages : {
			roleName : {
				required : "必填",
				maxlength : "最多50个字符"
			}
		}
	});
});

/**
 * 角色列表查询
 * @param pageCurr 当前页
 */
function roleListSelect(pageCurr) {
	var senddata = {
		"roleName" : queryMsg,
		"size" : pageSize,
		"pageId" : pageCurr
	};
	sendAjax({
		url : "role/page",
		data : senddata,
		success : function(data) {
			var template=$("#tp_roleList").html();
			$("#tb_roleList tbody").html(doT.template(template)(data.result));
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
						roleListSelect(obj.curr);
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
 * 新增角色
 */
function addRole() {
	$("#dialog-form").dialog("open");
	$("#dialog-form").dialog("option","title", "<div class='widget-header'><h4 class='smaller'><i></i>新增角色</h4></div>");
	isRoleNameUpdate = false;
}

/**
 * 修改角色名称
 * @param userId
 * @param userName
 * @param passWord
 */
function uptRoleName(roleId, roleName) {
	$("#roleId").val(roleId);
	$("#roleName").val(roleName);
	$("#dialog-form").dialog("open");
	$("#dialog-form").dialog("option", "title", "<div class='widget-header'><h4 class='smaller'><i></i>角色名称修改</h4></div>");
	isRoleNameUpdate = true;
}

/**
 * 角色删除
 */
function delSingleRole(roleId) {
	if (confirm("确认要删除吗？")) {
		sendAjax({
			url : "role/del",
			data : {
				"roleId" : roleId
			},
			success : function(data) {
				alert("删除成功");
				roleListSelect(1);
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
 * 确认新增或者修改角色
 */
function roleAddOrUpt() {
	if ($("#myform").valid()) {
		if(isRoleNameUpdate){
			sendAjax({
				url : "role/upt",
				data : {
					"roleId" : $("#roleId").val(),
					"roleName" : $("#roleName").val()
				},
				success : function(data) {
					$("#dialog-form").dialog("close");
					alert("修改成功");
					roleListSelect(1);
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
				url : "role/add",
				data : {
					"roleName" : $("#roleName").val()
				},
				success : function(data) {
					$("#dialog-form").dialog("close");
					alert("新增成功");
					roleListSelect(1);
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
 * 角色页面初始化,加载功能数据
 */
function functionListInit() {
	sendAjax({
		url : "role/init",
		success : function(data) {
			 var func=data.result;
			 for(var i=0;i<func.length;i++){
				 if(func[i].funLevel==1){
					 $("#tb_roleFunc").append("<tr id='tr"+func[i].funcId+"' ><td colspan=2><input type='checkbox' value='"+func[i].funcId+"' id='ck_menu_top_"+func[i].funcId+"' onclick='selectOrNot(this,"+func[i].funcId+")'>&nbsp;"+func[i].funName+"</td></tr>");
					 $("#tb_roleFunc").append("<tr><td colspan=2></td></tr>");
				 }else if(func[i].funLevel==2){
					 var $tr;
					 if(func[i].funSeqNo>1){
						 $tr=$("#tr"+func[i].funPid+"_"+(func[i].funSeqNo-1));
					 }else{
						 $tr=$("#tr"+func[i].funPid);
					 }
					 $tr.after("<tr id='tr"+func[i].funPid+"_"+func[i].funSeqNo+"'><td style='width:25%'><input type='checkbox' value='"+func[i].funcId+"' parent='"+func[i].funPid+"' id='ck_menu_"+func[i].funcId+"' onclick='selectOrNot(this,"+func[i].funcId+")'>&nbsp;"+func[i].funName+"</td><td id='td"+func[i].funcId+"'></td></tr>");
				}else if(func[i].funLevel==3){
					 var $td=$("#td"+func[i].funPid);
					 $td.append("<div style='width:95px;float:left;text-align: left'><input type='checkbox' value='"+func[i].funcId+"' id='ck_menu_"+func[i].funPid+"_"+func[i].funcId+"' parent='"+func[i].funPid+"' onclick='selectOrNot(this,"+func[i].funcId+")'>&nbsp;"+func[i].funName+"</div>");
				 	 if(func[i].funSeqNo%4==0){
				 	 	$td.append("<div style='width:100%;height:5px;float:left'><br></div>");
				 	 }
				}
			 }
			 $("#dialog-roleFuncForm").dialog({
			      autoOpen: false,
			      height: 400,
			      width: 600,
			      modal: true,
			      appendTo : "#div_pageContent",
			      buttons: null
			    });
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
 * 修改角色权限
 * @param roleId
 */
function editRoleFunction(roleId){
	sendAjax({
		url : "role/view",
		data:{"roleId":roleId},
		success : function(data) {
			roleList=",";
			$("input[type=checkbox][id^=ck_menu_]").prop("checked",false);
			var list=data.result;
			for(var i=0;i<list.length;i++){
				var obj = list[i];
				roleList += obj.funcId+",";
				if(obj.funLevel == 3){
					$("#ck_menu_"+obj.funPid+"_"+obj.funcId).prop("checked",true);
				}else if(obj.funLevel == 2){
					$("#ck_menu_"+obj.funcId).prop("checked",true);
				}else if(obj.funLevel == 1){
					$("#ck_menu_top_"+obj.funcId).prop("checked",true);
				}
			}
			
			$("#dialog-roleFuncForm").dialog("open");
			$("#dialog-roleFuncForm").dialog({
				buttons : [ {
					text : "保存",
					"class" : "btn btn-primary btn-minier",
					click : function() {
						saveRole(roleId);
					}
				}, {
					text : "关闭",
					"class" : "btn btn-primary btn-minier",
					click : function() {
						$(this).dialog("close");
					}
				} ]
			});
		},
		fail:function(data){
        	alert(data.msg);
        },
        error:function (XMLHttpRequest, textStatus, errorThrown){
            alert("网络异常");
        }
	});
	
}

/**
 * 功能选择按钮
 * @param obj
 * @param id
 */
function selectOrNot(obj,id){
	  var checked = false;
	  if($(obj).prop("checked")){
		  checked = true;
	  }
	  if(checked){
		  if(roleList.indexOf(","+id+",", 0)<0){
			  roleList=roleList+id+",";
		  }
	  }else{
		  if(roleList.indexOf(","+id+",", 0)>-1){
			  roleList=roleList.replace(","+id+",", ",");
		  }
	  }
}

/**
 * 权限保存
 * @param roleId
 */
function saveRole(roleId){
	  var menuList="";
	  if(roleList.length>2){
		  menuList=roleList.substr(1, roleList.length-2);
	  }
	  sendAjax({
		    url:"role/editRoleFunc",
		    data:{"roleId":roleId,"menuIds":menuList},
			success : function(data) {
				$("#dialog-roleFuncForm").dialog("close");
				alert("修改成功");
			},
			fail:function(data){
	        	alert(data.msg);
	        },
	        error:function (XMLHttpRequest, textStatus, errorThrown){
	            alert("网络异常");
	        }
		});
}