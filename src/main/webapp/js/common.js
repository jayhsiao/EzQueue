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
	
	facebookImg: function(fbId, size){
		return "http://graph.facebook.com/"+fbId+"/picture?width="+size+"&height="+size;
	},
	
	blockUI: function(){
		$.blockUI({ css: { 
            border: 'none', 
            padding: '15px', 
            backgroundColor: '#000', 
            '-webkit-border-radius': '10px', 
            '-moz-border-radius': '10px', 
            opacity: .5, 
            color: '#fff' 
        } });
	},
	
	unblockUI: function(){
		$.unblockUI();
	}, 
	
	checkUser: function(id, name, email, accounts){
		var body = {
			id: id,
			name: name, 
			email: email,
			accounts: accounts
		};
		return ajaxUtilObj.callJsonAJAX(ajaxUtilObj.POST, "/users/add", JSON.stringify(body), null);
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
