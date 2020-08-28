package com.daeunp.springsecurityserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.daeunp.springsecurityserver.service.CustomUserDetailsService;

import org.springframework.security.core.userdetails.UserDetailsService;
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends WebSecurityConfigurerAdapter{
	
	/*
	@Autowired
	private UserDetailService customUserDetailsService;
	*/
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService customUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		http.requestMatchers()
				.antMatchers("/login", "/oauth/authorize", "/auth/login", "/auth/ouath/authorize", "/rest/hello/hi")
				.and()
				.authorizeRequests()
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.permitAll();
		*/
		
		http
			.headers()
				.frameOptions()
				.disable()
				.and()
			.authorizeRequests()
				.antMatchers("/", "/login" ).permitAll()
				.antMatchers("/rest/hello/hi").authenticated();
			
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		auth.parentAuthenticationManager(authenticationManagerBean())
			.userDetailsService(customUserDetailsService)
			.passwordEncoder(passwordEncoder);
		*/
		
		
		auth.parentAuthenticationManager(authenticationManagerBean())
			.inMemoryAuthentication()
			.withUser("PETER")
			.password(passwordEncoder.encode("peter"))
			.roles("USER");
		
		
		
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	
	


}
