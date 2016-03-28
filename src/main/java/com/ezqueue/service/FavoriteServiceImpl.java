package com.ezqueue.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.model.Favorite;
import com.ezqueue.model.Queue;
import com.ezqueue.repository.FavoriteRepository;

@Service
public class FavoriteServiceImpl implements FavoriteService {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private FavoriteRepository favoriteRepository;
	
	public List<Queue> getFavorite(String userId) throws Exception {
		List<Queue> queues = new ArrayList<Queue>();
		
		List<Favorite> favorites = favoriteRepository.findByUser(userId);
		for(Favorite favorite: favorites){
			favorite.getQueue().setFavoriteId(favorite.getFavoriteId());
			queues.add(favorite.getQueue());
		}
		return queues;
	}
	
	public void addFavorite(Favorite favorite) throws Exception {
		favoriteRepository.save(favorite);
	}
	
	public void removeFavorite(String favoriteId) throws Exception {
		favoriteRepository.delete(favoriteId);
	}
}
