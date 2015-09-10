package com.alittlejacket.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CornerStoreAccessTokenConfiguration {

    @Value("${cornerstore.access.token}")
    private String cornerStoreAccessToken;

    @Bean
    public CornerStoreAccessToken cornerStoreAccessToken() {
        return new CornerStoreAccessToken(cornerStoreAccessToken);
    }
}
