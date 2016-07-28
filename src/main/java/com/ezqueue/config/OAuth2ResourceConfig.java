//package com.ezqueue.config;
//
//import java.io.IOException;
//
//import javax.annotation.PostConstruct;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
//import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
//import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;
//import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//@Configuration
//@EnableResourceServer
//public class OAuth2ResourceConfig extends ResourceServerConfigurerAdapter {
//    private static final Logger LOG = LoggerFactory.getLogger(OAuth2ResourceConfig.class);
//    
//    private TokenExtractor tokenExtractor = new BearerTokenExtractor();
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//    
//    @Autowired
//    private ClientDetailsService clientDetailsService;
//    
//    @PostConstruct 
//    public void postConstruct() {
//        LOG.info("enable resource server oauth configurations");
//    }
//    
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.addFilterAfter(new OncePerRequestFilter() {
//            @Override
//            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                    FilterChain filterChain) throws ServletException, IOException {
//                // We don't want to allow access to a resource with no token so
//                // clear
//                // the security context in case it is actually an
//                // OAuth2Authentication
//                if (tokenExtractor.extract(request) == null) {
//                    SecurityContextHolder.clearContext();
//                }
//                filterChain.doFilter(request, response);
//            }
//        }, AbstractPreAuthenticatedProcessingFilter.class);
//        
//        // @formatter:off
//        http
//            .authenticationProvider(clientDaoAuthenticationProvider())
//            .userDetailsService(userDetailsService)
//            .headers().cacheControl().disable()
//            .httpBasic()
//            .and()
//                .csrf().disable()
//            .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
//                .antMatchers("/version", "/favicon.ico","/ezqueue/home").permitAll();
////                .anyRequest().authenticated();
//        // @formatter:on
//    }
//    
//    /**
//     * The authentication provider allows the client use basic authentoication to send app info.
//     * For example, Basic base64(app_id + ':' + app_secret)
//     * However, the password encoder is PlaintextPasswordEncoder, so username:password is not allowed here because user table uses BCryptPasswordEncoder.
//     * @return
//     */
//    @Bean
//    public DaoAuthenticationProvider clientDaoAuthenticationProvider() {
//        DaoAuthenticationProvider clientDaoAuthenticationProvider = new DaoAuthenticationProvider();
//        clientDaoAuthenticationProvider.setUserDetailsService(new ClientDetailsUserDetailsService(clientDetailsService));
//        return clientDaoAuthenticationProvider;
//    }
//}