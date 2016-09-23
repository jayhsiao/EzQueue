var queueDetailObj = {
	
	registerEvent: function(){
		
		$(document).on("click", "#btn_back", function(event){
			queueDetailObj.back();
		});
		
		$(document).on("click", "#span_star i", function(event){
			queueDetailObj.star($(this));
		});
		
		$(document).on("click", "#btn_favorite", function(event){
			queueDetailObj.favorite($(this));
		});
		
		$(document).on("click", "#btn_queuing", function(event){
			queueDetailObj.queuing($(this));
		});
		
		$(document).on("click", "#btn_edit_confirm", function(event){
			queueDetailObj.edit();
		});
		
		$(document).on("click", "#btn_delete", function(event){
			queueDetailObj.deleteQueue();
		});
		
		$(document).on("click", "#btn_open", function(event){
			queueDetailObj.open();
		});
		
		$(document).on("click", "#btn_close", function(event){
			queueDetailObj.close();
		});
		
		$(document).on("click", "#btn_save", function(event){
			queueDetailObj.save();
		});
		
		$(document).on("click", "button[name='btn_waiting_success']", function(event){
			queueDetailObj.success($(this));
		});
		
		$(document).on("click", "button[name='btn_pass_success']", function(event){
			queueDetailObj.success($(this));
		});
		
		$(document).on("click", "button[name='btn_pass']", function(event){
			queueDetailObj.pass($(this));
		});
		
	},
	
	init: function(){
		console.log($("#input_userId").val());
		console.log($("#input_userId").val().length);
		if($("#input_userId").val().length > 0){
			$("#tr_user_star").show();
			$("#tr_queuing_num").show();
			
			$("#btn_favorite").show();
			$("#btn_queuing").show();
			
			if($("#input_detail_queueStatus").val() == "CLOSE"){
				$("#btn_favorite").attr("disabled", "disabled").addClass("disabled");
				$("#btn_queuing").attr("disabled", "disabled").addClass("disabled");
				$("#span_message").text("本日已結束排隊");
			}
		}
	},
	
	back: function(){
		$("#div_detail").empty();
		$("#div_list").show();
	}, 
	
	star : function(iObj){
		if($("#input_detail_starId").val().length == 0){
			$(iObj).parent().find("i:lt("+($(iObj).index()+1)+")").removeClass("fa-star-o").addClass("fa-star");
			$(iObj).parent().find("i:gt("+$(iObj).index()+")").removeClass("fa-star").addClass("fa-star-o");
			
			var body = {
				userId: $("#input_userId").val(),
				queueId: $("#input_detail_queueId").val(),
				starNum: $(iObj).index() + 1
			};
			ajaxUtilObj.callJsonAJAX(ajaxUtilObj.POST, "/stars/add", JSON.stringify(body))
			.done(function(starId){
				$("#input_detail_starId").val(starId);
			})
			.always(function(){
				commonObj.unblockUI();
			});
		}
		else{
			if($(iObj).index() == 0 && $(iObj).parent().find(".fa-star").length == 1){
				$(iObj).removeClass("fa-star").addClass("fa-star-o");
				
				var body = {
					starId: $("#input_detail_starId").val()
				};
				ajaxUtilObj.callJsonAJAX(ajaxUtilObj.DELETE, "/stars/remove", JSON.stringify(body))
				.done(function(){
					$("#input_detail_starId").val("");
				})
				.always(function(){
					commonObj.unblockUI();
				});
			}
			else{
				$(iObj).parent().find("i:lt("+($(iObj).index()+1)+")").removeClass("fa-star-o").addClass("fa-star");
				$(iObj).parent().find("i:gt("+$(iObj).index()+")").removeClass("fa-star").addClass("fa-star-o");
				
				var body = {
					starId: $("#input_detail_starId").val(), 
					starNum: $(iObj).index() + 1
				};
				ajaxUtilObj.callJsonAJAX(ajaxUtilObj.PATCH, "/stars/update", JSON.stringify(body))
				.always(function(){
					commonObj.unblockUI();
				});
			}
		}
	}, 
	
	favorite: function(btnObj){
		var iObj = $(btnObj).find("i");
		
		if($(iObj).hasClass("fa-heart")){
			var body = {
				favoriteId: $("#input_detail_favoriteId").val(), 
				queueId: $("#input_detail_queueId").val()
			};
			
			ajaxUtilObj.callJsonAJAX(ajaxUtilObj.DELETE, "/favorites/remove", JSON.stringify(body))
			.done(function(favoriteCount){
				$(iObj).removeClass().addClass("fa").addClass("fa-heart-o");
				$(".favorite_count").text(favoriteCount);
				favoriteCount
				$("#input_detail_favoriteId").val("");
			})
			.always(function(){
				commonObj.unblockUI();
			});
		}
		else {
			var body = {
				userId: $("#input_userId").val(),
				queueId: $("#input_detail_queueId").val()
			};
			
			ajaxUtilObj.callJsonAJAX(ajaxUtilObj.POST, "/favorites/add", JSON.stringify(body))
			.done(function(response){
				$(iObj).removeClass().addClass("fa").addClass("fa-heart");
				$(".favorite_count").text(response.favoriteCount);
				$("#input_detail_favoriteId").val(response.favoriteId);
			})
			.always(function(){
				commonObj.unblockUI();
			});
		}
	}, 
	
	queuing: function(btnObj){
		var iObj = $(btnObj).find("i");
		
		if($(iObj).hasClass("fa-user-times")){
			var body = {
				queuingId: $("#input_detail_queuingId").val(), 
				queueId: $("#input_detail_queueId").val()
			};
			
			ajaxUtilObj.callJsonAJAX(ajaxUtilObj.DELETE, "/queuings/remove", JSON.stringify(body))
			.done(function(queuingCount){
				$(iObj).removeClass().addClass("fa").addClass("fa-user-plus");
				$(".queuing_count").text(queuingCount);
				$("#input_detail_queuingId").val("");
				$("#span_queueNum").text("");
			})
			.always(function(){
				commonObj.unblockUI();
			});
		}
		else {
			var body = {
				userId: $("#input_userId").val(),
				queueId: $("#input_detail_queueId").val()
			};
			
			ajaxUtilObj.callJsonAJAX(ajaxUtilObj.POST, "/queuings/add", JSON.stringify(body))
			.done(function(response){
				$(iObj).removeClass().addClass("fa").addClass("fa-user-times");
				$(".queuing_count").text(response.queuingCount);
				$("#input_detail_queuingId").val(response.queuingId);
				$("#span_queueNum").text(response.queueNum);
			})
			.always(function(){
				commonObj.unblockUI();
			});
		}
	}, 
	
	edit: function(){
		$("#textarea_dscr").val($("#span_dscr").text());
		$("#input_phone").val($("#span_phone").text());
		$("#input_address").val($("#span_address").text());
	},
	
	deleteQueue: function(){
		var body = {
			queueId: $("#input_detail_queueId").val()
		};
		
		ajaxUtilObj.callJsonAJAX(ajaxUtilObj.DELETE, "/queues/remove", JSON.stringify(body))
		.done(function(){
			$("#div_detail").empty();
			$("#div_detail").html("<h1>刪除成功</h1>");
		})
		.always(function(){
			commonObj.unblockUI();
		});
	},
	
	open: function(){
		var body = {
			queueId: $("#input_detail_queueId").val()
		};
		
		ajaxUtilObj.callJsonAJAX(ajaxUtilObj.PATCH, "/queues/open", JSON.stringify(body))
		.done(function(){
			$("#span_message").text("");
			$("#btn_close_confirm").show();
			$("#btn_open_confirm").hide();
		})
		.always(function(){
			commonObj.unblockUI();
		});
	},
	
	close: function(){
		var body = {
			queueId: $("#input_detail_queueId").val()
		};
		
		ajaxUtilObj.callJsonAJAX(ajaxUtilObj.PATCH, "/queues/close", JSON.stringify(body))
		.done(function(){
			$("#span_message").text("");
			$("#btn_open_confirm").show();
			$("#btn_close_confirm").hide();
		})
		.always(function(){
			commonObj.unblockUI();
		});
	},
	
	save: function(){
		var body = {
			queueId: $("#input_detail_queueId").val(), 
			phone: $("#input_phone").val(), 
			address: $("#input_address").val(), 
			dscr: $("#textarea_dscr").val()
		};
		
		ajaxUtilObj.callJsonAJAX(ajaxUtilObj.PATCH, "/queues/edit", JSON.stringify(body))
		.done(function(queue){
			$("#span_dscr").text(queue.dscr);
			$("#span_phone").text(queue.phone);
			$("#span_address").text(queue.address);
			$("#iframe_map").attr("src", "http://maps.google.com.tw/maps?f=q&hl=zh-TW&geocode=&q="+queue.address+"&z=16&output=embed&t=");
		})
		.always(function(){
			commonObj.unblockUI();
		});
	}, 
	
	success: function(btnObj){
		var queuingId = $(btnObj).parent().find("input").val();
		
		var param = {
			queuingId: queuingId, 
			queueId: $("#input_detail_queueId").val()
		};
		
		queueDetailObj.next("/queuings/success?"+$.param(param));
	}, 
	
	pass: function(btnObj){
		var queuingId = $(btnObj).parent().find("input").val();
		
		var param = {
			queuingId: queuingId, 
			queueId: $("#input_detail_queueId").val()
		};
		
		queueDetailObj.next("/queuings/pass?"+$.param(param));
	}, 
	
	next: function(url){
		ajaxUtilObj.callJsonAJAX(ajaxUtilObj.GET, url, null)
		.done(function(resultMap){
			console.log(resultMap);
			var waitingQueuings = resultMap.waitingQueuings;
			var passQueuings = resultMap.passQueuings;
			
			var waitingCount = resultMap.waitingCount;
			var passCount = resultMap.passCount;
			var queuingCount = resultMap.queuingCount;
			var waitingLessCount = resultMap.waitingLessCount;
			var passLessCount = resultMap.passLessCount;
			
			queueDetailObj.getNextHtml(waitingQueuings, "#table_waiting");
			queueDetailObj.getNextHtml(passQueuings, "#table_pass");
			
			$("#span_queuing_count").text(queuingCount);
			$("#span_waiting_count").text(waitingCount);
			$("#span_pass_count").text(passCount);
			$(".queuing_count").text(queuingCount);
			$("#span_waiting_less_count").text(waitingLessCount);
			$("#span_pass_less_count").text(passLessCount);
		})
		.always(function(){
			commonObj.unblockUI();
		});
	}, 
	
	getNextHtml: function(queuings, tableId){
		var length = queuings.length;
		
		var trHtml = "";
		for(var i=0; i<length; i++){
			var queuing = queuings[i];
			trHtml += "<tr>";
			trHtml += "<td align='center' width='20%'><h1><span class='label label-default'>"+queuing.queueNum+"</span></h1></td>";
			trHtml += "<td align='center' width='40%'>";
			trHtml += 	"<img src='http://graph.facebook.com/"+queuing.user.facebookId+"/picture?width=50&height=50'><br/>"+queuing.user.name;
			trHtml += "</td>";
			trHtml += "<td width='40%'>";
			
			if(tableId == "#table_waiting"){
				trHtml += 	"<button type='button' class='btn btn-default' name='btn_waiting_success'><h4><i class='fa fa-check'></i></h4></button>&nbsp;";
				trHtml += 	"<button type='button' class='btn btn-default' name='btn_pass'><h4><i class='fa fa-clock-o'></i></h4></button>";
			}
			else if(tableId == "#table_pass"){
				trHtml += 	"<button type='button' class='btn btn-default' name='btn_pass_success'><h4><i class='fa fa-check'></i></h4></button>&nbsp;";
			}
			
			trHtml += 	"<input type='hidden' value='"+queuing.queuingId+"'";
			trHtml += "</td>";
			trHtml += "</tr>";
		}
		
		$(tableId).empty();
		$(tableId).append(trHtml);
	}
	
}
