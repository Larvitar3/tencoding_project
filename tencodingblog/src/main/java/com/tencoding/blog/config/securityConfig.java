package com.tencoding.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.tencoding.blog.auth.PrincipalDetailService;

@Configuration //IOC 관리
@EnableWebSecurity // 시큐리티 필토로 등록이 된다 * 필터 커스텀
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한 및 인증처리를 미리 체크
public class securityConfig extends WebSecurityConfigurerAdapter{
	
	// IOC 관리하고 싶어서 여기서 선언
	@Bean
	public BCryptPasswordEncoder encoderPWD() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 1. userDetailsService 들어갈 Object 만들어 줘야 한다
		// 2. passwordEncoder 우리가 사용하는 해시 암호화 함수를 알려줘야한다.
		
		// 커스텀한 객체를 넣어야한다. (1.)
		// passwodrdEncoder 사용한 암호화 함수 (2.)
		auth.userDetailsService(principalDetailService).passwordEncoder(encoderPWD());
	}
	
	

	// /auth/login_form.auth/join_form => /auth/**

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
//		http.csrf().disable(); // 개발 완료 전 테스트 시 사용. 실 서비스에는 사용 X 
		
	http
		.csrf()
			.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		.and()
		.authorizeHttpRequests()
			.antMatchers("/auth/**","/","/js/**", "/css/**","/img/**", "/test/**") // 허용하는 주소 입력
			.permitAll()
			.anyRequest()
			.authenticated()
		.and()
			.formLogin()
			.loginPage("/auth/login_form")
			.loginProcessingUrl("/auth/loginProc")
			.defaultSuccessUrl("/");
		
	}
	
}
