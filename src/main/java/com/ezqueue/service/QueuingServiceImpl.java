package com.ezqueue.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.model.User;
import com.ezqueue.model.Queuing;
import com.ezqueue.repository.QueuingRepository;

@Service
public class QueuingServiceImpl implements QueuingService {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private QueuingRepository queuingRepository;
	
	public List<Queuing> getQueuing(Integer userId) throws Exception {
		User user = new User();
		user.setUserId(userId);
		return queuingRepository.findByUser(user);
	}
	
	public void addQueuing(Queuing userQueueMap) throws Exception {
		queuingRepository.save(userQueueMap);
	}
	
	public void removeQueuing(Integer userQueueMapId) throws Exception {
		queuingRepository.delete(userQueueMapId);
	}
}
