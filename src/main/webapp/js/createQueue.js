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
		$("#span_name").text($("#name", window.parent.document).val());
	},
	
	createQueue: function(){
		var body = {
			userId: $("#userId", window.parent.document).val(),
			dscr: $("#textarea_dscr").val(),
			enable: $("input[name=enable]:checked").val()
		};
		var actionUrl = "/queue/add";
		var httpResponse = utilObj.callAJAX("POST", actionUrl, JSON.stringify(body));
		if(!httpResponse.success){
			$(".panel-primary").addClass("panel-danger");
			$(".panel-body").empty();
			$(".panel-body").append(httpResponse.returnMessage);
			return;
		}
		
		$(".panel-primary").addClass("panel-success");
		$(".panel-body").empty();
		$(".panel-body").append("創造成功");
	}
}
