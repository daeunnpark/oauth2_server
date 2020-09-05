package com.daeunp.springsecurityserver.controller.registr;

import com.daeunp.springsecurityserver.model.User;
import com.daeunp.springsecurityserver.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class loginController {

    @Autowired
    private CustomUserDetailsService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = {"/", "/home"})
    public ModelAndView home() {
        System.out.println("************HOMEEE");
        ModelAndView page = new ModelAndView();
        page.setViewName("home");
        return page;//new ModelAndView("home");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @GetMapping("/signup")
    public ModelAndView signup() {
        ModelAndView page = new ModelAndView("signup");
        page.addObject("user", new User());
        return page;
    }

    @PostMapping("/signup")
    public ModelAndView createNewUser(User user, BindingResult bindingResult) {
        if (userService.exists(user.getUsername())) {
            bindingResult
                    .rejectValue("username", "error.username",
                            "There is already a user registered with the username provided");
        }

        if (bindingResult.hasErrors()) {
			return signup();
		}

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setRoles("ROLE_USER");
		userService.saveUser(user);
		return login();
    }

    /*
    @GetMapping("/user")
    public ModelAndView user(Authentication auth) {
        User user = userService.findByUsername(auth.getName());

		ModelAndView page = new ModelAndView();
        page.addObject("currentUser", user);
        page.addObject("username", user.getUsername());
        page.addObject("name", user.getName());
        //page.addObject("id", user.getId());
        page.setViewName("user");

        return page;
    }
     */

    @GetMapping("/findUsername")
    public ModelAndView findUsername() {
        ModelAndView page = new ModelAndView("findUsername");
        page.addObject("user", new User());
        return page;
    }

    @GetMapping("/findUsernameByName")
    public ModelAndView findUserNameByName(@RequestParam(value = "name", required = true) String name) {
        User user = userService.findByName(name);

		ModelAndView page = new ModelAndView("findUsername");
        page.addObject("name", user.getName());
        page.addObject("username", user.getUsername());

        return page;
    }

    @GetMapping(value = "/findPassword")
    public ModelAndView findPassword() {
        ModelAndView page = new ModelAndView("findPassword");
        page.addObject("user", new User());
        return page;
    }

    @GetMapping(value = "/findPasswordByUsername")
    public ModelAndView indPasswordByUsername(@RequestParam(value = "username", required = true) String username) {
        User user = userService.findByUsername(username);

		ModelAndView page = new ModelAndView("findPassword");
        page.addObject("username", user.getUsername());
        page.addObject("password", user.getPassword());

        return page;
    }

}
