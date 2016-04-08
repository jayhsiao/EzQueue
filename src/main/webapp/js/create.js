$(document).ready(function(){
	createObj.registerEvent();
	createObj.init();
});

var createObj = {
	
	registerEvent: function(){
		
		$(document).on("click", ".dropdown-menu li", function(){
			var userId = $(this).find("#input_accounts_userId").val();
			var accountId = $(this).find("#input_accounts_accountId").val();
			var accountName = $(this).find("#input_accounts_accountName").val();
			
			$("#btn_accounts").text("");
			$("#btn_accounts").append("<img src='http://graph.facebook.com/"+accountId+"/picture?width=12&height=14'>&nbsp;"+accountName+"&nbsp;<span class='caret'></span>");
			
			$("#input_userId").val(userId);
		});
		
		$(document).on("click", "#btn_create", function(){
			createObj.createQueue($(this));
		});
	},
	
	init: function(){
		$("#span_title").text($("#name", window.parent.document).val());
	},
	
	createQueue: function(btnObj){
		var body = {
			userId: $("#input_userId").val(),
			title: $("#input_title").val(),
			phone: $("#input_phone").val(),
			address: $("#input_address").val(),
			dscr: $("#textarea_dscr").val(),
			enable: $("input[name=enable]:checked").val()
		};
		
		var spanObj = $("#span_spinner");
		ajaxUtilObj.callJsonAJAX("POST", "/queue/add", JSON.stringify(body), btnObj, spanObj, function(httpResponse){
			if(!httpResponse.success){
				$("#span_result").addClass("label").addClass("label-danger").text(httpResponse.returnMessage);
				return;
			}
			
			$(".panel-body").empty();
			$(".panel-body").append("<h1>建立成功</h1>");
		});
	}
}
