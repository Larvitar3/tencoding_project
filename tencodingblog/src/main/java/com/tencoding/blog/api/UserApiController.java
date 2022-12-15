package com.tencoding.blog.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.dto.ResponseDTO;
import com.tencoding.blog.dto.User;
import com.tencoding.blog.service.UserService;

@RestController
public class UserApiController {

	// DI
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/auth/joinProc")
	public ResponseDTO<Integer> save(@RequestBody User user) {
		System.out.println("userController : " + user);
		
		int result = userService.saveUser(user);
		
		return new ResponseDTO<Integer>(HttpStatus.OK, result);
		// 자바 Object ==> Json 형식
	}
	
	@PutMapping("/api/user")
	public ResponseDTO<?> update(@RequestBody User user){
		
		userService.updateUser(user);
		
		
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),
						user.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseDTO<Integer> (HttpStatus.OK, 1);
	}
	
	

//	@PostMapping("/user/login")
//	public ResponseDTO<?> login(@RequestBody User user) {
//		System.out.println("login 호출 됨 : " + user);
//		// principal : 접근 주체
//		User principal = userService.login(user);
//		System.out.println("principal : " + principal);
//		
//		if(principal != null) {
//			session.setAttribute("principal", principal);
//		}
//		return new ResponseDTO<User>(HttpStatus.OK, principal);
//	}
	
	@PostMapping("/user/logout")
	public String logout(){
		
		HttpSession httpSession = session;
		httpSession.invalidate(); // 세션 삭제 : 로그아웃 처리
		
		return "";
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
