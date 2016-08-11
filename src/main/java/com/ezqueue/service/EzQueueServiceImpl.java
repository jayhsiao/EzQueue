package com.ezqueue.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.util.EzQueueConstants;

@Service
public class EzQueueServiceImpl implements EzQueueService {
	
	@Autowired
	private QueueService queueService;
	
	@Autowired
	private QueueTypeService queueTypeService;
	
	@Override
	public Map<String, Object> init() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("queueTypes", queueTypeService.getQueueTypes());
		resultMap.put("limit", EzQueueConstants.INIT_LIMIT);
		resultMap.put("offset", EzQueueConstants.INIT_OFFSET);
		resultMap.put("queuingLimit", EzQueueConstants.INIT_QUEUING_LIMIT);
		resultMap.put("queuingOffset", EzQueueConstants.INIT_QUEUING_OFFSET);
		return resultMap;
	}
	
	@Override
	public List<Map<String, Object>> getMyQueues(String userId, int limit, int offset) {
		return queueService.getMyQueues(userId, limit, offset);
	}
	
	@Override
	public List<Map<String, Object>> getPromotionQueues(int limit, int offset) {
		return queueService.getPromotionQueues(limit, offset);
	}
	
	@Override
	public List<Map<String, Object>> getTypeQueues(String queueTypeId, int limit, int offset) {
		return queueService.getTypeQueues(queueTypeId, limit, offset);
	}
	
	@Override
	public List<Map<String, Object>> getFavoriteQueues(String userId, int limit, int offset) {
		return queueService.getFavoriteQueues(userId, limit, offset);
	}
	
	@Override
	public List<Map<String, Object>> getQueuingQueues(String userId, int limit, int offset) {
		return queueService.getQueuingQueues(userId, limit, offset);
	}
	
	@Override
	public List<Map<String, Object>> getSearchQueues(String text, int limit, int offset) {
		return queueService.getSearchQueues(text, limit, offset);
	}
	
	@Override
	public List<Map<String, Object>> getUserQueues(String userId, int limit, int offset) {
		return queueService.getUserQueues(userId, limit, offset);
	}
	
	@Override
	public Map<String, Object> createQueue(String userId) {
		return queueService.createQueue(userId);
	}
	
	@Override
	public Map<String, Object> getQueueDetail(String userId, String queueId) {
		return queueService.getQueueDetail(userId, queueId);
	}
	
}
