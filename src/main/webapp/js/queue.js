$(document).ready(function(){
	queueObj.registerEvent();
	queueObj.init();
});

var queueObj = {
	
	registerEvent: function(){
		
		$(document).on("mouseover", ".modal", function(event){
			$("#input_queueId").val("");
			$("#input_promotionId").val("");
			$("#input_favoriteId").val("");
			$("#input_queuingId").val("");
			$("#input_isMyQueues").val("");
			
			$("#input_queueId").val($(this).find("#input_map_queueId").val());
			$("#input_promotionId").val($(this).find("#input_map_promotionId").val());
			$("#input_favoriteId").val($(this).find("#input_map_favoriteId").val());
			$("#input_queuingId").val($(this).find("#input_map_queuingId").val());
			$("#input_isMyQueues").val($(this).find("#input_map_isMyQueues").val());
		});
		
		$(document).on("click", ".star i", function(event){
			$(this).parent().find("i:lt("+($(this).index()+1)+")").removeClass("fa-star-o").removeClass("star-o-color").addClass("fa-star").addClass("star-color");
			$(this).parent().find("i:gt("+$(this).index()+")").removeClass("fa-star").removeClass("star-color").addClass("fa-star-o").addClass("star-o-color");
			queueObj.star($(this));
		});
		
		$(document).on("click", "button[name=btn_queuing]", function(event){
			queueObj.queuing($(this));
		});
		
		$(document).on("click", "button[name=btn_cancel]", function(event){
			queueObj.cancel($(this));
		});
		
		$(document).on("click", "button[name=btn_favorite]", function(event){
			queueObj.favorite($(this));
		});
		
		$(document).on("click", "button[name=btn_queuing_success]", function(event){
			queueObj.updateStatus($(this), 1);
		});
		
		$(document).on("click", "button[name=btn_delete]", function(event){
			queueObj.deleteQueue($(this));
		});
		
		$(document).on("click", "button[name=btn_edit]", function(event){
			queueObj.edit($(this));
		});
		
		$(document).on("click", "button[name=btn_back]", function(event){
			queueObj.back($(this));
		});
		
		$(document).on("click", "button[name=btn_save]", function(event){
			queueObj.save($(this));
		});
		
	},
	
	init: function(){
		
	},
	
	queuing: function(btnObj){
		var body = {
			userId: $("#userId", window.parent.document).val(),
			queueId: $("#input_queueId").val()
		};
		
		var spanObj = $("#div_info_"+$("#input_queueId").val()+" #span_spinner");
		ajaxUtilObj.callJsonAJAX(ajaxUtilObj.POST, "/queuing/add", JSON.stringify(body), btnObj, spanObj, function(httpResponse){
			if(!httpResponse.success){
				$("#div_info_"+$("#input_queueId").val()+" #span_result").addClass("label").addClass("label-danger").text(httpResponse.returnMessage).fadeIn(0).fadeOut(2000);
				return;
			}
			$("#div_info_"+$("#input_queueId").val()+" #span_result").addClass("label").addClass("label-success").text("排隊成功").fadeIn(0).fadeOut(2000);
			var queuing = httpResponse.returnObject;
			$("#div_info_"+$("#input_queueId").val()+" #input_map_queuingId").val(queuing.queuingId);
			$("#input_queuingId").val(queuing.queuingId);
			
			$("#div_info_"+$("#input_queueId").val()+" #span_queueNum").text(queuing.queueNum);
			$("#div_info_"+$("#input_queueId").val()+" #btn_queuing").css("display", "none");
			$("#div_info_"+$("#input_queueId").val()+" #btn_cancel").css("display", "inline-block");
		});
	},
	
	cancel: function(btnObj){
		var body = {
			queuingId: $("#input_queuingId").val()
		};
		
		var spanObj = $("#div_info_"+$("#input_queueId").val()+" #span_spinner");
		ajaxUtilObj.callJsonAJAX(ajaxUtilObj.DELETE, "/queuing/remove", JSON.stringify(body), btnObj, spanObj, function(httpResponse){
			if(!httpResponse.success){
				$("#div_info_"+$("#input_queueId").val()+" #span_result").addClass("label").addClass("label-danger").text(httpResponse.returnMessage).fadeIn(0).fadeOut(2000);
				return;
			}
			$("#div_info_"+$("#input_queueId").val()+" #span_result").addClass("label").addClass("label-success").text("取消成功").fadeIn(0).fadeOut(2000);
			$("#div_info_"+$("#input_queueId").val()+" #input_map_queuingId").val("");
			$("#input_map_queuingId").val("");
			
			$("#div_info_"+$("#input_queueId").val()+" #span_queueNum").text("");
			$("#div_info_"+$("#input_queueId").val()+" #btn_cancel").css("display", "none");
			$("#div_info_"+$("#input_queueId").val()+" #btn_queuing").css("display", "inline-block");
		});
	},
	
	favorite: function(btnObj){
		if($("#div_info_"+$("#input_queueId").val()).find("#i_heart").hasClass("fa-heart")){
			var body = {
				favoriteId: $("#input_favoriteId").val()
			};
			
			var spanObj = $("#div_info_"+$("#input_queueId").val()+" #span_spinner");
			ajaxUtilObj.callJsonAJAX(ajaxUtilObj.DELETE, "/favorite/remove", JSON.stringify(body), btnObj, spanObj, function(httpResponse){
				if(!httpResponse.success){
					$("#div_info_"+$("#input_queueId").val()+" #span_result").addClass("label").addClass("label-danger").text(httpResponse.returnMessage).fadeIn(0).fadeOut(2000);
					return;
				}
				$("#div_info_"+$("#input_queueId").val()+" #i_heart").removeClass("fa-heart").addClass("fa-heart-o");
				$("#div_info_"+$("#input_queueId").val()+" #input_map_favoriteId").val("");
				$("#input_favoriteId").val("");
			});
		}
		else {
			var body = {
				userId: $("#userId", window.parent.document).val(),
				queueId: $("#input_queueId").val()
			};
			
			var spanObj = $("#div_info_"+$("#input_queueId").val()+" #span_spinner");
			ajaxUtilObj.callJsonAJAX(ajaxUtilObj.POST, "/favorite/add", JSON.stringify(body), btnObj, spanObj, function(httpResponse){
				if(!httpResponse.success){
					$("#div_info_"+$("#input_queueId").val()+" #span_result").addClass("label").addClass("label-danger").text(httpResponse.returnMessage).fadeIn(0).fadeOut(2000);
					return;
				}
				var favoriteId = httpResponse.returnObject;
				$("#div_info_"+$("#input_queueId").val()+" #i_heart").removeClass("fa-heart-o").addClass("fa-heart");
				$("#div_info_"+$("#input_queueId").val()+" #input_map_favoriteId").val(favoriteId);
				$("#input_favoriteId").val(favoriteId);
			});
		}
	},
	
	star: function(starObj){
		var body = {
			userId: $("#userId", window.parent.document).val(),
			queueId: $("#input_queueId").val(),
			star: $(starObj).index()+1
		};
		ajaxUtilObj.callJsonAJAX(ajaxUtilObj.PATCH, "/star/add", JSON.stringify(body), null, null, function(httpResponse){
			if(!httpResponse.success){
				$("#div_info_"+$("#input_queueId").val()+" #span_result").addClass("label").addClass("label-danger").text(httpResponse.returnMessage);
				return;
			}
			$("#div_info_"+$("#input_queueId").val()+" #span_result").addClass("label").addClass("label-success").text("打分成功").fadeIn(0).fadeOut(2000);
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
	
	back: function(btnObj){
		$(btnObj).hide();
		$(btnObj).parent().find("#btn_save").hide();
		$(btnObj).parent().find("#btn_delete").show();
		$(btnObj).parent().find("#btn_edit").show();
		$("#div_info_"+$("#input_queueId").val()+" #span_phone").show();
		$("#div_info_"+$("#input_queueId").val()+" #input_phone").val($("#div_info_"+$("#input_queueId").val()+" #span_phone").text()).hide();
		$("#div_info_"+$("#input_queueId").val()+" #span_address").show();
		$("#div_info_"+$("#input_queueId").val()+" #input_address").val($("#div_info_"+$("#input_queueId").val()+" #span_address").text()).hide();
		$("#div_info_"+$("#input_queueId").val()+" #span_dscr").show();
		$("#div_info_"+$("#input_queueId").val()+" #textarea_dscr").text($("#div_info_"+$("#input_queueId").val()+" #span_dscr").text()).hide();
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
			if(!httpResponse.success){
				$("#div_info_"+$("#input_queueId").val()+" #span_result").addClass("label").addClass("label-danger").text(httpResponse.returnMessage).fadeIn(0).fadeOut(2000);;
				return;
			}
			$("#div_info_"+$("#input_queueId").val()+" #span_result").addClass("label").addClass("label-success").text("修改成功").fadeIn(0).fadeOut(2000);
			
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
			if(!httpResponse.success){
				$("#div_info_"+$("#input_queueId").val()+" #span_result").addClass("label").addClass("label-danger").text(httpResponse.returnMessage).fadeIn(0).fadeOut(2000);
				return;
			}
			$("#div_info_"+$("#input_queueId").val()+" #span_result").addClass("label").addClass("label-success").text("刪除成功").fadeIn(0).fadeOut(3000);
			$(btnObj).parent().find("#btn_close").click();
			$("#div_"+$("#input_queueId").val()).fadeOut(1000);
		});
	},
	
	updateStatus: function(btnObj, status){
		var body = {
			queuingId: $(btnObj).val(),
			status: status
		};
		
		ajaxUtilObj.callJsonAJAX(ajaxUtilObj.PATCH, "/queuing/updateStatus", JSON.stringify(body), btnObj, null, function(httpResponse){
			if(!httpResponse.success){
				$("#div_info_"+$("#input_queueId").val()+" #span_result").addClass("label").addClass("label-danger").text(httpResponse.returnMessage);
				return;
			}
			$("#div_queuing_"+$(btnObj).val()).fadeOut(1000);
		});
	}
}
