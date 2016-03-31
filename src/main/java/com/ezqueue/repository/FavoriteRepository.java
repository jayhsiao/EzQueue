package com.ezqueue.repository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ezqueue.model.Favorite;
import com.ezqueue.model.Queue;
import com.ezqueue.model.User;

public interface FavoriteRepository extends PagingAndSortingRepository<Favorite, String>{
	
	public List<Favorite> findByUser(User user, Pageable pageable);
	
	public Favorite findByUserAndQueue(User user, Queue queue);
}
