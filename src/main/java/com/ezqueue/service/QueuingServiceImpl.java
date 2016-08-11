package com.ezqueue.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List<Queuing> getQueuingsByQueue(String queueId, QueuingStatus queuingStatus, int limit, int offset) {
		Queue queue = new Queue();
		queue.setQueueId(queueId);
		
		PageRequest pageRequest = new PageRequest(offset, limit);
		return queuingRepository.findByQueueAndStatusOrderByQueueNumAsc(queue, queuingStatus, pageRequest);
	}
	
	@Override
	public Map<String, Object> getNext(String queueId, QueuingStatus queuingStatus, int limit, int offset) {
		Map<String, Object> resultMap = new HashMap<>();
		
		Queue queue = new Queue();
		queue.setQueueId(queueId);
		
		List<QueuingStatus> queuingStatuss = new ArrayList<>();
		queuingStatuss.add(QueuingStatus.WAITING);
		int waitingCount = this.getQueuingCount(queue, queuingStatuss);
		
		queuingStatuss.clear();
		queuingStatuss.add(QueuingStatus.PASS);
		int passCount = this.getQueuingCount(queue, queuingStatuss);
		
		queuingStatuss.add(QueuingStatus.WAITING);
		int queuingCount = this.getQueuingCount(queue, queuingStatuss);
		
		resultMap.put("queuings", this.getQueuingsByQueue(queueId, queuingStatus, limit, offset));
		resultMap.put("queuingCount", queuingCount);
		resultMap.put("waitingCount", waitingCount);
		resultMap.put("passCount", passCount);
		return resultMap;
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
	public int getQueuingCount(Queue queue, List<QueuingStatus> queuingStatuss) {
		return queuingRepository.countByQueueAndStatusIn(queue, queuingStatuss);
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
		
		Queuing queuing = new Queuing();
		queuing.setQueuingId(StringUtil.getUUID());
		queuing.setUser(user);
		queuing.setQueue(queue);
		queuing.setQueueNum(queueNum);
		queuing.setStatus(QueuingStatus.WAITING);
		
		this.addQueuing(queuing);
		return queuing;
	}
	
	@Override
	public void addQueuing(Queuing queuing) {
		queuingRepository.save(queuing);
	}
	
	@Override
	public void updateStatus(String queuingId, QueuingStatus queuingStatus) {
		Queuing queuing = queuingRepository.findOne(queuingId);
		queuing.setStatus(queuingStatus);
		this.addQueuing(queuing);
	}
	
	@Override
	public void removeQueuing(String queuingId, QueuingStatus queuingStatus) {
		queuingRepository.delete(queuingId);
	}
	
}
