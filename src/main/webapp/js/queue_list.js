$(document).ready(function(){
	queueObj.registerEvent();
	queueObj.init();
});

var queueObj = {
	
	registerEvent: function(){
		
		$(document).on("mouseover", ".thumbnail", function(event){
			$("#input_userAccountId").val("");
			$("#input_queueId").val("");
			$("#input_starId").val("");
			$("#input_promotionId").val("");
			$("#input_favoriteId").val("");
			$("#input_queuingId").val("");
			$("#input_canEdit").val("");
			
			$("#input_userAccountId").val($(this).find("#input_map_userAccountId").val());
			$("#input_queueId").val($(this).find("#input_map_queueId").val());
			$("#input_starId").val($(this).find("#input_map_starId").val());
			$("#input_promotionId").val($(this).find("#input_map_promotionId").val());
			$("#input_favoriteId").val($(this).find("#input_map_favoriteId").val());
			$("#input_queuingId").val($(this).find("#input_map_queuingId").val());
			$("#input_canEdit").val($(this).find("#input_map_canEdit").val());
		});
		
		$(document).on("click", ".thumbnail #img_photo", function(event){
			var jsonObj = {
				userId: $("#input_userId").val(),
				queueId: $("#input_queueId").val(),
				canEdit: false
			};
			var url = "/ezqueue/queue?"+$.param(jsonObj);
			commonObj.getQueueDetail(url);
		});
		
	},
	
	init: function(){
		
	}
	
}
