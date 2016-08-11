package com.ezqueue.service;

import java.util.List;
import java.util.Map;

import com.ezqueue.model.User;

public interface UserService {
	
	public User getUser(String userId);
	
	public List<User> getUserList(String userId);
	
	public User check(String id, String name, String email, Map<String, Object> accounts);
	
	public User getUserByFacebookId(String facebookId);
	
	public User getUserByEmail(String email);
	
}
