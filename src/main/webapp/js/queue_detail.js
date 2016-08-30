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
		
		$(document).on("click", "#btn_edit", function(event){
			queueDetailObj.edit();
		});
		
		$(document).on("click", "#btn_delete", function(event){
			queueDetailObj.delete();
		});
		
		$(document).on("click", "#btn_save", function(event){
			queueDetailObj.save();
		});
		
		$(document).on("click", "#btn_revert", function(event){
			queueDetailObj.revert();
		});
		
		$(document).on("click", "button[name='btn_waiting_success']", function(event){
			queueDetailObj.success($(this), "WAITING");
		});
		
		$(document).on("click", "button[name='btn_pass_success']", function(event){
			queueDetailObj.success($(this), "PASS");
		});
		
		$(document).on("click", "button[name='btn_pass']", function(event){
			queueDetailObj.waitingToPass($(this));
		});
		
	},
	
	init: function(){
		if($("#input_userId").val().length > 0){
			$("#tr_user_star").show();
			$("#tr_queuing_num").show();
			
			$("#btn_favorite").show();
			$("#btn_queuing").show();
			
			$("#btn_back").attr("href", "#div_"+$("#input_detail_queueId").val());
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
				});
			}
			else{
				$(iObj).parent().find("i:lt("+($(iObj).index()+1)+")").removeClass("fa-star-o").addClass("fa-star");
				$(iObj).parent().find("i:gt("+$(iObj).index()+")").removeClass("fa-star").addClass("fa-star-o");
				
				var body = {
					starId: $("#input_detail_starId").val(), 
					starNum: $(iObj).index() + 1
				};
				ajaxUtilObj.callJsonAJAX(ajaxUtilObj.PATCH, "/stars/update", JSON.stringify(body));
			}
		}
	}, 
	
	favorite: function(btnObj){
		var iObj = $(btnObj).find("i");
		var spanDscrObj = $(btnObj).find("#span_favorite_dscr");
		
		if($(iObj).hasClass("fa-heart")){
			var body = {
				favoriteId: $("#input_detail_favoriteId").val()
			};
			
			$(iObj).removeClass().addClass("fa").addClass("fa-spinner").addClass("fa-spin");
			ajaxUtilObj.callJsonAJAX(ajaxUtilObj.DELETE, "/favorites/remove", JSON.stringify(body), btnObj, iObj)
			.done(function(){
				$(iObj).removeClass().addClass("fa").addClass("fa-heart-o");
				$(spanDscrObj).text("加入最愛");
				$("#input_detail_favoriteId").val("");
			});
		}
		else {
			var body = {
				userId: $("#input_userId").val(),
				queueId: $("#input_detail_queueId").val()
			};
			
			$(iObj).removeClass().addClass("fa").addClass("fa-spinner").addClass("fa-spin");
			ajaxUtilObj.callJsonAJAX(ajaxUtilObj.POST, "/favorites/add", JSON.stringify(body), btnObj, iObj)
			.done(function(favoriteId){
				$(iObj).removeClass().addClass("fa").addClass("fa-heart");
				$(spanDscrObj).text("不喜歡了");
				$("#input_detail_favoriteId").val(favoriteId);
			});
		}
	}, 
	
	queuing: function(btnObj){
		var iObj = $(btnObj).find("i");
		var spanDscrObj = $(btnObj).find("#span_queuing_dscr");
		
		if($(iObj).hasClass("fa-user-times")){
			var body = {
				queuingId: $("#input_detail_queuingId").val(), 
				status: "WAITING"
			};
			
			$(iObj).removeClass().addClass("fa").addClass("fa-spinner").addClass("fa-spin");
			ajaxUtilObj.callJsonAJAX(ajaxUtilObj.DELETE, "/queuings/remove", JSON.stringify(body), btnObj, iObj)
			.done(function(){
				$(iObj).removeClass().addClass("fa").addClass("fa-user-plus");
				$(spanDscrObj).text("抽號碼牌");
				$("#input_detail_queuingId").val("");
				$("#span_queueNum").text("");
			});
		}
		else {
			var body = {
				userId: $("#input_userId").val(),
				queueId: $("#input_detail_queueId").val()
			};
			
			$(iObj).removeClass().addClass("fa").addClass("fa-spinner").addClass("fa-spin");
			ajaxUtilObj.callJsonAJAX(ajaxUtilObj.POST, "/queuings/add", JSON.stringify(body))
			.done(function(queuing){
				$(iObj).removeClass().addClass("fa").addClass("fa-user-times");
				$(spanDscrObj).text("不想等了");
				$("#input_detail_queuingId").val(queuing.queuingId);
				
				$("#span_queueNum").text(queuing.queueNum);
			});
		}
	}, 
	
	edit: function(){
		$("#span_dscr").hide();
		$("#textarea_dscr").show();
		$("#span_phone").hide();
		$("#input_phone").show();
		$("#span_address").hide();
		$("#input_address").show();
		
		$("#btn_revert").show();
		$("#btn_save").show();
		$("#btn_edit").hide();
		$("#btn_delete").hide();
	}, 
	
	revert: function(){
		$("#span_dscr").show();
		$("#textarea_dscr").hide().val($("#span_dscr").text());
		$("#span_phone").show();
		$("#input_phone").hide().val($("#span_phone").text());
		$("#span_address").show();
		$("#input_address").hide().val($("#span_address").text());
		
		$("#btn_revert").hide();
		$("#btn_save").hide();
		$("#btn_edit").show();
		$("#btn_delete").show();
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
			$("#span_dscr").text(queue.dscr).show();
			$("#textarea_dscr").hide();
			$("#span_phone").text(queue.phone).show();
			$("#input_phone").hide();
			$("#span_address").text(queue.address).show();
			$("#input_address").hide();
			
			$("#btn_revert").hide();
			$("#btn_save").hide();
			$("#btn_edit").show();
			$("#btn_delete").show();
			
			$("#iframe_map").attr("src", "http://maps.google.com.tw/maps?f=q&hl=zh-TW&geocode=&q="+queue.address+"&z=16&output=embed&t=");
		});
	}, 
	
	success: function(btnObj, status){
		var queuingId = $(btnObj).parent().find("input").val();
		
		var body = {
			queuingId: queuingId, 
			status: status
		};
		
		ajaxUtilObj.callJsonAJAX(ajaxUtilObj.DELETE, "/queuings/remove", JSON.stringify(body))
		.done(function(){
			queueDetailObj.getNext(btnObj, status);
		});
	}, 
	
	waitingToPass: function(btnObj){
		var queuingId = $(btnObj).parent().find("input").val();
		
		var body = {
			queuingId: queuingId, 
			status: "PASS"
		};
		
		ajaxUtilObj.callJsonAJAX(ajaxUtilObj.PATCH, "/queuings/updateStatus", JSON.stringify(body))
		.done(function(){
			queueDetailObj.getNext(btnObj, "WAITING");
			queueDetailObj.getNext(btnObj, "PASS");
		});
	}, 
	
	getNext: function(btnObj, status){
		var queueId = $("#input_detail_queueId").val();
		
		var getBody = {
			queuingStatus: status, 
			limit: $("#input_init_queuing_limit").val(), 
			offset: $("#input_init_queuing_offset").val()
		};
		
		ajaxUtilObj.callJsonAJAX(ajaxUtilObj.GET, "/queuings/next/"+queueId+"?"+$.param(getBody), null)
		.done(function(resultMap){
			var queuings = resultMap.queuings;
			var waitingCount = resultMap.waitingCount;
			var passCount = resultMap.passCount;
			var queuingCount = resultMap.queuingCount;
			var length = queuings.length;
			
			$("#table_"+status).empty();
			var trHtml = "";
			for(var i=0; i<length; i++){
				var queuing = queuings[i];
				trHtml += "<tr>";
				trHtml += "<td align='center' width='20%'><h1><span class='label label-default'>"+queuing.queueNum+"</span></h1></td>";
				trHtml += "<td align='center' width='40%' style='word-break : break-all;'>";
				trHtml += 	"<img src='http://graph.facebook.com/"+queuing.user.facebookId+"/picture?width=50&height=50'><br/>"+queuing.user.name;
				trHtml += "</td>";
				trHtml += "<td width='40%'>";
				
				if(status == "WAITING"){
					trHtml += 	"<button type='button' class='btn btn-success' name='btn_waiting_success'><h4><i class='fa fa-smile-o'></i></h4></button>&nbsp;";
					trHtml += 	"<button type='button' class='btn btn-warning' name='btn_pass'><h4><i class='fa fa-meh-o'></i></h4></button>";
				}
				else if(status == "PASS"){
					trHtml += 	"<button type='button' class='btn btn-success' name='btn_pass_success'><h4><i class='fa fa-smile-o'></i></h4></button>&nbsp;";
				}
				
				trHtml += 	"<input type='hidden' value='"+queuing.queuingId+"'";
				trHtml += "</td>";
				trHtml += "</tr>";
			}
			
			$("#table_"+status).append(trHtml);
			$("#span_waiting_count").text(waitingCount);
			$("#span_pass_count").text(passCount);
			$("#span_queuing_count").text(queuingCount);
		});
	}
	
}
