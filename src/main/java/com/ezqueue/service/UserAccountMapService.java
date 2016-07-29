package com.ezqueue.service;

import java.util.List;

import com.ezqueue.model.UserAccountMap;

public interface UserAccountMapService {
	
	public UserAccountMap getUserAccountMap(String userId, String userAccountId);
	
	public List<UserAccountMap> getUserAccountMaps(String userId);
	
	public UserAccountMap addUserAccountMap(UserAccountMap userAccountMap);
	
	public void removeUserAccountMap(String userAccountMapId);
	
	public void removeByUser(String userId);
	
}
