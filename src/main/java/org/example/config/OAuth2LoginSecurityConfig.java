package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.oauth2.core.AuthorizationGrantType.AUTHORIZATION_CODE;
import static org.springframework.security.oauth2.core.oidc.OidcScopes.OPENID;
import static org.springframework.security.oauth2.core.oidc.OidcScopes.PROFILE;

@Configuration
@EnableWebSecurity
public class OAuth2LoginSecurityConfig {

    private ClientRegistration clientRegistration() {
        return ClientRegistration.withRegistrationId("avanpost")
                .clientId("cdi_kazna")
                .clientSecret("VlXrTpROZnpqi6IveN7gYWd1hXWcWI82")
                .authorizationGrantType(AUTHORIZATION_CODE)
                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                .scope(OPENID, PROFILE)
                .authorizationUri("http://localhost:8091/realms/cdi_kazna_realm/protocol/openid-connect/auth")
                .tokenUri("http://localhost:8091/realms/cdi_kazna_realm/protocol/openid-connect/token")
                .jwkSetUri("http://localhost:8091/realms/cdi_kazna_realm/protocol/openid-connect/certs")
                .build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(clientRegistration());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login()
        ;
        return http.build();
    }
}