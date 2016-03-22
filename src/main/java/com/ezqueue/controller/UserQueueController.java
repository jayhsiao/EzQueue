package com.ezqueue.controller;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ezqueue.model.UserQueueMap;
import com.ezqueue.service.UserQueueMapService;
import com.ezqueue.util.ResponseObject;

@RestController
@RequestMapping(value = "/userQueue")
public class UserQueueController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserQueueMapService userQueueService;
	
	@RequestMapping(value = "/queuing/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getQueuing(@PathVariable Integer userId){
		ResponseObject responseObject = new ResponseObject();
		try {
			List<UserQueueMap> userQueueMaps = userQueueService.getQueuing(userId);
			responseObject.setSuccess(true);
			responseObject.setReturnObject(userQueueMaps);
		} 
		catch (Exception e) {
			logger.error(e, e);
			responseObject.setSuccess(false);
			responseObject.setReturnMessage(e.getMessage());
		}
		return this.getResponse(responseObject);
	}
	
	@RequestMapping(value = "/complete/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Object> completeQueue(@PathVariable Integer userId){
		ResponseObject responseObject = new ResponseObject();
		try {
			List<UserQueueMap> userQueueMaps = userQueueService.getQueuing(userId);
			responseObject.setSuccess(true);
			responseObject.setReturnObject(userQueueMaps);
		} 
		catch (Exception e) {
			logger.error(e, e);
			responseObject.setSuccess(false);
			responseObject.setReturnMessage(e.getMessage());
		}
		return this.getResponse(responseObject);
	}
}
