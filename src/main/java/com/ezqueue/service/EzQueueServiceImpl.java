package com.ezqueue.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.model.User;

@Service
public class EzQueueServiceImpl implements EzQueueService{
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	public Map<String, Object> init(Integer userId) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		User user = userService.getUser(userId);
		
		resultMap.put("user", user);
		
		return resultMap;
	}
}
