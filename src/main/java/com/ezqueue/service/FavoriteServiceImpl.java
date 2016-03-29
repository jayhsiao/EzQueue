package com.ezqueue.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.model.Favorite;
import com.ezqueue.model.Queue;
import com.ezqueue.model.User;
import com.ezqueue.repository.FavoriteRepository;

@Service
public class FavoriteServiceImpl implements FavoriteService {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private FavoriteRepository favoriteRepository;
	
	public List<Favorite> getFavorites(String userId) throws Exception {
		User user = new User();
		user.setUserId(userId);
		
		return favoriteRepository.findByUser(user);
	}
	
	public Favorite getFavorite(String userId, String queueId) throws Exception {
		User user = new User();
		user.setUserId(userId);
		
		Queue queue = new Queue();
		queue.setQueueId(queueId);
		
		return favoriteRepository.findByUserAndQueue(user, queue);
	}
	
	public void addFavorite(Favorite favorite) throws Exception {
		favoriteRepository.save(favorite);
	}
	
	public void removeFavorite(String favoriteId) throws Exception {
		favoriteRepository.delete(favoriteId);
	}
}
