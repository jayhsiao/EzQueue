package com.ezqueue.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.model.Favorite;
import com.ezqueue.repository.FavoriteRepository;

@Service
public class FavoriteServiceImpl implements FavoriteService {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private FavoriteRepository favoriteRepository;
	
	public List<Favorite> getFavorite(Integer userId) throws Exception {
		return favoriteRepository.findByUser(userId);
	}
	
	public void addFavorite(Favorite favorite) throws Exception {
		favoriteRepository.save(favorite);
	}
	
	public void removeFavorite(Integer favoriteId) throws Exception {
		favoriteRepository.delete(favoriteId);
	}
}
