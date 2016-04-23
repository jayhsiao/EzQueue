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
import com.ezqueue.util.EzQueueConstants;
import com.ezqueue.util.QueuingStatus;
import com.ezqueue.util.ResponseObject;
import com.ezqueue.util.RetrunCode;

@RestController
@RequestMapping(value = "/queuing")
public class QueuingController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private QueuingService userQueueService;
	
	@RequestMapping(value = "/{userId}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Object> getQueuingsByUserId(@PathVariable String userId, @PathVariable Integer page){
		ResponseObject responseObject = new ResponseObject();
		try {
			List<Queuing> queuings = userQueueService.getQueuingsByUserId(userId, QueuingStatus.WAITTING, page, EzQueueConstants.PAGE_SIZE);
			responseObject.setReturnCode(RetrunCode.SUCCESS);
			responseObject.setReturnObject(queuings);
		} 
		catch (Exception e) {
			logger.error(e, e);
			responseObject.setReturnCode(RetrunCode.FAIL);
			responseObject.setReturnMessage(e.getMessage());
		}
		return this.getResponse(responseObject);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Object> addQueuing(@RequestBody Map<String, Object> map){
		ResponseObject responseObject = new ResponseObject();
		try {
			Queuing queuing = userQueueService.queuing(map);
			responseObject.setReturnCode(RetrunCode.SUCCESS);
			responseObject.setReturnObject(queuing);
		} 
		catch (Exception e) {
			logger.error(e, e);
			responseObject.setReturnCode(RetrunCode.FAIL);
			responseObject.setReturnMessage(e.getMessage());
		}
		return this.getResponse(responseObject);
	}
	
	@RequestMapping(value = "/updateStatus", method = RequestMethod.PATCH)
	public ResponseEntity<Object> updateWaittingStatus(@RequestBody Queuing queuing){
		ResponseObject responseObject = new ResponseObject();
		try {
			userQueueService.updateStatus(queuing);
			responseObject.setReturnCode(RetrunCode.SUCCESS);
		} 
		catch (Exception e) {
			logger.error(e, e);
			responseObject.setReturnCode(RetrunCode.FAIL);
			responseObject.setReturnMessage(e.getMessage());
		}
		return this.getResponse(responseObject);
	}
}
