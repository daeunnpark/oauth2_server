package com.daeunp.springsecurityserver.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.daeunp.springsecurityserver.model.Oauth2Client;
import com.daeunp.springsecurityserver.service.CustomOauth2ClientDetailsService;

@RestController
public class PrincipalController {
	
	@Autowired
	CustomOauth2ClientDetailsService customOauth2ClientDetailsService;
	
	@GetMapping("/principal")
	public Principal user(Principal principal) {
		return principal;
	}
	
	@GetMapping("/rest/hello/hi")
	public String hello() {
		return "Hi World";
	}
	
}
