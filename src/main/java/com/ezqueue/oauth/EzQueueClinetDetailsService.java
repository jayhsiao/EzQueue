package com.ezqueue.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;

import com.ezqueue.exception.EzQueueException;
import com.ezqueue.model.Application;
import com.ezqueue.service.ApplicationService;

@Service
public class EzQueueClinetDetailsService implements ClientDetailsService {
	
    @Autowired
    private ApplicationService applicationService;
    
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
    	 Application application = null;
         
         try {
        	 application = applicationService.getApplication(clientId);
         } catch(EzQueueException ex) {
        	 throw new NoSuchClientException("No client with requested id: " + clientId);
         }
         
         return new EzQueueClientDetails(application);
    }
}