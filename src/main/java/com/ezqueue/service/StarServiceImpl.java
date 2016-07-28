package com.ezqueue.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.model.Queue;
import com.ezqueue.model.Star;
import com.ezqueue.model.User;
import com.ezqueue.repository.StarRepository;
import com.ezqueue.util.StringUtil;

@Service
public class StarServiceImpl implements StarService {
	
	@Autowired
	private StarRepository starRepository;
	
	@Override
	public Double getAvgStar(String queueId) {
		Double avgStar = starRepository.getAvgStar(queueId);
		return avgStar != null? (double) Math.round(avgStar * 10)/10: 0.0;
	}
	
	@Override
	public int getStarsCount(Queue queue) {
		return starRepository.countByQueue(queue);
	}
	
	@Override
	public Star getStar(String userId, String queueId) {
		User user = new User();
		user.setUserId(userId);
		
		Queue queue = new Queue();
		queue.setQueueId(queueId);
		
		return starRepository.findByUserAndQueue(user, queue);
	}
	
	@Override
	public String addStar(String userId, String queueId, Integer starNum) {
		User user = new User();
		user.setUserId(userId);
		
		Queue queue = new Queue();
		queue.setQueueId(queueId);
		
		Star star = new Star();
		star.setStarId(StringUtil.getUUID());
		star.setUser(user);
		star.setQueue(queue);
		star.setStarNum(starNum);
		starRepository.save(star);
		
		return star.getStarId();
	}
	
	@Override
	public void updateStar(String starId, Integer starNum) {
		Star star = starRepository.findOne(starId);
		star.setStarNum(starNum);
		starRepository.save(star);
	}
	
	@Override
	public void removeStar(String starId) {
		starRepository.delete(starId);
	}
	
}
