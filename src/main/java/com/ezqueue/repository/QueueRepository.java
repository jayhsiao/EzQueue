package com.ezqueue.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ezqueue.model.Queue;

public interface QueueRepository extends CrudRepository<Queue, Integer>{
	
	public List<Queue> findByPromotionPriorityNotNullOrderByPromotionPriorityAsc();
	
	public List<Queue> findByUserIdOrderByCreateDateDesc(Integer userId);
}
