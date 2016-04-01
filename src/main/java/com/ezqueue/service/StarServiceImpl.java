package com.ezqueue.service;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.model.Queue;
import com.ezqueue.model.Star;
import com.ezqueue.model.User;
import com.ezqueue.repository.StarRepository;

@Service
public class StarServiceImpl implements StarService {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private StarRepository starRepository;
	
	public Integer getAvgStar(String queueId) throws Exception {
		Double star = starRepository.getAvgStar(queueId);
		return star != null? star.intValue(): 0;
	}
	
	public Integer getStar(String userId, String queueId) throws Exception {
		User user = new User();
		user.setUserId(userId);
		
		Queue queue = new Queue();
		queue.setQueueId(queueId);
		
		Star star = starRepository.findByUserAndQueue(user, queue);
		return star != null? star.getStar(): 0;
	}
	
	public void addStar(Star star) throws Exception {
		starRepository.save(star);
	}
	
	public void removeStar(String starId) throws Exception {
		starRepository.delete(starId);
	}
}
