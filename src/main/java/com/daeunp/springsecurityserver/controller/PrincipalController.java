package com.daeunp.springsecurityserver.controller;

import com.daeunp.springsecurityserver.service.CustomOauth2ClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class PrincipalController {

    @Autowired
    CustomOauth2ClientDetailsService customOauth2ClientDetailsService;

    @RequestMapping("/principal")
    public Principal user(Principal principal) {
        return principal;
    }
}
