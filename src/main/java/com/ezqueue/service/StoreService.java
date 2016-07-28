package com.ezqueue.service;

import java.util.List;
import java.util.Locale;

import com.ezqueue.model.Store;

public interface StoreService {
	
	public Store getStore(String storeId, Locale locale);
	
	public List<Store> getStores(Locale locale);
	
	public void addStore(Store store);
	
	public void removeStore(String storeId);
	
}
