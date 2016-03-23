package com.ezqueue.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ezqueue.model.User;
import com.ezqueue.model.Queuing;

public interface UserQueueRepository extends CrudRepository<Queuing, Integer>{
	
	@Query("select AVG(q.waittingTime) from Queuing q where q.queue.queueId = :queueId")
	public double getAvgWaittingTime(@Param("queueId") Integer queueId);
	
	public List<Queuing> findByUser(User user);
}
