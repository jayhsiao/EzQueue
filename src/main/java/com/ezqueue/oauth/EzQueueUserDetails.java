package com.ezqueue.oauth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ezqueue.model.User;
import com.google.common.collect.Lists;

public class EzQueueUserDetails implements UserDetails {
	
    private static final long serialVersionUID = 6263727229297823517L;
    
    private User user;
    
    public EzQueueUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Lists.newArrayList(new SimpleGrantedAuthority("USER"));
    }
    
    public String getUserId() {
    	return this.user.getUserId();
    }
    
    @Override
    public String getUsername() {
        return this.user.getName();
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
