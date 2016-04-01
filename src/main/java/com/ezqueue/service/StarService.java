package com.ezqueue.service;


import com.ezqueue.model.Star;

public interface StarService {
	
	public Integer getAvgStar(String queueId) throws Exception;

	public Integer getStar(String userId, String queueId) throws Exception;
	
	public void addStar(Star star) throws Exception;
	
	public void removeStar(String starId) throws Exception;
}
