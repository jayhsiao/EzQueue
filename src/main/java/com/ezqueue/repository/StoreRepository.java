package com.ezqueue.repository;


import org.springframework.data.repository.CrudRepository;

import com.ezqueue.model.Store;

public interface StoreRepository extends CrudRepository<Store, String>{
	
}
