package com.ezqueue.service;


import com.ezqueue.model.Star;

public interface StarService {
	
	public Double getAvgStar(String queueId) throws Exception;
	
	public Star getStar(String userId, String queueId) throws Exception;
	
	public void addStar(Star star) throws Exception;
}
