package com.ezqueue.service;

import java.util.List;
import java.util.Map;

import com.ezqueue.model.Queuing;
import com.ezqueue.util.QueuingStatus;

public interface QueuingService {
	
	public List<Queuing> getQueuingsByUser(String userId, int limit, int offset);
	
	public List<Queuing> getQueuingsByQueue(String queueId, QueuingStatus queuingStatus, int limit, int offset);
	
	public Map<String, Object> success(String queuingId, String queueId);
	
	public Map<String, Object> pass(String queuingId, String queueId);
	
	public Map<String, Object> next(String queueId);
	
	public Queuing getQueuing(String userId, String queueId);
	
	public Queuing queuing(String userId, String queueId);
	
	public void updateStatus(String queuingId, QueuingStatus queuingStatus);
	
	public void addQueuing(Queuing queuing);
	
	public void removeQueuing(String queuingId);
	
}
