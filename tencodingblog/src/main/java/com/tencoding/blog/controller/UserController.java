package com.tencoding.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.tencoding.blog.dto.KakaoAccount;
import com.tencoding.blog.dto.KakaoProfile;
import com.tencoding.blog.dto.OAuthToken;
import com.tencoding.blog.dto.User;
import com.tencoding.blog.service.UserService;

@Controller
//@RequestMapping("/user")
public class UserController {
	
	// yml 초기 파라미터를 가져온다.
	@Value("${tenco.key}")
	private String tencokey;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
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
	
	@GetMapping("/auth/kakao/callback")
//	@ResponseBody 데이터 리턴 : Test가 끝나면 주석 해준다. (화면을 돌려받아야하기때문)
	public String kakaoCallback(@RequestParam String code) {
		
		RestTemplate rt = new RestTemplate();
		
		// 헤더 만들기
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
		
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
		ResponseEntity<OAuthToken> res = rt.exchange("https://kauth.kakao.com/oauth/token", 
				HttpMethod.POST, reqKakaoToken, OAuthToken.class);
		
		
		OAuthToken authToken = res.getBody();
		
		// 헤더 2 만들기
		RestTemplate rt2 = new RestTemplate();
		
		HttpHeaders headers2 = new HttpHeaders();
		
		// JWT - 주의 Bearer 다음엔 무조건 뒤에 띄어주기
		headers2.add("Authorization", "Bearer " + authToken.accessToken);
		headers2.add("Content-Type", "application/x-www-form-urlencoded;");
		
		// 바디 2 만들기
		HttpEntity<MultiValueMap<String, String>> kakaoDataReq = new HttpEntity<>(headers2);
		
		// 파씽 받을 DTO 만들기
		ResponseEntity<KakaoProfile> kakaoDataRes = rt2.exchange
				("https://kapi.kakao.com/v2/user/me", HttpMethod.POST, kakaoDataReq, KakaoProfile.class);
		
		
		// User obj -> 판별
		// 1. 회원가입 -- 자동으로 DB에 저장
		// 2. 소셜 로그인 사용자가 우리 사이트에 이미 회원가입 된 사용자라면 DB에 저장하지않는다.
		
		KakaoAccount account = kakaoDataRes.getBody().kakaoAccount;
		
		User kakaoUser = User
				.builder()
				.username(account.profile.nickname + " _ " + 
				kakaoDataRes.getBody().id)
				.email(account.email)
				.password(tencokey)
				.oauth("kakao")
				.build();
		
		// user id가 없기때문에 username으로 검색하는 기능을 만들어주어야함.
		
		User originUser = userService.searchUserName(kakaoUser.getUsername());
		
		if(originUser.getUsername() == null) {
			userService.saveUser(kakaoUser);
		}
		
		// 신규 유저가 가입이든, 기존에 한번 가입 했던 유저도 무조건 소셜 로그인이든, 
		// 세션 생성해주어야한다		
		// 자동 로그인 처리 => 시큐리티 세션에다가 강제 저장

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken
						(kakaoUser.getUsername(), tencokey));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		return "redirect:/";
	}
	
	// 시큐리티 전에 컨트롤러에서 먼저 낚아 채서 여기로 옴!
	// 기존의 로그아웃 처리를 하지 않고, 우리가 재정의한 로그아웃 처리로 넘어옴
	@GetMapping("/m-logout")
	public String logout(HttpServletRequest req, HttpServletResponse res) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			new SecurityContextLogoutHandler().logout(req, res, authentication);
		}
		return "redirect:/";
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
