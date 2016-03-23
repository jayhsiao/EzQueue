package com.ezqueue.service;

import java.util.Map;

public interface EzQueueService {
	
	public Map<String, Object> init(Integer UserId) throws Exception;
	
	public Map<String, Object> createQueue(Integer userId) throws Exception;
}
