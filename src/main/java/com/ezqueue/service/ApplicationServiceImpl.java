package com.ezqueue.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezqueue.exception.EzQueueException;
import com.ezqueue.model.Application;
import com.ezqueue.repository.ApplicationRepository;
import com.ezqueue.util.StringUtil;
import com.google.common.collect.Lists;

@Service
public class ApplicationServiceImpl implements ApplicationService {
	
    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public Application getApplication(String applicationId) {
        return getApplication(applicationId, false);
    }

    @Override
    public Application getApplication(String applicationId, boolean includeSecret) {
        Application application = applicationRepository.findOne(applicationId);

        if (application == null) {
            throw new EzQueueException("APPLICATION_NOT_FOUND");
        }

        if (!includeSecret) {
            application.setSecret(null);
        }

        return application;
    }

    @Override
    public List<Application> getApplications() {
        List<Application> applications = Lists.newArrayList(applicationRepository.findAll());

        // Don't return secret field to the clients
        for (Application application : applications) {
            application.setSecret(null);
        }

        return applications;
    }
    
    @Override
    public void addApplication() {
    	List<Application> applications = new ArrayList<Application>();
    	Calendar calendar = Calendar.getInstance();
    	Date nowdate = calendar.getTime();
    	
    	Application application = new Application();
    	String key = StringUtil.getUUID();
    	application.setApplicationId(key);
    	application.setSecret(StringUtil.getEncrypPassword(key));
    	application.setGrantTypes("client_credentials");
    	application.setDescription("api");
    	application.setCreateDate(nowdate);
    	application.setCreateUser("JJ");
    	application.setUpdateDate(nowdate);
    	application.setUpdateUser("JJ");
    	applications.add(application);
    	
    	Application application1 = new Application();
    	String key1 = StringUtil.getUUID();
    	application1.setApplicationId(key1);
    	application1.setSecret(StringUtil.getEncrypPassword(key1));
    	application1.setGrantTypes("password,client_credentials,refresh_token,social_facebook");
    	application1.setDescription("portal");
    	application1.setCreateDate(nowdate);
    	application1.setCreateUser("JJ");
    	application1.setUpdateDate(nowdate);
    	application1.setUpdateUser("JJ");
    	applications.add(application1);
    	
    	Application application2 = new Application();
    	String key2 = StringUtil.getUUID();
    	application2.setApplicationId(key2);
    	application2.setSecret(StringUtil.getEncrypPassword(key2));
    	application2.setGrantTypes("password,client_credentials,refresh_token,social_facebook");
    	application2.setDescription("android");
    	application2.setCreateDate(nowdate);
    	application2.setCreateUser("JJ");
    	application2.setUpdateDate(nowdate);
    	application2.setUpdateUser("JJ");
    	applications.add(application2);
    	
    	Application application3 = new Application();
    	String key3 = StringUtil.getUUID();
    	application3.setApplicationId(key3);
    	application3.setSecret(StringUtil.getEncrypPassword(key3));
    	application3.setGrantTypes("password,client_credentials,refresh_token,social_facebook");
    	application3.setDescription("ios");
    	application3.setCreateDate(nowdate);
    	application3.setCreateUser("JJ");
    	application3.setUpdateDate(nowdate);
    	application3.setUpdateUser("JJ");
    	applications.add(application3);
    	
    	applicationRepository.save(applications);
    }
    
}
