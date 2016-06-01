function sendAjax(arrayParam) {
	var urlStr=arrayParam["url"]==null?"":arrayParam["url"];
	var sendData=arrayParam["data"]==null?null:arrayParam["data"];
	var sendType=arrayParam["type"]==null?"POST":arrayParam["type"];
	var msgType=arrayParam["dataType"]==null?"json":arrayParam["dataType"];
	var successFunc=arrayParam["success"]==null?null:arrayParam["success"];
	var failFunc=arrayParam["fail"]==null?function(data){alert(data.msg);}:arrayParam["fail"];
	var completeFunc=arrayParam["complete"]==null?null:arrayParam["complete"];
	var errorFunc=arrayParam["error"]==null?null:arrayParam["error"];
	var asyncFlag=arrayParam["async"]==null?true:arrayParam["async"];
	var traditionalFlag=arrayParam["traditional"]==null?false:arrayParam["traditional"];
    $.ajax({
            url: webAppPath+urlStr,
            type: sendType,
            async: false,
            data: sendData,
            dataType: msgType,
            async: asyncFlag,
            traditional:traditionalFlag,
            success: function(data) {
            	var status=data.status;
                if(status==200){
                	if(successFunc!=null){
                		successFunc(data);
                	}
                }else{
                	if(failFunc!=null){
                		failFunc(data);
                	}
                }
            },
          error: errorFunc,
          complete: completeFunc
     });
}

function  setFormDisable(formId,isDisabled){
	$("form[id='"+formId+"'] :text").attr("disabled",isDisabled);  
	$("form[id='"+formId+"'] textarea").attr("disabled",isDisabled);  
	$("form[id='"+formId+"'] select").attr("disabled",isDisabled);  
	$("form[id='"+formId+"'] :radio").attr("disabled",isDisabled);  
	$("form[id='"+formId+"'] :checkbox").attr("disabled",isDisabled); 
}