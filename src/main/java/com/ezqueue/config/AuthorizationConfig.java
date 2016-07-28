//package com.ezqueue.config;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.security.oauth2.provider.CompositeTokenGranter;
//import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
//import org.springframework.security.oauth2.provider.OAuth2RequestValidator;
//import org.springframework.security.oauth2.provider.TokenGranter;
//import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
//import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
//import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
//import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
//import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
//import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
//import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//
//import com.ezqueue.security.EzQueueClientDetailsService;
//
//@Configuration
//@EnableAuthorizationServer
//public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
//    
//    @Autowired
//    private TokenStore tokenStore;
//    
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private EzQueueClientDetailsService clientDetailsService;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//    
//    @Autowired
//    private OAuth2RequestValidator requestValidator;
//    
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        // client detail service must be set before setting token granter.
//        // Otherwise, InMemoryClientDetailsService is used.
//        endpoints.setClientDetailsService(clientDetailsService);
//
//        // @formatter:off
//        endpoints.tokenStore(tokenStore)
//            .tokenServices(tokenServices())
//            .authenticationManager(authenticationManager)
//            .userDetailsService(userDetailsService)
//            .accessTokenConverter(accessTokenConverter())
//            .tokenGranter(tokenGranter(endpoints))
//            .requestValidator(requestValidator);
//        // @formatter:on
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.withClientDetails(clientDetailsService);
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//        // @formatter:off
//        oauthServer
//            .tokenKeyAccess("permitAll()")
//            .checkTokenAccess("isAuthenticated()");
//        // @formatter:off
//    }
//
//    @Bean
//    public AccessTokenConverter accessTokenConverter() {
//        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
//        accessTokenConverter.setIncludeGrantType(true);
//        return accessTokenConverter;
//    }
//    
//    @Bean
//    @Primary
//    public AuthorizationServerTokenServices tokenServices() {
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setSupportRefreshToken(true);
//        tokenServices.setReuseRefreshToken(false);
//        tokenServices.setTokenStore(this.tokenStore);
//        tokenServices.setClientDetailsService(clientDetailsService);
//        return tokenServices;
//    }
//    
//    @Bean
//    public OAuth2RequestFactory requestFactory() {
//        return new DefaultOAuth2RequestFactory(clientDetailsService);
//    }
//    
//    /**
//     * Add {@link FacebookTokenGranter} to the default token granter.
//     * @param endpoints
//     * @return
//     */
//    private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
//        ClientDetailsService clientDetails = endpoints.getClientDetailsService();
//        AuthorizationServerTokenServices tokenServices = endpoints.getTokenServices();
//        OAuth2RequestFactory requestFactory = endpoints.getOAuth2RequestFactory();
//        AuthorizationCodeServices authorizationCodeServices = endpoints.getAuthorizationCodeServices();
//
//        List<TokenGranter> tokenGranters = new ArrayList<TokenGranter>();
//        
//        // add default token granters
//        tokenGranters.add(new AuthorizationCodeTokenGranter(tokenServices, authorizationCodeServices, clientDetails, requestFactory));
//        tokenGranters.add(new RefreshTokenGranter(tokenServices, clientDetails, requestFactory));
//        tokenGranters.add(new ImplicitTokenGranter(tokenServices, clientDetails, requestFactory));
//        tokenGranters.add(new ClientCredentialsTokenGranter(tokenServices, clientDetails, requestFactory));
////        tokenGranters.add(new EzQueueDeviceValidateTokenGranter(tokenServices, clientDetails, requestFactory));
//        
//        return new CompositeTokenGranter(tokenGranters);
//    }
//}