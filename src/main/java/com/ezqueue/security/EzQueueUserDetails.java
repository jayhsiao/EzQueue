package com.ezqueue.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ezqueue.model.User;
import com.google.common.collect.ImmutableList;

public class EzQueueUserDetails implements UserDetails {
	
	private static final long serialVersionUID = -4968222183342448287L;
	
	private User user;
	
	public EzQueueUserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return ImmutableList.of(new SimpleGrantedAuthority("user"));
	}
	
	public String getUserId() {
		return this.user.getUserId();
	}

	@Override
	public String getUsername() {
		return this.user.getEmail();
	}
	
	@Override
	public String getPassword() {
		return null;
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

}
