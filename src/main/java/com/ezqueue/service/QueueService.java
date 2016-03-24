package com.ezqueue.service;

import java.util.List;

import com.ezqueue.model.Queue;
import com.ezqueue.model.User;

public interface QueueService {

	public List<Queue> getPromotionQueues() throws Exception;
	
	public List<Queue> getMyQueues(User user) throws Exception;
	
	public void addQueue(Queue queue) throws Exception;
	
	public void removeQueue(Integer queueId) throws Exception;
}
