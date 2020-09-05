package com.daeunp.springsecurityserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daeunp.springsecurityserver.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>{
	boolean existsByUsername(String username);
	Optional <User> findByName(String name);
	Optional <User> findByUsername(String username);
}
