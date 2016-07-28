package com.ezqueue.controller.admin;


import java.util.List;
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
import com.ezqueue.service.PromotionService;

@RestController
@RequestMapping(value = "/admin/promotions")
public class AdminPromotionController extends BaseController {
	
	@Autowired
	private PromotionService promotionService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getPromotion(@RequestParam String userId, @RequestParam String queueId){
		return this.getResponse(promotionService.getPromotion(queueId));
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getFavorites(
			@PathVariable String userId, 
			@RequestParam(value = "limit", required = false, defaultValue = "limit") int limit,
			@RequestParam(value = "offset", required = false, defaultValue = "offset") int offset){
		return this.getResponse(promotionService.getPromotions(limit, offset));
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> addPromotion(@RequestBody Map<String, Object> map){
		List<String> queueIds = (List<String>) map.get("queueIds");
		promotionService.addPromotion(queueIds);
		return this.getResponse();
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.DELETE)
	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> removePromotion(@RequestBody Map<String, Object> map){
		List<String> promotionIds = (List<String>) map.get("promotionIds");
		promotionService.removePromotion(promotionIds);
		return this.getResponse();
	}
	
}
