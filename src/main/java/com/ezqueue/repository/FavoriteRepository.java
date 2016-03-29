package com.ezqueue.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ezqueue.model.Favorite;
import com.ezqueue.model.Queue;
import com.ezqueue.model.User;

public interface FavoriteRepository extends CrudRepository<Favorite, String>{
	
	public List<Favorite> findByUser(User user);
	
	public Favorite findByUserAndQueue(User user, Queue queue);
}
