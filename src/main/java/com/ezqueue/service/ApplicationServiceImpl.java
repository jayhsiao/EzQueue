package com.ezqueue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezqueue.exception.EzQueueException;
import com.ezqueue.model.Application;
import com.ezqueue.repository.ApplicationRepository;
import com.google.common.collect.Lists;

@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {
	
    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    @Transactional(readOnly = true)
    public Application getApplication(String applicationId) {
        return getApplication(applicationId, false);
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public List<Application> getApplications() {
        List<Application> applications = Lists.newArrayList(this.applicationRepository.findAll());

        // Don't return secret field to the clients
        for (Application application : applications) {
            application.setSecret(null);
        }

        return applications;
    }
}
