package com.ezqueue.service;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.model.Store;
import com.ezqueue.repository.StoreRepository;
import com.google.common.collect.Lists;

@Service
public class StoreServiceImpl implements StoreService {
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Override
	public Store getStore(String storeId, Locale locale) {
		return storeRepository.findOne(storeId);
	}
	
	@Override
	public List<Store> getStores(Locale locale) {
		return Lists.newArrayList(storeRepository.findAll().iterator());
	}
	
	@Override
	public void addStore(Store store) {
		storeRepository.save(store);
	}
	
	@Override
	public void removeStore(String storeId) {
		storeRepository.delete(storeId);
	}
	
}
