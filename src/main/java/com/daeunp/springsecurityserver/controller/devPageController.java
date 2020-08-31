package com.daeunp.springsecurityserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.daeunp.springsecurityserver.model.Oauth2Client;
import com.daeunp.springsecurityserver.model.User;
import com.daeunp.springsecurityserver.service.CustomOauth2ClientDetailsService;
import com.daeunp.springsecurityserver.service.CustomUserDetailsService;

@Controller
@RequestMapping("/dev")
public class devPageController {
/*
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	CustomOauth2ClientDetailsService customOauth2ClientDetailsService;
	

	@GetMapping(value={"/home", "/"})
	public ModelAndView home() {
		ModelAndView page = new ModelAndView();
		page.setViewName("home");
		return page;
	}
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView page = new ModelAndView();
		page.setViewName("login");
		return page;
	}
	
	@GetMapping("/signup")
	public ModelAndView signUp() {
		ModelAndView page = new ModelAndView();
	    User user = new User();
	    page.addObject("user", user);
	    page.setViewName("signup"); 
	    return page;
	}	
	
	@PostMapping("/signup")
	public ModelAndView createNewUser(User user, BindingResult bindingResult) {
	    System.out.println("SIGN UP HEREEE");
	    System.out.println(user.getUsername());
	    System.out.println(user.getPassword());
	    System.out.println(user.getName());
	    
	    ModelAndView page = new ModelAndView();
	    
	    if(customUserDetailsService.exists(user.getUsername())) {
	        bindingResult
            .rejectValue("username", "error.username",
                    "There is already a user registered with the username provided");
	    }
	    
	    if(bindingResult.hasErrors()) {
	    	System.out.println("has errors");
	    	page.setViewName("signup");
	    }else {
	    	customUserDetailsService.saveUser(user);
            page.addObject("successMessage", "User has been registered successfully");
	        page.addObject("user", new User());
	        page.setViewName("login");
	    } 
	    return page;
	}
	
	@GetMapping("/app/register")
	public ModelAndView registerPage() {
		ModelAndView page = new ModelAndView();
		Oauth2Client client = new Oauth2Client();
		page.addObject("client", client);
		page.setViewName("registerApp");
		return page;
	}
	
	@PostMapping("/app/register")
	public void register(Oauth2Client client) {
		System.out.println(client.getName());
		System.out.println(client.getHomepageUrl());
		System.out.println(client.getRegisteredRedirectUri());
		
		//generate Id
		client.setClientId("aaa");
		client.setClientSecret("bbb");
		
		//add if already exists
    	customOauth2ClientDetailsService.save(client);
	    registerPage();
	}

	@RequestMapping("/app/view")
	public ModelAndView viewApps() {
		System.out.println("app view called");
		ModelAndView page = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getName());
		System.out.println(auth.getPrincipal().toString());
		//page.addObject("apps", customOauth2ClientDetailsService.findByUserId(auth.getName()));

		page.setViewName("viewApp");
		return page;
	}
	*/
}
