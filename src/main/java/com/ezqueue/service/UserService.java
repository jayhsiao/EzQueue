package com.ezqueue.service;

import java.util.List;
import java.util.Map;

import com.ezqueue.model.User;

public interface UserService {
	
	public String check(Map<String, Object> map) throws Exception;
	
	public User getUser(String userId) throws Exception;
	
	public List<User> getUserAccount(String userId) throws Exception;
}
