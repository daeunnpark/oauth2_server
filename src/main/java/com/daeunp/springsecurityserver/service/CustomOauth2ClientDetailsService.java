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
        return new BaseClientDetails(client);
    }
    
    public List<Oauth2Client> findByUserId(Integer userId){
    	return oauth2ClientRepository.findByUserId(userId);	
    }
    
    public void save(Oauth2Client client) {
    	oauth2ClientRepository.save(client);
    }
}
