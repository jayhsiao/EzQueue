package com.ezqueue.service;

import java.util.Map;

import com.ezqueue.model.User;

public interface EzQueueService {
	
	public Map<String, Object> signup(User user) throws Exception;
	
	public Map<String, Object> signin(String userId, String password) throws Exception;
	
	public Map<String, Object> init(String userId) throws Exception;
	
	public Map<String, Object> getMyQueues(User user) throws Exception;
	
	public Map<String, Object> getPromotionQueues() throws Exception;
}
