var queueTypeObj = {
	
	registerEvent: function(){
		
		$(document).on("mouseover", ".queue-type", function(event){
			$(this).addClass("queue-mouse-hover");
		});
		
		$(document).on("mouseout", ".queue-type", function(event){
			$(this).removeClass("queue-mouse-hover");
		});
		
		$(document).on("click", ".queue-type", function(event){
			queueTypeObj.getQueueList($(this));
		});
		
	},
	
	init: function(){
		
	}, 
	
	getQueueList: function(queueType){
		var url = "/ezqueue/type/"+$(queueType).attr("id")+"?";
		commonObj.getInitQueueList(url + mainObj.initParam($("#input_init_limit").val(), $("#input_init_offset").val()));
		
		$("#input_url").val(url);
		$("#input_offset").val(parseInt($("#input_init_offset").val()) + 1);
	}
	
}
