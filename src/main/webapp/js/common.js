var commonObj = {
		
	registerEvent: function(){
		
		$(document).on("click", "#span_facebook_user", function(event){
			$("#navbar li").removeClass("active");
			commonObj.getInitQueueList("/ezqueue/user/"+$(this).find("#input_userAccountId").val()+"?limit="+$("#input_init_limit").val()+"&offset="+$("#input_init_offset").val());
		});
		
		$(document).on("mouseover", "#span_facebook_user", function(event){
			$(this).addClass("user-mouse-hover");
		});
		
		$(document).on("mouseout", "#span_facebook_user", function(event){
			$(this).removeClass("user-mouse-hover");
		});
	}, 
	
	blockUI: function(){
		$.blockUI.defaults.css = {}
		$.blockUI({ 
			baseZ: 9999,
			css: { 
				padding:	0,
				margin:		0,
				width:		'100%',
				top:		'35%',
				textAlign:	'center',
				cursor:		'wait'
	        },
	        message: '<h1><i class="fa fa-circle-o-notch fa-spin fa-3x fa-fw"></i></h1>' });
	},
	
	unblockUI: function(){
		$.unblockUI();
	}, 
	
	getInitQueueList: function(url){
		ajaxUtilObj.callHtmlAJAX(url)
		.done(function(page){
			$("#div_detail").html("");
			$("#div_list_queue").html("");
			$("#div_list").show();
			commonObj.initPage(page);
		})
		.always(function(){
			commonObj.unblockUI();
		});
	}, 
	
	getMoreQueueList: function(url){
		ajaxUtilObj.callHtmlAJAX(url)
		.done(function(page){
			commonObj.initPage(page);
		})
		.always(function(){
			commonObj.unblockUI();
		});
	}, 
	
	getCreate: function(url){
		ajaxUtilObj.callHtmlAJAX(url)
		.done(function(page){
			$("#div_detail").html("");
			$("#div_list_queue").html("");
			$("btn_more").hide();
			$("#div_list").show();
			$("#div_list_queue").append(page).promise().always(function(){
				commonObj.unblockUI();
			});
		});
	}, 

	getQueueDetail: function(url){
		ajaxUtilObj.callHtmlAJAX(url)
		.done(function(page){
			$("#div_list").hide();
			$("#div_detail").html("");
			
			$("#div_detail").append(page).promise()
			.done(function(){
				$(document).scrollTop(0);
				FB.XFBML.parse();
				queueDetailObj.init();
			})
			.always(function(){
				commonObj.unblockUI();
			});
		});
	}, 
	
	initPage: function(page){
		$("#div_list_queue").append(page).promise()
		.done(function(){
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
