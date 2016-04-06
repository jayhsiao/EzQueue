$(document).ready(function(){
	homeObj.registerEvent();
	homeObj.init();
});

var homeObj = {
		
	registerEvent: function(){
		
		$(document).on("click", ".fb-login-button", function(event){
			console.log('connected');
			// @fb_check.js
			checkLoginState(homeObj.login);
		});
	},
	
	init: function(){
		
	},
	
	login: function(){
		// Here we run a very simple test of the Graph API after login is
		// successful.  See statusChangeCallback() for when this call is made.
		console.log('Welcome!  Fetching your information.... ');
		FB.api('/me?fields=id,name,email', function(response) {
			console.log('Successful login for: ' + JSON.stringify(response));
			var body = {
				fbId: response.id,
				name: response.name,
				email: response.email
			};
			var actionUrl = "/user/add";
			ajaxUtilObj.callAJAX(ajaxUtilObj.POST, actionUrl, JSON.stringify(body), null, null, function(httpResponse){
				if(!httpResponse.success){
					$("#span_result").addClass("label").addClass("label-danger").text(httpResponse.returnMessage);
					return;
				}
				$("#span_result").addClass("label").addClass("label-success").text("Sign in success!!!");
				var userId = httpResponse.returnObject;
				window.location = "/ezqueue/init/"+userId;
			});
		});
	}
}
