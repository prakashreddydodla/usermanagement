package com.otsi.rpd.authservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;




@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private AwsCognitoJwtAuthFilter awsCognitoJwtAuthenticationFilter;
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.headers().cacheControl();
		http.csrf().disable()
		        .authorizeRequests()
		        .antMatchers("/auth/login").permitAll()
		        .antMatchers("/auth/signup").permitAll()
		        .antMatchers("/auth/confirmEmail").permitAll()
		       .antMatchers("/auth/getUserInfo/**").hasAnyAuthority("user","admin")
		        .anyRequest().authenticated()
				
				.and()
				.addFilterBefore(awsCognitoJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
				
	}
	
}
