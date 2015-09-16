package com.alittlejacket.configuration;

import org.springframework.stereotype.Component;

@Component
public class CornerStoreAccessToken {

    private final String token;

    public CornerStoreAccessToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
