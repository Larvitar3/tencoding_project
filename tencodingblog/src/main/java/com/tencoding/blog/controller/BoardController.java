package com.tencoding.blog.controller;

import java.security.Principal;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	// 로그인이 인증되면 컨트롤러에서 어떻게 세션을 찾는가
	
	@GetMapping({"","/"})
	public String index(@AuthenticationPrincipal Principal principal) {
		
		if(principal != null) {
			
		}
		
		return "index";
	}
	
	@GetMapping("/board/save_form")
	public String saveForm() {
		
		
		
		return "/board/save_form";
	}
	
}
