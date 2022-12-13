package com.tencoding.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/user")
public class UserController {
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/auth/login_form")
	public String loginForm() {
		
		return "user/login_form";
	}
	
	@GetMapping("/auth/join_form")
	public String joinForm() {
		
		return "user/join_form";
	}
	
//	@GetMapping("/logout") // 따로 정리
//	public String logout(){
//		
//		HttpSession httpSession = session;
//		httpSession.invalidate(); // 세션 삭제 : 로그아웃 처리
//		
//		return "/index";
//	}
	
	
}
