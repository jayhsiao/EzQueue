package com.ezqueue.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.model.User;
import com.ezqueue.repository.UserRepository;
import com.ezqueue.util.StringUtil;

@Service
public class UserServiceImpl implements UserService {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	public User addUser(User user) throws Exception {
		User oldUser = userRepository.findByFbId(user.getFbId());
		if(oldUser == null){
			user.setUserId(StringUtil.getUUID());
			oldUser = userRepository.save(user);
		}
		return oldUser;
	}
	
	public User getUser(String userId) throws Exception {
		return userRepository.findOne(userId);
	}
}
