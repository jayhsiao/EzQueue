package com.ezqueue.repository;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ezqueue.model.Favorite;
import com.ezqueue.model.Queue;
import com.ezqueue.model.User;

public interface FavoriteRepository extends PagingAndSortingRepository<Favorite, String>{
	
	public Favorite findByUserAndQueue(User user, Queue queue);
	
	public List<Favorite> findByUser(User user);
	
	public int countByQueue(Queue queue);
	
}
