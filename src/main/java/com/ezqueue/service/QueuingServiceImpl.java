package com.ezqueue.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
	
	public List<Queuing> getQueuings(String userId) throws Exception {
		User user = new User();
		user.setUserId(userId);
		
		return queuingRepository.findByUser(user);
	}
	
	public Queuing getQueuing(String userId, String queueId) throws Exception {
		User user = new User();
		user.setUserId(userId);
		
		Queue queue = new Queue();
		queue.setQueueId(queueId);
		
		return queuingRepository.findByUserAndQueue(user, queue);
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
		
		Integer queueNum = queuingRepository.countByQueue(startDate, endDate, queue.getQueueId()) + 1;
		queuing.setQueueNum(queueNum);
		
		queuingRepository.save(queuing);
		return queueNum;
	}
	
	public void removeQueuing(String queuingId) throws Exception {
		queuingRepository.delete(queuingId);
	}
	
	public Double getAvgWaittingTime(String queueId) throws Exception {
		return queuingRepository.getAvgWaittingTime(queueId);
	}
	
	public Integer getWaittingCount(String queueId, Integer queueNum) throws Exception {
		return queuingRepository.getWaittingCount(queueId, queueNum);
	}
	
}
