package com.demo.server2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.server2.dto.User;

@RestController
@RequestMapping("/api/server")
public class APIController {

	
	
	@GetMapping("/hello/{userId}/name/{username}")
	public User serverTest(@PathVariable String userId, @PathVariable String username) {
		
		System.out.println(userId + " >>>>>>>>>>>>>><<<<<<<<< " + username);
		User user = new User();
		user.setAge(userId);
		user.setName(username);
//		user.setName("현식이");
//		user.setAge("17");
		
		return user;
	}
	
	
	
}
