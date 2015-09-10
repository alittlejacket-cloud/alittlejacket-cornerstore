package com.alittlejacket.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource(value = "emptyConnection.properties", ignoreResourceNotFound = true)
public class ClientConnectionTestConfiguration {

    @Value("${sync.client.max.total.connections}")
    private Integer syncClientMaxTotalConnections;

    @Value("${sync.client.max.connections.per.route}")
    private Integer syncClientMaxConnectionsPerRoute;

    @Value("${sync.client.read.timeout.milliseconds}")
    private Integer syncClientReadTimeoutMilliseconds;

    @Value("${async.client.max.total.connections}")
    private Integer asyncClientMaxTotalConnections;

    @Value("${async.client.max.connections.per.route}")
    private Integer asyncClientMaxConnectionsPerRoute;

    @Value("${async.client.read.timeout.milliseconds}")
    private Integer asyncClientReadTimeoutMilliseconds;

    @Bean
    public ClientConnectionProperties clientConnectionProperties() {
        return new ClientConnectionProperties.Builder().syncClientMaxTotalConnections(syncClientMaxTotalConnections)
            .syncClientMaxConnectionsPerRoute(syncClientMaxConnectionsPerRoute)
            .syncClientReadTimeoutMilliseconds(syncClientReadTimeoutMilliseconds)
            .asyncClientMaxTotalConnections(asyncClientMaxTotalConnections)
            .asyncClientMaxConnectionsPerRoute(asyncClientMaxConnectionsPerRoute)
            .asyncClientReadTimeoutMilliseconds(asyncClientReadTimeoutMilliseconds).build();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
