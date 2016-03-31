package com.ezqueue.repository;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.ezqueue.model.Promotion;
import com.ezqueue.model.Queue;

public interface PromotionRepository extends PagingAndSortingRepository<Promotion, String>{
	
	public Promotion findByQueue(Queue queue);
}
