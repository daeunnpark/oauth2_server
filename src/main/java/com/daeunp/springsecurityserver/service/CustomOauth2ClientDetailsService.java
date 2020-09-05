package com.daeunp.springsecurityserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daeunp.springsecurityserver.model.Oauth2Client;
import com.daeunp.springsecurityserver.repository.Oauth2ClientRepository;

@Service
public class CustomOauth2ClientDetailsService implements ClientDetailsService{

    @Autowired
    private Oauth2ClientRepository oauth2ClientRepository;


    @Override
    @Transactional
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Oauth2Client client = oauth2ClientRepository.findByClientId(clientId);
        System.out.println("clientId = " +  clientId);
        System.out.println(client.toString());
        System.out.println(client.getName());
        System.out.println(client.getClientSecret());
        ClientDetails d = new BaseClientDetails(client);
        System.out.println(d.toString());
        return new BaseClientDetails(client);
    }

    public List<Oauth2Client> findByUsername(String username){
    	return oauth2ClientRepository.findByUserUsername(username);
    }
    public Oauth2Client findByUsernameAndClientname(String username, String appName ){
        return oauth2ClientRepository.findByUserUsernameAndName(username, appName);
    }
    public void delete(Oauth2Client client){
        oauth2ClientRepository.delete(client);
    }
    public void save(Oauth2Client client) {
    	oauth2ClientRepository.save(client);
    }
}
