package com.ezqueue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.ezqueue.model.QueueType;
import com.ezqueue.repository.QueueTypeRepository;
import com.ezqueue.util.CacheNamesConstants;
import com.google.common.collect.Lists;

@Service
public class QueueTypeServiceImpl implements QueueTypeService {
	
	@Autowired
	private QueueTypeRepository queueTypeRepository;
	
	@Override
	@Cacheable(CacheNamesConstants.QUEUE_TYPES)
	public List<QueueType> getQueueTypes() {
		Sort sort = new Sort(new Order(Direction.ASC, "displayOrder"));
		return  Lists.newArrayList(queueTypeRepository.findAll(sort).iterator());
	}
}
