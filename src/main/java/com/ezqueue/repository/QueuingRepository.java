package com.ezqueue.repository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ezqueue.model.User;
import com.ezqueue.util.QueuingStatus;
import com.ezqueue.model.Queue;
import com.ezqueue.model.Queuing;

public interface QueuingRepository extends PagingAndSortingRepository<Queuing, String>{
	
//	@Query("select AVG(q.endDate - q.startDate) from Queuing q where q.queue.queueId = :queueId")
//	public Double getAvgWaittingTime(@Param("queueId") String queueId);
	
	public List<Queuing> findByUser(User user, Pageable pageable);
	
	public List<Queuing> findByQueueAndStatusOrderByQueueNumAsc(Queue queue, QueuingStatus queuingStatus, Pageable pageable);
	
	public Queuing findByUserAndQueue(User user, Queue queue);
	
	public int countByQueue(Queue queue);
	
}
