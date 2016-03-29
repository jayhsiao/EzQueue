package com.ezqueue.service;

import java.util.List;
import java.util.Map;

import com.ezqueue.model.User;

public interface EzQueueService {
	
	public Map<String, Object> init(String userId) throws Exception;
	
	public Map<String, Object> getMyQueues(User user) throws Exception;
	
	public List<Map<String, Object>> getPromotionQueues(String userId) throws Exception;
	
	public List<Map<String, Object>> getQueuingQueues(String userId) throws Exception;
	
	public List<Map<String, Object>> getFavoriteQueues(String userId) throws Exception;
}
