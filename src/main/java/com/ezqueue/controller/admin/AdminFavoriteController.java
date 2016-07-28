package com.ezqueue.controller.admin;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ezqueue.controller.BaseController;
import com.ezqueue.model.Favorite;
import com.ezqueue.model.Queue;
import com.ezqueue.model.User;
import com.ezqueue.service.FavoriteService;
import com.ezqueue.util.StringUtil;

@RestController
@RequestMapping(value = "/admin/favorites")
public class AdminFavoriteController extends BaseController {
	
	@Autowired
	private FavoriteService favoriteService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getFavorite(@RequestParam String userId, @RequestParam String queueId){
		return this.getResponse(favoriteService.getFavorite(userId, queueId));
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getFavorites(@PathVariable String userId){
		return this.getResponse(favoriteService.getFavorites(userId));
	}
	
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
		return this.getResponse();
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.DELETE)
	public ResponseEntity<Object> removeFavorite(@RequestBody Map<String, Object> map){
		favoriteService.removeFavorite((String) map.get("favoriteId"));
		return this.getResponse();
	}
	
}
