package com.ezqueue.service;

import java.util.List;

import com.ezqueue.model.Promotion;

public interface PromotionService {
	
	public Promotion getPromotion(String queueId);

	public List<Promotion> getPromotions(int limit, int offset);
	
	public void addPromotion(List<String> queueIds);
	
	public void removePromotions(List<String> queueIds);
	
}
