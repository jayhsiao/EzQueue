package com.ezqueue.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.model.User;
import com.ezqueue.model.Queue;
import com.ezqueue.model.Queuing;
import com.ezqueue.repository.QueuingRepository;
import com.ezqueue.util.QueuingStatus;
import com.ezqueue.util.StringUtil;

@Service
public class QueuingServiceImpl implements QueuingService {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private QueuingRepository queuingRepository;
	
	public List<Queuing> getQueuingsByUserId(String userId, int status) throws Exception {
		User user = new User();
		user.setUserId(userId);
		
		return queuingRepository.findByUserAndStatus(user, status);
	}
	
	public List<Queuing> getQueuingsByQueueId(String queueId, int status) throws Exception {
		Queue queue = new Queue();
		queue.setQueueId(queueId);
		
		return queuingRepository.findByQueueAndStatus(queue, status);
	}
	
	public Queuing getQueuing(String userId, String queueId, int status) throws Exception {
		User user = new User();
		user.setUserId(userId);
		
		Queue queue = new Queue();
		queue.setQueueId(queueId);
		
		return queuingRepository.findByUserAndQueueAndStatus(user, queue, status);
	}
	
	synchronized public Integer addQueuing(Map<String, Object> map) throws Exception {
		User user = new User();
		user.setUserId((String) map.get("userId"));
		
		Queue queue = new Queue();
		queue.setQueueId((String) map.get("queueId"));
		
		Queuing queuing = new Queuing();
		queuing.setQueuingId(StringUtil.getUUID());
		queuing.setUser(user);
		queuing.setQueue(queue);
		queuing.setStatus(QueuingStatus.WAITTING);
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = calendar.getTime();
		
		calendar.add(Calendar.DAY_OF_MONTH, +1);
		Date endDate = calendar.getTime();
		
		Integer maxQueueNum = queuingRepository.getMaxQueueNum(startDate, endDate, queue.getQueueId());
		maxQueueNum = maxQueueNum == null? 1: maxQueueNum + 1;
		queuing.setQueueNum(maxQueueNum);
		
		this.addQueuing(queuing);
		return maxQueueNum;
	}
	
	public void addQueuing(Queuing queuing) throws Exception {
		queuingRepository.save(queuing);
	}
	
	public void removeQueuing(String queuingId) throws Exception {
		queuingRepository.delete(queuingId);
	}
	
	public Double getAvgWaittingTime(String queueId) throws Exception {
		return queuingRepository.getAvgWaittingTime(queueId);
	}
	
	public void updateWaittingStatus(Queuing queuing) throws Exception {
		Queuing oldQueuing = queuingRepository.findOne(queuing.getQueuingId());
		oldQueuing.setEndDate(Calendar.getInstance().getTime());
		oldQueuing.setWaittingTime(TimeUnit.MILLISECONDS.toSeconds(oldQueuing.getEndDate().getTime() - oldQueuing.getStartDate().getTime()));
		oldQueuing.setStatus(queuing.getStatus());
		
		this.addQueuing(oldQueuing);
	}
}
