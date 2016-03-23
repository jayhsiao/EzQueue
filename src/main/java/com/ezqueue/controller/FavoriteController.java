package com.ezqueue.controller;


import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ezqueue.model.Favorite;
import com.ezqueue.model.Queue;
import com.ezqueue.model.User;
import com.ezqueue.service.FavoriteService;
import com.ezqueue.util.ResponseObject;

@RestController
@RequestMapping(value = "/favorite")
public class FavoriteController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private FavoriteService favoriteService;
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getFavorite(@PathVariable Integer userId){
		ResponseObject responseObject = new ResponseObject();
		try {
			List<Favorite> favorites = favoriteService.getFavorite(userId);
			responseObject.setSuccess(true);
			responseObject.setReturnObject(favorites);
		} 
		catch (Exception e) {
			logger.error(e, e);
			responseObject.setSuccess(false);
			responseObject.setReturnMessage(e.getMessage());
		}
		return this.getResponse(responseObject);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Object> addFavorite(@RequestBody Map<String, Object> map){
		ResponseObject responseObject = new ResponseObject();
		try {
			Favorite favorite = new Favorite();
			
			User user = new User();
			user.setUserId((Integer) map.get("userId"));
			
			Queue queue = new Queue();
			queue.setQueueId((Integer) map.get("queueId"));
			
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
			favoriteService.removeFavorite((Integer) map.get("favoriteId"));
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
