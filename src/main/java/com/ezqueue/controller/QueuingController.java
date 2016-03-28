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
import com.ezqueue.model.Queuing;
import com.ezqueue.model.User;
import com.ezqueue.service.QueuingService;
import com.ezqueue.util.QueuingStatus;
import com.ezqueue.util.ResponseObject;
import com.ezqueue.util.StringUtil;

@RestController
@RequestMapping(value = "/queuing")
public class QueuingController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private QueuingService userQueueService;
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getQueuing(@PathVariable String userId){
		ResponseObject responseObject = new ResponseObject();
		try {
			List<Queue> queuings = userQueueService.getQueuing(userId);
			responseObject.setSuccess(true);
			responseObject.setReturnObject(queuings);
		} 
		catch (Exception e) {
			logger.error(e, e);
			responseObject.setSuccess(false);
			responseObject.setReturnMessage(e.getMessage());
		}
		return this.getResponse(responseObject);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Object> addQueuing(@RequestBody Map<String, Object> map){
		ResponseObject responseObject = new ResponseObject();
		try {
			User user = new User();
			user.setUserId((String) map.get("userId"));
			
			Queue queue = new Queue();
			queue.setQueueId((String) map.get("queueId"));
			
			Queuing queuing = new Queuing();
			queuing.setQueuingId(StringUtil.getUUID());
			queuing.setUser(user);
			queuing.setQueue(queue);
			queuing.setStatus(QueuingStatus.WAITTING);
			
			userQueueService.addQueuing(queuing);
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
	public ResponseEntity<Object> removeQueuing(@RequestBody Map<String, Object> map){
		ResponseObject responseObject = new ResponseObject();
		try {
			userQueueService.removeQueuing((String) map.get("queuingId"));
			responseObject.setSuccess(true);
		} 
		catch (Exception e) {
			logger.error(e, e);
			responseObject.setSuccess(false);
			responseObject.setReturnMessage(e.getMessage());
		}
		return this.getResponse(responseObject);
	}
	
	@RequestMapping(value = "/updateStatus", method = RequestMethod.PATCH)
	public ResponseEntity<Object> updateStatus(@RequestBody Queuing queuing){
		ResponseObject responseObject = new ResponseObject();
		try {
			userQueueService.addQueuing(queuing);
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
