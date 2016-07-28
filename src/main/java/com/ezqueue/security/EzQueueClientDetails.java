package com.ezqueue.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import com.ezqueue.model.Application;
import com.google.common.collect.Sets;

public class EzQueueClientDetails implements ClientDetails {
	
    private static final long serialVersionUID = 122023601086229140L;
    
    private Application application;
    private int accessTokenExpiration;
    private int refreshTokenExpiration;
    
    public EzQueueClientDetails(Application application, int accessTokenExpiration, int refreshTokenExpiration) {
        this.application = application;
        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshTokenExpiration = refreshTokenExpiration;
    }
    
    @Override
    public String getClientId() {
        return this.application.getApplicationId();
    }

    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return this.application.getSecret();
    }

    @Override
    public boolean isScoped() {
        return false;
    }

    @Override
    public Set<String> getScope() {
        return this.application.getScopeSet();
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return this.application.getGrantTypeSet();
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return Sets.newHashSet(this.application.getRedirectUri());
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenExpiration;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenExpiration;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return true;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}