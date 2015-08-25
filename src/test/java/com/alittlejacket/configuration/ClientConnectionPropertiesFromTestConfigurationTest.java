package com.alittlejacket.configuration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ClientConnectionConfiguration.class)
public class ClientConnectionPropertiesFromTestConfigurationTest {

    @Autowired
    private ClientConnectionProperties clientConnectionProperties;

    @Test
    public void clientConnectionProperties_Should_HaveValuesFromPropertyFile_When_ValuesAreSetInPropertyFile() {
        assertThat(clientConnectionProperties.getSyncClientMaxTotalConnections(), is(equalTo(200)));
        assertThat(clientConnectionProperties.getSyncClientMaxConnectionsPerRoute(), is(equalTo(20)));
        assertThat(clientConnectionProperties.getSyncClientReadTimeoutMilliseconds(), is(equalTo(60000)));
        
        assertThat(clientConnectionProperties.getAsyncClientMaxTotalConnections(), is(equalTo(200)));
        assertThat(clientConnectionProperties.getAsyncClientMaxConnectionsPerRoute(), is(equalTo(20)));
        assertThat(clientConnectionProperties.getAsyncClientReadTimeoutMilliseconds(), is(equalTo(60000)));
    }
}
