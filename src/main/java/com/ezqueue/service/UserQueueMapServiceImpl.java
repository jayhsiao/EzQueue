package com.ezqueue.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.model.User;
import com.ezqueue.model.UserQueueMap;
import com.ezqueue.repository.UserQueueRepository;

@Service
public class UserQueueMapServiceImpl implements UserQueueMapService {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserQueueRepository userQueueRepository;
	
	public List<UserQueueMap> getQueuing(Integer userId) throws Exception {
		User user = new User();
		user.setUserId(userId);
		return userQueueRepository.findByUser(user);
	}
}
