package com.ezqueue.service;

import java.util.List;
import java.util.Map;

import com.ezqueue.model.Queue;

public interface QueueService {
	
	public List<Map<String, Object>> getMyQueues(String userId, int page, int size) throws Exception;

	public List<Map<String, Object>> getPromotionQueues(String userId, int page, int size) throws Exception;
	
	public List<Map<String, Object>> getQueuingQueues(String userId, int page, int size) throws Exception;
	
	public List<Map<String, Object>> getFavoriteQueues(String userId, int page, int size) throws Exception;
	
	public void addQueue(Queue queue) throws Exception;
	
	public void removeQueue(String queueId) throws Exception;
}
