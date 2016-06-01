var pageSize=8;
var queryMsg="";

$(function(){
	
	devicePartListSelect(1);
	
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
					//roleAddOrUpt();
				}
			}, {
				text : "关闭",
				"class" : "btn btn-primary btn-minier",
				click : function() {
					$(this).dialog("close");
				}
			} ]
		});
	
	$("#form_partSearch").on("submit",function(){
		queryMsg = $("#partSerialQuery").val();
		devicePartListSelect(1);
		return false;
	});
	
	 //实例化一个plupload上传对象
    var uploader = new plupload.Uploader({
        browse_button : 'browse', //触发文件选择对话框的按钮，为那个元素id
        url : '../devicePart/upload', //服务器端的上传页面地址
        flash_swf_url : '../assets/js/plupload-2.1.2/js/Moxie.swf', //swf文件，当需要使用swf方式进行上传时需要配置该参数
        silverlight_xap_url : '../assets/js/plupload-2.1.2/js/Moxie.xap' //silverlight文件，当需要使用silverlight方式进行上传时需要配置该参数
    });    

    //在实例对象上调用init()方法进行初始化
    uploader.init();
    uploader.bind('FileUploaded',function(uploader,file,responseObject){
    	alert(responseObject.response);
    	devicePartListSelect(1);
    });

    //最后给"开始上传"按钮注册事件
    document.getElementById('start_upload').onclick = function(){
        uploader.start(); //调用实例对象的start()方法开始上传文件，当然你也可以在其他地方调用该方法
    };
});

/**
 * 设备配件列表查询
 * @param pageCurr 当前页
 */
function devicePartListSelect(pageCurr) {
	var senddata = {
		"partSerial" : queryMsg,
		"size" : pageSize,
		"pageId" : pageCurr
	};
	sendAjax({
		url : "devicePart/page",
		data : senddata,
		success : function(data) {
			var template=$("#tp_partList").html();
			$("#tb_partList tbody").html(doT.template(template)(data.result));
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
						devicePartListSelect(obj.curr);
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
