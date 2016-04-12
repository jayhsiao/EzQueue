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
		
		$(document).on("click", "#a_promotion", function(event){
			mainObj.getHtml($(this).parent(), $(this).parent().find("span"), "/ezqueue/promotion/"+$("#userId").val()+"/0");
		});
		
		$(document).on("click", "#a_favorite", function(event){
			mainObj.getHtml($(this).parent(), $(this).parent().find("span"), "/ezqueue/favorite/"+$("#userId").val()+"/0");
		});
		
		$(document).on("click", "#a_myQueue", function(event){
			mainObj.getHtml($(this).parent(), $(this).parent().find("span"), "/ezqueue/myQueues/"+$("#userId").val()+"/0");
		});
		
		$(document).on("click", "#a_queueing", function(event){
			mainObj.getHtml($(this).parent(), $(this).parent().find("span"), "/ezqueue/queuing/"+$("#userId").val()+"/0");
		});
		
		$(document).on("click", "#a_create", function(event){
			mainObj.getHtml($(this).parent(), $(this).parent().find("span"), "/ezqueue/createQueue/"+$("#userId").val());
		});
		
		$(document).on("click", "#btn_search", function(event){
			$("#navbar li").removeClass("active");
			if($("#input_search").val().length > 0){
				mainObj.getHtml($(this), $(this).parent().find("span"), "/ezqueue/search/"+$("#userId").val()+"/"+$("#input_search").val()+"/0");
			}
		});
		
		$(document).on("click", "#a_logout", function(event){
			FB.logout(function(response) {
				window.location = "/ezqueue/home";
			});
		});
		
	},
	
	// 初始化
	init: function(){
		$("#a_promotion").click();
	},
	
	getHtml: function(liObj, spanObj, url){
		$("#div_main").empty();
		ajaxUtilObj.callHtmlAJAX(ajaxUtilObj.GET, url, liObj, spanObj, function(httpResponse){
			$("#div_main").html(httpResponse);
			FB.XFBML.parse();
		});
	}
}
