var createObj = {
	
	registerEvent: function(){
		
		$(document).on("click", "#ul_accounts li", function(){
			var userId = $(this).find("#input_accounts_userId").val();
			var accountId = $(this).find("#input_accounts_accountId").val();
			var accountName = $(this).find("#input_accounts_accountName").val();
			
			$("#input_create_userId").val(userId);
			
			$("#btn_accounts").empty();
			$("#btn_accounts").append("<img src='http://graph.facebook.com/"+accountId+"/picture?width=12&height=12'>&nbsp;"+accountName+"&nbsp;<span class='caret'></span>");
		});
		
		$(document).on("click", "#ul_queueTypes li", function(){
			var queueTypeId = $(this).find("#input_queueType_queueTypeId").val();
			var queueTypeDscr = $(this).find("#input_queueType_dscr").val();
			var queueTypeIconClass = $(this).find("#input_queueType_iconClass").val();
			
			$("#btn_queueTypes").empty();
			$("#btn_queueTypes").append("<i class='fa "+queueTypeIconClass+"'></i>&nbsp;"+queueTypeDscr+"&nbsp;<span class='caret'></span>");
			
			$("#input_create_queueTypeId").val(queueTypeId);
		});
		
		$(document).on("click", "#btn_create", function(){
			createObj.createQueue($(this));
		});
	},
	
	init: function(){
		
	},
	
	validation: function(){
		if($("#btn_accounts").text().indexOf("帳號") > 0){
			$("#span_result").text("請選擇建立帳號");
			return false;
		} 
		else {
			$("#span_result").text("");
		}
		
		if($("#btn_queueTypes").text().indexOf("類別") > 0){
			$("#span_result").text("請選擇類別");
			return false;
		} 
		else {
			$("#span_result").text("");
		}
		
		if($("#input_title").val().length == 0){
			$("#input_title").parent().parent().addClass("has-error");
			$("#span_result").text("請輸入名稱");
			return false;
		} 
		else {
			$("#input_title").parent().parent().removeClass("has-error");
			$("#span_result").text("");
		}
		if($("#input_phone").val().length == 0){
			$("#input_phone").parent().parent().addClass("has-error");
			$("#span_result").text("請輸入電話");
			return false;
		} 
		else {
			$("#input_phone").parent().parent().removeClass("has-error");
			$("#span_result").text("");
		}
		if($("#input_address").val().length == 0){
			$("#input_address").parent().parent().addClass("has-error");
			$("#span_result").text("請輸入地址");
			return false;
		} 
		else {
			$("#input_address").parent().parent().removeClass("has-error");
			$("#span_result").text("");
		}
		if($("#textarea_dscr").val().length == 0){
			$("#textarea_dscr").parent().parent().addClass("has-error");
			$("#span_result").text("請輸入簡介");
			return false;
		} 
		else {
			$("#textarea_dscr").parent().parent().removeClass("has-error");
			$("#span_result").text("");
		}
		return true;
	},
	
	createQueue: function(btnObj){
		
		if(!createObj.validation()){
			return;
		}
		
		var body = {
			userId: $("#input_create_userId").val(),
			title: $("#input_title").val(),
			phone: $("#input_phone").val(),
			address: $("#input_address").val(),
			dscr: $("#textarea_dscr").val(),
			queueTypeId: $("#input_create_queueTypeId").val()
		};
		
		ajaxUtilObj.callJsonAJAX(ajaxUtilObj.POST, "/queues/add", JSON.stringify(body))
		.done(function(httpResponse){
			$("div .panel-body").empty();
			$("div .panel-body").append("<h1>建立成功</h1>");
		});
	}
}
