package com.ezqueue.model;

import java.util.Collections;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Sets;

@Entity
@Table(name = "application")
public class Application extends ModelBase {
	
	private static final long serialVersionUID = 9102699426415067190L;

	@Id
    @Column(name = "application_id", length = 11)
    private String applicationId;
    
    @Column(name = "secret", length = 11)
    private String secret;
    
    @Column(name = "scopes")
    private String scopes;
    
    @Column(name = "grant_types")
    private String grantTypes;
    
    @Column(name = "redirect_uri")
    private String redirectUri;
    
    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getScopes() {
        return scopes;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }
    
    public void setScopeSet(Set<String> scopes) {
        if(CollectionUtils.isEmpty(scopes)) {
            return;
        }
        
        setScopes(Joiner.on(",").join(scopes));
    }
    
    @SuppressWarnings("unchecked")
    public Set<String> getScopeSet() {
        if(StringUtils.isEmpty(getScopes())) {
            return Collections.EMPTY_SET;
        }
        
        Iterable<String> scopes = Splitter.on(',').trimResults().omitEmptyStrings().split(getScopes());
        return Sets.newHashSet(scopes);
    }

    public String getGrantTypes() {
        return grantTypes;
    }

    public void setGrantTypes(String grantTypes) {
        this.grantTypes = grantTypes;
    }

    public void setGrantTypeSet(Set<String> grantTypes) {
        setGrantTypes(Joiner.on(",").join(grantTypes));
    }
    
    public Set<String> getGrantTypeSet() {
        Iterable<String> grantTypes = Splitter.on(',').trimResults().omitEmptyStrings().split(getGrantTypes());
        return Sets.newHashSet(grantTypes);
    }
    
    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
    
}
