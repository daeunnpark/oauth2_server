package com.daeunp.springsecurityserver.service;


import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.daeunp.springsecurityserver.model.CustomUserDetails;
import com.daeunp.springsecurityserver.model.User;
import com.daeunp.springsecurityserver.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		user.orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
		return user.map(CustomUserDetails::new).get();
	}

	public User findByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		user.orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
		return user.get();
	}
	public boolean exists(String username) {
		return userRepository.existsByUsername(username);
	}

	public void saveUser(User user) {



		userRepository.save(user);
	}

	public User findByName(String name) {
		Optional<User> user = userRepository.findByName(name);
		user.orElseThrow(() -> new UsernameNotFoundException("Name not found: " + name));
		return user.get();
	}


}
