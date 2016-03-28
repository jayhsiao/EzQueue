package com.ezqueue.service;

import java.util.List;

import com.ezqueue.model.Queue;
import com.ezqueue.model.Queuing;

public interface QueuingService {
	
	public List<Queue> getQueuing(String userId) throws Exception;
	
	public void addQueuing(Queuing queuing) throws Exception;
	
	public void removeQueuing(String queuingId) throws Exception;
}
