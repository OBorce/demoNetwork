package com.example.demo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.Assert.assertTrue;

@ActiveProfiles("test")
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationTest extends BaseTest {
    @Autowired
    private JwtTokenStore tokenStore;

    @Test
    public void authenticateAndGetAccessToken() {
        final var tokenValue = obtainAccessToken("clientId", "user", "pass");
        final var auth = tokenStore.readAuthentication(tokenValue);
        assertTrue(auth.isAuthenticated());
        final var authAuthorities = auth.getAuthorities();
        assertTrue(authAuthorities.contains(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
