package com.ezqueue.service;


import java.util.List;

import com.ezqueue.model.QueueType;

public interface QueueTypeService {
	
	public List<QueueType> getQueueType() throws Exception;
}
