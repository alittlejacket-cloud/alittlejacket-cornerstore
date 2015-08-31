package com.alittlejacket.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ClientConnectionConfiguration.class, RestConfiguration.class})
public class RestConfigurationTest {

    @Autowired
    private RestTemplate syncRestTemplate;
    
    @Autowired
    private AsyncRestTemplate asyncRestTemplate;
    
    @Test
    public void test() {
       
    }
}
