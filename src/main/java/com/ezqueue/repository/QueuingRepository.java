package com.ezqueue.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ezqueue.model.User;
import com.ezqueue.model.Queue;
import com.ezqueue.model.Queuing;

public interface QueuingRepository extends PagingAndSortingRepository<Queuing, String>{
	
	@Query("select AVG(q.endDate - q.startDate) from Queuing q where q.queue.queueId = :queueId")
	public Double getAvgWaittingTime(@Param("queueId") String queueId);
	
	public List<Queuing> findByUserAndStatus(User user, Integer status, Pageable pageable);
	
	public List<Queuing> findByQueueAndStatus(Queue queue, Integer status, Pageable pageable);
	
	public Queuing findByUserAndQueueAndStatus(User user, Queue queue, int status);
	
	@Query("select max(q.queueNum) from Queuing q where now() between :startDate and :endDate and q.queue.queueId = :queueId")
	public Integer getMaxQueueNum(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("queueId") String queueId);
}
