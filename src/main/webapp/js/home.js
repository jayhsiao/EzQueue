$(document).ready(function(){
	ezqueueObj.registerEvent();
	ezqueueObj.init();
});

var ezqueueObj = {
		
	registerEvent: function(){
		
		$("#btn_signup").on("click", function(){
			ezqueueObj.signup();
		});
		
		$("#btn_signin").on("click", function(){
			ezqueueObj.signin();
		});
	},
	
	init: function(){
		
	},
	
	signup: function(){
		$("#submitForm").prop("method", "GET");
		$("#submitForm").prop("action", "/ezQueue/signup");
		$("#submitForm").submit();
	},
	
	signin: function(){
		$("#submitForm").prop("method", "GET");
		$("#submitForm").prop("action", "/ezQueue/signin");
		$("#submitForm").submit();
	},
}
