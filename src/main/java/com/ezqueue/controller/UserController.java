package com.ezqueue.controller;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ezqueue.model.User;
import com.ezqueue.service.UserService;
import com.ezqueue.util.ResponseObject;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getStore(@PathVariable Integer userId) throws Exception{
		ResponseObject responseObject = new ResponseObject();
		try {
			User user = userService.getUser(userId);
			responseObject.setSuccess(true);
			responseObject.setReturnObject(user);
		} 
		catch (Exception e) {
			logger.error(e, e);
			responseObject.setSuccess(false);
			responseObject.setReturnMessage(e.getMessage());
		}
        return this.getResponse(responseObject);
	}
}
