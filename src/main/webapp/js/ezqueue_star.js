var starObj = {
	
	addStar: function(userId, queueId, starNum){
		var body = {
			userId: userId,
			queueId: queueId,
			starNum: starNum
		};
		
		ajaxUtilObj.callJsonAJAX(ajaxUtilObj.POST, "/stars/add", JSON.stringify(body), null)
		.done(function(starId){
			$("#input_starId").val(starId);
		});
	},
	
	updateStar: function(starId, starNum){
		var body = {
			starId: starId,
			starNum: starNum
		};
		
		ajaxUtilObj.callJsonAJAX(ajaxUtilObj.PATCH, "/stars/update", JSON.stringify(body), null);
	},
	
	removeStar: function(starId){
		var body = {
			starId: starId
		};
		
		ajaxUtilObj.callJsonAJAX(ajaxUtilObj.DELETE, "/stars/remove", JSON.stringify(body), null)
		.done(function(){
			$("#input_starId").val("");
		});
	}
	
}
