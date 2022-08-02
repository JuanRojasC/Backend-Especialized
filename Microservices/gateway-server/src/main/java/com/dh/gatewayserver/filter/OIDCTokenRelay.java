package com.dh.gatewayserver.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.server.ServerWebExchange;

public class OIDCTokenRelay extends AbstractGatewayFilterFactory<OIDCTokenRelay.OIDCTokenRelayConfig> {
    public OIDCTokenRelay() {
        super(OIDCTokenRelayConfig.class);
    }

    @Override
    public GatewayFilter apply(OIDCTokenRelayConfig config) {
        return ((exchange, chain) ->
                exchange.getPrincipal()
                        .filter(OAuth2AuthenticationToken.class::isInstance)
                        .cast(OAuth2AuthenticationToken.class)
                        .map(OAuth2AuthenticationToken::getPrincipal)
                        .filter(OidcUser.class::isInstance)
                        .cast(OidcUser.class)
                        .map(token -> withOidcIdToken(exchange, token.getIdToken()))
                        .defaultIfEmpty(exchange)
                        .flatMap(chain::filter)
        );
    }

    private ServerWebExchange withOidcIdToken(ServerWebExchange exchange, OidcIdToken accessToken) {
        return exchange.mutate()
                .request(request -> request.headers(headers -> headers.setBearerAuth(accessToken.getTokenValue())))
                .build();
    }

    public static class OIDCTokenRelayConfig {

    }
}
