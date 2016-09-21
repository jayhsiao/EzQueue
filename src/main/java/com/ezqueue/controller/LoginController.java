package com.ezqueue.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController extends BaseController {
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Object> login() throws Exception{
        return this.getResponse();
	}
	
}
