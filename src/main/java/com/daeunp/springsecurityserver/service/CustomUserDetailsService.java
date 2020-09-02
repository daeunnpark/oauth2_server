package com.daeunp.springsecurityserver.service;

import com.daeunp.springsecurityserver.model.CustomUserDetails;
import com.daeunp.springsecurityserver.model.User;
import com.daeunp.springsecurityserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return new CustomUserDetails(user);
    }

	public boolean exists(String username) {
		return userRepository.findByUsername(username) != null;
	}

    public User findByUsername(String username) {
    	return userRepository.findByUsername(username);
    }

	public User findByName(String name) {
		return userRepository.findByName(name);
	}

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setRoles("ROLE_USER");
        userRepository.save(user);
    }
}
