$(document).ready(function(){
	queueObj.registerEvent();
	queueObj.init();
});

var queueObj = {
	
	registerEvent: function(){
		
		
		$(document).on("click", ".star i", function(event){
			var star = 0;
			if($(this).index() == 0 && $(this).parent().find(".fa-star").length == 1){
				$(this).removeClass("fa-star").addClass("fa-star-o");
			}
			else{
				$(this).parent().find("i:lt("+($(this).index()+1)+")").removeClass("fa-star-o").addClass("fa-star");
				$(this).parent().find("i:gt("+$(this).index()+")").removeClass("fa-star").addClass("fa-star-o");
				star = $(this).index() + 1;
			}
			queueObj.addStar(star);
		});
		
		$(document).on("click", "button[name=btn_queuing]", function(event){
			queueObj.queuing($(this));
		});
		
		$(document).on("click", "button[name=btn_favorite]", function(event){
			queueObj.favorite($(this));
		});
		
		$(document).on("click", "button[name=btn_queuing_success]", function(event){
			var queuingId = $(this).parent().find("#input_waitting").attr("id");
			queueObj.updateStatus($(this), queuingId, 1);
		});
		
		$(document).on("click", "button[name=btn_delete]", function(event){
			queueObj.deleteQueue($(this));
		});
		
		$(document).on("click", "button[name=btn_edit]", function(event){
			queueObj.edit($(this));
		});
		
		$(document).on("click", "#btn_back", function(event){
			queueObj.back();
		});
		
		$(document).on("click", "button[name=btn_save]", function(event){
			queueObj.save($(this));
		});
		
	},
	
	init: function(){
		
	},
	
	queuing: function(btnObj){
		var iObj = $(btnObj).find("i");
		if($(iObj).hasClass("fa-user-times")){
			var body = {
				queuingId: $("#input_queuingId").val()
			};
			
			$(iObj).removeClass().addClass("fa").addClass("fa-spinner").addClass("fa-spin");
			ajaxUtilObj.callJsonAJAX(ajaxUtilObj.DELETE, "/queuings/remove", JSON.stringify(body), btnObj)
			.done(function(){
				$(iObj).removeClass().addClass("fa").addClass("fa-user-plus");
				$("#div_"+$("#input_queueId").val()+" #input_map_queuingId").val("");
				$("#input_queuingId").val("");
				
				$("#div_info_"+$("#input_queueId").val()+" #span_queueNum").text("");
			});
		}
		else {
			var body = {
				userId: $("#input_userId").val(),
				queueId: $("#input_queueId").val()
			};
			
			$(iObj).removeClass().addClass("fa").addClass("fa-spinner").addClass("fa-spin");
			ajaxUtilObj.callJsonAJAX(ajaxUtilObj.POST, "/queuings/add", JSON.stringify(body), btnObj)
			.done(function(queuing){
				$(iObj).removeClass().addClass("fa").addClass("fa-user-times");
				$("#div_"+$("#input_queueId").val()+" #input_map_queuingId").val(queuing.queuingId);
				$("#input_queuingId").val(queuing.queuingId);
				console.log(queuing);
				$("#div_info_"+$("#input_queueId").val()+" #span_queueNum").text(queuing.queueNum);
			});
		}
	},
	
	favorite: function(btnObj){
		var iObj = $(btnObj).find("i");
		if($(iObj).hasClass("fa-heart")){
			var body = {
				favoriteId: $("#input_favoriteId").val()
			};
			
			$(iObj).removeClass().addClass("fa").addClass("fa-spinner").addClass("fa-spin");
			ajaxUtilObj.callJsonAJAX(ajaxUtilObj.DELETE, "/favorites/remove", JSON.stringify(body), btnObj)
			.done(function(){
				$(iObj).removeClass().addClass("fa").addClass("fa-heart-o");
				$("#div_"+$("#input_queueId").val()+" #input_map_favoriteId").val("");
				$("#input_favoriteId").val("");
			});
		}
		else {
			var body = {
				userId: $("#input_userId").val(),
				queueId: $("#input_queueId").val()
			};
			
			$(iObj).removeClass().addClass("fa").addClass("fa-spinner").addClass("fa-spin");
			ajaxUtilObj.callJsonAJAX(ajaxUtilObj.POST, "/favorites/add", JSON.stringify(body), btnObj)
			.done(function(favoriteId){
				$(iObj).removeClass().addClass("fa").addClass("fa-heart");
				$("#div_"+$("#input_queueId").val()+" #input_map_favoriteId").val(favoriteId);
				$("#input_favoriteId").val(favoriteId);
			});
		}
	},
	
	addStar: function(star){
		var body = {
			userId: $("#input_userId").val(),
			queueId: $("#input_queueId").val(),
			star: star
		};
		ajaxUtilObj.callJsonAJAX(ajaxUtilObj.PATCH, "/stars/add", JSON.stringify(body), null)
		.done(function(httpResponse){
			if("0000" != httpResponse.returnCode){
				$("#div_info_"+$("#input_queueId").val()+" #span_result").text(httpResponse.returnMessage);
				return;
			}
		});
	},
	
	edit: function(btnObj){
		$(btnObj).hide();
		$(btnObj).parent().find("#btn_delete").hide();
		$(btnObj).parent().find("#btn_save").show();
		$(btnObj).parent().find("#btn_back").show();
		$("#div_info_"+$("#input_queueId").val()+" #span_phone").hide();
		$("#div_info_"+$("#input_queueId").val()+" #input_phone").show();
		$("#div_info_"+$("#input_queueId").val()+" #span_address").hide();
		$("#div_info_"+$("#input_queueId").val()+" #input_address").show();
		$("#div_info_"+$("#input_queueId").val()+" #span_dscr").hide();
		$("#div_info_"+$("#input_queueId").val()+" #textarea_dscr").show();
	},
	
	back: function(){
		$("#div_detail").empty();
		$("#div_main").show();
	},
	
	save: function(btnObj){
		var body = {
			queueId: $("#input_queueId").val(),
			phone: $("#div_info_"+$("#input_queueId").val()+" #input_phone").val(),
			address: $("#div_info_"+$("#input_queueId").val()+" #input_address").val(),
			dscr: $("#div_info_"+$("#input_queueId").val()+" #textarea_dscr").val()
		};
		
		var spanObj = $("#div_info_"+$("#input_queueId").val()+" #span_spinner");
		ajaxUtilObj.callJsonAJAX(ajaxUtilObj.PATCH, "/queue/update", JSON.stringify(body), btnObj, spanObj, function(httpResponse){
			if("0000" != httpResponse.returnCode){
				$("#div_info_"+$("#input_queueId").val()+" #span_result").text(httpResponse.returnMessage).fadeIn(0).fadeOut(2000);;
				return;
			}
			$("#div_info_"+$("#input_queueId").val()+" #span_result").text("修改成功").fadeIn(0).fadeOut(2000);
			
			$(btnObj).hide();
			$(btnObj).parent().find("#btn_back").hide();
			$(btnObj).parent().find("#btn_delete").show();
			$(btnObj).parent().find("#btn_edit").show();
			$("#div_info_"+$("#input_queueId").val()+" #input_phone").hide();
			$("#div_info_"+$("#input_queueId").val()+" #input_address").hide();
			$("#div_info_"+$("#input_queueId").val()+" #textarea_dscr").hide();
			
			$("#div_info_"+$("#input_queueId").val()+" #span_phone").text($("#div_info_"+$("#input_queueId").val()+" #input_phone").val()).hide();
			$("#div_info_"+$("#input_queueId").val()+" #span_address").text($("#div_info_"+$("#input_queueId").val()+" #input_address").val()).hide();
			$("#div_info_"+$("#input_queueId").val()+" #span_dscr").text($("#div_info_"+$("#input_queueId").val()+" #textarea_dscr").val()).hide();
			
			$("#div_info_"+$("#input_queueId").val()+" #span_phone").show();
			$("#div_info_"+$("#input_queueId").val()+" #span_address").show();
			$("#div_info_"+$("#input_queueId").val()+" #span_dscr").show();
		});
	},
	
	deleteQueue: function(btnObj){
		var body = {
			queueId: $("#input_queueId").val()
		};
		
		var spanObj = $("#div_info_"+$("#input_queueId").val()+" #span_spinner");
		ajaxUtilObj.callJsonAJAX(ajaxUtilObj.DELETE, "/queue/remove", JSON.stringify(body), btnObj, spanObj, function(httpResponse){
			if("0000" != httpResponse.returnCode){
				$("#div_info_"+$("#input_queueId").val()+" #span_result").text(httpResponse.returnMessage).fadeIn(0).fadeOut(2000);
				return;
			}
			$("#div_info_"+$("#input_queueId").val()+" #span_result").text("刪除成功").fadeIn(0).fadeOut(3000);
			$(btnObj).parent().find("#btn_close").click();
			$("#div_"+$("#input_queueId").val()).fadeOut(1000);
		});
	},
	
	updateStatus: function(btnObj, queuingId, status){
		var body = {
			queuingId: queuingId,
			status: status
		};
		
		var spanObj = $("#div_info_"+$("#input_queueId").val()+" #span_spinner");
		ajaxUtilObj.callJsonAJAX(ajaxUtilObj.PATCH, "/queuing/updateStatus", JSON.stringify(body), btnObj, spanObj, function(httpResponse){
			if("0000" != httpResponse.returnCode){
				$("#div_info_"+$("#input_queueId").val()+" #span_result").text(httpResponse.returnMessage);
				return;
			}
			$("#div_queuing_"+$(btnObj).val()).fadeOut(1000);
		});
	}, 
	
	getHtml: function(url){
		mainObj.getHtml(null, url);
	}
}
