$(document).ready(function(){
	queueDetailObj.registerEvent();
	queueDetailObj.init();
});

var queueDetailObj = {
	
	registerEvent: function(){
		
		$(document).on("click", "#btn_back", function(event){
			queueDetailObj.back();
		});
		
		$(document).on("click", "#span_star i", function(event){
			queueDetailObj.star($(this));
		});
		
		$(document).on("click", "#btn_favorite", function(event){
			queueDetailObj.favorite($(this));
		});
		
		$(document).on("click", "#btn_queuing", function(event){
			queueDetailObj.queuing($(this));
		});
		
	},
	
	init: function(){
		
	},
	
	back: function(){
		$("#div_detail").empty();
		$("#div_main").show();
	}, 
	
	star : function(iObj){
		if($("#input_starId").val().length == 0){
			$(iObj).parent().find("i:lt("+($(iObj).index()+1)+")").removeClass("fa-star-o").addClass("fa-star");
			$(iObj).parent().find("i:gt("+$(iObj).index()+")").removeClass("fa-star").addClass("fa-star-o");
			starObj.addStar($("#input_userId").val(), $("#input_queueId").val(), $(iObj).index() + 1);
		}
		else{
			if($(iObj).index() == 0 && $(iObj).parent().find(".fa-star").length == 1){
				$(iObj).removeClass("fa-star").addClass("fa-star-o");
				starObj.removeStar($("#input_starId").val());
			}
			else{
				$(iObj).parent().find("i:lt("+($(iObj).index()+1)+")").removeClass("fa-star-o").addClass("fa-star");
				$(iObj).parent().find("i:gt("+$(iObj).index()+")").removeClass("fa-star").addClass("fa-star-o");
				starObj.updateStar($("#input_starId").val(), $(iObj).index() + 1);
			}
		}
	}, 
	
	favorite: function(btnObj){
		var iObj = $(btnObj).find("i");
		var spanDscrObj = $(btnObj).find("#span_favorite_dscr");
		
		if($(iObj).hasClass("fa-heart")){
			var body = {
				favoriteId: $("#input_favoriteId").val()
			};
			
			$(iObj).removeClass().addClass("fa").addClass("fa-spinner").addClass("fa-spin");
			ajaxUtilObj.callJsonAJAX(ajaxUtilObj.DELETE, "/favorites/remove", JSON.stringify(body), btnObj, iObj)
			.done(function(){
				$(iObj).removeClass().addClass("fa").addClass("fa-heart-o");
				$(spanDscrObj).text("加入最愛");
				
				$("#div_"+$("#input_queueId").val()+" #input_map_favoriteId").val("");
				$("#input_favoriteId").val("");
			});
		}
		else {
			var body = {
				userId: $("#input_userId").val(),
				queueId: $("#input_queueId").val()
			};
			
			$(iObj).removeClass().addClass("fa").addClass("fa-spinner").addClass("fa-spin");
			ajaxUtilObj.callJsonAJAX(ajaxUtilObj.POST, "/favorites/add", JSON.stringify(body), btnObj, iObj)
			.done(function(favoriteId){
				$(iObj).removeClass().addClass("fa").addClass("fa-heart");
				$(spanDscrObj).text("不喜歡了");
				
				$("#div_"+$("#input_queueId").val()+" #input_map_favoriteId").val(favoriteId);
				$("#input_favoriteId").val(favoriteId);
			});
		}
	}, 
	
	queuing: function(btnObj){
		var iObj = $(btnObj).find("i");
		var spanDscrObj = $(btnObj).find("#span_queuing_dscr");
		
		if($(iObj).hasClass("fa-user-times")){
			var body = {
				queuingId: $("#input_queuingId").val()
			};
			
			$(iObj).removeClass().addClass("fa").addClass("fa-spinner").addClass("fa-spin");
			ajaxUtilObj.callJsonAJAX(ajaxUtilObj.DELETE, "/queuings/remove", JSON.stringify(body), btnObj, iObj)
			.done(function(){
				$(iObj).removeClass().addClass("fa").addClass("fa-user-plus");
				$(spanDscrObj).text("抽號碼牌");
				
				$("#div_"+$("#input_queueId").val()+" #input_map_queuingId").val("");
				$("#input_queuingId").val("");
				
				$("#span_queueNum").text("");
			});
		}
		else {
			var body = {
				userId: $("#input_userId").val(),
				queueId: $("#input_queueId").val()
			};
			
			$(iObj).removeClass().addClass("fa").addClass("fa-spinner").addClass("fa-spin");
			ajaxUtilObj.callJsonAJAX(ajaxUtilObj.POST, "/queuings/add", JSON.stringify(body), btnObj, iObj)
			.done(function(queuing){
				$(iObj).removeClass().addClass("fa").addClass("fa-user-times");
				$(spanDscrObj).text("不想等了");
				
				$("#div_"+$("#input_queueId").val()+" #input_map_queuingId").val(queuing.queuingId);
				$("#input_queuingId").val(queuing.queuingId);
				
				$("#span_queueNum").text(queuing.queueNum);
			});
		}
	}
	
}
