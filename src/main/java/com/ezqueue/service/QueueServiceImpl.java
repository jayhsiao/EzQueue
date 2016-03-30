package com.ezqueue.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.ezqueue.util.QueuingStatus;

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
	
	public List<Map<String, Object>> getMyQueues(String userId) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		User user = new User();
		user.setUserId(userId);
		
		List<Queue> queues = queueRepository.findByUserOrderByCreateDateDesc(user);
		for(Queue queue: queues){
			Map<String, Object> queueMap = new HashMap<String, Object>();
			
			Favorite favorite = favoriteService.getFavorite(userId, queue.getQueueId());
			Promotion promotion = promotionService.getPromotion(queue.getQueueId());
			List<Queuing> queuings = queuingService.getQueuingsByQueueId(queue.getQueueId(), QueuingStatus.WAITTING);
			this.sortQueuings(queuings);
			Double avgSeconds = queuingService.getAvgWaittingTime(queue.getQueueId());
			
			String queuingId = null;
			Integer queueNum = null;
			
			
			
			for(Queuing queuing: queuings){
				if(userId.equals(queuing.getUser().getUserId())){
					queuingId = queuing.getQueuingId();
					queueNum = queuing.getQueueNum();
					break;
				}
			}
			
			queueMap.put("queue", queue);
			queueMap.put("avgWaittingTime", this.getAvgWaittingTimeString(avgSeconds));
			queueMap.put("queueNum", queueNum);
			queueMap.put("queuings", queuings);
			queueMap.put("promotionId", promotion != null? promotion.getPromotionId(): null);
			queueMap.put("favoriteId", favorite != null? favorite.getFavoriteId(): null);
			queueMap.put("queuingId", queuingId);
			queueMap.put("isMyQueues", true);
			
			list.add(queueMap);
		}
		return list;
	}
	
	public List<Map<String, Object>> getPromotionQueues(String userId) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		List<Promotion> promotions = promotionService.getPromotions();
		for(Promotion promotion: promotions){
			Map<String, Object> queueMap = new HashMap<String, Object>();
			
			Queue queue = promotion.getQueue();
			
			Favorite favorite = favoriteService.getFavorite(userId, queue.getQueueId());
			List<Queuing> queuings = queuingService.getQueuingsByQueueId(queue.getQueueId(), QueuingStatus.WAITTING);
			Double avgSeconds = queuingService.getAvgWaittingTime(queue.getQueueId());
			
			String queuingId = null;
			Integer queueNum = null;
			for(Queuing queuing: queuings){
				if(userId.equals(queuing.getUser().getUserId())){
					queuingId = queuing.getQueuingId();
					queueNum = queuing.getQueueNum();
					break;
				}
			}
			
			queueMap.put("queue", queue);
			queueMap.put("avgWaittingTime", this.getAvgWaittingTimeString(avgSeconds));
			queueMap.put("queueNum", queueNum);
			queueMap.put("queuings", queuings);
			queueMap.put("promotionId", promotion.getPromotionId());
			queueMap.put("favoriteId", favorite != null? favorite.getFavoriteId(): null);
			queueMap.put("queuingId", queuingId);
			queueMap.put("isMyQueues", false);
			
			list.add(queueMap);
		}
		return list;
	}
	
	public List<Map<String, Object>> getQueuingQueues(String userId) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		List<Queuing> queuings = queuingService.getQueuingsByUserId(userId, QueuingStatus.WAITTING);
		for(Queuing queuing: queuings){
			Map<String, Object> queueMap = new HashMap<String, Object>();
			
			Queue queue = queuing.getQueue();
			
			Promotion promotion = promotionService.getPromotion(queue.getQueueId());
			Favorite favorite = favoriteService.getFavorite(userId, queue.getQueueId());
			Double avgSeconds = queuingService.getAvgWaittingTime(queue.getQueueId());
			
			queueMap.put("queue", queue);
			queueMap.put("avgWaittingTime", this.getAvgWaittingTimeString(avgSeconds));
			queueMap.put("queueNum", queuing.getQueueNum());
			queueMap.put("queuings", queuings);
			queueMap.put("promotionId", promotion != null? promotion.getPromotionId(): null);
			queueMap.put("favoriteId", favorite != null? favorite.getFavoriteId(): null);
			queueMap.put("queuingId", queuing.getQueuingId());
			queueMap.put("isMyQueues", false);
			
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
			
			Promotion promotion = promotionService.getPromotion(queue.getQueueId());
			List<Queuing> queuings = queuingService.getQueuingsByQueueId(queue.getQueueId(), QueuingStatus.WAITTING);
			Double avgSeconds = queuingService.getAvgWaittingTime(queue.getQueueId());
			
			String queuingId = null;
			Integer queueNum = null;
			for(Queuing queuing: queuings){
				if(userId.equals(queuing.getUser().getUserId())){
					queuingId = queuing.getQueuingId();
					queueNum = queuing.getQueueNum();
					break;
				}
			}
			
			queueMap.put("queue", queue);
			queueMap.put("avgWaittingTime", this.getAvgWaittingTimeString(avgSeconds));
			queueMap.put("queueNum", queueNum);
			queueMap.put("queuings", queuings);
			queueMap.put("promotionId", promotion != null? promotion.getPromotionId(): null);
			queueMap.put("favoriteId", favorite.getFavoriteId());
			queueMap.put("queuingId", queuingId);
			queueMap.put("isMyQueues", false);
			
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
		String avgWaittingTimeString = "0 秒";
		if(seconds != null){
			long hours = TimeUnit.SECONDS.toHours(seconds.intValue());
			long minute = TimeUnit.SECONDS.toMinutes(seconds.intValue()) - (TimeUnit.SECONDS.toHours(seconds.intValue()) * 60);
			long second = TimeUnit.SECONDS.toSeconds(seconds.intValue()) - (TimeUnit.SECONDS.toMinutes(seconds.intValue()) * 60);
			avgWaittingTimeString =  hours + " : " + minute + " : " + second;
		}
		return avgWaittingTimeString;
	}
	
	private void sortQueuings(List<Queuing> queuings){
		Collections.sort(queuings, new Comparator<Queuing>() {
			@Override
			public int compare(Queuing o1, Queuing o2) {
				if(o1.getQueueNum() > o2.getQueueNum()){
					return 1;
				}
				else{
					return -1;
				}
			}
		});
	}
}
