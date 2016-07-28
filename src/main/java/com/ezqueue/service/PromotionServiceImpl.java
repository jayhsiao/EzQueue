package com.ezqueue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ezqueue.model.Promotion;
import com.ezqueue.model.Queue;
import com.ezqueue.repository.PromotionRepository;
import com.ezqueue.util.StringUtil;
import com.google.common.collect.Lists;

@Service
public class PromotionServiceImpl implements PromotionService {
	
	@Autowired
	private PromotionRepository promotionRepository;
	
	@Override
	public Promotion getPromotion(String queueId) {
		Queue queue = new Queue();
		queue.setQueueId(queueId);
		
		return promotionRepository.findByQueue(queue);
	}
	
	@Override
	public List<Promotion> getPromotions(int limit, int offset) {
		PageRequest pageRequest = new PageRequest(limit, offset, Direction.DESC, "createDate");
		return Lists.newArrayList(promotionRepository.findAll(pageRequest).iterator());
	}
	
	@Override
	public void addPromotion(List<String> queueIds) {
		
		for(String queueId: queueIds){
			Queue queue = new Queue();
			queue.setQueueId(queueId);
			
			Promotion promotion = new Promotion();
			promotion.setPromotionId(StringUtil.getUUID());
			promotion.setQueue(queue);
			
			promotionRepository.save(promotion);
		}
	}
	
	@Override
	public void removePromotion(List<String> promotionIds) {
		for(String promotionId: promotionIds){
			Promotion promotion = new Promotion();
			promotion.setPromotionId(promotionId);
			promotionRepository.delete(promotion);
		}
	}
	
}
