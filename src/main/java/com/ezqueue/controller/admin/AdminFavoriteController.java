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
import com.ezqueue.service.FavoriteService;

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
		String userId = (String) map.get("userId");
		String queueId = (String) map.get("queueId");
		return this.getResponse(favoriteService.addFavorite(userId, queueId));
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.DELETE)
	public ResponseEntity<Object> removeFavorite(@RequestBody Map<String, Object> map){
		String favoriteId = (String) map.get("favoriteId");
		String queueId = (String) map.get("queueId");
		return this.getResponse(favoriteService.removeFavorite(favoriteId, queueId));
	}
	
}
