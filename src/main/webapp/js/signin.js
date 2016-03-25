$(document).ready(function(){
	ezqueueObj.registerEvent();
	ezqueueObj.init();
});

var ezqueueObj = {
		
	registerEvent: function(){
		
		$("#btn_signin").on("click", function(){
			ezqueueObj.signin();
		});
	},
	
	init: function(){
		
	},
	
	signin: function(){
		$("#submitForm").prop("method", "POST");
		$("#submitForm").prop("action", "/ezQueue/doSignin");
		$("#submitForm").submit();
	},
}
