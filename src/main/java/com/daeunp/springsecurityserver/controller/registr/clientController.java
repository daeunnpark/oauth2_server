package com.daeunp.springsecurityserver.controller.registr;

import com.daeunp.springsecurityserver.model.Oauth2Client;
import com.daeunp.springsecurityserver.model.User;
import com.daeunp.springsecurityserver.service.CustomOauth2ClientDetailsService;
import com.daeunp.springsecurityserver.service.CustomUserDetailsService;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@RestController
@RequestMapping(path = "/app")
public class clientController {

    @Autowired
    CustomUserDetailsService userService;

    @Autowired
    CustomOauth2ClientDetailsService clientService;

    @GetMapping("/register")
    public ModelAndView registerPage() {
        ModelAndView page = new ModelAndView("register");
        page.addObject("client", new Oauth2Client());
        return page;
    }

    @PostMapping("/register")
    public ModelAndView register(Oauth2Client client, Authentication auth) throws NoSuchAlgorithmException {
        client.setUser(userService.findByUsername(auth.getName()));
        client.setClientId(client.getName() + System.currentTimeMillis());
        client.setClientSecret(sha256(UUID.randomUUID().toString()));
        //client.setRedirectUri("http://10.113.97.165:8081");
        client.setAccessTokenValiditySeconds(3600);
        client.setAuthorities("USER");
        client.setAutoApprove(false);
        client.setGrantTypes("authorization_code");
        client.setRefreshTokenValiditySeconds(3600);
        client.setResourceIds("oauth2-resource");
        client.setScope("id,name,email,phone");
        clientService.save(client);

        return new ModelAndView("redirect:/app/view/" + client.getName());
    }

    @GetMapping("/view/all")
    public ModelAndView viewApps(Authentication auth) {
        ModelAndView page = new ModelAndView("viewAll");
        page.addObject("clients", clientService.findByUsername(auth.getName()));
        return page;
    }

    @GetMapping("/view/{clientName}")
    public ModelAndView viewApp(Authentication auth, @PathVariable String clientName) {
        ModelAndView page = new ModelAndView("view");
        Oauth2Client client = clientService.findByUsernameAndClientname(auth.getName(), clientName);
        page.addObject("client", client);
        return page;
    }

    @PostMapping("/delete/{clientName}")
    public ModelAndView deleteApp(Authentication auth, @PathVariable String clientName) {
        clientService.delete(clientService.findByUsernameAndClientname(auth.getName(), clientName));
        return new ModelAndView("redirect:/app/view/all");
    }

    public String sha256(String original) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(original.getBytes());
        byte[] digest = md.digest();
        return Hex.encodeHexString(digest);
    }

}
