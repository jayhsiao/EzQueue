$(document).ready(function(){
	commonObj.registerEvent();
	mainObj.registerEvent();
	queueObj.registerEvent();
	queueDetailObj.registerEvent();
	createObj.registerEvent();
});

var mainObj = {
		
	registerEvent: function(){
		
		$(document).on("click", "#navbar li", function(event){
			$("#navbar li").removeClass("active");
			$(this).addClass("active");
			$("#input_search").val("");
		});
		
		$(document).on("click", "#ul_all a", function(event){
			var url = "/ezqueue/type/"+$(this).attr("id")+"?";
			commonObj.getInitQueueList(url + mainObj.initParam($("#input_init_limit").val(), $("#input_init_offset").val()));
			
			$("#input_url").val(url);
			$("#input_offset").val(parseInt($("#input_init_offset").val()) + 1);
		});
		
		$(document).on("click", "#a_promotion", function(event){
			var url = "/ezqueue/promotion?";
			commonObj.getInitQueueList(url + mainObj.initParam($("#input_init_limit").val(), $("#input_init_offset").val()));
			
			$("#input_url").val(url);
			$("#input_offset").val(parseInt($("#input_init_offset").val()) + 1);
		});
		
		$(document).on("click", "#a_me", function(event){
			var url = "/ezqueue/me/"+$("#input_userId").val()+"?";
			commonObj.getInitQueueList(url + mainObj.initParam($("#input_init_limit").val(), $("#input_init_offset").val()));
			
			$("#input_url").val(url);
			$("#input_offset").val(parseInt($("#input_init_offset").val()) + 1);
		});
		
		$(document).on("click", "#a_favorite", function(event){
			var url = "/ezqueue/favorite/"+$("#input_userId").val()+"?";
			commonObj.getInitQueueList(url + mainObj.initParam($("#input_init_limit").val(), $("#input_init_offset").val()));
			
			$("#input_url").val(url);
			$("#input_offset").val(parseInt($("#input_init_offset").val()) + 1);
		});
		
		$(document).on("click", "#a_queueing", function(event){
			var url = "/ezqueue/queuing/"+$("#input_userId").val()+"?";
			commonObj.getInitQueueList(url + mainObj.initParam($("#input_init_limit").val(), $("#input_init_offset").val()));
			
			$("#input_url").val(url);
			$("#input_offset").val(parseInt($("#input_init_offset").val()) + 1);
		});
		
		$(document).on("click", "#a_create", function(event){
			var url = "/ezqueue/create/"+$("#input_userId").val();
			commonObj.getCreate(url);
		});
		
		$(document).on("click", "#btn_search", function(event){
			$("#navbar li").removeClass("active");
			var searchText = $("#input_search").val();
			if(searchText.length > 0){
				var url = "/ezqueue/search/"+searchText+"?";
				commonObj.getInitQueueList(url + mainObj.initParam($("#input_init_limit").val(), $("#input_init_offset").val()));
				
				$("#input_url").val(url);
				$("#input_offset").val(parseInt($("#input_init_offset").val()) + 1);
			}
		});
		
		$(document).on("click", "#a_setting", function(event){
			
		});
		
		$(document).on("click", "#a_logout", function(event){
			FB.logout(function(response) {
				mainObj.doNoLogin();
			});
		});
		
		$(document).on("click", "#btn_more", function(event){
			var url = $("#input_url").val() + mainObj.initParam($("#input_init_limit").val(), $("#input_offset").val());
			commonObj.getMoreQueueList(url);
			
			$("#input_offset").val(parseInt($("#input_offset").val()) + 1);
		});
		
	},
	
	initParam: function(limit, offset){
		var initParam = {
			limit: limit,
			offset: offset
		}
		return $.param(initParam);
	}, 
	
	init: function(){
		var queueDetailId = $("#input_queueDetailId").val();
		if(queueDetailId.length > 0){
			var jsonObj = {
				queueId: queueDetailId
			};
			var url = "/ezqueue/queue?"+$.param(jsonObj);
			commonObj.getQueueDetail(url);
			$("#input_queueDetailId").val("");
			return;
		}
		
		$("#a_promotion").click();
	}, 
	
	doLogin: function(){
		FB.api(
			"/me?fields=id,name,email,accounts",
		    function (response) {
		    	if (response && !response.error) {
		    		/* handle the result */
		    		var id = response.id;
		    		var name = response.name;
		    		var email = response.email;
		    		var accounts = response.accounts;
		    		
		    		commonObj.checkUser(id, name, email, accounts)
		    		.done(function(user){
		    			
		    			$("#img_header_facebook_user").attr("src", "http://graph.facebook.com/"+user.facebookId+"/picture?width=12&height=12");
		    			$("#span_header_facebook_user_name").text(user.name);
		    			
		    			mainObj.showLogin(user);
		    			mainObj.init();
		    		});
		    	}
		    }
	    );
	}, 
	
	doNoLogin: function(){
		mainObj.showNologin();
		mainObj.init();
	}, 
	
	showLogin: function(user){
		$("#input_userId").val(user.userId);
		
		$("#li_facebook_login").hide();
		$("#li_facebook_user").show();
		
		$("#li_search").show();
	}, 
	
	showNologin: function(){
		$("#input_userId").val("");
		
		$("#li_facebook_login").show();
		$("#li_facebook_user").hide();
		
		$("#li_search").hide();
	}
	
}
