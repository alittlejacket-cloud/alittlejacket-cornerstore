package com.alittlejacket.client;

import java.util.Map;



public interface RestClient {

    <T> T post(String url, Object request, Class<T> responseType, Map<String,?> uriVariables);
    
    <T> T get(String url, Class<T> responseType, Map<String,?> uriVariables);
    
    <T> T get(String url, Class<T> responseType);
    
    <T> void put(String url, Object request, Map<String,?> uriVariables); 
    
    void delete(String url, Map<String,?> urlVariables);
}
