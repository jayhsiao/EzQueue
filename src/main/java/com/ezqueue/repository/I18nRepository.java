package com.ezqueue.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ezqueue.model.Favorite;
import com.ezqueue.model.I18n;

public interface I18nRepository extends PagingAndSortingRepository<Favorite, String>{
	
	@Query("select i.defaultEn from I18n i where i.value = :value")
	public String getByValue(@Param("value") String value);
	
	@Query("select i from I18n i where i.defaultEn = :defaultEn and i.locale = :locale")
	public I18n getI18nByValue(@Param("defaultEn") String defaultEn, @Param("locale") String locale);
	
}
