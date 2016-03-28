$(document).ready(function(){
	// 註冊事件
	ezqueueObj.registerEvent();
	// 初始化
	ezqueueObj.init();
});

var ezqueueObj = {
		
	// 註冊事件
	registerEvent: function(){
		
		$("#div_menu a").on("click", function(){
			$("#div_menu a").removeClass("active");
			$(this).addClass("active");
		});
		
		$("#a_promotions").on("click", function(){
			ezqueueObj.getPromotionQueues();
		});
		
		$("#a_myQueue").on("click", function(){
			ezqueueObj.getMyQueues();
		});
		
		$("#a_queueing").on("click", function(){
			ezqueueObj.getQueuing();
		});
		
		$("#a_create").on("click", function(){
			ezqueueObj.createQueue();
		});
	},
	
	// 初始化
	init: function(){
		$('#mainFrame').css('height', $(window).height()+'px');
		$("#a_promotions").click();
	},
	
	getPromotionQueues: function(){
		$("#submitForm").prop("method", "GET");
		$("#submitForm").prop("action", "/ezQueue/promotionQueues");
		$("#submitForm").submit();
	},
	
	getMyQueues: function(){
		$("#submitForm").prop("method", "GET");
		$("#submitForm").prop("action", "/ezQueue/myQueues/"+$("#userId").val());
		$("#submitForm").submit();
	},
	
	getQueuing: function(){
		$("#submitForm").prop("method", "GET");
		$("#submitForm").prop("action", "/ezQueue/queuing/"+$("#userId").val());
		$("#submitForm").submit();
	},
	
	createQueue: function(){
		$("#submitForm").prop("method", "GET");
		$("#submitForm").prop("action", "/ezQueue/createQueue");
		$("#submitForm").submit();
	}
}
