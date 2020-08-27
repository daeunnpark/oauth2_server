package com.daeunp.springsecurityserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends WebSecurityConfigurerAdapter{
	
	/*
	@Autowired
	private UserDetailService customUserDetailsService;
	*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.requestMatchers()
				.antMatchers("/login", "/oauth/authorize")
				.and()
			.authorizeRequests()
				.anyRequest()
				.authenticated()
				.and()
			.formLogin()
				.permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.parentAuthenticationManager(authenticationManagerBean())
			.inMemoryAuthentication()
			.withUser("PETER")
			.password("peter")
			.roles("ADMIN");
		
		/*
		 auth.parentAuthenticationManager(authenticationManager)
		 	  .userDetailsService(customUserDetailsService);
		 */
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}

}
