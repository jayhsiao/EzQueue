package com.ezqueue.service;

import java.util.List;
import java.util.Map;

import com.ezqueue.model.Queuing;

public interface QueuingService {
	
	public List<Queuing> getQueuingsByUserId(String userId, int status) throws Exception;
	
	public List<Queuing> getQueuingsByQueueId(String queueId, int status) throws Exception;
	
	public Queuing getQueuing(String userId, String queueId, int status) throws Exception;
	
	public Integer addQueuing(Map<String, Object> map) throws Exception;
	
	public void addQueuing(Queuing queuing) throws Exception;
	
	public void removeQueuing(String queuingId) throws Exception;
	
	public Double getAvgWaittingTime(String queueId) throws Exception;
	
	public void updateWaittingStatus(Queuing queuing) throws Exception;
}
