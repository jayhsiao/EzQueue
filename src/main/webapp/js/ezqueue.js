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
		
		$("#label_promotions").on("click", function(){
			ezqueueObj.getPromotions();
		});
		
		$("#label_create").on("click", function(){
			ezqueueObj.createQueueHtml();
		});
		
		$("#btn_create").on("click", function(){
			ezqueueObj.createQueue();
		});
	},
	
	// 初始化
	init: function(){
		// 自動觸發強力推薦
//		$("#label_promotions").click();
	},
	
	getPromotions: function(){
		var actionUrl = "/rest/queue/promotions";
		var httpResponse = utilObj.callAJAX("GET", actionUrl, null);
		if(!httpResponse.success){
			alert(httpResponse.returnMessage);
			return;
		}
		
		var promotionsQueues = httpResponse.returnObject;
		
		ezqueueObj.getQueues(promotionsQueues);
		
		$("#div_result").empty();
		$("#div_result").append();
	},
	
	// 取得排隊畫面
	getQueues: function(queues){
		var divHtml = "";
		for(var i=0; i<queues.length; i++){
			var sampleData = {
				queueId:    queues[i].queueId,
				queueCount: queues[i].userQueues.length,
				panelTitle: queues[i].user.name,
				panelBody:  queues[i].dscr,
				createDate: queues[i].createDate,
				btnHtml:    "<button type='button' id='"+btnId+"' value='"+queues[i].queueId+"' class='btn btn-default'>"+btnName+"</button>"
			};
			// 排隊模板
			divHtml += ezqueueObj.getQueueSample(sampleData);
		}
		return divHtml;
	},
	
	getQueueTemplate: function(templateData){
		var divHtml = "";
		divHtml += "<div id='div_"+templateData.id+"' class='row' style='width: 400px;'>";
			divHtml += "<div class='panel panel-primary'>";
				divHtml += "<div class='panel-heading'>";
					divHtml += "<table>";
					divHtml += "<tr>";
					
					var queueCount = "";
					if(!templateData.isCreate){
						queueCount = "<span class='badge'>"+templateData.queueCount+"</span>";
					}
					divHtml += "<td width='100%' nowrap><h2 class='panel-title'>"+queueCount + templateData.panelTitle+"</h2></td>";
					divHtml += "</tr>";
					divHtml += "</table>";
				divHtml += "</div>";
				divHtml += "<div class='panel-body'>";
					divHtml += "<p>"+templateData.panelBody+"</p><br/>";
				divHtml += "</div>";
			divHtml += "</div>";
		divHtml += "</div>";
		return divHtml;
	},
	
	createQueueHtml: function(){
		var panelBody = "";
		panelBody += "<input type='checkbox' id='startDate' name='enable' value='true' checked>開啟 ";
		panelBody += "<input type='checkbox' id='endDate' name='enable' value='false'>暫時關閉";
		panelBody += "<br/><br/>";
		panelBody += "排隊描述<br/>";
		panelBody += "<textarea id='textarea_dscr' name='dscr' rows='5' cols='40' maxlength='200'></textarea>";
		panelBody += "<br/><br/>";
		panelBody += "<button type='button' id='btn_create' class='btn btn-default'>開啟排隊</button>";
		
		var templateData = {
			id: "create",
			isCreate: true,
			panelTitle: $("#name").val(),
			panelBody:  panelBody
		};
		
		var divHtml = ezqueueObj.getQueueTemplate(templateData);
		
		$("#div_result").empty();
		$("#div_result").append(divHtml);
	},
	
	
	createQueue: function(){
		console.log("test");
		var body = {
			userId: $("#userId").val(),
			dscr: $("#textarea_dscr").val()
		};
		
		var actionUrl = "/queue/create";
		var httpResponse = utilObj.callAJAX("POST", actionUrl, JSON.stringify(body));
		if(!httpResponse.success){
			alert(httpResponse.returnMessage);
			return;
		}
	}
}
