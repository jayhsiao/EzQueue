package com.ezqueue.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EzQueueServiceImpl implements EzQueueService {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private QueueService queueService;
	
	public Map<String, Object> init(String userId) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("user", userService.getUser(userId));
		return resultMap;
	}
	
	public List<Map<String, Object>> getMyQueues(String userId, int page, int size) throws Exception {
		return queueService.getMyQueues(userId, page, size);
	}
	
	public List<Map<String, Object>> getPromotionQueues(String userId, int page, int size) throws Exception {
		return queueService.getPromotionQueues(userId, page, size);
	}
	
	public List<Map<String, Object>> getQueuingQueues(String userId, int page, int size) throws Exception {
		return queueService.getQueuingQueues(userId, page, size);
	}
	
	public List<Map<String, Object>> getFavoriteQueues(String userId, int page, int size) throws Exception {
		return queueService.getFavoriteQueues(userId, page, size);
	}
}
