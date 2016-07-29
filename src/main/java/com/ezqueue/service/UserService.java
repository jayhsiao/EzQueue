package com.ezqueue.service;

import java.util.Map;

import com.ezqueue.model.User;

public interface UserService {
	
	public User getUser(String userId);
	
	public User check(String id, String name, String email, Map<String, Object> accounts);
	
	public User getUserByFacebookId(String facebookId);
	
	public User getUserByEmail(String email);
	
}
