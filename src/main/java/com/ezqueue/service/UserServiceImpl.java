package com.ezqueue.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	
	public String check(Map<String, Object> map) {
		
		String id = (String) map.get("id");
		String name = (String) map.get("name");
		String email = (String) map.get("email");
		List<Map<String, Object>> accounts = (List<Map<String, Object>>) map.get("accounts");
		
		String userId = null;
		User oldUser = userRepository.findById(id);
		if(oldUser == null){
			User newUser = new User();
			newUser.setUserId(StringUtil.getUUID());
			newUser.setId(id);
			newUser.setName(name);
			newUser.setEmail(email);
			userRepository.save(newUser);
			
			userId = newUser.getUserId();
			
			if(accounts != null && !accounts.isEmpty()){
				for(Map<String, Object> accountMap: accounts){
					User newAccountUser = new User();
					newAccountUser.setUserId(StringUtil.getUUID());
					newAccountUser.setId((String) accountMap.get("id"));
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
					
					User accountUser = userRepository.findByParentAndId(userId, accountId);
					if(accountUser == null){
						User newAccountUser = new User();
						newAccountUser.setUserId(StringUtil.getUUID());
						newAccountUser.setId(accountId);
						newAccountUser.setName(accountName);
						newAccountUser.setParent(userId);
						userRepository.save(newAccountUser);
					}
					
					ids.add(accountId);
				}
				
				List<User> accountUsers = userRepository.findByParentAndIdNotIn(userId, ids);
				userRepository.delete(accountUsers);
			}
		}
		
		return userId;
	}
	
	public User getUser(String userId) {
		return userRepository.findOne(userId);
	}
	
	public List<User> getUserAccount(String userId) {
		return userRepository.findByParent(userId);
	}
	
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
