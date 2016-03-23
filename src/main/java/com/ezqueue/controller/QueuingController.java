package com.ezqueue.controller;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ezqueue.model.Queuing;
import com.ezqueue.service.QueuingService;
import com.ezqueue.util.ResponseObject;

@RestController
@RequestMapping(value = "/queuing")
public class QueuingController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private QueuingService userQueueService;
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getQueuing(@PathVariable Integer userId){
		ResponseObject responseObject = new ResponseObject();
		try {
			List<Queuing> queuings = userQueueService.getQueuing(userId);
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
	public ResponseEntity<Object> addQueuing(@RequestBody Queuing queuing){
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
	
	@RequestMapping(value = "/remove", method = RequestMethod.DELETE)
	public ResponseEntity<Object> removeQueuing(@RequestBody Queuing queuing){
		ResponseObject responseObject = new ResponseObject();
		try {
			userQueueService.removeQueuing(queuing.getQueuingId());
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
