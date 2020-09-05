package com.daeunp.springsecurityserver.controller.registr;

import com.daeunp.springsecurityserver.model.Oauth2Client;
import com.daeunp.springsecurityserver.model.User;
import com.daeunp.springsecurityserver.service.CustomOauth2ClientDetailsService;
import com.daeunp.springsecurityserver.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;

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

}
