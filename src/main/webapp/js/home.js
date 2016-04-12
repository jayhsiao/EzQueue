$(document).ready(function(){
	homeObj.registerEvent();
	homeObj.init();
});

var homeObj = {
		
	registerEvent: function(){
		
	},
	
	init: function(){
		
	},
	
	login: function(){
		// Here we run a very simple test of the Graph API after login is
		// successful.  See statusChangeCallback() for when this call is made.
		console.log('Welcome!  Fetching your information.... ');
		FB.api('/me?fields=id,name,email', function(me_response) {
			console.log('me_response: ' + JSON.stringify(me_response));
			
			FB.api('/me/accounts?fields=id,name', function(accounts_response) {
				console.log('accounts_response: '+JSON.stringify(accounts_response));
				
				var body = {
					id: me_response.id,
					name: me_response.name,
					email: me_response.email,
					accounts: accounts_response.data
				};
				
				ajaxUtilObj.callJsonAJAX(ajaxUtilObj.POST, "/user/check", JSON.stringify(body), null, $("#span_spin"), function(httpResponse){
					if(!httpResponse.success){
						$("#span_result").text(httpResponse.returnMessage);
						return;
					}
					$("#span_result").text("Sign in success!!!");
					var userId = httpResponse.returnObject;
					window.location = "/ezqueue/init/"+userId;
				});
			});
		});
	}
}
