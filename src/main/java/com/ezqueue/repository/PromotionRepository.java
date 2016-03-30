package com.ezqueue.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ezqueue.model.Promotion;

public interface PromotionRepository extends CrudRepository<Promotion, String>{
	
	@Query("select p from Promotion p where now() between p.startDate and p.endDate")
	public List<Promotion> getPromotions();
	
	@Query("select p from Promotion p where now() between p.startDate and p.endDate and p.queue.queueId = :queueId")
	public Promotion getPromotion(@Param("queueId") String queueId);
}
