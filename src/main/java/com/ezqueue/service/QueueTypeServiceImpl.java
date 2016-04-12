package com.ezqueue.service;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.model.QueueType;
import com.ezqueue.repository.QueueTypeRepository;
import com.google.common.collect.Lists;

@Service
public class QueueTypeServiceImpl implements QueueTypeService {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private QueueTypeRepository queueTypeRepository;
	
	public List<QueueType> getQueueType() throws Exception {
		return  Lists.newArrayList(queueTypeRepository.findAll().iterator());
	}
}
