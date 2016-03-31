$(document).ready(function(){
	queueObj.registerEvent();
	queueObj.init();
});

var queueObj = {
	
	registerEvent: function(){
		
		$("button[name=btn_queuing]").on("click", function(){
			queueObj.queuing($(this));
		});
		
		$("button[name=btn_cancel]").on("click", function(){
			queueObj.cancel($(this));
		});
		
		$("button[name=btn_favorite]").on("click", function(){
			queueObj.favorite($(this));
		});
		
		$(document).on("click", "button[name=btn_queuing_success]", function(event){
			queueObj.updateWaittingStatus($(this), 1);
		});
		
		$(document).on("click", "button[name=btn_queuing_pass]", function(event){
			queueObj.updateWaittingStatus($(this), 2);
		});
	},
	
	init: function(){
		
	},
	
	queuing: function(btnObj){
		var body = {
			userId: $("#userId", window.parent.document).val(),
			queueId: $(btnObj).val()
		};
		var actionUrl = "/queuing/add";
		ajaxUtilObj.callAJAX(ajaxUtilObj.POST, actionUrl, JSON.stringify(body), function(httpResponse){
			if(!httpResponse.success){
				$("#div_"+$(btnObj).val()+" #i_result").addClass("fa-close").css("color", "red").text(httpResponse.returnMessage);
				return;
			}
			
			var queuing = httpResponse.returnObject;
			$("#div_"+$(btnObj).val()+" #input_queuingId").val(queuing.queuingId);
			$("#div_"+$(btnObj).val()+" #span_queueNum").text(queuing.queueNum);
			$("#div_"+$(btnObj).val()+" #i_result").addClass("fa-check").css("color", "red");
			$(btnObj).css("display", "none");
			$("#div_"+$(btnObj).val()+" #btn_cancel").css("display", "inline-block");
		});
	},
	
	cancel: function(btnObj){
		var body = {
			queuingId: $("#div_"+$(btnObj).val()+" #input_queuingId").val()
		};
		var actionUrl = "/queuing/remove";
		ajaxUtilObj.callAJAX(ajaxUtilObj.DELETE, actionUrl, JSON.stringify(body), function(httpResponse){
			if(!httpResponse.success){
				$("#div_"+$(btnObj).val()+" #i_result").append("<div class='label label-danger'>"+httpResponse.returnMessage+"</div>");
				return;
			}
			
			$("#div_"+$(btnObj).val()+" #input_queuingId").val("");
			$("#div_"+$(btnObj).val()+" #i_result").addClass("label").addClass("label-success").text("取消成功");
			$(btnObj).css("display", "none");
			$("#div_"+$(btnObj).val()+" #btn_queuing").css("display", "inline-block");
		});
	},
	
	favorite: function(btnObj){
		if($(btnObj).find("#i_heart").hasClass("fa-heart")){
			var body = {
				favoriteId: $("#div_"+$(btnObj).val()+" #input_favoriteId").val()
			};
			var actionUrl = "/favorite/remove";
			ajaxUtilObj.callAJAX(ajaxUtilObj.DELETE, actionUrl, JSON.stringify(body), function(httpResponse){
				if(!httpResponse.success){
					$("#div_"+$(btnObj).val()+" #span_result").append("<div class='label label-danger'>"+httpResponse.returnMessage+"</div>");
					return;
				}
				
				$("#div_"+$(btnObj).val()+" #i_heart").removeClass("fa-heart").addClass("fa-heart-o");
				$("#div_"+$(btnObj).val()+" #input_favoriteId").val("");
			});
		}
		else {
			var body = {
				userId: $("#userId", window.parent.document).val(),
				queueId: $(btnObj).val()
			};
			var actionUrl = "/favorite/add";
			ajaxUtilObj.callAJAX(ajaxUtilObj.POST, actionUrl, JSON.stringify(body), function(httpResponse){
				if(!httpResponse.success){
					$("#div_"+$(btnObj).val()+" #span_result").append("<div class='label label-danger'>"+httpResponse.returnMessage+"</div>");
					return;
				}
				
				var favoriteId = httpResponse.returnObject;
				$("#div_"+$(btnObj).val()+" #i_heart").removeClass("fa-heart-o").addClass("fa-heart");
				$("#div_"+$(btnObj).val()+" #input_favoriteId").val(favoriteId);
			});
		}
	},
	
	updateWaittingStatus: function(btnObj, status){
		var body = {
			queuingId: $(btnObj).val(),
			status: status
		};
		var actionUrl = "/queuing/updateWaittingStatus";
		ajaxUtilObj.callAJAX(ajaxUtilObj.PATCH, actionUrl, JSON.stringify(body), function(httpResponse){
			if(!httpResponse.success){
				$("#div_"+$(btnObj).val()+" #span_result").append("<div class='label label-danger'>"+httpResponse.returnMessage+"</div>");
				return;
			}
			$("#div_queuing_"+$(btnObj).val()).fadeOut("slow");
		});
	}
}
