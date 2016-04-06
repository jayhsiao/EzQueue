package com.ezqueue.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
	
	@Autowired
	private StarService starsService;
	
	public List<Map<String, Object>> getMyQueues(String userId, int page, int size) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		User user = new User();
		user.setUserId(userId);
		
		PageRequest pageRequest = new PageRequest(page, size, Direction.DESC, "createDate");
		List<Queue> queues = queueRepository.findByUser(user, pageRequest);
		for(Queue queue: queues){
			Map<String, Object> queueMap = new HashMap<String, Object>();
			
			Favorite favorite = favoriteService.getFavorite(userId, queue.getQueueId());
			Promotion promotion = promotionService.getPromotion(queue.getQueueId());
			List<Queuing> queuings = queuingService.getQueuingsByQueueId(queue.getQueueId(), QueuingStatus.WAITTING, page, size);
			Double avgSeconds = queuingService.getAvgWaittingTime(queue.getQueueId());
			Integer star = starsService.getStar(userId, queue.getQueueId());
			Double avgStar = starsService.getAvgStar(queue.getQueueId());
			
			Integer queuingCount = 0;
			if(queuings != null && queuings.size() > 1){
				queuingCount = queuings.size() - 1;
			}
			
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
			queueMap.put("star", star);
			queueMap.put("avgStar", avgStar);
			queueMap.put("queuingCount", queuingCount);
			queueMap.put("avgWaittingTime", this.getAvgWaittingTimeString(avgSeconds));
			queueMap.put("queueNum", queueNum);
			queueMap.put("queuings", queuings);
			queueMap.put("promotionId", promotion != null? promotion.getPromotionId(): null);
			queueMap.put("favoriteId", favorite != null? favorite.getFavoriteId(): null);
			queueMap.put("queuingId", queuingId);
			queueMap.put("isMyQueues", true);
			queueMap.put("isQueuing", false);
			
			list.add(queueMap);
		}
		return list;
	}
	
	public List<Map<String, Object>> getPromotionQueues(String userId, int page, int size) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		List<Promotion> promotions = promotionService.getPromotions(page, size);
		for(Promotion promotion: promotions){
			Map<String, Object> queueMap = new HashMap<String, Object>();
			
			Queue queue = promotion.getQueue();
			
			Favorite favorite = favoriteService.getFavorite(userId, queue.getQueueId());
			List<Queuing> queuings = queuingService.getQueuingsByQueueId(queue.getQueueId(), QueuingStatus.WAITTING, page, size);
			Double avgSeconds = queuingService.getAvgWaittingTime(queue.getQueueId());
			Integer star = starsService.getStar(userId, queue.getQueueId());
			Double avgStar = starsService.getAvgStar(queue.getQueueId());
			
			Integer queuingCount = 0;
			if(queuings != null && queuings.size() > 1){
				queuingCount = queuings.size() - 1;
			}
			
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
			queueMap.put("star", star);
			queueMap.put("avgStar", avgStar);
			queueMap.put("queuingCount", queuingCount);
			queueMap.put("avgWaittingTime", this.getAvgWaittingTimeString(avgSeconds));
			queueMap.put("queueNum", queueNum);
			queueMap.put("queuings", queuings);
			queueMap.put("promotionId", promotion.getPromotionId());
			queueMap.put("favoriteId", favorite != null? favorite.getFavoriteId(): null);
			queueMap.put("queuingId", queuingId);
			queueMap.put("isMyQueues", false);
			queueMap.put("isQueuing", false);
			
			list.add(queueMap);
		}
		return list;
	}
	
	public List<Map<String, Object>> getQueuingQueues(String userId, int page, int size) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		List<Queuing> queuings = queuingService.getQueuingsByUserId(userId, QueuingStatus.WAITTING, page, size);
		for(Queuing queuing: queuings){
			Map<String, Object> queueMap = new HashMap<String, Object>();
			
			Queue queue = queuing.getQueue();
			
			Promotion promotion = promotionService.getPromotion(queue.getQueueId());
			Favorite favorite = favoriteService.getFavorite(userId, queue.getQueueId());
			Double avgSeconds = queuingService.getAvgWaittingTime(queue.getQueueId());
			Integer star = starsService.getStar(userId, queue.getQueueId());
			Double avgStar = starsService.getAvgStar(queue.getQueueId());
			
			Integer queuingCount = 0;
			if(queuings != null && queuings.size() > 1){
				queuingCount = queuings.size() - 1;
			}
			
			queueMap.put("queue", queue);
			queueMap.put("star", star);
			queueMap.put("avgStar", avgStar);
			queueMap.put("queuingCount", queuingCount);
			queueMap.put("avgWaittingTime", this.getAvgWaittingTimeString(avgSeconds));
			queueMap.put("queueNum", queuing.getQueueNum());
			queueMap.put("queuings", queuings);
			queueMap.put("promotionId", promotion != null? promotion.getPromotionId(): null);
			queueMap.put("favoriteId", favorite != null? favorite.getFavoriteId(): null);
			queueMap.put("queuingId", queuing.getQueuingId());
			queueMap.put("isMyQueues", false);
			queueMap.put("isQueuing", true);
			
			list.add(queueMap);
		}
		return list;
	}
	
	public List<Map<String, Object>> getFavoriteQueues(String userId, int page, int size) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		List<Favorite> favorites = favoriteService.getFavorites(userId, page, size);
		for(Favorite favorite: favorites){
			Map<String, Object> queueMap = new HashMap<String, Object>();
			
			Queue queue = favorite.getQueue();
			
			Promotion promotion = promotionService.getPromotion(queue.getQueueId());
			List<Queuing> queuings = queuingService.getQueuingsByQueueId(queue.getQueueId(), QueuingStatus.WAITTING, page, size);
			Double avgSeconds = queuingService.getAvgWaittingTime(queue.getQueueId());
			Integer star = starsService.getStar(userId, queue.getQueueId());
			Double avgStar = starsService.getAvgStar(queue.getQueueId());
			
			Integer queuingCount = 0;
			if(queuings != null && queuings.size() > 1){
				queuingCount = queuings.size() - 1;
			}
			
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
			queueMap.put("star", star);
			queueMap.put("avgStar", avgStar);
			queueMap.put("queuingCount", queuingCount);
			queueMap.put("avgWaittingTime", this.getAvgWaittingTimeString(avgSeconds));
			queueMap.put("queueNum", queueNum);
			queueMap.put("queuings", queuings);
			queueMap.put("promotionId", promotion != null? promotion.getPromotionId(): null);
			queueMap.put("favoriteId", favorite.getFavoriteId());
			queueMap.put("queuingId", queuingId);
			queueMap.put("isMyQueues", false);
			queueMap.put("isQueuing", false);
			
			list.add(queueMap);
		}
		return list;
	}
	
	public void addQueue(Queue queue) throws Exception {
		Date now = Calendar.getInstance().getTime();
		queue.setCreateDate(now);
		queue.setCreateUser(queue.getUser().getUserId());
		queue.setUpdateDate(now);
		queue.setUpdateUser(queue.getUser().getUserId());
		queueRepository.save(queue);
	}
	
	public void removeQueue(String queueId) throws Exception {
		queueRepository.delete(queueId);
	}
	
	public void update(Queue queue) throws Exception {
		Queue oldQueue = queueRepository.findOne(queue.getQueueId());
		oldQueue.setPhone(queue.getPhone());
		oldQueue.setAddress(queue.getAddress());
		oldQueue.setDscr(queue.getDscr());
		queueRepository.save(oldQueue);
	}
	
	private String getAvgWaittingTimeString(Double seconds){
		String avgWaittingTimeString = null;
		if(seconds != null){
			long hours = TimeUnit.SECONDS.toHours(seconds.intValue());
			long minute = TimeUnit.SECONDS.toMinutes(seconds.intValue()) - (TimeUnit.SECONDS.toHours(seconds.intValue()) * 60);
			long second = TimeUnit.SECONDS.toSeconds(seconds.intValue()) - (TimeUnit.SECONDS.toMinutes(seconds.intValue()) * 60);
			avgWaittingTimeString =  hours + " : " + minute + " : " + second;
		}
		return avgWaittingTimeString;
	}
}
