package com.ezqueue.security;

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
public class EzQueueClientDetailsService implements ClientDetailsService {
	
    @Autowired
    private ApplicationService applicationService;
    
    private int accessTokenExpiration = 86400;
    private int refreshTokenExpiration = 86400;
    
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Application application = null;
        
        try {
            application = this.applicationService.getApplication(clientId, true);
        } catch(EzQueueException ex) {
            throw new NoSuchClientException("No client with requested id: " + clientId);
        }
        
        return new EzQueueClientDetails(application, accessTokenExpiration, refreshTokenExpiration);
    }
}
