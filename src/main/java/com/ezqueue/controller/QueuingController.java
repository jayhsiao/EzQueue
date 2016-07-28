package com.ezqueue.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ezqueue.service.QueuingService;
import com.ezqueue.util.QueuingStatus;

@RestController
@RequestMapping(value = "/queuings")
public class QueuingController extends BaseController {
	
	@Autowired
	private QueuingService userQueueService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Object> addQueuing(@RequestBody Map<String, Object> map) {
		String userId = (String) map.get("userId");
		String queueId = (String) map.get("queueId");
		return this.getResponse(userQueueService.queuing(userId, queueId));
	}
	
	@RequestMapping(value = "/updateStatus", method = RequestMethod.PATCH)
	public ResponseEntity<Object> updateQueuing(@RequestBody Map<String, Object> map) {
		String userId = (String) map.get("userId");
		String queuingId = (String) map.get("queuingId");
		QueuingStatus queuingStatus = QueuingStatus.valueOf((String) map.get("status"));
		userQueueService.updateStatus(userId, queuingId, queuingStatus);
		return this.getResponse();
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.DELETE)
	public ResponseEntity<Object> updateWaittingStatus(@RequestBody Map<String, Object> map) {
		String queuingId = (String) map.get("queuingId");
		userQueueService.removeQueuing(queuingId);
		return this.getResponse();
	}
	
}
