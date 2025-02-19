package org.fastcampus.community_feed.common.config;

import org.fastcampus.community_feed.auth.domain.TokenProvider;
import org.fastcampus.community_feed.common.principal.AuthPrincipalArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class AuthConfig implements WebMvcConfigurer {
    private TokenProvider tokenProvider;

    public AuthConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void addArgumentResolvers(List argumentResolvers) {
        argumentResolvers.add(new AuthPrincipalArgumentResolver(tokenProvider));
    }
}