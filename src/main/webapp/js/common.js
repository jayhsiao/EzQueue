var commonObj = {
		
	registerEvent: function(){
		
		$(document).on("click", "#span_facebook_user", function(event){
			$("#navbar li").removeClass("active");
			commonObj.getInitQueueList("/ezqueue/user/"+$(this).find("#input_userAccountId").val()+"?limit="+$("#input_init_limit").val()+"&offset="+$("#input_init_offset").val());
		});
		
	}, 
	
	blockUI: function(){
		$.blockUI.defaults.css = {}
		$.blockUI({ 
			css: { 
				padding:	0,
				margin:		0,
				width:		'30%',
				top:		'40%',
				left:		'35%',
				textAlign:	'center',
				cursor:		'wait'
	        },
	        message: '<h1><i class="fa fa-spinner fa-spin fa-3x fa-fw"></i></h1>' });
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
		return ajaxUtilObj.callJsonAJAX(ajaxUtilObj.POST, "/users/check", JSON.stringify(body), null);
	},
	
	getInitQueueList: function(url){
		ajaxUtilObj.callHtmlAJAX(url)
		.done(function(page){
			$("#div_detail").empty();
			$("#div_list_queue").empty();
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
			$("#div_list_queue").empty();
			$("btn_more").hide();
			$("#div_list_queue").append(page);
			$("#div_list").show();
		});
	}, 

	getQueueDetail: function(url){
		ajaxUtilObj.callHtmlAJAX(url)
		.done(function(page){
			$("#div_list").hide();
			$("#div_detail").empty();
			
			$("#div_detail").html(page).promise()
			.done(function(){
				commonObj.unblockUI();
				
				FB.XFBML.parse();
				queueDetailObj.init();
			});
		});
	}, 
	
	initPage: function(page){
		$("#div_list_queue").append(page).promise()
		.done(function(){
			commonObj.unblockUI();
			
			if($("#input_list_size").val() == $("#input_init_limit").val()){
				$("#btn_more").show();
				$("#input_list_size").remove();
			}
			else{
				$("#btn_more").hide();
			}
	    });
	}
	
}
