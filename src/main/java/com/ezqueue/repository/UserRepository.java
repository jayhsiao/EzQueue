package com.ezqueue.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ezqueue.model.User;

public interface UserRepository extends CrudRepository<User, String>{
	
	public User findByFbId(String FbId);
	
	public List<User> findByParent(String parent);
	
	public User findByParentAndFbId(String parent, String fbId);
	
	public List<User> findByParentAndFbIdNotIn(String parent, List<String> users);
	
	public User findByEmail(String email);
}
