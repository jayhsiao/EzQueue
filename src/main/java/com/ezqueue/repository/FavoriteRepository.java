package com.ezqueue.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ezqueue.model.Favorite;

public interface FavoriteRepository extends CrudRepository<Favorite, String>{
	
	public List<Favorite> findByUser(String userId);
}
