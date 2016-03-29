package com.ezqueue.service;

import java.util.List;
import java.util.Map;

import com.ezqueue.model.Queuing;

public interface QueuingService {
	
	public List<Queuing> getQueuings(String userId) throws Exception;
	
	public Queuing getQueuing(String userId, String queueId) throws Exception;
	
	public Integer addQueuing(Map<String, Object> map) throws Exception;
	
	public void removeQueuing(String queuingId) throws Exception;
	
	public Double getAvgWaittingTime(String queueId) throws Exception;
	
	public Integer getWaittingCount(String queueId, Integer queueNum) throws Exception;
}
