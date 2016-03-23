package com.ezqueue.service;

import java.util.List;

import com.ezqueue.model.Queuing;

public interface QueuingService {
	
	public List<Queuing> getQueuing(Integer userId) throws Exception;
	
	public void addQueuing(Queuing userQueueMap) throws Exception;
	
	public void removeQueuing(Integer userQueueMapId) throws Exception;
}
