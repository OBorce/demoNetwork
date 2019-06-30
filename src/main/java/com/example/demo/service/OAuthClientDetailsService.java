package com.example.demo.service;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repository.OAuthClientDetailsRepository;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

@Service
public class OAuthClientDetailsService implements ClientDetailsService {
    private final OAuthClientDetailsRepository clientDetailsRepository;

    public OAuthClientDetailsService(OAuthClientDetailsRepository clientDetailsRepository) {
        this.clientDetailsRepository = clientDetailsRepository;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientDetailsRepository.findById(clientId)
                .map(clientDetails -> {
                    var baseClientDetails = new BaseClientDetails(clientDetails.getClientId(),
                            clientDetails.getResourceIds(),
                            clientDetails.getScope(),
                            clientDetails.getAuthorizedGrantTypes(),
                            clientDetails.getAuthorities(),
                            clientDetails.getWebServerRedirectUri()
                    );
                    baseClientDetails.setAccessTokenValiditySeconds(clientDetails.getAccessTokenValidity());
                    baseClientDetails.setRefreshTokenValiditySeconds(clientDetails.getRefreshTokenValidity());
                    baseClientDetails.setClientSecret(clientDetails.getClientSecret());

                    return baseClientDetails;
                })
                .orElseThrow(UserNotFoundException::new);
    }
}
