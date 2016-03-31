package com.ezqueue.service;

import java.util.List;
import java.util.Map;

public interface EzQueueService {
	
	public Map<String, Object> init(String userId) throws Exception;
	
	public List<Map<String, Object>> getMyQueues(String userId, int page, int size) throws Exception;
	
	public List<Map<String, Object>> getPromotionQueues(String userId, int page, int size) throws Exception;
	
	public List<Map<String, Object>> getQueuingQueues(String userId, int page, int size) throws Exception;
	
	public List<Map<String, Object>> getFavoriteQueues(String userId, int page, int size) throws Exception;
}
