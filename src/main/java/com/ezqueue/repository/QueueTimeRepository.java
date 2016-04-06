package com.ezqueue.repository;

import org.springframework.data.repository.CrudRepository;

import com.ezqueue.model.QueueTime;

public interface QueueTimeRepository extends CrudRepository<QueueTime, String>{
	
}
