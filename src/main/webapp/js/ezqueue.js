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
		
		$("#a_favorite").on("click", function(){
			ezqueueObj.getFavorite();
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
		$('#mainFrame').css('height', ($(window).height() - $(".page-header").height() - 100) +'px');
		$("#a_myQueue").click();
	},
	
	getPromotionQueues: function(){
		$("#submitForm").prop("method", "GET");
		$("#submitForm").prop("action", "/ezqueue/promotion/"+$("#userId").val());
		$("#submitForm").submit();
	},
	
	getFavorite: function(){
		$("#submitForm").prop("method", "GET");
		$("#submitForm").prop("action", "/ezqueue/favorite/"+$("#userId").val());
		$("#submitForm").submit();
	},
	
	getMyQueues: function(){
		$("#submitForm").prop("method", "GET");
		$("#submitForm").prop("action", "/ezqueue/myQueues/"+$("#userId").val());
		$("#submitForm").submit();
	},
	
	getQueuing: function(){
		$("#submitForm").prop("method", "GET");
		$("#submitForm").prop("action", "/ezqueue/queuing/"+$("#userId").val());
		$("#submitForm").submit();
	},
	
	createQueue: function(){
		$("#submitForm").prop("method", "GET");
		$("#submitForm").prop("action", "/ezqueue/createQueue");
		$("#submitForm").submit();
	}
}
