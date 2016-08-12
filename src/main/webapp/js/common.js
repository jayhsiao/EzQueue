var commonObj = {
		
	registerEvent: function(){
		
		$(document).on("click", "#span_facebook_user", function(event){
			$("#navbar li").removeClass("active");
			commonObj.getQueueList("/ezqueue/me/"+$(this).find("#input_userAccountId").val()+"?limit=12&offset=0");
		});
		
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
	
	getInitQueueList: function(url){
		ajaxUtilObj.callHtmlAJAX(url)
		.done(function(page){
			$("#div_detail").empty();
			$("#div_list").empty();
			$("#div_list").show();
			commonObj.initPage(page);
		});
	}, 
	
	getMoreQueueList: function(url){
		ajaxUtilObj.callHtmlAJAX(url)
		.done(function(page){
			commonObj.initPage(page);
		});
	}, 
	
	getCreate: function(url){
		ajaxUtilObj.callHtmlAJAX(url)
		.done(function(page){
			$("#div_detail").empty();
			$("#div_list").empty();
			$("#div_list").append(page);
			$("btn_more").hide();
			$("#div_list").show();
		});
	}, 

	getQueueDetail: function(url){
		ajaxUtilObj.callHtmlAJAX(url)
		.done(function(page){
			$("#div_list").hide();
			$("#btn_more").hide();
			
			$("#div_detail").empty();
			$("#div_detail").append(page);
			FB.XFBML.parse();
			
			queueDetailObj.init();
		});
	}, 
	
	initPage: function(page){
		if($.trim(page).length == 0){
			$("#btn_more").hide();
		}
		else{
			var length = 0;
			$(page + " .queue").each(function(){
				var textLength = $.trim($(this).text()).length;
				if(textLength > 0) length++;
			});
			if(length < $("#input_init_limit").val()) {
				$("#btn_more").hide();
			}
			else{
				$("#btn_more").show();
			}
			
			$("#div_list").append(page);
		}
	}
	
}
