package com.ezqueue.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ezqueue.model.User;
import com.ezqueue.model.Queue;
import com.ezqueue.model.Queuing;

public interface QueuingRepository extends CrudRepository<Queuing, String>{
	
	@Query("select AVG(q.waittingTime) from Queuing q where q.queue.queueId = :queueId")
	public Double getAvgWaittingTime(@Param("queueId") String queueId);
	
	@Query("select count(*) from Queuing q where q.queue.queueId = :queueId and q.queueNum < :queueNum")
	public Integer getWaittingCount(@Param("queueId") String queueId, @Param("queueNum") Integer queueNum);
	
	public List<Queuing> findByUser(User user);
	
	public Queuing findByUserAndQueue(User user, Queue queue);
	
	@Query("select count(*) from Queuing q where now() between :startDate and :endDate and q.queue.queueId = :queueId")
	public Integer countByQueue(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("queueId") String queueId);
}
