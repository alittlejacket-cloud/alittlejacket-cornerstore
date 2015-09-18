package com.alittlejacket.configuration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ClientConnectionConfiguration.class, RestConfiguration.class})
public class RestConfigurationTest {

    @Autowired
    private RestTemplate syncRestTemplate;

    @Autowired
    private AsyncRestTemplate asyncRestTemplate;

    @Test
    public void syncRestTemplateIsNotNull() {
        assertThat(syncRestTemplate, is(notNullValue()));
        assertThat(syncRestTemplate.getRequestFactory(), is(notNullValue()));
    }

    @Test
    public void asyncRestTemplateIsNotNull() {
        assertThat(asyncRestTemplate, is(notNullValue()));
        assertThat(asyncRestTemplate.getAsyncRequestFactory(), is(notNullValue()));
    }

    @Test
    public void syncRestTemplate_Should_ReturnStringValue_When_MockRestServiceServerIsUsed() {
        String url = "url";
        String responsValue = "hallo";

        MockRestServiceServer mockServer = MockRestServiceServer.createServer(syncRestTemplate);

        mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
            .andRespond(withSuccess(responsValue, MediaType.TEXT_PLAIN));

        String value = syncRestTemplate.getForObject(url, String.class);
        assertThat(value, is(equalTo(responsValue)));

        mockServer.verify();
    }

    @Test
    public void asyncRestTemplate_Should_ReturnStringValue_When_MockRestServiceServerIsUsed() {
        String url = "url";
        String responsValue = "hallo";
        DeferredResult<String> deferredResult = new DeferredResult<>();

        MockRestServiceServer mockServer = MockRestServiceServer.createServer(asyncRestTemplate);

        mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
            .andRespond(withSuccess(responsValue, MediaType.TEXT_PLAIN));


        ListenableFuture<ResponseEntity<String>> futureEntity = asyncRestTemplate
            .exchange(url, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), String.class);

        futureEntity.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
            @Override
            public void onSuccess(ResponseEntity<String> result) {
                deferredResult.setResult(result.getBody());
            }

            @Override
            public void onFailure(Throwable throwable) {
                deferredResult.setErrorResult(throwable.getMessage());
            }
        });


        assertThat(deferredResult.getResult(), is(equalTo(responsValue)));

        mockServer.verify();
    }
}
