package com.ezqueue.service;

import java.util.List;
import java.util.Map;

import com.ezqueue.model.Queue;

public interface QueueService {
	
	public Map<String, Object> createQueue(String userId) throws Exception;
	
	public List<Map<String, Object>> getAllQueues(String userId, String queueTypeId, int page) throws Exception;
	
	public List<Map<String, Object>> getMyQueues(String userId, int page) throws Exception;

	public List<Map<String, Object>> getPromotionQueues(String userId, int page) throws Exception;
	
	public List<Map<String, Object>> getQueuingQueues(String userId, int page) throws Exception;
	
	public List<Map<String, Object>> getFavoriteQueues(String userId, int page) throws Exception;
	
	public List<Map<String, Object>> getUserQueues(String userId, int page) throws Exception;
	
	public List<Map<String, Object>> getSearchQueues(String userId, String text, int page) throws Exception;
	
	public void addQueue(Queue queue) throws Exception;
	
	public void removeQueue(String queueId) throws Exception;
	
	public void update(Queue queue) throws Exception;
}
