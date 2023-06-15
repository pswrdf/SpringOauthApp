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

@Configuration
@EnableWebFluxSecurity
public class OAuth2LoginSecurityConfig {

    @Bean
    public ReactiveClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryReactiveClientRegistrationRepository(сlientRegistration());
    }

    private ClientRegistration сlientRegistration() {
        return ClientRegistration.withRegistrationId("avanpost")
                .clientId("cdi_kazna")
                .clientSecret("8wAY1VfYhX8qxpShCaxI4qI3EMgV3WQC")
                .authorizationGrantType(AUTHORIZATION_CODE)
                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                .scope("openid")
                .authorizationUri("http://localhost:8091/realms/cdi_kazna_realm/protocol/openid-connect/auth")
                .tokenUri("http://localhost:8091/realms/cdi_kazna_realm/protocol/openid-connect/token")
                .userInfoUri("http://localhost:8091/realms/cdi_kazna_realm/protocol/openid-connect/userinfo")
                .userNameAttributeName("preferred_username")
                .build();
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(authorize -> authorize
                .pathMatchers("/login/**",
                        "/oauth2/authorization/**")
                .permitAll()
                .anyExchange()
                .authenticated()
                .and()
                .oauth2Login()
        );
        return http.build();
    }
}