package com.ezqueue.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ezqueue.model.User;

public interface UserRepository extends CrudRepository<User, String>{
	
	public User findById(String id);
	
	public List<User> findByParent(String parent);
	
	public User findByParentAndId(String parent, String id);
	
	public List<User> findByParentAndIdNotIn(String parent, List<String> users);
	
	public User findByEmail(String email);
}
