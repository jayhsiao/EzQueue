package com.ezqueue.service;

import java.util.List;

import com.ezqueue.model.Favorite;
import com.ezqueue.model.Queue;

public interface FavoriteService {

	public List<Queue> getFavorite(String userId) throws Exception;
	
	public void addFavorite(Favorite favorite) throws Exception;
	
	public void removeFavorite(String favoriteId) throws Exception;
}
