package com.daeunp.springsecurityserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class PrincipalController {

    @RequestMapping("/principal")
    public Principal user(Principal principal) {
    	return principal;
    }
}