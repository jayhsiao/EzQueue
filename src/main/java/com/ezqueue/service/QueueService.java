package com.ezqueue.service;

import java.util.List;
import java.util.Map;

import com.ezqueue.model.Queue;
import com.ezqueue.util.QueueStatus;

public interface QueueService {
	
	public List<Map<String, Object>> getMyQueues(String userId, int limit, int offset);
	
	public List<Map<String, Object>> getTypeQueues(String queueTypeId, int limit, int offset);
	
	public List<Map<String, Object>> getPromotionQueues(int limit, int offset);
	
	public List<Map<String, Object>> getFavoriteQueues(String userId, int limit, int offset);
	
	public List<Map<String, Object>> getQueuingQueues(String userId, int limit, int offset);
	
	public List<Map<String, Object>> getSearchQueues(String text, int limit, int offset);
	
	public Map<String, Object> createQueue(String userId);
	
	public List<Map<String, Object>> getUserQueues(String userId, int limit, int offset);
	
	public Map<String, Object> getQueueDetail(String userId, String queueId);
	
	public Queue getQueue(String queueId);
	
	public void addQueue(String userId, String queueTypeId, String startDate, String endDate, String title, String phone, String address, String dscr, int queueNum, QueueStatus queueStatus);
	
	public void addQueue(Queue queue);
	
	public void removeQueue(String queueId);
	
	public Queue edit(String queueId, String phone, String address, String dscr);
	
	public void updateStatus(String queueId, QueueStatus queueStatus);
	
}
