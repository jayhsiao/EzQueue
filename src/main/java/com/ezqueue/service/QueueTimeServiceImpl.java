package com.ezqueue.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.model.QueueTime;
import com.ezqueue.repository.QueueTimeRepository;

@Service
public class QueueTimeServiceImpl implements QueueTimeService {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private QueueTimeRepository queueTimeRepository;
	
	public void addQueueTime(QueueTime queueTime) throws Exception {
		
	}
}
