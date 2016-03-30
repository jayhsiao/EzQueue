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

import com.ezqueue.model.Queuing;
import com.ezqueue.service.QueuingService;
import com.ezqueue.util.QueuingStatus;
import com.ezqueue.util.ResponseObject;

@RestController
@RequestMapping(value = "/queuing")
public class QueuingController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private QueuingService userQueueService;
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getQueuings(@PathVariable String userId){
		ResponseObject responseObject = new ResponseObject();
		try {
			List<Queuing> queuings = userQueueService.getQueuingsByUserId(userId, QueuingStatus.WAITTING);
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
			Integer queueNum = userQueueService.addQueuing(map);
			responseObject.setSuccess(true);
			responseObject.setReturnObject(queueNum);
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
	
	@RequestMapping(value = "/updateWaittingStatus", method = RequestMethod.PATCH)
	public ResponseEntity<Object> updateWaittingStatus(@RequestBody Queuing queuing){
		ResponseObject responseObject = new ResponseObject();
		try {
			userQueueService.updateWaittingStatus(queuing);
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
