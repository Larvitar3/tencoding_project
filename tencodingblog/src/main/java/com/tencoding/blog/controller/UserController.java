package com.tencoding.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

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
	
	@GetMapping("/user/update_form")
	public String updateForm() {
		
		return "user/update_form";
	}
	
	// 데이터 리턴
	@GetMapping("/auth/kakao/callback")
	@ResponseBody
	public String kakaoCallback(@RequestParam String code) {
		
		RestTemplate rt = new RestTemplate();
		
		// 헤더 만들기
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// 바디 만들기
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "188c73400922baf0db1a9d0b3dc58279");
		params.add("redirect_uri", "http://localhost:9090/auth/kakao/callback");
		params.add("code", code);
		
		HttpEntity<MultiValueMap<String, String>> 
		reqKakaoToken = new HttpEntity<>(params, headers);
		
		// 헤더를 변조해서 실행시키는 메서드는 RestTemplate exchange() 이다
		// https://kauth.kakao.com/oauth/token
		ResponseEntity<String> res = rt.exchange("https://kauth.kakao.com/oauth/token", 
				HttpMethod.POST, reqKakaoToken, String.class);
		
		
		return "kakao code \n " + res;
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
