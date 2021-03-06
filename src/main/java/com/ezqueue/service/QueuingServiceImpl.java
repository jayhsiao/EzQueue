package com.ezqueue.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ezqueue.model.User;
import com.ezqueue.model.Queue;
import com.ezqueue.model.Queuing;
import com.ezqueue.repository.QueuingRepository;
import com.ezqueue.util.EzQueueConstants;
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
	public Map<String, Object> success(String queuingId, String queueId) {
		Queuing queuing = queuingRepository.findOne(queuingId);
		if(queuing != null){
			queuingRepository.delete(queuingId);
		}
		
		return this.next(queueId);
	}
	
	@Override
	public Map<String, Object> pass(String queuingId, String queueId) {
		Queuing queuing = queuingRepository.findOne(queuingId);
		if(queuing != null){
			this.updateStatus(queuingId, QueuingStatus.PASS);
		}
		
		return this.next(queueId);
	}
	
	@Override
	public Map<String, Object> next(String queueId) {
		Map<String, Object> resultMap = new HashMap<>();
		
		Queue queue = queueService.getQueue(queueId);
		List<Queuing> queuings = queue.getQueuings();
		
		List<Queuing> waitingQueuings = queuings.stream().filter(q -> QueuingStatus.WAITING.equals(q.getStatus())).collect(Collectors.toList());
		List<Queuing> passQueuings = queuings.stream().filter(q -> QueuingStatus.PASS.equals(q.getStatus())).collect(Collectors.toList());
		
		long waitingLessCount = waitingQueuings.stream().count() - EzQueueConstants.INIT_QUEUING_LIMIT;
		if(waitingLessCount < 0){
			waitingLessCount = 0;
		}
		
		long passLessCount = passQueuings.stream().count() - EzQueueConstants.INIT_QUEUING_LIMIT;
		if(passLessCount < 0){
			passLessCount = 0;
		}
		
		resultMap.put("waitingQueuings", waitingQueuings.stream().limit(EzQueueConstants.INIT_QUEUING_LIMIT).collect(Collectors.toList()));
		resultMap.put("passQueuings", passQueuings.stream().limit(EzQueueConstants.INIT_QUEUING_LIMIT).collect(Collectors.toList()));
		resultMap.put("queuingCount", queuings.stream().count());
		resultMap.put("waitingCount", waitingQueuings.stream().count());
		resultMap.put("passCount", passQueuings.stream().count());
		resultMap.put("waitingLessCount", waitingLessCount);
		resultMap.put("passLessCount", passLessCount);
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
	synchronized public Map<String, Object> queuing(String userId, String queueId) {
		Map<String, Object> resultMap = new HashMap<>();
		
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
		
		resultMap.put("queuingId", queuing.getQueuingId());
		resultMap.put("queueNum", queuing.getQueueNum());
		resultMap.put("queuingCount", queuingRepository.countByQueue(queue));
		
		return resultMap;
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
	public long removeQueuing(String queuingId, String queueId) {
		queuingRepository.delete(queuingId);
		Queue queue = new Queue();
		queue.setQueueId(queueId);
		return queuingRepository.countByQueue(queue);
	}
	
}
