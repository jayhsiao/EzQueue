package com.ezqueue.service;

import java.util.List;
import java.util.Map;

import com.ezqueue.model.Favorite;
import com.ezqueue.model.Queue;

public interface FavoriteService {
	
	public Favorite getFavorite(String userId, String queueId);
	
	public List<Favorite> getFavorites(String userId);
	
	public int getFavoriteCount(Queue queue);

	public Map<String, Object> addFavorite(String userId, String queueId);
	
	public long removeFavorite(String favoriteId, String queueId);
	
}
