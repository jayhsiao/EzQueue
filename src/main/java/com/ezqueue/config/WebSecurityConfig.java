//package com.ezqueue.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//import com.ezqueue.security.EzQueueUserDetailsService;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//	
//	@Autowired
//	private EzQueueUserDetailsService ezQueueUserDetailsService;
//	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/img/**", "/js/**", "/css/**");
//	}
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(ezQueueUserDetailsService);
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.csrf().disable()
//			.authorizeRequests()
//            	.anyRequest().authenticated();
////		.antMatchers("/version", "/oauth/token", "/favicon.ico").permitAll()
////		.formLogin()
////		.and()
////		.loginPage("")
////		.loginProcessingUrl("")
////		.failureUrl("")
////		.and()
////			.logout()
////			.deleteCookies("")
////			.deleteCookies("")
////		.and()
////			.authorizeRequests()
////			.anyRequest().authenticated();
//	}
//	
//	@Override
//	@Bean
//	protected AuthenticationManager authenticationManager() throws Exception {
//		return super.authenticationManager();
//	}
//	
//}
