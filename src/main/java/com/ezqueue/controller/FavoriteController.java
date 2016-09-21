package com.ezqueue.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ezqueue.service.FavoriteService;

@RestController
@RequestMapping(value = "/favorites")
public class FavoriteController extends BaseController {
	
	@Autowired
	private FavoriteService favoriteService;
	
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
