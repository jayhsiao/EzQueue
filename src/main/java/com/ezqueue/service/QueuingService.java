package com.ezqueue.service;

import java.util.List;

import com.ezqueue.model.Queue;
import com.ezqueue.model.Queuing;
import com.ezqueue.util.QueuingStatus;

public interface QueuingService {
	
	public List<Queuing> getQueuingsByUser(String userId, int limit, int offset);
	
	public List<Queuing> getQueuingsByQueue(String queueId, int limit, int offset);
	
	public Queuing getQueuing(String userId, String queueId);
	
	public int getQueuingCount(Queue queue);
	
	public Queuing queuing(String userId, String queueId);
	
	public void updateStatus(String userId, String queuingId, QueuingStatus queuingStatus);
	
	public Double getAvgWaittingTime(String queueId);
	
	public void addQueuing(Queuing queuing);
	
	public void removeQueuing(String queuingId);
	
}
