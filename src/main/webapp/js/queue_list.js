var queueObj = {
	
	registerEvent: function(){
		
		$(document).on("mouseover", ".queue", function(event){
			$(this).addClass("mouse-hover");
		});
		
		$(document).on("mouseout", ".queue", function(event){
			$(this).removeClass("mouse-hover");
		});
		
		$(document).on("click", ".queue #img_photo", function(event){
			queueObj.getQueueDetail($(this));
		});
		
	},
	
	init: function(){
		
	}, 
	
	getQueueDetail: function(imgObj){
		$(".queue").removeClass("choose-queue");
		$(imgObj).closest("div").addClass("choose-queue");
		
		var jsonObj = {
			userId: $("#input_userId").val(),
			queueId: $(imgObj).parent().find("#input_list_queueId").val()
		};
		var url = "/ezqueue/queue/detail?"+$.param(jsonObj);
		commonObj.getQueueDetail(url);
	}
	
}
