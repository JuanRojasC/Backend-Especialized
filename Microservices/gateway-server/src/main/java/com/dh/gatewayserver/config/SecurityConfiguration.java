package com.dh.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http, ReactiveClientRegistrationRepository client) {
        http.oauth2Login();
        http.logout(logoutSpec -> logoutSpec.logoutSuccessHandler(
                new OidcClientInitiatedServerLogoutSuccessHandler(client)
        ));
        http.authorizeExchange().anyExchange().authenticated();
        http.cors().disable();
        return http.build();
    }
}
