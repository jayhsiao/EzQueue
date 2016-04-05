$(document).ready(function(){
	createObj.registerEvent();
	createObj.init();
});

var createObj = {
	
	registerEvent: function(){
		
		$("#btn_create").on("click", function(){
			createObj.createQueue();
		});
	},
	
	init: function(){
		$("#span_title").text($("#name", window.parent.document).val());
	},
	
	createQueue: function(){
		var body = {
			userId: $("#userId", window.parent.document).val(),
			title: $("#input_title").val(),
			phone: $("#input_phone").val(),
			address: $("#input_address").val(),
			dscr: $("#textarea_dscr").val(),
			enable: $("input[name=enable]:checked").val()
		};
		console.log(JSON.stringify(body));
		var actionUrl = "/queue/add";
		ajaxUtilObj.callAJAX("POST", actionUrl, JSON.stringify(body), function(httpResponse){
			if(!httpResponse.success){
				$(".panel-primary").addClass("panel-danger");
				$(".panel-body").empty();
				$(".panel-body").append(httpResponse.returnMessage);
				return;
			}
			
			$(".panel-primary").addClass("panel-success");
			$(".panel-body").empty();
			$(".panel-body").append("<h1>建立成功</h1>");
		});
	}
}
