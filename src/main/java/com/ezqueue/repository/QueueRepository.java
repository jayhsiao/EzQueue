package com.ezqueue.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ezqueue.model.Queue;
import com.ezqueue.model.QueueType;
import com.ezqueue.model.User;

public interface QueueRepository extends PagingAndSortingRepository<Queue, String>{
	
	public List<Queue> findByUserIn(List<User> users, Pageable pageable);
	
	@Query("select q from Queue q where q.title like :text or q.dscr like :text or q.address like :text")
	public List<Queue> getQueueByText(@Param("text") String text, Pageable pageable);
	
	public List<Queue> findByQueueType(QueueType queueType, Pageable pageable);
	
}
