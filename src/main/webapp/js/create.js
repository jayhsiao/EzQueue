$(document).ready(function(){
	createObj.registerEvent();
	createObj.init();
});

var createObj = {
	
	registerEvent: function(){
		
		$(document).on("click", "#btn_create", function(){
			createObj.createQueue($(this));
		});
	},
	
	init: function(){
		$("#span_title").text($("#name", window.parent.document).val());
	},
	
	createQueue: function(btnObj){
		var body = {
			userId: $("#userId", window.parent.document).val(),
			title: $("#input_title").val(),
			phone: $("#input_phone").val(),
			address: $("#input_address").val(),
			dscr: $("#textarea_dscr").val(),
			enable: $("input[name=enable]:checked").val()
		};
		
		var spanObj = $("#span_spinner");
		ajaxUtilObj.callAJAX("POST", "/queue/add", JSON.stringify(body), btnObj, spanObj, function(httpResponse){
			if(!httpResponse.success){
				$("#span_result").addClass("label").addClass("label-danger").text(httpResponse.returnMessage);
				return;
			}
			
			$(".panel-body").empty();
			$(".panel-body").append("<h1>建立成功</h1>");
		});
	}
}
