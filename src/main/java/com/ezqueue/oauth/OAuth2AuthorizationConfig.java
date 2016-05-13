//package com.ezqueue.oauth;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//
//@Configuration
//@EnableAuthorizationServer
//public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
//    
//	@Autowired  
//    private EzQueueTokenStoreConfig ezQueueTokenStoreConfig;
//
//    @Autowired  
//    private EzQueueClinetDetailsService ezQueueClinetDetailsService;
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//    	clients.withClientDetails(ezQueueClinetDetailsService);
//    }
//    
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//    	endpoints.tokenStore(ezQueueTokenStoreConfig.tokenStore());
//    }
//}