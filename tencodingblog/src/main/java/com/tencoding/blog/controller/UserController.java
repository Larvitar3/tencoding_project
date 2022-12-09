package com.tencoding.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/login-form")
	public String loginForm() {
		
		return "user/login-form";
	}
	
	@GetMapping("/join-form")
	public String joinForm() {
		
		return "user/join-form";
	}
	
}
