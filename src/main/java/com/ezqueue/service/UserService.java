package com.ezqueue.service;

import com.ezqueue.model.User;

public interface UserService {
	
	public User addUser(User user) throws Exception;
	
	public User getUser(String userId) throws Exception;
}
