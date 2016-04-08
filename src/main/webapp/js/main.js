$(document).ready(function(){
	mainObj.registerEvent();
	mainObj.init();
});

var mainObj = {
		
	registerEvent: function(){
		
		$(document).on("click", "#navbar li", function(event){
			$("#navbar li").removeClass("active");
			$(this).addClass("active");
		});
		
		$(document).on("click", "#a_promotion", function(event){
			mainObj.getHtml($(this), "/ezqueue/promotion/"+$("#userId").val());
		});
		
		$(document).on("click", "#a_favorite", function(event){
			mainObj.getHtml($(this), "/ezqueue/favorite/"+$("#userId").val());
		});
		
		$(document).on("click", "#a_myQueue", function(event){
			mainObj.getHtml($(this), "/ezqueue/myQueues/"+$("#userId").val());
		});
		
		$(document).on("click", "#a_queueing", function(event){
			mainObj.getHtml($(this), "/ezqueue/queuing/"+$("#userId").val());
		});
		
		$(document).on("click", "#a_create", function(event){
			mainObj.getHtml($(this), "/ezqueue/createQueue/"+$("#userId").val());
		});
	},
	
	// 初始化
	init: function(){
		$("#a_promotion").click();
	},
	
	getHtml: function(liObj, url){
		$("#div_main").empty();
		var spanObj = $(liObj).parent().find("span");
		ajaxUtilObj.callHtmlAJAX(ajaxUtilObj.GET, url, spanObj, function(httpResponse){
			$("#div_main").html(httpResponse);
			FB.XFBML.parse();
		});
	}
}
