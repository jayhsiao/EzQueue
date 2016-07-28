package com.ezqueue.service;

import java.util.List;

import com.ezqueue.model.Favorite;
import com.ezqueue.model.Queue;

public interface FavoriteService {
	
	public Favorite getFavorite(String userId, String queueId);
	
	public List<Favorite> getFavorites(String userId);
	
	public int getFavoriteCount(Queue queue);

	public void addFavorite(Favorite favorite);
	
	public void removeFavorite(String favoriteId);
	
}
