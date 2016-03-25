package com.ezqueue.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.model.Queue;
import com.ezqueue.model.User;
import com.ezqueue.repository.QueueRepository;
import com.ezqueue.repository.QueuingRepository;

@Service
public class QueueServiceImpl implements QueueService {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private QueueRepository queueRepository;
	
	@Autowired
	private QueuingRepository queuingRepository;
	
	public List<Queue> getPromotionQueues() throws Exception {
		List<Queue> queues = queueRepository.findByPromotionPriorityNotNullOrderByPromotionPriorityAsc();
		for(Queue queue: queues){
			Double avgWaittingTime = null;
			if(queue.getQueueId() != null){
				avgWaittingTime = queuingRepository.getAvgWaittingTime(queue.getQueueId());
			}
			queue.setAvgWaittingTime(avgWaittingTime == null? 0.0: avgWaittingTime);
		}
		return queues;
	}
	
	public List<Queue> getMyQueues(User user) throws Exception {
		return queueRepository.findByUserOrderByCreateDateDesc(user);
	}
	
	public void addQueue(Queue queue) throws Exception {
		queueRepository.save(queue);
	}
	
	public void removeQueue(String queueId) throws Exception {
		queueRepository.delete(queueId);
	}
}
