package com.ezqueue.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.model.Favorite;
import com.ezqueue.model.Promotion;
import com.ezqueue.model.Queue;
import com.ezqueue.model.Queuing;
import com.ezqueue.model.User;
import com.ezqueue.repository.QueueRepository;

@Service
public class QueueServiceImpl implements QueueService {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private QueueRepository queueRepository;
	
	@Autowired
	private PromotionService promotionService;
	
	@Autowired
	private FavoriteService favoriteService;
	
	@Autowired
	private QueuingService queuingService;
	
	public List<Queue> getMyQueues(User user) throws Exception {
		return queueRepository.findByUserOrderByCreateDateDesc(user);
	}
	
	public List<Map<String, Object>> getPromotionQueues(String userId) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		List<Promotion> promotions = promotionService.getPromotions();
		for(Promotion promotion: promotions){
			Map<String, Object> queueMap = new HashMap<String, Object>();
			
			Queue queue = promotion.getQueue();
			Double avgSeconds = queuingService.getAvgWaittingTime(queue.getQueueId());
			Favorite favorite = favoriteService.getFavorite(userId, queue.getQueueId());
			
			Queuing queuing = queuingService.getQueuing(userId, queue.getQueueId());
			Integer waittingCount = 0;
			Integer queueNum = null;
			if(queuing == null){
				if(queue.getQueuings() != null){
					waittingCount = queue.getQueuings().size();
				}
			}
			else {
				waittingCount = queuingService.getWaittingCount(queue.getQueueId(), queuing.getQueueNum());
				queueNum = queuing.getQueueNum();
			}
			
			queueMap.put("queue", queue);
			queueMap.put("waittingCount", waittingCount);
			queueMap.put("avgWaittingTime", this.getAvgWaittingTimeString(avgSeconds));
			queueMap.put("queueNum", queueNum);
			queueMap.put("promotionId", promotion.getPromotionId());
			queueMap.put("favoriteId", favorite != null? favorite.getFavoriteId(): null);
			queueMap.put("queuingId", queuing != null? queuing.getQueuingId(): null);
			
			list.add(queueMap);
		}
		return list;
	}
	
	public List<Map<String, Object>> getQueuingQueues(String userId) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		List<Queuing> queuings = queuingService.getQueuings(userId);
		for(Queuing queuing: queuings){
			Map<String, Object> queueMap = new HashMap<String, Object>();
			
			Queue queue = queuing.getQueue();
			Double avgSeconds = queuingService.getAvgWaittingTime(queue.getQueueId());
			Favorite favorite = favoriteService.getFavorite(userId, queue.getQueueId());
			Promotion promotion = promotionService.getPromotion(queue.getQueueId());
			
			Integer waittingCount = queuingService.getWaittingCount(queue.getQueueId(), queuing.getQueueNum());
			
			queueMap.put("queue", queue);
			queueMap.put("waittingCount", waittingCount);
			queueMap.put("avgWaittingTime", this.getAvgWaittingTimeString(avgSeconds));
			queueMap.put("queueNum", queuing.getQueueNum());
			queueMap.put("promotionId", promotion != null? promotion.getPromotionId(): null);
			queueMap.put("favoriteId", favorite != null? favorite.getFavoriteId(): null);
			queueMap.put("queuingId", queuing.getQueuingId());
			
			list.add(queueMap);
		}
		return list;
	}
	
	public List<Map<String, Object>> getFavoriteQueues(String userId) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		List<Favorite> favorites = favoriteService.getFavorites(userId);
		for(Favorite favorite: favorites){
			Map<String, Object> queueMap = new HashMap<String, Object>();
			
			Queue queue = favorite.getQueue();
			Double avgSeconds = queuingService.getAvgWaittingTime(queue.getQueueId());
			Promotion promotion = promotionService.getPromotion(queue.getQueueId());
			
			Queuing queuing = queuingService.getQueuing(userId, queue.getQueueId());
			Integer waittingCount = 0;
			Integer queueNum = null;
			if(queuing == null){
				if(queue.getQueuings() != null){
					waittingCount = queue.getQueuings().size();
				}
			}
			else {
				waittingCount = queuingService.getWaittingCount(queue.getQueueId(), queuing.getQueueNum());
				queueNum = queuing.getQueueNum();
			}
			
			queueMap.put("queue", queue);
			queueMap.put("waittingCount", waittingCount);
			queueMap.put("avgWaittingTime", this.getAvgWaittingTimeString(avgSeconds));
			queueMap.put("queueNum", queueNum);
			queueMap.put("promotionId", promotion != null? promotion.getPromotionId(): null);
			queueMap.put("favoriteId", favorite.getFavoriteId());
			queueMap.put("queuingId", queuing != null? queuing.getQueuingId(): null);
			
			list.add(queueMap);
		}
		return list;
	}
	
	public void addQueue(Queue queue) throws Exception {
		queueRepository.save(queue);
	}
	
	public void removeQueue(String queueId) throws Exception {
		queueRepository.delete(queueId);
	}
	
	private String getAvgWaittingTimeString(Double seconds){
		String avgWaittingTimeString = "0 ¬í";
		if(seconds != null){
			long hours = TimeUnit.SECONDS.toHours(seconds.intValue());
			long minute = TimeUnit.SECONDS.toMinutes(seconds.intValue()) - (TimeUnit.SECONDS.toHours(seconds.intValue()) * 60);
			long second = TimeUnit.SECONDS.toSeconds(seconds.intValue()) - (TimeUnit.SECONDS.toMinutes(seconds.intValue()) * 60);
			avgWaittingTimeString =  hours + " ¤p®É " + minute + " ¤À " + second + " ¬í";
		}
		return avgWaittingTimeString;
	}
}
