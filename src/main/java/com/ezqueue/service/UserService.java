package com.ezqueue.service;

import java.util.List;
import java.util.Map;

import com.ezqueue.model.User;

public interface UserService {
	
	public User getUser(String userId);
	
	public String check(Map<String, Object> map);
	
	public User getUserByFbId(String fbId);
	
	public List<User> getUserAccount(String userId);
	
	public User getUserByEmail(String email);
}
