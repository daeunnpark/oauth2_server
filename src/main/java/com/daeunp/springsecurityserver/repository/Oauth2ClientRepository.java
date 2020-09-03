package com.daeunp.springsecurityserver.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daeunp.springsecurityserver.model.Oauth2Client;

public interface Oauth2ClientRepository extends JpaRepository<Oauth2Client, Integer> {
	Oauth2Client findByClientId(String clientId);
	List<Oauth2Client> findByUserId(Integer id);

}