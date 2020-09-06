package com.daeunp.springsecurityserver.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import com.daeunp.springsecurityserver.service.CustomOauth2ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter{

	/*
	@Autowired
	private PasswordEncoder passwordEncoder;
	*/
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomOauth2ClientDetailsService clientDetailService;

	@Autowired
	private UserDetailsService customUserDetailsService;

	@Autowired
	private TokenStore tokenStore;
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
				//.passwordEncoder(passwordEncoder)
                .allowFormAuthenticationForClients();
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(clientDetailService);
		/*
		clients
				.inMemory()
				.withClient("clientID")
				.secret("nono")
				.authorizedGrantTypes("authorization_code" )
				.scopes("name")
				.accessTokenValiditySeconds(3600).
				refreshTokenValiditySeconds(3600)
				.redirectUris("https://oauth.pstmn.io/v1/callback");

		 */
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
				//.tokenStore(tokenStore);
		//endpoints.setClientDetailsService(clientDetailService);
		endpoints.userDetailsService(customUserDetailsService);

				//.userDetailsService(customUserDetailsService);
	}
	@Bean
	public PasswordEncoder encoder(){
		return  NoOpPasswordEncoder.getInstance();
	}

	/*
	@Bean
	public FilterRegistrationBean oauth2ClientFilterRegistration(
			OAuth2ClientContextFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(filter);
		registration.setOrder(-100);
		return registration;
	}

	 */
}
