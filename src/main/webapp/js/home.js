$(document).ready(function(){
	homeObj.registerEvent();
	homeObj.init();
});

var homeObj = {
	
	registerEvent: function(){
		$("#btn_login").click(function(){
			homeObj.login();
		});
	},
	
	init: function(){
		
	},
	
	login: function(){
		
		var body = {
			scope: $("#scope").val()
		};
		console.log(JSON.stringify(body));
		
		ajaxUtilObj.callHtmlAJAX(ajaxUtilObj.POST, "/connect/facebook", JSON.stringify(body), null, $("#span_spin"), function(httpResponse){
			console.log(httpResponse);
		});
	}
}
