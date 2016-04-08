package com.ezqueue.service;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.model.Queue;
import com.ezqueue.model.Star;
import com.ezqueue.model.User;
import com.ezqueue.repository.StarRepository;
import com.ezqueue.util.StringUtil;

@Service
public class StarServiceImpl implements StarService {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private StarRepository starRepository;
	
	public Double getAvgStar(String queueId) throws Exception {
		Double avgStar = starRepository.getAvgStar(queueId);
		return  avgStar != null? (double) Math.round(avgStar * 10)/10: null;
	}
	
	public Star getStar(String userId, String queueId) throws Exception {
		User user= new User();
		user.setUserId(userId);
		
		Queue queue = new Queue();
		queue.setQueueId(queueId);
		
		Star star = starRepository.findByUserAndQueue(user, queue);
		return star;
	}
	
	public void addStar(Star star) throws Exception {
		Star oldStar = starRepository.findByUserAndQueue(star.getUser(), star.getQueue());
		if(oldStar == null){
			star.setStarId(StringUtil.getUUID());
			starRepository.save(star);
		}
		else {
			oldStar.setStar(star.getStar());
			starRepository.save(oldStar);
		}
	}
}
