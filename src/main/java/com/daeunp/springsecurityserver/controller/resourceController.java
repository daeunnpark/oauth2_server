package com.daeunp.springsecurityserver.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.daeunp.springsecurityserver.model.User;
import com.daeunp.springsecurityserver.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.daeunp.springsecurityserver.model.Oauth2Client;
import com.daeunp.springsecurityserver.service.CustomOauth2ClientDetailsService;

@RestController
public class resourceController {
	
	@Autowired
	CustomOauth2ClientDetailsService customOauth2ClientDetailsService;

	@Autowired
	CustomUserDetailsService userService;

	@RequestMapping("/userInfo/kwj")
	public ResponseEntity<Map<String, Object>> userData(@AuthenticationPrincipal OAuth2Authentication auth, Principal p) {
		Map<String, Object> body = new HashMap<String, Object>();
		Map<String, Object> profile = new HashMap<String, Object>();

		Set<String> scopes = auth.getOAuth2Request().getScope();
		User user = userService.findByUsername(p.getName());

		if (scopes.contains("id")) {
			profile.put("id", user.getUsername());
		}
		if (scopes.contains("name")) {
			profile.put("name", user.getName());
		}
		if (scopes.contains("email")) {
			profile.put("email", user.getEmail());
		}
		if (scopes.contains("phone")) {
			profile.put("phone", user.getPhone());
		}
		System.out.println(profile);
		System.out.println(profile.toString());

		body.put("profile", profile);
		return new ResponseEntity<Map<String, Object>>(body, HttpStatus.OK);
	}


	@RequestMapping("/principal")
	public Principal userData2( Principal p) {
		return p;
		/*
		Map<String, Object> body = new HashMap<String, Object>();
		Map<String, Object> profile = new HashMap<String, Object>();

		Set<String> scopes = auth.getOAuth2Request().getScope();
		User user = userService.findByUsername(p.getName());

		if (scopes.contains("id")) {
			profile.put("id", user.getUsername());
		}
		if (scopes.contains("name")) {
			profile.put("name", user.getName());
		}
		if (scopes.contains("email")) {
			profile.put("email", user.getEmail());
		}
		if (scopes.contains("phone")) {
			profile.put("phone", user.getPhone());
		}

		body.put("profile", profile);
		return "dataaaaas";

		 */
	}

}
