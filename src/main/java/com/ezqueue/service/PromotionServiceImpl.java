package com.ezqueue.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ezqueue.model.Promotion;
import com.ezqueue.model.Queue;
import com.ezqueue.repository.PromotionRepository;
import com.google.common.collect.Lists;

@Service
public class PromotionServiceImpl implements PromotionService {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private PromotionRepository promotionRepository;
	
	public List<Promotion> getPromotions(int page, int size) throws Exception {
		PageRequest pageRequest = new PageRequest(page, size, Direction.DESC, "createDate");
		return Lists.newArrayList(promotionRepository.findAll(pageRequest).iterator());
	}
	
	public Promotion getPromotion(String queueId) throws Exception {
		Queue queue = new Queue();
		queue.setQueueId(queueId);
		
		return promotionRepository.findByQueue(queue);
	}
	
	public void addPromotion(Promotion promotion) throws Exception {
		promotionRepository.save(promotion);
	}
	
	public void removePromotion(String promotionId) throws Exception {
		promotionRepository.delete(promotionId);
	}
	
}
