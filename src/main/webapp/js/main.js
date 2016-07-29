$(document).ready(function(){
	mainObj.registerEvent();
	mainObj.init();
});

var mainObj = {
		
	registerEvent: function(){
		
		$(document).on("click", "#navbar li", function(event){
			$("#navbar li").removeClass("active");
			$(this).addClass("active");
			$("#input_search").val("");
		});
		
		$(document).on("click", "#ul_all a", function(event){
			var queryJson = {
				queueTypeId: $(this).attr("id")
			};
			var url = "/ezqueue/all/"+$("#input_userId").val()+"?"+$.param(queryJson)+"&limit=12&offset=0";
			commonObj.getQueueList(url);
		});
		
		$(document).on("click", "#a_promotion", function(event){
			commonObj.getQueueList("/ezqueue/promotion/"+$("#input_userId").val()+"?limit=12&offset=0");
		});
		
		$(document).on("click", "#a_favorite", function(event){
			commonObj.getQueueList("/ezqueue/favorite/"+$("#input_userId").val()+"?limit=12&offset=0");
		});
		
		$(document).on("click", "#a_myQueue", function(event){
			commonObj.getQueueList("/ezqueue/myQueues/"+$("#input_userId").val()+"?limit=12&offset=0");
		});
		
		$(document).on("click", "#a_queueing", function(event){
			commonObj.getQueueList("/ezqueue/queuing/"+$("#input_userId").val()+"?limit=12&offset=0");
		});
		
		$(document).on("click", "#a_create", function(event){
			commonObj.getQueueList("/ezqueue/createQueue/"+$("#input_userId").val()+"?limit=12&offset=0");
		});
		
		$(document).on("click", "#btn_search", function(event){
			$("#navbar li").removeClass("active");
			if($("#input_search").val().length > 0){
				var queryJson = {
					userId: $("#input_userId").val(),
					text: $("#input_search").val()
				};
				var url = "/ezqueue/search/?"+$.param(queryJson);
				commonObj.getQueueList(url);
			}
		});
		
		$(document).on("click", "#a_setting", function(event){
			
		});
		
		$(document).on("click", "#a_logout", function(event){
			$("#span_spinner_setting").show();
			FB.logout(function(response) {
				mainObj.login_not();
			});
		});
		
	},
	
	init: function(){
//		$("#a_promotion").click();
	}, 
	
	login: function(){
		console.log("login");
		
		FB.api(
			"/me?fields=id,name,email,accounts",
		    function (response) {
		    	if (response && !response.error) {
		    		console.log(response);
		    		/* handle the result */
		    		var id = response.id;
		    		var name = response.name;
		    		var email = response.email;
		    		var accounts = response.accounts;
		    		
		    		commonObj.blockUI();
		    		commonObj.checkUser(id, name, email, accounts)
		    		.done(function(user){
		    			console.log(user);
		    			
		    			$("#img_facebook_user").attr("src", commonObj.facebookImg(user.facebookId, 12));
		    			$("#span_facebook_user_name").text(user.name);
		    			
		    			$("#a_myQueue").show();
		    			$("#a_fbLogin").hide();
		    			
		    			commonObj.unblockUI();
		    		});
		    	}
		    }
	    );
	}, 
	
	login_not: function(){
		console.log("login_not");
		$("#a_myQueue").hide();
		$("#a_fbLogin").show();
	}
	
}
