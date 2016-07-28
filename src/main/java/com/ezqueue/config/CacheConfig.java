package com.ezqueue.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ezqueue.util.CacheNamesConstants;

@Configuration
@EnableCaching
public class CacheConfig {
	
    @Bean
    public ConcurrentMapCacheManager concurrentMapCacheManager() {
        return new ConcurrentMapCacheManager(
        		CacheNamesConstants.QUEUE_TYPES, 
        		CacheNamesConstants.USERS);
    }
    
}
