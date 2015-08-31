package com.alittlejacket.configuration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ClientConnectionConfiguration.class)
public class ClientConnectionPropertiesTest {

    @Autowired
    private ClientConnectionProperties clientConnectionProperties;

    @Test
    public void clientConnectionProperties_Should_HaveValuesFromPropertyFile_When_ValuesAreSetInPropertyFile() {
        assertThat(clientConnectionProperties.getSyncClientMaxTotalConnections(), is(greaterThan(0)));
        assertThat(clientConnectionProperties.getSyncClientMaxConnectionsPerRoute(), is(greaterThan(0)));
        assertThat(clientConnectionProperties.getSyncClientReadTimeoutMilliseconds(), is(greaterThan(0)));

        assertThat(clientConnectionProperties.getAsyncClientMaxTotalConnections(), is(greaterThan(0)));
        assertThat(clientConnectionProperties.getAsyncClientMaxConnectionsPerRoute(), is(greaterThan(0)));
        assertThat(clientConnectionProperties.getAsyncClientReadTimeoutMilliseconds(), is(greaterThan(0)));
    }
}
