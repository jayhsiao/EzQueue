package com.ezqueue.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ezqueue.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public ResponseEntity<Object> check(@RequestBody Map<String, Object> map) throws Exception{
        return this.getResponse(userService.check(map));
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getUser(@PathVariable String userId) throws Exception{
        return this.getResponse(userService.getUser(userId));
	}
}
