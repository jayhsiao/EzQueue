var utilObj = {
		
	callAJAX: function(method, url, body){
		var response = null;
		$.ajax({
			type : method,
			url : url,
			data: body,
			contentType: "application/json",
            dataType: "json",
			async : true,
			success : function(httpResponse) {
				response = httpResponse;
			}
		});
		return response;
	}
}
