package com.daeunp.springsecurityserver.repository;

import com.daeunp.springsecurityserver.model.Oauth2Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Oauth2ClientRepository extends JpaRepository<Oauth2Client, Integer> {
    Oauth2Client findByClientId(String clientId);

    List<Oauth2Client> findByUserId(String user);
}