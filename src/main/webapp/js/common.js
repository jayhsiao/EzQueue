$(document).ready(function(){
	commonObj.registerEvent();
});

var commonObj = {
		
	registerEvent: function(){
		
		$(document).on("click", "#span_facebook_user", function(event){
			$("#navbar li").removeClass("active");
			commonObj.getQueueList("/ezqueue/myQueues/"+$("#input_userAccountId").val()+"?limit=12&offset=0");
		});
		
	},
	
	getQueueList: function(url){
		ajaxUtilObj.callHtmlAJAX(url)
		.done(function(page){
			$("#div_main").empty().html(page).show();
			$("#div_detail").empty();
		});
	}, 

	getQueueDetail: function(url){
		ajaxUtilObj.callHtmlAJAX(url)
		.done(function(page){
			$("#div_main").hide();
			$("#div_detail").empty().html(page);
			FB.XFBML.parse();
		});
	}
	
}
