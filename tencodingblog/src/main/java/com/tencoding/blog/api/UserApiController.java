package com.tencoding.blog.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.advice.ErrorResponse;
import com.tencoding.blog.dto.ResponseDTO;
import com.tencoding.blog.dto.User;
import com.tencoding.blog.service.UserService;

@RestController
@RequestMapping("/api")
public class UserApiController {

	// DI
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession session;
	
	@PostMapping("/user")
	public ResponseDTO<Integer> save(@RequestBody User user) {
		System.out.println("userController : " + user);
		
		int result = userService.saveUser(user);
		
		return new ResponseDTO<Integer>(HttpStatus.OK, result);
		// 자바 Object ==> Json 형식
	}
	
	@PostMapping("/user/login")
	public ResponseDTO<?> login(@RequestBody User user) {
		System.out.println("login 호출 됨 : " + user);
		// principal : 접근 주체
		User principal = userService.login(user);
		System.out.println("principal : " + principal);
		
		if(principal != null) {
			session.setAttribute("principal", principal);
		}
		return new ResponseDTO<User>(HttpStatus.OK, principal);
	}
	
	@PostMapping("/user/logout")
	public String logout(){
		
		HttpSession httpSession = session;
		httpSession.invalidate(); // 세션 삭제 : 로그아웃 처리
		
		return "";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
