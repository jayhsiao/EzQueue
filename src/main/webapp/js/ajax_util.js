var ajaxUtilObj = {
		
	GET:    "GET",	
	POST:   "POST",
	DELETE: "DELETE",
	PUT:    "PUT",
	PATCH:  "PATCH",
	
	callAJAX: function(method, url, body, btnObj, spanObj, returnMethod){
		$.ajax({
			type : method,
			url : url,
			data: body,
			contentType: "application/json",
            dataType: "json",
			async : true,
			beforeSend: function(){
				if(btnObj){
					$(btnObj).attr("disabled", true);
					$(btnObj).addClass("disabled");
				}
				if(spanObj){
					$(spanObj).show();
				}
			},
			complete: function(){
				if(btnObj){
					$(btnObj).attr("disabled", false);
					$(btnObj).removeClass("disabled");
				}
				if(spanObj){
					$(spanObj).hide();
				}
			},
			success : function(httpResponse) {
				console.log(httpResponse);
				returnMethod(httpResponse);
			}
		});
	}
}
