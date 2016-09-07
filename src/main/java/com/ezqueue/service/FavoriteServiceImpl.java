package com.ezqueue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.model.Favorite;
import com.ezqueue.model.Queue;
import com.ezqueue.model.User;
import com.ezqueue.repository.FavoriteRepository;

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
	public void addFavorite(Favorite favorite) {
		favoriteRepository.save(favorite);
	}
	
	@Override
	public void removeFavorite(String favoriteId) {
		favoriteRepository.delete(favoriteId);
	}
	
}
