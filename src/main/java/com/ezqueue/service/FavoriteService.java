package com.ezqueue.service;


import java.util.List;

import com.ezqueue.model.Favorite;

public interface FavoriteService {

	public List<Favorite> getFavorites(String userId) throws Exception;
	
	public Favorite getFavorite(String userId, String queueId) throws Exception;
	
	public void addFavorite(Favorite favorite) throws Exception;
	
	public void removeFavorite(String favoriteId) throws Exception;
}
