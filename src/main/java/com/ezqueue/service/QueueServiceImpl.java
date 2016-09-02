package com.ezqueue.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
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
import com.ezqueue.model.UserAccountMap;
import com.ezqueue.repository.QueueRepository;
import com.ezqueue.util.EzQueueConstants;
import com.ezqueue.util.QueueStatus;
import com.ezqueue.util.QueueTypeStatus;
import com.ezqueue.util.QueuingStatus;
import com.ezqueue.util.StringUtil;
import com.google.common.collect.Lists;

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
	
	@Autowired
	private UserAccountMapService userAccountMapService;
	
	@Override
	public List<Map<String, Object>> getTypeQueues(String queueTypeId, int limit, int offset) {
		List<Map<String, Object>> list = new ArrayList<>();
		
		QueueType queueType = new QueueType();
		queueType.setQueueTypeId(queueTypeId);
		
		PageRequest pageRequest = new PageRequest(offset, limit, Direction.DESC, "createDate");
		List<Queue> queues = null;
		if(queueTypeId.equals(QueueTypeStatus.ALL.name())){
			queues = Lists.newArrayList(queueRepository.findAll(pageRequest)).stream().filter(queue -> QueueStatus.OPEN.equals(queue.getStatus())).collect(Collectors.toList());
		}
		else{
			queues = queueRepository.findByQueueType(queueType, pageRequest).stream().filter(queue -> QueueStatus.OPEN.equals(queue.getStatus())).collect(Collectors.toList());
		}
		
		for(Queue queue: queues){
			list.add(this.getQueueMap(queue));
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
		
		List<UserAccountMap> userAccountMaps= userAccountMapService.getUserAccountMaps(userId);
		for(UserAccountMap userAccountMap: userAccountMaps){
			users.add(userService.getUser(userAccountMap.getUserAccountId()));
		}
		
		PageRequest pageRequest = new PageRequest(offset, limit, Direction.DESC, "createDate");
		List<Queue> queues = queueRepository.findByUserIn(users, pageRequest);
		
		for(Queue queue: queues){
			list.add(this.getQueueMap(queue));
		}
		
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getPromotionQueues(int limit, int offset) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		List<Promotion> promotions = promotionService.getPromotions(offset, limit).stream().filter(promotion -> QueueStatus.OPEN.equals(promotion.getQueue().getStatus())).collect(Collectors.toList());
		for(Promotion promotion: promotions){
			list.add(this.getQueueMap(promotion.getQueue()));
		}
		
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getFavoriteQueues(String userId, int limit, int offset) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		List<Favorite> favorites = favoriteService.getFavorites(userId).stream().filter(favorite -> QueueStatus.OPEN.equals(favorite.getQueue().getStatus())).collect(Collectors.toList());
		for(Favorite favorite: favorites){
			list.add(this.getQueueMap(favorite.getQueue()));
		}
		
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getQueuingQueues(String userId, int limit, int offset) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		List<Queuing> queuings = queuingService.getQueuingsByUser(userId, limit, offset).stream().filter(queuing -> QueueStatus.OPEN.equals(queuing.getQueue().getStatus())).collect(Collectors.toList());
		for(Queuing queuing: queuings){
			list.add(this.getQueueMap(queuing.getQueue()));
		}
		
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getSearchQueues(String title, int limit, int offset) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		PageRequest pageRequest = new PageRequest(offset, limit, Direction.DESC, "createDate");
		List<Queue> queues = queueRepository.getQueueByText("%"+title+"%", pageRequest).stream().filter(queue -> QueueStatus.OPEN.equals(queue.getStatus())).collect(Collectors.toList());
		for(Queue queue: queues){
			list.add(this.getQueueMap(queue));
		}
		
		return list;
	}
	
	@Override
	public Map<String, Object> createQueue(String userId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("accounts", userService.getUserList(userId));
		resultMap.put("queueTypes", queueTypeService.getQueueTypes());
		return resultMap;
	}
	
	@Override
	public List<Map<String, Object>> getUserQueues(String userId, int limit, int offset) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		User user = new User();
		user.setUserId(userId);
		
		PageRequest pageRequest = new PageRequest(offset, limit, Direction.DESC, "createDate");
		List<Queue> queues = queueRepository.findByUser(user, pageRequest).stream().filter(queue -> QueueStatus.OPEN.equals(queue.getStatus())).collect(Collectors.toList());
		for(Queue queue: queues){
			list.add(this.getQueueMap(queue));
		}
		
		return list;
	}
	
	@Override
	public Map<String, Object> getQueueDetail(String userId, String queueId) {
		return this.getQueueDetailMap(userId, this.getQueue(queueId));
	}
	
	@Override
	public Queue getQueue(String queueId) {
		return queueRepository.findOne(queueId);
	}
	
	@Override
	public void addQueue(String userId, String queueTypeId, String startDate, String endDate, String title, String phone, String address, String dscr, int queueNum, QueueStatus queueStatus) {
		Queue queue = new Queue();
		queue.setQueueId(StringUtil.getUUID());
		
		User user = new User();
		user.setUserId(userId);
		queue.setUser(user);
		
		queue.setTitle(title);
		queue.setPhone(phone);
		queue.setAddress(address);
		queue.setDscr(dscr);
		
		QueueType queueType = new QueueType();
		queueType.setQueueTypeId(queueTypeId);
		queue.setQueueType(queueType);
		
		queue.setQueueNum(queueNum);
		queue.setStatus(queueStatus);
		queue.setStartDate(startDate);
		
		if(!StringUtils.isEmpty(endDate)){
			queue.setEndDate(endDate + " 23:59:59");
		}
		
		this.addQueue(queue);
	}
	
	@Override
	public void addQueue(Queue queue) {
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
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
	public Queue edit(String queueId, String phone, String address, String dscr) {
		Queue queue = queueRepository.findOne(queueId);
		queue.setPhone(phone);
		queue.setAddress(address);
		queue.setDscr(dscr);
		queueRepository.save(queue);
		return queue;
	}
	
	@Override
	public void updateStatus(String queueId, QueueStatus queueStatus) {
		Queue queue = queueRepository.findOne(queueId);
		if(QueueStatus.OPEN.equals(queueStatus)){
			queue.setStatus(QueueStatus.CLOSE);
		}
		else{
			queue.setStatus(QueueStatus.OPEN);
		}
		queueRepository.save(queue);
	}
	
	private Map<String, Object> getQueueMap(Queue queue){
		Map<String, Object> queueMap = new HashMap<String, Object>();
		String queueId = queue.getQueueId();
		
		Promotion promotion = promotionService.getPromotion(queueId);
		
		List<QueuingStatus> queuingStatuss = new ArrayList<>();
		queuingStatuss.add(QueuingStatus.WAITING);
		queuingStatuss.add(QueuingStatus.PASS);
		int queuingCount = queuingService.getQueuingCount(queue, queuingStatuss);
		
		queueMap.put("queue", queue);
		queueMap.put("user", queue.getUser());
		queueMap.put("promotion", promotion);
		
		queueMap.put("favoriteCount", StringUtil.formatNumber(favoriteService.getFavoriteCount(queue)));
		queueMap.put("queuingCount", StringUtil.formatNumber(queuingCount));
		queueMap.put("starsCount", StringUtil.formatNumber(starsService.getStarsCount(queue)));
		
		queueMap.put("totalStar", EzQueueConstants.TOTAL_STAR);
		queueMap.put("avgStar", starsService.getAvgStar(queueId));
//		queueMap.put("avgWaittingTime", this.getAvgWaittingTimeString(queuingService.getAvgWaittingTime(queueId)));
		
		return queueMap;
	}
	
	private Map<String, Object> getQueueDetailMap(String userId, Queue queue){
		Map<String, Object> queueMap = new HashMap<String, Object>();
		boolean canEdit = false;
		String queueId = queue.getQueueId();
		
		if(!StringUtils.isEmpty(userId)){
			Favorite favorite = favoriteService.getFavorite(userId, queueId);
			Queuing queuing = queuingService.getQueuing(userId, queueId);
			Star star = starsService.getStar(userId, queueId);
			
			List<User> accounts = userService.getUserList(userId);
			if(!accounts.stream().filter(user -> user.getUserId().equals(queue.getUser().getUserId())).collect(Collectors.toList()).isEmpty()){
				canEdit = true;
				queueMap.put("queuings_WAITING", queuingService.getQueuingsByQueue(queueId, QueuingStatus.WAITING,EzQueueConstants.INIT_QUEUING_LIMIT, EzQueueConstants.INIT_QUEUING_OFFSET));
				queueMap.put("queuings_PASS", queuingService.getQueuingsByQueue(queueId, QueuingStatus.PASS, EzQueueConstants.INIT_QUEUING_LIMIT, EzQueueConstants.INIT_QUEUING_OFFSET));
			}
			
			queueMap.put("favorite", favorite);
			queueMap.put("queuing", queuing);
			queueMap.put("star", star);
			
			queueMap.put("isOpen", QueueStatus.OPEN.name().equals(queue.getStatus().name()));
		}
		
		List<QueuingStatus> queuingStatuss = new ArrayList<>();
		queuingStatuss.add(QueuingStatus.WAITING);
		int waitingCount = queuingService.getQueuingCount(queue, queuingStatuss);
		
		queuingStatuss.clear();
		queuingStatuss.add(QueuingStatus.PASS);
		int passCount = queuingService.getQueuingCount(queue, queuingStatuss);
		
		queuingStatuss.add(QueuingStatus.WAITING);
		int queuingCount = queuingService.getQueuingCount(queue, queuingStatuss);
		
		queueMap.put("queue", queue);
		queueMap.put("user", queue.getUser());
		
		queueMap.put("waitingCount", StringUtil.formatNumber(waitingCount));
		queueMap.put("passCount", StringUtil.formatNumber(passCount));
		queueMap.put("queuingCount", StringUtil.formatNumber(queuingCount));
		queueMap.put("favoriteCount", StringUtil.formatNumber(favoriteService.getFavoriteCount(queue)));
		queueMap.put("starsCount", StringUtil.formatNumber(starsService.getStarsCount(queue)));
		
		queueMap.put("totalStar", EzQueueConstants.TOTAL_STAR);
		queueMap.put("avgStar", starsService.getAvgStar(queueId));
//		queueMap.put("avgWaittingTime", this.getAvgWaittingTimeString(queuingService.getAvgWaittingTime(queueId)));
		
		queueMap.put("canEdit", canEdit);
		
		return queueMap;
	}
	
//	private String getAvgWaittingTimeString(Double avgSeconds){
//		String avgWaittingTimeString = null;
//		if(avgSeconds != null){
//			StringBuffer avgWaittingTimeSb = new StringBuffer();
//			long hours = TimeUnit.SECONDS.toHours(avgSeconds.intValue());
//			long minutes = TimeUnit.SECONDS.toMinutes(avgSeconds.intValue()) - (TimeUnit.SECONDS.toHours(avgSeconds.intValue()) * 60);
//			long seconds = TimeUnit.SECONDS.toSeconds(avgSeconds.intValue()) - (TimeUnit.SECONDS.toMinutes(avgSeconds.intValue()) * 60);
//			
//			if(hours > 0) avgWaittingTimeSb.append(hours).append("  ");
//			if(minutes > 0) avgWaittingTimeSb.append(minutes).append("  ");
//			if(seconds > 0) avgWaittingTimeSb.append(seconds).append(" ");
//			avgWaittingTimeString = avgWaittingTimeSb.toString();
//		}
//		return avgWaittingTimeString;
//	}
	
}
