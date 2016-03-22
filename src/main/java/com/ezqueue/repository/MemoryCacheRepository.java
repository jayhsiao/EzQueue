package com.ezqueue.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class MemoryCacheRepository {
    private Map<String, String> tokenMap = new HashMap<String, String>();
    
    public String getCache(String key) {
    	return (String) tokenMap.get(key);
    }
    
    public void setCache(String key, String value) {
    	tokenMap.put(key, value);
    }
    
    public void deleteCache(String key) {
    	tokenMap.remove(key);
    }
    
    public void expire(String key, long timeout) {
        
    }
}
