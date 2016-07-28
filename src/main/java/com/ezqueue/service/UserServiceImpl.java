package com.ezqueue.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.model.User;
import com.ezqueue.repository.UserRepository;
import com.ezqueue.util.StringUtil;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public String check(Map<String, Object> map) {
		
		String fbId = (String) map.get("fbId");
		String name = (String) map.get("name");
		String email = (String) map.get("email");
		List<Map<String, Object>> accounts = (List<Map<String, Object>>) map.get("accounts");
		
		String userId = null;
		User oldUser = userRepository.findByFbId(fbId);
		if(oldUser == null){
			User newUser = new User();
			newUser.setUserId(StringUtil.getUUID());
			newUser.setFbId(fbId);
			newUser.setName(name);
			newUser.setEmail(email);
			userRepository.save(newUser);
			
			userId = newUser.getUserId();
			
			if(accounts != null && !accounts.isEmpty()){
				for(Map<String, Object> accountMap: accounts){
					User newAccountUser = new User();
					newAccountUser.setUserId(StringUtil.getUUID());
					newAccountUser.setFbId((String) accountMap.get("id"));
					newAccountUser.setName((String) accountMap.get("name"));
					newAccountUser.setParent(userId);
					userRepository.save(newAccountUser);
				}
			}
		}
		else{
			userId = oldUser.getUserId();
			
			if(accounts != null && !accounts.isEmpty()){
				List<String> ids = new ArrayList<String>();
				for(Map<String, Object> accountMap: accounts){
					String accountId = (String) accountMap.get("id");
					String accountName = (String) accountMap.get("name");
					
					User accountUser = userRepository.findByParentAndFbId(userId, accountId);
					if(accountUser == null){
						User newAccountUser = new User();
						newAccountUser.setUserId(StringUtil.getUUID());
						newAccountUser.setFbId(accountId);
						newAccountUser.setName(accountName);
						newAccountUser.setParent(userId);
						userRepository.save(newAccountUser);
					}
					
					ids.add(accountId);
				}
				
				List<User> accountUsers = userRepository.findByParentAndFbIdNotIn(userId, ids);
				userRepository.delete(accountUsers);
			}
		}
		
		return userId;
	}
	
	@Override
	public User getUser(String userId) {
		return userRepository.findOne(userId);
	}
	
	@Override
	public User getUserByFbId(String userId) {
		return userRepository.findByFbId(userId);
	}
	
	@Override
	public List<User> getUserAccount(String userId) {
		return userRepository.findByParent(userId);
	}
	
	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
