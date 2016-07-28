package com.ezqueue.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ezqueue.model.Favorite;
import com.ezqueue.model.Promotion;
import com.ezqueue.model.Queue;
import com.ezqueue.model.QueueType;
import com.ezqueue.model.Queuing;
import com.ezqueue.model.Star;
import com.ezqueue.model.User;
import com.ezqueue.repository.QueueRepository;
import com.ezqueue.util.EzQueueConstants;

@Service
public class QueueServiceImpl implements QueueService {
	
	@Autowired
	private QueueRepository queueRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PromotionService promotionService;
	
	@Autowired
	private FavoriteService favoriteService;
	
	@Autowired
	private QueuingService queuingService;
	
	@Autowired
	private StarService starsService;
	
	@Autowired
	private QueueTypeService queueTypeService;
	
	@Override
	public Map<String, Object> createQueue(String userId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<User> accounts = new ArrayList<User>();
		accounts.add(userService.getUser(userId));
		accounts.addAll(userService.getUserAccount(userId));
		resultMap.put("accounts", accounts);
		resultMap.put("queueTypes", queueTypeService.getQueueTypes());
		return resultMap;
	}
	
	@Override
	public List<Map<String, Object>> getAllQueues(String userId, String queueTypeId, int limit, int offset) {
		List<Map<String, Object>> list = new ArrayList<>();
		
		QueueType queueType = new QueueType();
		queueType.setQueueTypeId(queueTypeId);
		
		PageRequest pageRequest = new PageRequest(offset, limit, Direction.DESC, "createDate");
		List<Queue> queues = queueRepository.findByQueueType(queueType, pageRequest);
		
		for(Queue queue: queues){
			list.add(this.getQueueMap(userId, queue, false));
		}
		
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getMyQueues(String userId, int limit, int offset) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		User user = new User();
		user.setUserId(userId);
		
		List<User> users = new ArrayList<User>();
		users.add(userService.getUser(userId));
		users.addAll(userService.getUserAccount(userId));
		
		PageRequest pageRequest = new PageRequest(offset, limit, Direction.DESC, "createDate");
		List<Queue> queues = queueRepository.findByUserIn(users, pageRequest);
		
		for(Queue queue: queues){
			list.add(this.getQueueMap(userId, queue, true));
		}
		
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getPromotionQueues(String userId, int limit, int offset) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		List<Promotion> promotions = promotionService.getPromotions(offset, limit);
		
		for(Promotion promotion: promotions){
			Queue queue = promotion.getQueue();
			list.add(this.getQueueMap(userId, queue, false));
		}
		
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getQueuingQueues(String userId, int limit, int offset) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		List<Queuing> queuings = queuingService.getQueuingsByUser(userId, limit, offset);
		
		for(Queuing queuing: queuings){
			Queue queue = queuing.getQueue();
			list.add(this.getQueueMap(userId, queue, false));
		}
		
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getFavoriteQueues(String userId, int limit, int offset) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		List<Favorite> favorites = favoriteService.getFavorites(userId);
		
		for(Favorite favorite: favorites){
			Queue queue = favorite.getQueue();
			list.add(this.getQueueMap(userId, queue, false));
		}
		
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getUserQueues(String userId, int limit, int offset) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		User user = new User();
		user.setUserId(userId);
		
		List<User> users = new ArrayList<User>();
		users.add(user);
		
		PageRequest pageRequest = new PageRequest(offset, limit, Direction.DESC, "createDate");
		List<Queue> queues = queueRepository.findByUserIn(users, pageRequest);
		
		for(Queue queue: queues){
			list.add(this.getQueueMap(userId, queue, false));
		}
		
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getSearchQueues(String userId, String title, int limit, int offset) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		PageRequest pageRequest = new PageRequest(offset, limit, Direction.DESC, "createDate");
		List<Queue> queues = queueRepository.getQueueByText("%"+title+"%", pageRequest);
		
		for(Queue queue: queues){
			list.add(this.getQueueMap(userId, queue, false));
		}
		
		return list;
	}
	
	@Override
	public Map<String, Object> getQueueDetail(String userId, String queueId, boolean canEdit) {
		Queue queue = this.getQueue(queueId);
		return this.getQueueMap(userId, queue, canEdit);
	}
	
	@Override
	public Queue getQueue(String queueId) {
		return queueRepository.findOne(queueId);
	}
	
	@Override
	public void addQueue(Queue queue) {
		Date now = Calendar.getInstance().getTime();
		queue.setCreateDate(now);
		queue.setCreateUser(queue.getUser().getUserId());
		queue.setUpdateDate(now);
		queue.setUpdateUser(queue.getUser().getUserId());
		queueRepository.save(queue);
	}
	
	@Override
	public void removeQueue(String queueId) {
		queueRepository.delete(queueId);
	}
	
	@Override
	public void update(Queue queue) {
		Queue oldQueue = queueRepository.findOne(queue.getQueueId());
		oldQueue.setPhone(queue.getPhone());
		oldQueue.setAddress(queue.getAddress());
		oldQueue.setDscr(queue.getDscr());
		queueRepository.save(oldQueue);
	}
	
	private String getAvgWaittingTimeString(Double avgSeconds){
		String avgWaittingTimeString = null;
		if(avgSeconds != null){
			StringBuffer avgWaittingTimeSb = new StringBuffer();
			long hours = TimeUnit.SECONDS.toHours(avgSeconds.intValue());
			long minutes = TimeUnit.SECONDS.toMinutes(avgSeconds.intValue()) - (TimeUnit.SECONDS.toHours(avgSeconds.intValue()) * 60);
			long seconds = TimeUnit.SECONDS.toSeconds(avgSeconds.intValue()) - (TimeUnit.SECONDS.toMinutes(avgSeconds.intValue()) * 60);
			
			if(hours > 0) avgWaittingTimeSb.append(hours).append(" 時 ");
			if(minutes > 0) avgWaittingTimeSb.append(minutes).append(" 分 ");
			if(seconds > 0) avgWaittingTimeSb.append(seconds).append(" 秒");
			avgWaittingTimeString = avgWaittingTimeSb.toString();
		}
		return avgWaittingTimeString;
	}
	
	private Map<String, Object> getQueueMap(String userId, Queue queue, boolean canEdit){
		Map<String, Object> queueMap = new HashMap<String, Object>();
		
		Promotion promotion = promotionService.getPromotion(queue.getQueueId());
		Favorite favorite = favoriteService.getFavorite(userId, queue.getQueueId());
		Queuing queuing = queuingService.getQueuing(userId, queue.getQueueId());
		Star star = starsService.getStar(userId, queue.getQueueId());
		
		Double avgSeconds = queuingService.getAvgWaittingTime(queue.getQueueId());
		Double avgStar = starsService.getAvgStar(queue.getQueueId());
		avgSeconds = 390.0;
		
		queueMap.put("queue", queue);
		queueMap.put("user", queue.getUser());
		queueMap.put("promotion", promotion);
		queueMap.put("favorite", favorite);
		queueMap.put("queuing", queuing);
		queueMap.put("star", star);
		
		queueMap.put("favoriteCount", favoriteService.getFavoriteCount(queue));
		queueMap.put("queuingCount", queuingService.getQueuingCount(queue));
		queueMap.put("starsCount", starsService.getStarsCount(queue));
		
		
		queueMap.put("totalStar", EzQueueConstants.TOTAL_STAR);
		queueMap.put("avgStar", avgStar);
		queueMap.put("avgWaittingTime", this.getAvgWaittingTimeString(avgSeconds));
		
		queueMap.put("canEdit", canEdit);
		
		return queueMap;
	}
	
}
