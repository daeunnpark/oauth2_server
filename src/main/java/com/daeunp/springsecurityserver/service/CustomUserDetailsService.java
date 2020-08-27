package com.daeunp.springsecurityserver.service;


import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.daeunp.springsecurityserver.model.CustomUserDetails;
import com.daeunp.springsecurityserver.model.User;
import com.daeunp.springsecurityserver.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public boolean exists(String username) {
		return userRepository.findByUsername(username)!=null;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)  {
		User user = userRepository.findByUsername(username);		
		return new CustomUserDetails(user);
	}
	
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(true);
		user.setRoles("ROLE_USER");
		userRepository.save(user);
	}
	
	public User findByName(String name) {
		return userRepository.findByName(name);	
	}

}
