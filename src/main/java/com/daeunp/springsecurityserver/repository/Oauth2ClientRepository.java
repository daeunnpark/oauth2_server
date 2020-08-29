package com.daeunp.springsecurityserver.repository;

import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daeunp.springsecurityserver.model.Oauth2Client;

public interface Oauth2ClientRepository extends JpaRepository<Oauth2Client, Integer> {
	Oauth2Client findByClientId(String clientId);
}