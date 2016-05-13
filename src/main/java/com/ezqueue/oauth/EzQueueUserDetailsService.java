package com.ezqueue.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ezqueue.exception.EzQueueException;
import com.ezqueue.model.User;
import com.ezqueue.service.UserService;

@Service
public class EzQueueUserDetailsService implements UserDetailsService {
	
    @Autowired
    private UserService userService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        
        try {
        	user = userService.getUserByEmail(username);
        } catch(EzQueueException ex) {
            throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
        }
        
        return new EzQueueUserDetails(user);
    }
}