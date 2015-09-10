package com.alittlejacket.configuration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CornerStoreAccessTokenConfiguration.class)
public class CornerStoreAccessTokenConfigurationTest {

    @Autowired
    private CornerStoreAccessToken cornerStoreAccessToken;

    @Test
    public void accessToken_Should_HaveNonEmptyValue_When_ValueIsSetInPropertyFile() {
        assertThat(cornerStoreAccessToken, notNullValue());
        assertThat(cornerStoreAccessToken.getToken(), is(notNullValue()));
    }
}