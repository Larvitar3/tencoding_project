package com.demo.client.service;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.demo.client.dto.UserResponse;

@Service
public class RestTemplateService {

	// hello 만든 후 다른서버에 접근해서 결과를 받는다.
	public UserResponse hello() {
		
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8080")
				.path("/api/server/hello/{userId}/name/{username}")
				.encode()
				.build()
				.expand("춘식", "13")
				.toUri();
		
		System.out.println("주소 확인 : " + uri.toString());
		
		RestTemplate restTemplate = new RestTemplate();
		
//		String result = restTemplate.getForObject(uri, String.class);
		ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);
		System.out.println(result.getStatusCode());
		System.out.println(result.getBody());
		return result.getBody();
	}
	
}
