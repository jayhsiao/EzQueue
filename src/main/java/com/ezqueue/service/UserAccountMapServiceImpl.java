package com.ezqueue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.model.UserAccountMap;
import com.ezqueue.repository.UserAccountMapRepository;

@Service
public class UserAccountMapServiceImpl implements UserAccountMapService {
	
	@Autowired
	private UserAccountMapRepository userAccountMapRepository;
	
	@Override
	public UserAccountMap getUserAccountMap(String userId, String userAccountId) {
		return userAccountMapRepository.findByUserIdAndUserAccountId(userId, userAccountId);
	}

	@Override
	public List<UserAccountMap> getUserAccountMaps(String userId) {
		return userAccountMapRepository.findByUserId(userId);
	}
	
	@Override
	public UserAccountMap addUserAccountMap(UserAccountMap userAccountMap) {
		return userAccountMapRepository.save(userAccountMap);
	}

	@Override
	public void removeUserAccountMap(String userAccountMapId) {
		userAccountMapRepository.delete(userAccountMapId);
	}
	
	@Override
	public void removeByUser(String userId) {
		userAccountMapRepository.deleteByUserId(userId);
	}
	
}
