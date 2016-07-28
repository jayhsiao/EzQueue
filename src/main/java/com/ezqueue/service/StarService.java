package com.ezqueue.service;


import com.ezqueue.model.Queue;
import com.ezqueue.model.Star;

public interface StarService {
	
	public Double getAvgStar(String queueId);
	
	public int getStarsCount(Queue queue);
	
	public Star getStar(String userId, String queueId);
	
	public String addStar(String userId, String queueId, Integer starNum);
	
	public void updateStar(String starId, Integer starNum);
	
	public void removeStar(String starId);
	
}
