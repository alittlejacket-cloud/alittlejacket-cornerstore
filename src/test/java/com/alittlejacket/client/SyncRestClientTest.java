package com.alittlejacket.client;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.OK;

import com.alittlejacket.configuration.CornerStoreAccessToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RunWith(MockitoJUnitRunner.class)
public class SyncRestClientTest {

    @InjectMocks
    private SyncRestClient client;

    @Mock
    private RestTemplate syncRestTemplate;

    @Mock
    private CornerStoreAccessToken accessToken;

    @Test(expected = IllegalArgumentException.class)
    public void post_Should_ThrowIllegalArgumentException_When_UrlIsNull() {
        client.post(null, new Object(), String.class, new HashMap<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void post_Should_ThrowIllegalArgumentException_When_UrlIsEmpty() {
        client.post("", new Object(), String.class, new HashMap<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void post_Should_ThrowIllegalArgumentException_When_RequestIsNull() {
        client.post("url", null, String.class, new HashMap<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void post_Should_ThrowIllegalArgumentException_When_ResponseTypeIsNull() {
        client.post("url", new Object(), null, new HashMap<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void post_Should_ThrowIllegalArgumentException_When_UriVariablesAreNull() {
        client.post("url", new Object(), String.class, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void post_Should_ThrowIllegalArgumentException_When_UriVariablesAreEmpty() {
        client.post("url", new Object(), String.class, new HashMap<>());
    }

    //TODO - test for post

    @Test(expected = IllegalArgumentException.class)
    public void get_WithoutUriVariables_Should_ThrowIllegalArgumentException_When_UrlIsNull() {
        client.get(null, String.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void get_WithoutUriVariables_Should_ThrowIllegalArgumentException_When_UrlIsEmpty() {
        client.get("", String.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void get_WithoutUriVariables_Should_ThrowIllegalArgumentException_When_UrlIsEmptyWithWhiteSpace() {
        client.get("   ", String.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void get_WithoutUriVariables_Should_ThrowNullPointerException_When_ResponseTypeIsNull() {
        client.get("url", null);
    }

    @Test
    public void get_WithoutUriVariables_Should_ReturnValidObject_When_AllParametersAreSet() {
        String url = "url";
        HttpMethod method = GET;
        String responseBody = "hallo";

        when(syncRestTemplate.exchange(eq(url), eq(method), anyObject(), eq(String.class)))
            .thenReturn(new ResponseEntity<>(responseBody, OK));

        String request = client.get(url, String.class);
        assertThat(request, is(equalTo(responseBody)));

        verify(syncRestTemplate, times(1)).exchange(eq(url), eq(method), anyObject(), eq(String.class));
    }
}
