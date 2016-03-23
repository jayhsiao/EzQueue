$(document).ready(function(){
	ezqueueObj.registerEvent();
	ezqueueObj.init();
});

var ezqueueObj = {
		
	registerEvent: function(){
		
		$("#btn_create").on("click", function(){
			ezqueueObj.createQueue();
		});
	},
	
	init: function(){
		
	},
	
	createQueue: function(){
		var body = {
			userId: $("#userId").val(),
			dscr: $("#textarea_dscr").val(),
			enable: $("input[name=enable]:checked").val()
		};
		var actionUrl = "/queue/add";
		var httpResponse = utilObj.callAJAX("POST", actionUrl, JSON.stringify(body));
		if(!httpResponse.success){
			$(".panel-primary").addClass("panel-danger");
			$(".panel-primary").removeClass();
			$(".panel-title").text(httpResponse.returnMessage);
			return;
		}
		
		$(".panel-primary").addClass("panel-success");
		$(".panel-body").empty();
		$(".panel-body").append("Success");
	}
}
