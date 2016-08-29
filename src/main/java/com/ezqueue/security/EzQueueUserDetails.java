package com.ezqueue.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.stereotype.Service;

import com.ezqueue.model.User;

@Service
public class EzQueueUserDetails implements SocialUserDetails {
	
	private static final long serialVersionUID = 4468977728695649270L;
	
	private User user;
	
	public EzQueueUserDetails() {
		
	}
	
	public EzQueueUserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return null;
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
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getUserId() {
		return this.user.getFacebookId();
	}

	
}
