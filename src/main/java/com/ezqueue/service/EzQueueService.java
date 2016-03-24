package com.ezqueue.service;

import java.util.Map;

import com.ezqueue.model.User;

public interface EzQueueService {
	
	public Map<String, Object> init(Integer UserId) throws Exception;
	
	public Map<String, Object> createQueue(Integer userId) throws Exception;
	
	public Map<String, Object> getMyQueues(User user) throws Exception;
	
	public Map<String, Object> getPromotionQueues() throws Exception;
}
