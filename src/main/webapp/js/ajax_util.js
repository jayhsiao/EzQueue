var ajaxUtilObj = {
		
	GET:    "GET",	
	POST:   "POST",
	DELETE: "DELETE",
	PUT:    "PUT",
	PATCH:  "PATCH",
	
	registration: function(data){
		return $.ajax({
			type : ajaxUtilObj.POST,
			url : "/users/registration",
			data: data,
			contentType: "application/json",
			async : true
		});
	},
	
	login: function(id, password){
		return $.ajax({
			type : ajaxUtilObj.POST,
			url : "/login",
			contentType: "application/json",
			async : true,
			beforeSend: function(xhr){
				var basic = btoa(id + ":" + password);
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
