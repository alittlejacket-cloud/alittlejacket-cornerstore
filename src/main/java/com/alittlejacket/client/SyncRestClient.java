package com.alittlejacket.client;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class SyncRestClient implements RestClient {
	
	@Autowired
	private RestTemplate syncRestTemplate;

	@Override
	public <T> T post(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T get(String url, Class<T> responseType, Map<String, ?> uriVariables) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T get(String url, Class<T> responseType) {
		checkArgument(!isNullOrEmpty(url), "Url must not be null or empty.");
		checkNotNull(responseType, "Response type must not be null.");
		return null;
	}

	@Override
	public <T> void put(String url, Object request, Map<String, ?> uriVariables) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String url, Map<String, ?> urlVariables) {
		// TODO Auto-generated method stub

	}

}
