package com.ezqueue.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.model.Queue;
import com.ezqueue.repository.QueueRepository;
import com.ezqueue.repository.UserQueueRepository;

@Service
public class QueueServiceImpl implements QueueService {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private QueueRepository queueRepository;
	
	@Autowired
	private UserQueueRepository userQueueRepository;
	
	public List<Queue> getPromotionQueues() throws Exception {
		List<Queue> queues = queueRepository.findByPromotionPriorityNotNullOrderByPromotionPriorityAsc();
		for(Queue queue: queues){
			queue.setAvgWaittingTime(userQueueRepository.getAvgWaittingTime(queue.getQueueId()));
		}
		return queues;
	}
	
	public List<Queue> getMyQueues(Integer userId) throws Exception {
		return queueRepository.findByUserIdOrderByCreateDateDesc(userId);
	}
	
	public void createQueue(Queue queue) throws Exception {
		queueRepository.save(queue);
	}
	
	public void deleteQueue(Integer queueId) throws Exception {
		queueRepository.delete(queueId);
	}
}
