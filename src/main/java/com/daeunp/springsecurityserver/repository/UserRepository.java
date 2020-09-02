package com.daeunp.springsecurityserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daeunp.springsecurityserver.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUsername(String username);
	User findByName(String name);
}
