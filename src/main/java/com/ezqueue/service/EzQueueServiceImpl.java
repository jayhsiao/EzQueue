package com.ezqueue.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.model.User;

@Service
public class EzQueueServiceImpl implements EzQueueService {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private QueueService queueService;
	
	public Map<String, Object> signup(User user) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("user", userService.addUser(user));
		return resultMap;
	}
	
	public Map<String, Object> signin(String userId, String password) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("user", userService.verifyUser(userId, password));
		return resultMap;
	}
	
	public Map<String, Object> init(String userId) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("user", userService.getUser(userId));
		return resultMap;
	}
	
	public Map<String, Object> getMyQueues(User user) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("queues", queueService.getMyQueues(user));
		return resultMap;
	}
	
	public Map<String, Object> getPromotionQueues() throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("queues", queueService.getPromotionQueues());
		return resultMap;
	}
}
