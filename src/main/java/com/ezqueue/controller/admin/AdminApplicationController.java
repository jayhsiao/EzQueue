package com.ezqueue.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ezqueue.controller.BaseController;
import com.ezqueue.service.ApplicationService;

@RestController
@RequestMapping(value = "/admin/applications")
public class AdminApplicationController extends BaseController {
	
	@Autowired
	private ApplicationService applicationService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Object> addApplication(){
		applicationService.addApplication();
		return this.getResponse();
	}
	
}
