var queueObj = {
	
	registerEvent: function(){
		
		$(document).on("mouseover", ".queue", function(event){
			$(this).addClass("queue-mouse-hover");
		});
		
		$(document).on("mouseout", ".queue", function(event){
			$(this).removeClass("queue-mouse-hover");
		});
		
		$(document).on("click", ".queue", function(event){
			queueObj.getQueueDetail($(this));
		});
		
	},
	
	init: function(){
		
	}, 
	
	getQueueDetail: function(queue){
		$(".queue").removeClass("choose-queue");
		$(queue).closest("div").addClass("choose-queue");
		
		var jsonObj = {
			userId: $("#input_userId").val(),
			queueId: $(queue).find("#input_list_queueId").val()
		};
		var url = "/ezqueue/queue/detail?"+$.param(jsonObj);
		commonObj.getQueueDetail(url);
	}
	
}
