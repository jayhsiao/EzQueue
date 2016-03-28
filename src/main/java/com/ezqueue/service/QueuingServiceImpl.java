package com.ezqueue.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.model.User;
import com.ezqueue.model.Queue;
import com.ezqueue.model.Queuing;
import com.ezqueue.repository.QueuingRepository;

@Service
public class QueuingServiceImpl implements QueuingService {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private QueuingRepository queuingRepository;
	
	public List<Queue> getQueuing(String userId) throws Exception {
		List<Queue> queues = new ArrayList<Queue>();
		
		User user = new User();
		user.setUserId(userId);
		
		List<Queuing> queuings = queuingRepository.findByUser(user);
		for(Queuing queuing: queuings){
			queuing.getQueue().setQueuingId(queuing.getQueuingId());
			queues.add(queuing.getQueue());
		}
		
		return queues;
	}
	
	public void addQueuing(Queuing userQueueMap) throws Exception {
		queuingRepository.save(userQueueMap);
	}
	
	public void removeQueuing(String queuingId) throws Exception {
		queuingRepository.delete(queuingId);
	}
}
