package com.ezqueue.repository;

import org.springframework.data.repository.CrudRepository;

import com.ezqueue.model.Application;

public interface ApplicationRepository extends CrudRepository<Application, String> {
	
}
