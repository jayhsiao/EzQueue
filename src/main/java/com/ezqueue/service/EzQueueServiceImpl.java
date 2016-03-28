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
	
	@Autowired
	private QueuingService queuingService;
	
	@Autowired
	private FavoriteService favoriteService;
	
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
	
	public Map<String, Object> getQueuing(User user) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("queues", queuingService.getQueuing(user.getUserId()));
		return resultMap;
	}
	
	public Map<String, Object> getFavorite(User user) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("queues", favoriteService.getFavorite(user.getUserId()));
		return resultMap;
	}
}
