package com.ezqueue.service;

import java.util.List;

import com.ezqueue.model.Application;

public interface ApplicationService {
	
	public Application getApplication(String applicationId);
	
    public Application getApplication(String applicationId, boolean includeSecret);
    
    public List<Application> getApplications();
    
    public void addApplication();
}
