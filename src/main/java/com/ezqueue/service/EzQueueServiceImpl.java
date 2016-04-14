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
	
	@Autowired
	private QueueTypeService queueTypeService;
	
	@Override
	public Map<String, Object> init(String userId) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("user", userService.getUser(userId));
		resultMap.put("queueTypes", queueTypeService.getQueueTypes());
		return resultMap;
	}
	
	@Override
	public Map<String, Object> createQueue(String userId) throws Exception {
		return queueService.createQueue(userId);
	}
	
	@Override
	public List<Map<String, Object>> getAllQueues(String userId, String queueTypeId, int page) throws Exception {
		return queueService.getAllQueues(userId, queueTypeId, page);
	}
	
	@Override
	public List<Map<String, Object>> getMyQueues(String userId, int page) throws Exception {
		return queueService.getMyQueues(userId, page);
	}
	
	@Override
	public List<Map<String, Object>> getPromotionQueues(String userId, int page) throws Exception {
		return queueService.getPromotionQueues(userId, page);
	}
	
	@Override
	public List<Map<String, Object>> getQueuingQueues(String userId, int page) throws Exception {
		return queueService.getQueuingQueues(userId, page);
	}
	
	@Override
	public List<Map<String, Object>> getFavoriteQueues(String userId, int page) throws Exception {
		return queueService.getFavoriteQueues(userId, page);
	}
	
	@Override
	public List<Map<String, Object>> getUserQueues(String userId, int page) throws Exception {
		return queueService.getUserQueues(userId, page);
	}
	
	@Override
	public List<Map<String, Object>> getSearchQueues(String userId, String text, int page) throws Exception {
		return queueService.getSearchQueues(userId, text, page);
	}
}
