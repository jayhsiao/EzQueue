var queueObj = {
	
	registerEvent: function(){
		
		$(document).on("click", ".thumbnail #img_photo", function(event){
			queueObj.getQueueDetail($(this));
		});
		
	},
	
	init: function(){
		
	}, 
	
	getQueueDetail: function(imgObj){
		var jsonObj = {
			userId: $("#input_userId").val(),
			queueId: $(imgObj).parent().find("#input_list_queueId").val()
		};
		var url = "/ezqueue/queue/detail?"+$.param(jsonObj);
		commonObj.getQueueDetail(url);
	}
	
}
