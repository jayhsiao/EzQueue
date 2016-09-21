package com.ezqueue.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.model.Favorite;
import com.ezqueue.model.Queue;
import com.ezqueue.model.User;
import com.ezqueue.repository.FavoriteRepository;
import com.ezqueue.util.StringUtil;

@Service
public class FavoriteServiceImpl implements FavoriteService {
	
	@Autowired
	private FavoriteRepository favoriteRepository;
	
	@Override
	public Favorite getFavorite(String userId, String queueId) {
		User user = new User();
		user.setUserId(userId);
		
		Queue queue = new Queue();
		queue.setQueueId(queueId);
		
		return favoriteRepository.findByUserAndQueue(user, queue);
	}
	
	@Override
	public List<Favorite> getFavorites(String userId) {
		User user = new User();
		user.setUserId(userId);
		
		return favoriteRepository.findByUser(user);
	}
	
	@Override
	public int getFavoriteCount(Queue queue) {
		return favoriteRepository.countByQueue(queue);
	}
	
	@Override
	public Map<String, Object> addFavorite(String userId, String queueId) {
		Map<String, Object> resultMap = new HashMap<>();
		User user = new User();
		user.setUserId(userId);
		
		Queue queue = new Queue();
		queue.setQueueId(queueId);
		
		Favorite favorite = new Favorite();
		favorite.setFavoriteId(StringUtil.getUUID());
		favorite.setUser(user);
		favorite.setQueue(queue);
		
		favoriteRepository.save(favorite);
		
		resultMap.put("favoriteId", favorite.getFavoriteId());
		resultMap.put("favoriteCount", favoriteRepository.countByQueue(queue));
		
		return resultMap;
	}
	
	@Override
	public long removeFavorite(String favoriteId, String queueId) {
		favoriteRepository.delete(favoriteId);
		Queue queue = new Queue();
		queue.setQueueId(queueId);
		return favoriteRepository.countByQueue(queue);
	}
	
}
