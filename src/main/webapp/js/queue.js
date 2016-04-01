$(document).ready(function(){
	queueObj.registerEvent();
	queueObj.init();
});

var queueObj = {
	
	registerEvent: function(){
		
		$(document).on("mouseover", "h1 .fa-star-o", function(event){
			$(this).parent().find("i:lt("+($(this).index()+1)+")").removeClass("fa-star-o").addClass("fa-star");
		});
		$(document).on("mouseover", "h1 .fa-star", function(event){
			$(this).parent().find("i:gt("+$(this).index()+")").removeClass("fa-star").addClass("fa-star-o");
		});
		
		$("button[name=btn_queuing]").on("click", function(){
			queueObj.queuing($(this));
		});
		
		$("button[name=btn_cancel]").on("click", function(){
			queueObj.cancel($(this));
		});
		
		$("button[name=btn_favorite]").on("click", function(){
			queueObj.favorite($(this));
		});
		
		$("button[name=btn_delete]").on("click", function(){
			queueObj.deleteQueue($(this));
		});
		
		$("button[name=btn_edit]").on("click", function(){
			queueObj.edit($(this));
		});
		
		$("button[name=btn_save]").on("click", function(){
			queueObj.save($(this));
		});
		
		$(document).on("click", "button[name=btn_queuing_success]", function(event){
			queueObj.updateStatus($(this), 1);
		});
		
		$(document).on("click", "button[name=btn_queuing_pass]", function(event){
			queueObj.updateStatus($(this), 2);
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
			$("#div_"+$(btnObj).val()+" #span_result").addClass("label").addClass("label-success").text("排隊成功");
			var queuing = httpResponse.returnObject;
			$("#div_"+$(btnObj).val()+" #input_queuingId").val(queuing.queuingId);
			$("#div_"+$(btnObj).val()+" #span_queueNum").text(queuing.queueNum);
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
				$("#div_"+$(btnObj).val()+" #span_result").addClass("label").addClass("label-danger").text(httpResponse.returnMessage);
				return;
			}
			$("#div_"+$(btnObj).val()+" #span_result").addClass("label").addClass("label-success").text("取消成功");
			$("#div_"+$(btnObj).val()+" #input_queuingId").val("");
			$("#div_"+$(btnObj).val()+" #span_queueNum").text("");
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
					$("#div_"+$(btnObj).val()+" #span_result").addClass("label").addClass("label-danger").text(httpResponse.returnMessage);
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
					$("#div_"+$(btnObj).val()+" #span_result").addClass("label").addClass("label-danger").text(httpResponse.returnMessage);
					return;
				}
				var favoriteId = httpResponse.returnObject;
				$("#div_"+$(btnObj).val()+" #i_heart").removeClass("fa-heart-o").addClass("fa-heart");
				$("#div_"+$(btnObj).val()+" #input_favoriteId").val(favoriteId);
			});
		}
	},
	
	edit: function(btnObj){
		$(btnObj).hide();
		$(btnObj).parent().find("#btn_delete").hide();
		$(btnObj).parent().find("#btn_save").show();
		$("#div_"+$(btnObj).val()+" #span_dscr").hide();
		$("#div_"+$(btnObj).val()+" #textarea_dscr").val($("#div_"+$(btnObj).val()+" #span_dscr").text()).show();
	},
	
	save: function(btnObj){
		var body = {
			queueId: $(btnObj).val(),
			dscr: $("#div_"+$(btnObj).val()+" #textarea_dscr").val()
		};
		var actionUrl = "/queue/update";
		ajaxUtilObj.callAJAX(ajaxUtilObj.PATCH, actionUrl, JSON.stringify(body), function(httpResponse){
			if(!httpResponse.success){
				$("#div_"+$(btnObj).val()+" #span_result").addClass("label").addClass("label-danger").text(httpResponse.returnMessage);
				return;
			}
			$("#div_"+$(btnObj).val()+" #span_result").addClass("label").addClass("label-success").text("修改成功");
			$(btnObj).hide();
			$(btnObj).parent().find("#btn_delete").show();
			$(btnObj).parent().find("#btn_edit").show();
			$("#div_"+$(btnObj).val()+" #span_dscr").text(body.dscr).show();
			$("#div_"+$(btnObj).val()+" #textarea_dscr").val("").hide();
		});
	},
	
	deleteQueue: function(btnObj){
		var body = {
			queueId: $(btnObj).val()
		};
		var actionUrl = "/queue/remove";
		ajaxUtilObj.callAJAX(ajaxUtilObj.DELETE, actionUrl, JSON.stringify(body), function(httpResponse){
			if(!httpResponse.success){
				$("#div_"+$(btnObj).val()+" #span_result").addClass("label").addClass("label-danger").text(httpResponse.returnMessage);
				return;
			}
			$("#div_"+$(btnObj).val()+" #span_result").addClass("label").addClass("label-success").text("刪除成功");
			$("#div_"+$(btnObj).val()).fadeOut(1000);
		});
	},
	
	updateStatus: function(btnObj, status){
		var body = {
			queuingId: $(btnObj).val(),
			status: status
		};
		var actionUrl = "/queuing/updateStatus";
		ajaxUtilObj.callAJAX(ajaxUtilObj.PATCH, actionUrl, JSON.stringify(body), function(httpResponse){
			if(!httpResponse.success){
				$("#div_"+$(btnObj).val()+" #span_result").addClass("label").addClass("label-danger").text(httpResponse.returnMessage);
				return;
			}
			$("#div_queuing_"+$(btnObj).val()).fadeOut(1000);
		});
	}
}
