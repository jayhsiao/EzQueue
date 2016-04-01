package com.ezqueue.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ezqueue.model.Queue;
import com.ezqueue.model.Star;
import com.ezqueue.model.User;

public interface StarRepository extends CrudRepository<Star, String>{
	
	@Query("select AVG(s.star) from Star s where s.queue.queueId = :queueId")
	public Double getAvgStar(@Param("queueId") String queueId);
	
	public Star findByUserAndQueue(User user, Queue queue);
}
