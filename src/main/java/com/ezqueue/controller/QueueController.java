package com.ezqueue.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ezqueue.model.Queue;
import com.ezqueue.model.QueueType;
import com.ezqueue.model.User;
import com.ezqueue.service.QueueService;
import com.ezqueue.util.QueueStatus;
import com.ezqueue.util.StringUtil;

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
		User user = new User();
		user.setUserId((String) map.get("userId"));
		
		Queue queue = new Queue();
		queue.setQueueId(StringUtil.getUUID());
		queue.setUser(user);
		queue.setTitle((String) map.get("title"));
		queue.setPhone((String) map.get("phone"));
		queue.setAddress((String) map.get("address"));
		queue.setDscr((String) map.get("dscr"));
		
		QueueType queueType = new QueueType();
		queueType.setQueueTypeId((String) map.get("queueTypeId"));
		queue.setQueueType(queueType);
		
		queue.setQueueNum(0);
		queue.setStatus(QueueStatus.OPEN);
		queue.setStartDate((String) map.get("startDate"));
		queue.setEndDate((String) map.get("endDate"));
		
		queueService.addQueue(queue);
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
	
}
