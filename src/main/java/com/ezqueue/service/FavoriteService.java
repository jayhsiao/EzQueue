package com.ezqueue.service;

import java.util.List;

import com.ezqueue.model.Favorite;

public interface FavoriteService {

	public List<Favorite> getFavorite(Integer userId) throws Exception;
	
	public void addFavorite(Favorite favorite) throws Exception;
	
	public void removeFavorite(Integer favoriteId) throws Exception;
}
