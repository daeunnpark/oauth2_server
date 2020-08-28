package com.daeunp.springsecurityserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.daeunp.springsecurityserver.service.CustomUserDetailsService;

import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService customUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/oauth/token", "/ouath/authorize").permitAll()
				.anyRequest().authenticated()
				.and()
			.httpBasic()
				.and()
			.csrf().disable();
		
		http.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		
		auth.parentAuthenticationManager(authenticationManagerBean())
			.userDetailsService(customUserDetailsService)
			.passwordEncoder(passwordEncoder);
	
		/*
		auth.parentAuthenticationManager(authenticationManagerBean())
			.inMemoryAuthentication()
			.withUser("PETER")
			.password(passwordEncoder.encode("peter"))
			.roles("USER");
		*/
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	
	


}
