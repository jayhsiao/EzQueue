package com.ezqueue.repository;

import org.springframework.data.repository.CrudRepository;

import com.ezqueue.model.User;

public interface UserRepository extends CrudRepository<User, String>{
	
	public User findByFacebookId(String facebookId);
	
	public User findByEmail(String email);
	
}
