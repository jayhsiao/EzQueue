package com.ezqueue.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.model.User;
import com.ezqueue.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	public User getUser(Integer userId) throws Exception {
		return userRepository.findOne(userId);
	}
}
