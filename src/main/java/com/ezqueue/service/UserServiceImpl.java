package com.ezqueue.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.exception.EzQueueException;
import com.ezqueue.model.User;
import com.ezqueue.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	public User addUser(User user) throws Exception {
		return userRepository.save(user);
	}
	
	public User verifyUser(String userId, String password) throws Exception {
		User user = userRepository.findByUserIdAndPassword(userId, password);
		if(user == null){
			throw new EzQueueException("使用者帳號或密碼錯誤");
		}
		return user;
	}
	
	public User getUser(String userId) throws Exception {
		return userRepository.findOne(userId);
	}
}
