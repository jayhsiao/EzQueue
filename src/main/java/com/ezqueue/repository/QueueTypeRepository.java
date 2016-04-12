package com.ezqueue.repository;


import org.springframework.data.repository.CrudRepository;

import com.ezqueue.model.QueueType;

public interface QueueTypeRepository extends CrudRepository<QueueType, String>{
	
}
