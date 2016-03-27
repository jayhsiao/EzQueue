$(document).ready(function(){
	ezqueueObj.registerEvent();
	ezqueueObj.init();
});

var ezqueueObj = {
	
	registerEvent: function(){
		
		$("#btn_create").on("click", function(){
			ezqueueObj.createQueue();
		});
		
		$("button[name=btn_queuing]").on("click", function(){
			ezqueueObj.queuing($(this));
		});
		
		$("button[name=btn_edit]").on("click", function(){
			ezqueueObj.edit($(this));
		});
		
		$("button[name=btn_save]").on("click", function(){
			ezqueueObj.save($(this));
		});
	},
	
	init: function(){
		if($("#isCreate").val() == "true"){
			$("button[name=btn_create]").show();
		}
		else if($("#isPromotion").val() == "true"){
			$("button[name=btn_queuing]").show();
		}
		else if($("#isMyQueues").val() == "true"){
			$("button[name=btn_edit]").show();
			$("button[name=btn_delete]").show();
		}
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
	},
	
	queuing: function(btnObj){
		var body = {
			userId: $("#userId", window.parent.document).val(),
			queueId: $(btnObj).val()
		};
		var actionUrl = "/queuing/add";
		var httpResponse = utilObj.callAJAX("POST", actionUrl, JSON.stringify(body));
		if(!httpResponse.success){
			$("#div_"+$(btnObj).val()+" .panel-primary").addClass("panel-danger");
			$(".panel-primary").removeClass();
			$(".panel-title").text(httpResponse.returnMessage);
			return;
		}
		
		$(".panel-primary").addClass("panel-success");
		$(".panel-body").empty();
		$(".panel-body").append("Success");
	},
	
	edit: function(btnObj){
		var queueId = $(btnObj).val();
		
		$("#div_"+queueId+" #textarea_dscr").val($("#div_"+queueId+" #div_dscr").text());
		
		$("#div_"+queueId+" #div_textarea").show();
		$("#div_"+queueId+" #div_btn-group").show();
		$("#div_"+queueId+" #div_avgWaittingTime").hide();
		
		$("#div_"+queueId+" #btn_save").show();
		$("#div_"+queueId+" #btn_edit").hide();
		$("#div_"+queueId+" #btn_delete").hide();
	}
}
