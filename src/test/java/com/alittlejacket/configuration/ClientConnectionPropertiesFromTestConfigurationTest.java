package com.alittlejacket.configuration;

import static com.alittlejacket.configuration.ClientConnectionProperties.DEFAULT_MAX_CONNECTIONS_PER_ROUTE;
import static com.alittlejacket.configuration.ClientConnectionProperties.DEFAULT_MAX_TOTAL_CONNECTIONS;
import static com.alittlejacket.configuration.ClientConnectionProperties.DEFAULT_READ_TIMEOUT_MILLISECONDS;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ClientConnectionTestConfiguration.class)
public class ClientConnectionPropertiesFromTestConfigurationTest {

    @Autowired
    private ClientConnectionProperties clientConnectionProperties;
    
    @Test
    public void clientConnectionProperties_Should_HaveDefaultValues_When_ValuesAreNotSetInPropertyFile() {
        assertThat(clientConnectionProperties.getSyncClientMaxTotalConnections(),
                is(equalTo(DEFAULT_MAX_TOTAL_CONNECTIONS)));

        assertThat(clientConnectionProperties.getSyncClientMaxConnectionsPerRoute(),
                is(equalTo(DEFAULT_MAX_CONNECTIONS_PER_ROUTE)));

        assertThat(clientConnectionProperties.getSyncClientReadTimeoutMilliseconds(),
                is(equalTo(DEFAULT_READ_TIMEOUT_MILLISECONDS)));

        assertThat(clientConnectionProperties.getAsyncClientMaxTotalConnections(),
                is(equalTo(DEFAULT_MAX_TOTAL_CONNECTIONS)));

        assertThat(clientConnectionProperties.getAsyncClientMaxConnectionsPerRoute(),
                is(equalTo(DEFAULT_MAX_CONNECTIONS_PER_ROUTE)));

        assertThat(clientConnectionProperties.getAsyncClientReadTimeoutMilliseconds(),
                is(equalTo(DEFAULT_READ_TIMEOUT_MILLISECONDS)));
    }
}
