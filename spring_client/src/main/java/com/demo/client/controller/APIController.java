package com.demo.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.client.dto.UserResponse;
import com.demo.client.service.RestTemplateService;

@RestController
@RequestMapping("/api/client")
public class APIController {
	
	
	private final RestTemplateService service;
	
	public APIController(RestTemplateService service) {
		this.service = service;
	}
	
	@GetMapping("/get-hello")
	public UserResponse getHello() {
		
		return service.hello();
	}


//	@GetMapping("/get-hello")
//	public String getHello() {
//		
//		return service.hello().toString();
//	}
	
}
