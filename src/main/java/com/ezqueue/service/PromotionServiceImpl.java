package com.ezqueue.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.model.Promotion;
import com.ezqueue.repository.PromotionRepository;

@Service
public class PromotionServiceImpl implements PromotionService {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private PromotionRepository promotionRepository;
	
	public List<Promotion> getPromotions() throws Exception {
		return promotionRepository.getPromotions();
	}
	
	public Promotion getPromotion(String queueId) throws Exception {
		return promotionRepository.getPromotion(queueId);
	}
	
	public void addPromotion(Promotion promotion) throws Exception {
		promotionRepository.save(promotion);
	}
	
	public void removePromotion(String promotionId) throws Exception {
		promotionRepository.delete(promotionId);
	}
	
}
