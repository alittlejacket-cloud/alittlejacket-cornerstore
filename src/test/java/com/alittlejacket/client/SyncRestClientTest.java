package com.alittlejacket.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class SyncRestClientTest {

    @InjectMocks
    private SyncRestClient client;

    @Mock
    private RestTemplate syncRestTemplate;

    @Test(expected = IllegalArgumentException.class)
    public void get_WithoutUriVariables_Should_ThrowIllegalArgumentException_When_UrlIsNull() {
        client.get(null, String.class);
    }
}
