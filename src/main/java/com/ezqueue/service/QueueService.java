package com.ezqueue.service;

import java.util.List;
import java.util.Map;

import com.ezqueue.model.Queue;
import com.ezqueue.model.User;

public interface QueueService {
	
	public List<Queue> getMyQueues(User user) throws Exception;

	public List<Map<String, Object>> getPromotionQueues(String userId) throws Exception;
	
	public List<Map<String, Object>> getQueuingQueues(String userId) throws Exception;
	
	public List<Map<String, Object>> getFavoriteQueues(String userId) throws Exception;
	
	public void addQueue(Queue queue) throws Exception;
	
	public void removeQueue(String queueId) throws Exception;
}
