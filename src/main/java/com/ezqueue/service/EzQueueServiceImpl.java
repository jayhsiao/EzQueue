package com.ezqueue.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.model.Queue;
import com.ezqueue.model.User;

@Service
public class EzQueueServiceImpl implements EzQueueService {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private QueueService queueService;
	
	public Map<String, Object> init(Integer userId) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		User user = userService.getUser(userId);
		
		resultMap.put("user", user);
		
		return resultMap;
	}
	
	public Map<String, Object> createQueue(Integer userId) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		User user = userService.getUser(userId);
		
		resultMap.put("user", user);
		
		return resultMap;
	}
	
	public Map<String, Object> getMyQueues(User user) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Queue> queues= queueService.getMyQueues(user);
		
		resultMap.put("queues", queues);
		resultMap.put("isMyQueue", true);
		
		return resultMap;
	}
	
	public Map<String, Object> getPromotionQueues() throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Queue> queues= queueService.getPromotionQueues();
		
		resultMap.put("queues", queues);
		resultMap.put("isPromotion", true);
		
		return resultMap;
	}
}
