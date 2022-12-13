package com.tencoding.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration //IOC 관리
@EnableWebSecurity // 시큐리티 필토로 등록이 된다 * 필터 커스텀
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한 및 인증처리를 미리 체크
public class securityConfig extends WebSecurityConfigurerAdapter{

	// /auth/login_form.auth/join_form => /auth/**

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable(); // 개발 완료 전 테스트 시 사용. 실 서비스에는 사용 X 
		
		http
		.authorizeHttpRequests()
			.antMatchers("/auth/**","/","/js/**", "/css/**") // 허용하는 주소 입력
			.permitAll()
			.anyRequest()
			.authenticated()
		.and()
			.formLogin()
			.loginPage("/auth/login_form");
	}
	
}
