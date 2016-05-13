//package com.ezqueue.oauth;
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
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
//import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
//import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//@Configuration
//@ConditionalOnProperty(prefix = "com.ezqueue.oauth", name = "enabled", havingValue = "true")
//@EnableResourceServer
//public class OAuth2ResourceConfig extends ResourceServerConfigurerAdapter {
//    private static final Logger LOG = LoggerFactory.getLogger(OAuth2ResourceConfig.class);
//    
//    private TokenExtractor tokenExtractor = new BearerTokenExtractor();
//
//    @Autowired
//    private UserDetailsService userDetailsService;
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
//            .csrf().disable()
//            .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
//                .antMatchers("/version", "/favicon.ico").permitAll()
//                .anyRequest().authenticated();
//        // @formatter:on
//        http.headers().cacheControl().disable();
//        http.userDetailsService(userDetailsService);
//    }
//}