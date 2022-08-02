//package com.digitalhouse.msusers.config.feign;
//
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
//import org.springframework.stereotype.Component;
//
//@Component
//public class FeignInterceptor implements RequestInterceptor {
//
//    private final String AUTHORIZATION_HEADER = "Authorization";
//    private final String TOKEN_TYPE = "Bearer";
//
//    @Override
//    public void apply(RequestTemplate requestTemplate) {
//        String token = getAccessToken();
//        if (token != null) {
//            requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s", TOKEN_TYPE, token));
//        }
//
//    }
//
//    private String getAccessToken() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        try {
//            return ((JwtAuthenticationToken) authentication).getToken().getTokenValue();
//        } catch (Exception e) {
//            return null;
//        }
//    }
//}
