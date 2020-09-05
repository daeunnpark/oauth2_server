package com.daeunp.springsecurityserver.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Override
	public void configure(HttpSecurity http) throws Exception {
        System.out.println("*******resource");
        http.requestMatchers()
                // For org.springframework.security.web.SecurityFilterChain.matches(HttpServletRequest)
                .requestMatchers(
                        new NegatedRequestMatcher(

                                new OrRequestMatcher(
                                        new AntPathRequestMatcher("/api/login"),
                                        new AntPathRequestMatcher("/api/logout"),
                                        new AntPathRequestMatcher("/api/oauth/authorize"),
                                        new AntPathRequestMatcher("/api/oauth/confirm_access")
                                )
                        )
                )
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .csrf().disable();

	}

}