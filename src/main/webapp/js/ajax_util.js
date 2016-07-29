var ajaxUtilObj = {
		
	GET:    "GET",	
	POST:   "POST",
	DELETE: "DELETE",
	PUT:    "PUT",
	PATCH:  "PATCH",
	
	callJsonAJAX: function(method, url, body, disabledObj){
		return $.ajax({
			type : method,
			url : url,
			data: body,
			contentType: "application/json",
			async : true,
			beforeSend: function(){
				if(disabledObj){
					$(disabledObj).attr("disabled", true);
					$(disabledObj).addClass("disabled");
				}
			},
			complete: function(){
				if(disabledObj){
					$(disabledObj).attr("disabled", false);
					$(disabledObj).removeClass("disabled");
				}
			}
		});
	}, 
	
	callHtmlAJAX: function(url){
		return $.ajax({
			type : ajaxUtilObj.GET,
			url : url,
			contentType: "application/json",
            dataType: "html",
			async : true,
			beforeSend: function(){
				commonObj.blockUI();
			},
			complete: function(){
				commonObj.unblockUI();
			}
		});
	}
}
