package com.ezqueue.controller;


import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ezqueue.model.Queue;
import com.ezqueue.model.Star;
import com.ezqueue.model.User;
import com.ezqueue.service.StarService;
import com.ezqueue.util.ResponseObject;
import com.ezqueue.util.RetrunCode;

@RestController
@RequestMapping(value = "/star")
public class StarController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private StarService starService;
	
	@RequestMapping(value = "/add", method = RequestMethod.PATCH)
	public ResponseEntity<Object> addStar(@RequestBody Map<String, Object> map){
		ResponseObject responseObject = new ResponseObject();
		try {
			User user = new User();
			user.setUserId((String) map.get("userId"));
			
			Queue queue = new Queue();
			queue.setQueueId((String) map.get("queueId"));
			
			Star star = new Star();
			star.setUser(user);
			star.setQueue(queue);
			star.setStar((Integer) map.get("star"));
			
			starService.addStar(star);
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
