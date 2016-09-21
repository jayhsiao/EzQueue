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
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<Object> check(@RequestBody Map<String, Object> map) throws Exception{
		String id = (String) map.get("id");
		String name = (String) map.get("name");
		String email = (String) map.get("email");
		Map<String, Object> accounts = (Map<String, Object>) map.get("accounts");
        return this.getResponse(userService.registration(id, name, email, accounts));
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getUser(@PathVariable String userId) throws Exception{
        return this.getResponse(userService.getUser(userId));
	}
	
}
