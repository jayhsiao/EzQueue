var ajaxUtilObj = {
		
	GET:    "GET",	
	POST:   "POST",
	DELETE: "DELETE",
	PUT:    "PUT",
	PATCH:  "PATCH",
	
	login: function(url){
		return $.ajax({
			type : ajaxUtilObj.POST,
			url : url,
			contentType: "application/json",
			async : true,
			beforeSend: function(xhr){
				var basic = btoa(facebookObj.id + ":" + facebookObj.password);
				xhr.setRequestHeader("Authorization", "Basic " + basic);
			}
		});
	}, 
	
	callJsonAJAX: function(method, url, body){
		return $.ajax({
			type : method,
			url : url,
			data: body,
			contentType: "application/json",
			async : true,
			beforeSend: function(xhr){
				commonObj.blockUI();
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
			}
		});
	}
}
