package com.ezqueue.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ezqueue.model.UserAccountMap;

public interface UserAccountMapRepository extends CrudRepository<UserAccountMap, String>{
	
	public UserAccountMap findByUserIdAndUserAccountId(String userId, String userAccountId);
	
	public List<UserAccountMap> findByUserId(String userId);
	
	public void deleteByUserId(String userId);
	
}
