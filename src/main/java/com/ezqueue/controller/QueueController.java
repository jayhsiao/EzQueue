package com.ezqueue.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ezqueue.service.QueueService;
import com.ezqueue.util.EzQueueConstants;
import com.ezqueue.util.QueueStatus;

@RestController
@RequestMapping(value = "/queues")
public class QueueController extends BaseController {
	
	@Autowired
	private QueueService queueService;
	
	@RequestMapping(value = "/promotion/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getPromotionsQueues(
			@RequestParam(value = "limit", required = false, defaultValue = "limit") int limit,
			@RequestParam(value = "offset", required = false, defaultValue = "offset") int offset){
		return this.getResponse(queueService.getPromotionQueues(limit, offset));
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Object> addQueue(@RequestBody Map<String, Object> map){
		String userId = (String) map.get("userId");
		String queueTypeId = (String) map.get("queueTypeId");
		String title = (String) map.get("title");
		String phone = (String) map.get("phone");
		String address = (String) map.get("address");
		String dscr = (String) map.get("dscr");
		String startDate = (String) map.get("startDate");
		String endDate = (String) map.get("endDate");
		queueService.addQueue(userId, queueTypeId, startDate, endDate, title, phone, address, dscr, EzQueueConstants.INIT_QUEUE_NUM, QueueStatus.OPEN);
		return this.getResponse();
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.DELETE)
	public ResponseEntity<Object> removeQueue(@RequestBody Map<String, Object> map){
		queueService.removeQueue((String) map.get("queueId"));
		return this.getResponse();
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.PATCH)
	public ResponseEntity<Object> edit(@RequestBody Map<String, Object> map){
		String queueId = (String) map.get("queueId");
		String phone = (String) map.get("phone");
		String address = (String) map.get("address");
		String dscr = (String) map.get("dscr");
		return this.getResponse(queueService.edit(queueId, phone, address, dscr));
	}
	
	@RequestMapping(value = "/updateStatus", method = RequestMethod.PATCH)
	public ResponseEntity<Object> updateStatus(@RequestBody Map<String, Object> map){
		String queueId = (String) map.get("queueId");
		QueueStatus queueStatus = QueueStatus.valueOf((String) map.get("queueStatus"));
		queueService.updateStatus(queueId, queueStatus);
		return this.getResponse();
	}
	
}
