package com.ezqueue.repository;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class I18NCacheRepository {
    private static final Logger LOG = LoggerFactory.getLogger(I18NCacheRepository.class);
    
    public static final Locale DEFAULT_LOCALE = new Locale("en", "US");

    @Autowired
    private MemoryCacheRepository memoryCacheRepository;

    public String getMessage(String key, Locale locale) {
    	return memoryCacheRepository.getCache(key);
    }
}
