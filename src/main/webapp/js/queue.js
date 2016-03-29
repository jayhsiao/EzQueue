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
				$("#div_"+$(btnObj).val()+" #span_result").addClass("label").addClass("label-danger").text(httpResponse.returnMessage);
				return;
			}
			
			var queueNum = httpResponse.returnObject;
			console.log(queueNum);
			$("#div_"+$(btnObj).val()+" #span_queueNum").text(queueNum);
			$("#div_"+$(btnObj).val()+" #span_result").addClass("label").addClass("label-success").text("排隊成功");
			$(btnObj).attr("disabled", true);
			$(btnObj).addClass("disabled");
		});
	},
	
	cancel: function(btnObj){
		var body = {
			queuingId: $("#div_"+$(btnObj).val()+" #input_queuingId").val()
		};
		var actionUrl = "/queuing/remove";
		ajaxUtilObj.callAJAX(ajaxUtilObj.DELETE, actionUrl, JSON.stringify(body), function(httpResponse){
			if(!httpResponse.success){
				$("#div_"+$(btnObj).val()+" #span_result").append("<div class='label label-danger'>"+httpResponse.returnMessage+"</div>");
				return;
			}
			
			$("#div_"+$(btnObj).val()).removeClass("panel-primary").addClass("panel-default")
			$("#div_"+$(btnObj).val()+" #span_result").addClass("label").addClass("label-success").text("取消成功");
			$("#div_"+$(btnObj).val()+" button").attr("disabled", true);
			$("#div_"+$(btnObj).val()+" button").addClass("disabled");
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
				
				$("#div_"+$(btnObj).val()+" #i_heart").removeClass("fa-heart-o").addClass("fa-heart");
			});
		}
	}
}
