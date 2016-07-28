package com.ezqueue.controller;


import java.util.Map;

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
import com.ezqueue.util.StringUtil;

@RestController
@RequestMapping(value = "/favorites")
public class FavoriteController extends BaseController {
	
	@Autowired
	private FavoriteService favoriteService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Object> addFavorite(@RequestBody Map<String, Object> map){
		User user = new User();
		user.setUserId((String) map.get("userId"));
		
		Queue queue = new Queue();
		queue.setQueueId((String) map.get("queueId"));
		
		Favorite favorite = new Favorite();
		favorite.setFavoriteId(StringUtil.getUUID());
		favorite.setUser(user);
		favorite.setQueue(queue);
		
		favoriteService.addFavorite(favorite);
		return this.getResponse(favorite.getFavoriteId());
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.DELETE)
	public ResponseEntity<Object> removeFavorite(@RequestBody Map<String, Object> map){
		favoriteService.removeFavorite((String) map.get("favoriteId"));
		return this.getResponse();
	}
	
}
