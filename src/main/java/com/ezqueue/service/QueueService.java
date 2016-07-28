package com.ezqueue.service;

import java.util.List;
import java.util.Map;

import com.ezqueue.model.Queue;

public interface QueueService {
	
	public Map<String, Object> createQueue(String userId);
	
	public List<Map<String, Object>> getAllQueues(String userId, String queueTypeId, int limit, int offset);
	
	public List<Map<String, Object>> getMyQueues(String userId, int limit, int offset);

	public List<Map<String, Object>> getPromotionQueues(String userId, int limit, int offset);
	
	public List<Map<String, Object>> getQueuingQueues(String userId, int limit, int offset);
	
	public List<Map<String, Object>> getFavoriteQueues(String userId, int limit, int offset);
	
	public List<Map<String, Object>> getUserQueues(String userId, int limit, int offset);
	
	public List<Map<String, Object>> getSearchQueues(String userId, String text, int limit, int offset);
	
	public Map<String, Object> getQueueDetail(String userId, String queueId, boolean canEdit);
	
	public Queue getQueue(String queueId);
	
	public void addQueue(Queue queue);
	
	public void removeQueue(String queueId);
	
	public void update(Queue queue);
}
