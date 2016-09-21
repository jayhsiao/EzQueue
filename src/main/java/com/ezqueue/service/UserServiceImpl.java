package com.ezqueue.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.exception.EzQueueException;
import com.ezqueue.model.User;
import com.ezqueue.model.UserAccountMap;
import com.ezqueue.repository.UserRepository;
import com.ezqueue.util.StringUtil;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserAccountMapService userAccountMapService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@SuppressWarnings("unchecked")
	public User registration(String id, String name, String email, Map<String, Object> accounts) {
		
		User user = userRepository.findByFacebookId(id);
		if(user == null){
			user = new User();
			user.setUserId(StringUtil.getUUID());
			user.setFacebookId(id);
			user.setEmail(email);
			user.setName(name);
			userRepository.save(user);
		}
		else{
			user.setEmail(email);
			user.setName(name);
			userRepository.save(user);
		}
		
		if(accounts != null && !accounts.isEmpty()){
			
			userAccountMapService.removeByUser(user.getUserId());
			
			List<Map<String, String>> datas = (List<Map<String, String>>) accounts.get("data");
			for(Map<String, String> data: datas){
				String accountfacebookId = (String) data.get("id");
				String accountfacebookName = (String) data.get("name");
				String isVerified = String.valueOf(data.get("is_verified"));
				
				User userAccount = userRepository.findByFacebookId(accountfacebookId);
				if(userAccount == null){
					userAccount = new User();
					userAccount.setUserId(StringUtil.getUUID());
					userAccount.setFacebookId(accountfacebookId);
					userAccount.setName(accountfacebookName);
					userAccount.setIsVerified(isVerified);
				}
				else{
					userAccount.setName(accountfacebookName);
					userAccount.setIsVerified(isVerified);
				}
				userRepository.save(userAccount);
				
				UserAccountMap userAccountMap = new UserAccountMap();
				userAccountMap.setUserAccountMapId(StringUtil.getUUID());
				userAccountMap.setUserId(user.getUserId());
				userAccountMap.setUserAccountId(userAccount.getUserId());
				userAccountMapService.addUserAccountMap(userAccountMap);
			}
		}
		
		return user;
	}
	
	@Override
	public User getUser(String userId) {
		return userRepository.findOne(userId);
	}
	
	@Override
	public List<User> getUserList(String userId) {
		List<User> accounts = new ArrayList<>();
		accounts.add(this.getUser(userId));
		
		List<UserAccountMap> userAccountMaps = userAccountMapService.getUserAccountMaps(userId);
		for(UserAccountMap userAccountMap: userAccountMaps){
			accounts.add(this.getUser(userAccountMap.getUserAccountId()));
		}
		return accounts;
	}
	
	@Override
	public User getUserByFacebookId(String facebookId) {
		User user = userRepository.findByFacebookId(facebookId);
		if(user == null){
			throw new EzQueueException(String.format("User %s does not exist!", facebookId));
		}
		return user;
	}
	
	@Override
	public User getUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if(user == null){
			throw new EzQueueException(String.format("User %s does not exist!", email));
		}
		return user;
	}
}
