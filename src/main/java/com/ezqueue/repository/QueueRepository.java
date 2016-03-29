package com.ezqueue.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ezqueue.model.Queue;
import com.ezqueue.model.User;

public interface QueueRepository extends CrudRepository<Queue, String>{
	
	public List<Queue> findByUserOrderByCreateDateDesc(User user);
}
