package com.ezqueue.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ezqueue.model.Queue;
import com.ezqueue.model.User;

public interface QueueRepository extends PagingAndSortingRepository<Queue, String>{
	
	public List<Queue> findByUser(User user, Pageable pageable);
}
