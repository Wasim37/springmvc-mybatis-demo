
$(function(){
	deviceDetailView(deviceSerialGlobal);
	
	$("#dialog-form").dialog(
		{
			autoOpen : false,
			height : 530,
			width : 750,
			appendTo : "#div_pageContent",
			title : "",
			title_html : true,
			modal : true,
			resizable : true,
			open : function() {

			},
			close : function() {

			},
			buttons : [{
				text : "关闭",
				"class" : "btn btn-primary btn-minier",
				click : function() {
					$(this).dialog("close");
				}
			} ]
		});
});


/**
 * 设备详情展示
 * @param deviceSerial
 */
function deviceDetailView(deviceSerial) {
	var senddata = {
		"deviceSerial" : deviceSerial
	};
	sendAjax({
		url : "deviceStatus/detailView",
		data : senddata,
		success : function(data) {
			var template=$("#tp_detailList").html();
			$("#tb_detailList tbody").html(doT.template(template)(data.result));
		}
	});
}

//折线图展示
var type;
function staticsDataView(key){
	type = key;
	$.ajax({
		url : "device/chartPage.html",
		type : 'GET',
		data : "",
		dataType : 'html',
		success : function(msg) {
			$('#content').html(msg);
			$("#dialog-form").dialog("open");
			$("#dialog-form").dialog("option","title", "<div class='widget-header'><h4 class='smaller'><i></i>图表展示</h4></div>");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("网络繁忙") ;     
		},
		complete : function() {
			//alert('') ;
		}
	});
}

function back(){
	histy.back(1);
}