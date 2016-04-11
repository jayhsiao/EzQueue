package com.ezqueue.controller;


import java.util.List;
import java.util.Map;

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
import com.ezqueue.util.EzQueueConstants;
import com.ezqueue.util.ResponseObject;
import com.ezqueue.util.StringUtil;

@RestController
@RequestMapping(value = "/queue")
public class QueueController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private QueueService queueService;
	
	@RequestMapping(value = "/promotion/{userId}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Object> getPromotionsQueues(@PathVariable String userId, @PathVariable Integer page){
		ResponseObject responseObject = new ResponseObject();
		try {
			List<Map<String, Object>> queues = queueService.getPromotionQueues(userId, page, EzQueueConstants.PAGE_SIZE);
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
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Object> addQueue(@RequestBody Map<String, Object> map){
		ResponseObject responseObject = new ResponseObject();
		try {
			User user = new User();
			user.setUserId((String) map.get("userId"));
			
			Queue queue = new Queue();
			queue.setQueueId(StringUtil.getUUID());
			queue.setUser(user);
			queue.setTitle((String) map.get("title"));
			queue.setPhone((String) map.get("phone"));
			queue.setAddress((String) map.get("address"));
			queue.setDscr((String) map.get("dscr"));
			queue.setEnable(Boolean.valueOf((String) map.get("enable")));
			
			queueService.addQueue(queue);
			responseObject.setSuccess(true);
		} 
		catch (Exception e) {
			logger.error(e, e);
			responseObject.setSuccess(false);
			responseObject.setReturnMessage(e.getMessage());
		}
		return this.getResponse(responseObject);
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.DELETE)
	public ResponseEntity<Object> removeQueue(@RequestBody Map<String, Object> map){
		ResponseObject responseObject = new ResponseObject();
		try {
			queueService.removeQueue((String) map.get("queueId"));
			responseObject.setSuccess(true);
		} 
		catch (Exception e) {
			logger.error(e, e);
			responseObject.setSuccess(false);
			responseObject.setReturnMessage(e.getMessage());
		}
		return this.getResponse(responseObject);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PATCH)
	public ResponseEntity<Object> update(@RequestBody Queue queue){
		ResponseObject responseObject = new ResponseObject();
		try {
			queueService.update(queue);
			responseObject.setSuccess(true);
		} 
		catch (Exception e) {
			logger.error(e, e);
			responseObject.setSuccess(false);
			responseObject.setReturnMessage(e.getMessage());
		}
		return this.getResponse(responseObject);
	}
	
	@RequestMapping(value = "/close", method = RequestMethod.PATCH)
	public ResponseEntity<Object> closeQueue(@RequestBody Queue queue){
		ResponseObject responseObject = new ResponseObject();
		try {
			queue.setEnable(false);
			queueService.addQueue(queue);
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
