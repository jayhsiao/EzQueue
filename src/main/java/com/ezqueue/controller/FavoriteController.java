package com.ezqueue.controller;


import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ezqueue.model.Favorite;
import com.ezqueue.model.Queue;
import com.ezqueue.model.User;
import com.ezqueue.service.FavoriteService;
import com.ezqueue.util.ResponseObject;
import com.ezqueue.util.StringUtil;

@RestController
@RequestMapping(value = "/favorite")
public class FavoriteController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private FavoriteService favoriteService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Object> addFavorite(@RequestBody Map<String, Object> map){
		ResponseObject responseObject = new ResponseObject();
		try {
			User user = new User();
			user.setUserId((String) map.get("userId"));
			
			Queue queue = new Queue();
			queue.setQueueId((String) map.get("queueId"));
			
			Favorite favorite = new Favorite();
			favorite.setFavoriteId(StringUtil.getUUID());
			favorite.setUser(user);
			favorite.setQueue(queue);
			
			favoriteService.addFavorite(favorite);
			responseObject.setSuccess(true);
		} 
		catch (Exception e) {
			logger.error(e, e);
			responseObject.setSuccess(false);
			responseObject.setReturnMessage(e.getMessage());
		}
		return this.getResponse(responseObject);
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.DELETE)
	public ResponseEntity<Object> removeFavorite(@RequestBody Map<String, Object> map){
		ResponseObject responseObject = new ResponseObject();
		try {
			favoriteService.removeFavorite((String) map.get("favoriteId"));
			responseObject.setSuccess(true);
		} 
		catch (Exception e) {
			logger.error(e, e);
			responseObject.setSuccess(false);
			responseObject.setReturnMessage(e.getMessage());
		}
		return this.getResponse(responseObject);
	}
}
