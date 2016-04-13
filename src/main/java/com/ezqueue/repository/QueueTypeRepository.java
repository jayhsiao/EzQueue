package com.ezqueue.repository;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.ezqueue.model.QueueType;

public interface QueueTypeRepository extends PagingAndSortingRepository<QueueType, String>{
	
}
