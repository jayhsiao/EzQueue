package com.ezqueue.service;

import java.util.List;

import com.ezqueue.model.Promotion;

public interface PromotionService {

	public List<Promotion> getPromotions(int page, int size) throws Exception;
	
	public Promotion getPromotion(String queueId) throws Exception;
	
	public void addPromotion(Promotion promotion) throws Exception;
	
	public void removePromotion(String promotionId) throws Exception;
}
