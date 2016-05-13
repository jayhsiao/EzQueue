//package com.ezqueue.oauth;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
//
//@Configuration
//public class EzQueueTokenStoreConfig {
//	
//    @Bean
//    public TokenStore tokenStore() {
//    	InMemoryTokenStore tokenStore = new InMemoryTokenStore();
//        tokenStore.setAuthenticationKeyGenerator(new EzQueueAuthenticationKeyGenerator());
//        return tokenStore;
//    }
//}
