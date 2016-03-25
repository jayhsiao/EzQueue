$(document).ready(function(){
	ezqueueObj.registerEvent();
	ezqueueObj.init();
});

var ezqueueObj = {
		
	registerEvent: function(){
		
		$("#btn_signup").on("click", function(){
			ezqueueObj.signup();
		});
	},
	
	init: function(){
		
	},
	
	signup: function(){
		console.log("test");
		$("#submitForm").prop("method", "GET");
		$("#submitForm").prop("action", "/ezQueue/doSignup");
		$("#submitForm").submit();
	}
}
