var ajaxUtilObj = {
		
	GET:    "GET",	
	POST:   "POST",
	DELETE: "DELETE",
	PUT:    "PUT",
	PATCH:  "PATCH",
	
	callAJAX: function(method, url, body, returnMethod){
		$.ajax({
			type : method,
			url : url,
			data: body,
			contentType: "application/json",
            dataType: "json",
			async : true,
			success : function(httpResponse) {
				console.log(httpResponse);
				returnMethod(httpResponse);
			}
		});
	}
}
