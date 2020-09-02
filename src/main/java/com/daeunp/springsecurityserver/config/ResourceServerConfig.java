package com.daeunp.springsecurityserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import com.daeunp.springsecurityserver.service.CustomUserDetailsService;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Override
	public void configure(HttpSecurity http) throws Exception {
	    /*
		http.authorizeRequests()
				.antMatchers("/principal").authenticated()
				.antMatchers("/").permitAll();

	     */
        http.requestMatchers()
                // For org.springframework.security.web.SecurityFilterChain.matches(HttpServletRequest)
                .requestMatchers(
                        new NegatedRequestMatcher(

                                new OrRequestMatcher(
                                        new AntPathRequestMatcher("/login"),
                                        new AntPathRequestMatcher("/logout"),
                                        new AntPathRequestMatcher("/oauth/authorize"),
                                        new AntPathRequestMatcher("/oauth/confirm_access")
                                )
                        )
                )
                .and()
                .authorizeRequests().anyRequest().authenticated();
	}




}