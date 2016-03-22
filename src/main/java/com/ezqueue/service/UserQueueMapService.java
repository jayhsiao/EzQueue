package com.ezqueue.service;

import java.util.List;

import com.ezqueue.model.UserQueueMap;

public interface UserQueueMapService {
	
	public List<UserQueueMap> getQueuing(Integer userId) throws Exception;
}
