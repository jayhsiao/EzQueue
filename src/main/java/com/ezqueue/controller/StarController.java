package com.ezqueue.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ezqueue.service.StarService;

@RestController
@RequestMapping(value = "/stars")
public class StarController extends BaseController {
	
	@Autowired
	private StarService starService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Object> addStar(@RequestBody Map<String, Object> map){
		String userId = (String) map.get("userId");
		String queueId = (String) map.get("queueId");
		Integer starNum = (Integer) map.get("starNum");
		return this.getResponse(starService.addStar(userId, queueId, starNum));
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PATCH)
	public ResponseEntity<Object> updateStar(@RequestBody Map<String, Object> map){
		String starId = (String) map.get("starId");
		Integer starNum = (Integer) map.get("starNum");
		starService.updateStar(starId, starNum);
		return this.getResponse();
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.DELETE)
	public ResponseEntity<Object> removeStar(@RequestBody Map<String, Object> map){
		starService.removeStar((String) map.get("starId"));
		return this.getResponse();
	}
	
}
