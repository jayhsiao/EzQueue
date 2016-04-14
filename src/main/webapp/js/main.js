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
				userId: $("#input_userId").val(),
				queueTypeId: $(this).attr("id")
			};
			var url = "/ezqueue/all/?"+$.param(queryJson);
			mainObj.getHtml($(this).parent(), $("#span_spinner_queueType"), url);
		});
		
		$(document).on("click", "#a_promotion", function(event){
			mainObj.getHtml($(this).parent(), $("#span_spinner_promotion"), "/ezqueue/promotion/"+$("#input_userId").val());
		});
		
		$(document).on("click", "#a_favorite", function(event){
			mainObj.getHtml($(this).parent(), $("#span_spinner_favorite"), "/ezqueue/favorite/"+$("#input_userId").val());
		});
		
		$(document).on("click", "#a_myQueue", function(event){
			mainObj.getHtml($(this).parent(), $("#span_spinner_myQueue"), "/ezqueue/myQueues/"+$("#input_userId").val());
		});
		
		$(document).on("click", "#a_queueing", function(event){
			mainObj.getHtml($(this).parent(), $("#span_spinner_queueing"), "/ezqueue/queuing/"+$("#input_userId").val());
		});
		
		$(document).on("click", "#a_create", function(event){
			mainObj.getHtml($(this).parent(), $("#span_spinner_create"), "/ezqueue/createQueue/"+$("#input_userId").val());
		});
		
		$(document).on("click", "#btn_search", function(event){
			$("#navbar li").removeClass("active");
			if($("#input_search").val().length > 0){
				var queryJson = {
					userId: $("#input_userId").val(),
					text: $("#input_search").val()
				};
				var url = "/ezqueue/search/?"+$.param(queryJson);
				mainObj.getHtml($(this), $("#span_spinner_search"), url);
			}
		});
		
		$(document).on("click", "#a_setting", function(event){
			
		});
		
		$(document).on("click", "#a_logout", function(event){
			$("#span_spinner_setting").show();
			FB.logout(function(response) {
				window.location = "/ezqueue/home";
			});
		});
		
	},
	
	init: function(){
		$("#a_promotion").click();
	},
	
	getHtml: function(liObj, spanObj, url){
		ajaxUtilObj.callHtmlAJAX(ajaxUtilObj.GET, url, liObj, spanObj, function(httpResponse){
			$("#div_main").empty();
			$("#div_main").html(httpResponse);
			FB.XFBML.parse();
		});
	}
}
