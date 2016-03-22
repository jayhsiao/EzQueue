package com.ezqueue.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ezqueue.model.User;
import com.ezqueue.model.UserQueueMap;

public interface UserQueueRepository extends CrudRepository<UserQueueMap, Integer>{
	
	@Query("select AVG(u.waittingTime) from UserQueueMap u where u.queue.queueId = :queueId")
	public double getAvgWaittingTime(@Param("queueId") Integer queueId);
	
	public List<UserQueueMap> findByUser(User user);
}
