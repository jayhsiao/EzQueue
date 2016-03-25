package com.ezqueue.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ezqueue.model.User;
import com.ezqueue.model.Queuing;

public interface QueuingRepository extends CrudRepository<Queuing, String>{
	
	@Query("select AVG(q.waittingTime) from Queuing q where q.queue.queueId = :queueId")
	public Double getAvgWaittingTime(@Param("queueId") String queueId);
	
	public List<Queuing> findByUser(User user);
}
