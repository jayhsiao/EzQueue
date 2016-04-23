package com.ezqueue.controller;


import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ezqueue.model.User;
import com.ezqueue.service.UserService;
import com.ezqueue.util.ResponseObject;
import com.ezqueue.util.RetrunCode;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public ResponseEntity<Object> check(@RequestBody Map<String, Object> map) throws Exception{
		ResponseObject responseObject = new ResponseObject();
		try {
			System.out.println(map.entrySet());
			String userId = userService.check(map);
			responseObject.setReturnCode(RetrunCode.SUCCESS);
			responseObject.setReturnObject(userId);
		}
		catch (Exception e) {
			logger.error(e, e);
			responseObject.setReturnCode(RetrunCode.FAIL);
			responseObject.setReturnMessage(e.getMessage());
		}
        return this.getResponse(responseObject);
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getUser(@PathVariable String userId) throws Exception{
		ResponseObject responseObject = new ResponseObject();
		try {
			User user = userService.getUser(userId);
			responseObject.setReturnCode(RetrunCode.SUCCESS);
			responseObject.setReturnObject(user);
		} 
		catch (Exception e) {
			logger.error(e, e);
			responseObject.setReturnCode(RetrunCode.FAIL);
			responseObject.setReturnMessage(e.getMessage());
		}
        return this.getResponse(responseObject);
	}
}
