package com.alittlejacket.configuration;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.IOReactorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.AsyncClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguration {

    @Autowired
    private ClientConnectionProperties clientConnectionProperties;

    @Bean
    public RestTemplate syncRestTemplate() {
        return new RestTemplate(syncHttpRequestFactory());
    }

    @Bean
    public AsyncRestTemplate asyncRestTemplate() throws IOReactorException {
        return new AsyncRestTemplate(asyncHttpRequestFactory(), syncRestTemplate());
    }

    private CloseableHttpClient syncHttpClient() {
        final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(clientConnectionProperties.getSyncClientMaxTotalConnections());
        connectionManager.setDefaultMaxPerRoute(clientConnectionProperties.getSyncClientMaxConnectionsPerRoute());

        final RequestConfig config = RequestConfig.custom()
            .setConnectTimeout(clientConnectionProperties.getSyncClientReadTimeoutMilliseconds()).build();

        return HttpClientBuilder.create().setConnectionManager(connectionManager).setDefaultRequestConfig(config)
            .build();
    }

    private ClientHttpRequestFactory syncHttpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory(syncHttpClient());
    }

    private CloseableHttpAsyncClient asyncHttpClient() throws IOReactorException {
        final PoolingNHttpClientConnectionManager connectionManager = new PoolingNHttpClientConnectionManager(
            new DefaultConnectingIOReactor(IOReactorConfig.DEFAULT));
        connectionManager.setMaxTotal(clientConnectionProperties.getAsyncClientMaxTotalConnections());
        connectionManager.setDefaultMaxPerRoute(clientConnectionProperties.getAsyncClientMaxConnectionsPerRoute());

        final RequestConfig config = RequestConfig.custom()
            .setConnectTimeout(clientConnectionProperties.getAsyncClientReadTimeoutMilliseconds()).build();

        return HttpAsyncClientBuilder.create().setConnectionManager(connectionManager).setDefaultRequestConfig(config)
            .build();
    }

    private AsyncClientHttpRequestFactory asyncHttpRequestFactory() throws IOReactorException {
        return new HttpComponentsAsyncClientHttpRequestFactory(asyncHttpClient());
    }
}
