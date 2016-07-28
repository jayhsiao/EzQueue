package com.ezqueue.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ezqueue.model.User;
import com.ezqueue.model.Queue;
import com.ezqueue.model.Queuing;
import com.ezqueue.repository.QueuingRepository;
import com.ezqueue.util.QueuingStatus;
import com.ezqueue.util.StringUtil;

@Service
public class QueuingServiceImpl implements QueuingService {
	
	@Autowired
	private QueuingRepository queuingRepository;
	
	@Autowired
	private QueueService queueService;
	
	@Override
	public List<Queuing> getQueuingsByUser(String userId, int limit, int offset) {
		User user = new User();
		user.setUserId(userId);
		
		PageRequest pageRequest = new PageRequest(offset, limit);
		return queuingRepository.findByUser(user, pageRequest);
	}
	
	@Override
	public List<Queuing> getQueuingsByQueue(String queueId, int limit, int offset) {
		Queue queue = new Queue();
		queue.setQueueId(queueId);
		
		PageRequest pageRequest = new PageRequest(offset, limit);
		return queuingRepository.findByQueue(queue, pageRequest);
	}
	
	@Override
	public Queuing getQueuing(String userId, String queueId) {
		User user = new User();
		user.setUserId(userId);
		
		Queue queue = new Queue();
		queue.setQueueId(queueId);
		
		return queuingRepository.findByUserAndQueue(user, queue);
	}
	
	@Override
	public int getQueuingCount(Queue queue) {
		List<QueuingStatus> queuingStatuss = new ArrayList<>();
		queuingStatuss.add(QueuingStatus.WAITTING);
		queuingStatuss.add(QueuingStatus.PASS);
		
		int queuingCount = queuingRepository.countByQueueAndStatusIn(queue, queuingStatuss);
		if(queuingCount > 0){
			return queuingCount - 1;
		}
		return queuingCount;
	}
	
	@Override
	synchronized public Queuing queuing(String userId, String queueId) {
		
		Queue updateQueue = queueService.getQueue(queueId);
		Integer queueNum = updateQueue.getQueueNum() + 1;
		updateQueue.setQueueNum(queueNum);
		queueService.addQueue(updateQueue);
		
		User user = new User();
		user.setUserId(userId);
		Queue queue = new Queue();
		queue.setQueueId(queueId);
		
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		
		Queuing queuing = new Queuing();
		queuing.setQueuingId(StringUtil.getUUID());
		queuing.setUser(user);
		queuing.setQueue(queue);
		queuing.setStartDate(now);
		queuing.setQueueNum(queueNum);
		queuing.setStatus(QueuingStatus.WAITTING);
		
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		this.addQueuing(queuing);
		return queuing;
	}
	
	@Override
	public Double getAvgWaittingTime(String queueId) {
		return queuingRepository.getAvgWaittingTime(queueId);
	}
	
	@Override
	public void addQueuing(Queuing queuing) {
		queuingRepository.save(queuing);
	}
	
	@Override
	public void updateStatus(String userId, String queuingId, QueuingStatus queuingStatus) {
		Queuing queuing = queuingRepository.findOne(queuingId);
		queuing.setStatus(queuingStatus);
		this.addQueuing(queuing);
	}
	
	@Override
	public void removeQueuing(String queuingId) {
		queuingRepository.delete(queuingId);
	}
}
