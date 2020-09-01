package com.daeunp.springsecurityserver.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.daeunp.springsecurityserver.service.CustomUserDetailsService;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;


@Configuration
//@EnableResourceServer
@EnableWebSecurity
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsService customUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/**")
				.authorizeRequests()
				.antMatchers("/oauth/authorize**", "/login**", "/error**")
				.permitAll()
			.and()
				.authorizeRequests()
				.anyRequest().authenticated()
			.and()
				.formLogin().permitAll();

/*
		http.requestMatchers()
				// For org.springframework.security.web.SecurityFilterChain.matches(HttpServletRequest)
				.requestMatchers(
						new OrRequestMatcher(
								new AntPathRequestMatcher("/login"),
								new AntPathRequestMatcher("/logout"),
								new AntPathRequestMatcher("/oauth/authorize"),
								new AntPathRequestMatcher("/oauth/confirm_access")
						)
				)
				.and()
				.authorizeRequests().anyRequest().authenticated()
				.and()
				.formLogin().permitAll()
				.and()
				.logout().permitAll();
*/
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring()
	    	.antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.parentAuthenticationManager(authenticationManagerBean())
			.userDetailsService(customUserDetailsService)
			.passwordEncoder(passwordEncoder);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
}