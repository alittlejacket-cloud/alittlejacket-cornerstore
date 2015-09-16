package com.alittlejacket.client;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.springframework.http.HttpMethod.GET;

import com.alittlejacket.configuration.CornerStoreAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class SyncRestClient implements RestClient {

    @Autowired
    private RestTemplate syncRestTemplate;

    @Autowired
    private CornerStoreAccessToken accessToken;

    @Override
    public <T> T post(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T get(String url, Class<T> responseType, Map<String, ?> uriVariables) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T get(String url, Class<T> responseType) {
        validateUrl(url);
        validateResponseType(responseType);
        return exchange(url, GET, responseType);
    }

    @Override
    public void put(String url, Object request, Map<String, ?> uriVariables) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(String url, Map<String, ?> urlVariables) {
        // TODO Auto-generated method stub

    }

    private <T> T exchange(String url, HttpMethod method, Class<T> responseType, Map<String, ?> uriVariables) {
        ResponseEntity<T> response =
            syncRestTemplate.exchange(url, method, getHttpEntity(), responseType, uriVariables);

        return response.getBody();
    }

    private <T> T exchange(String url, HttpMethod method, Class<T> responseType) {
        ResponseEntity<T> response =
            syncRestTemplate.exchange(url, method, getHttpEntity(), responseType);

        return response.getBody();
    }

    private HttpEntity<String> getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Token " + accessToken.getToken());
        return new HttpEntity<>(headers);
    }

    private void validateUrl(String url) {
        if (isBlank(url)) {
            throw new IllegalArgumentException("Url must not be null or empty.");
        }
    }

    private <T> void validateResponseType(Class<T> responseType) {
        if (responseType == null) {
            throw new IllegalArgumentException("Response type must not be null.");
        }
    }
}
