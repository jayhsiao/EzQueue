package com.ezqueue.controller;


import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ezqueue.model.Queue;
import com.ezqueue.model.User;
import com.ezqueue.service.QueueService;
import com.ezqueue.util.ResponseObject;

@RestController
@RequestMapping(value = "/queue")
public class QueueController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private QueueService queueService;
	
	@RequestMapping(value = "/promotions", method = RequestMethod.GET)
	public ResponseEntity<Object> getPromotions(){
		ResponseObject responseObject = new ResponseObject();
		try {
			List<Queue> queues = queueService.getPromotionQueues();
			responseObject.setSuccess(true);
			responseObject.setReturnObject(queues);
		} 
		catch (Exception e) {
			logger.error(e, e);
			responseObject.setSuccess(false);
			responseObject.setReturnMessage(e.getMessage());
		}
		return this.getResponse(responseObject);
	}
	
//	@RequestMapping(value = "/favorite", method = RequestMethod.GET)
//	public ResponseEntity<Object> favorite(@RequestBody UserQueue userQueue){
//		queueService.queue(userQueue);
//		return this.getResponse(true, null, userQueue);
//	}
//	
	@RequestMapping(value = "/my/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getMyQueues(@PathVariable Integer userId){
		ResponseObject responseObject = new ResponseObject();
		try {
			User user = new User();
			user.setUserId(userId);
			
			List<Queue> queues = queueService.getMyQueues(userId);
			responseObject.setSuccess(true);
			responseObject.setReturnObject(queues);
		} 
		catch (Exception e) {
			logger.error(e, e);
			responseObject.setSuccess(false);
			responseObject.setReturnMessage(e.getMessage());
		}
		return this.getResponse(responseObject);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Object> createQueue(@RequestBody Map<String, Object> map){
		ResponseObject responseObject = new ResponseObject();
		try {
			Queue queue = new Queue();
			queue.setUserId((Integer) map.get("userId"));
			queue.setDscr((String) map.get("dscr"));
			queue.setEnable((boolean) map.get("enable"));
			
			queueService.createQueue(queue);
			responseObject.setSuccess(true);
		} 
		catch (Exception e) {
			logger.error(e, e);
			responseObject.setSuccess(false);
			responseObject.setReturnMessage(e.getMessage());
		}
		return this.getResponse(responseObject);
	}
	
	@RequestMapping(value = "/delete/{queueId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteQueue(@PathVariable Integer queueId){
		ResponseObject responseObject = new ResponseObject();
		try {
			queueService.deleteQueue(queueId);
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
