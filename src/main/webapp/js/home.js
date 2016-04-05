$(document).ready(function(){
	homeObj.registerEvent();
	homeObj.init();
});

var homeObj = {
		
	registerEvent: function(){
		
	},
	
	init: function(){
		//@fb.js
		FB.getLoginStatus(function(response) {
			statusChangeCallback(response);
		});
	}
}
