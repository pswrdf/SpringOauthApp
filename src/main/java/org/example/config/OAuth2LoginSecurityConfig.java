package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.oauth2.core.AuthorizationGrantType.AUTHORIZATION_CODE;
import static org.springframework.security.oauth2.core.oidc.OidcScopes.OPENID;
import static org.springframework.security.oauth2.core.oidc.OidcScopes.PROFILE;

@Configuration
@EnableWebFluxSecurity
public class OAuth2LoginSecurityConfig {

    @Bean
    public ReactiveClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryReactiveClientRegistrationRepository(clientRegistration());
    }

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
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(authorize -> authorize
                        .pathMatchers("/login/**")
                        .permitAll()
                        .anyExchange()
                        .authenticated()
                )
                .oauth2Login()
        ;
        return http.build();
    }
}