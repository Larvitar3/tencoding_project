package com.tencoding.blog.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.dto.User;
import com.tencoding.blog.model.RoleType;
import com.tencoding.blog.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/dummy")
public class DummyControllerTest {

	@Autowired // 순환 참조 조심.
	private UserRepository userRepository;

	@GetMapping("/user")
	public List<User> list() {
		return userRepository.findAll();
	}

	public Page<User> pageList(@PageableDefault(size = 5, 
			sort = "id", direction = Direction.DESC) Pageable pageable){
		
		Page<User> userPage = userRepository.findAll(pageable);
		
		List<User> users = userRepository.findAll(pageable).getContent();
		
		return userPage;
		
	}

	// 회원 조회
	@GetMapping("/user/{id}")
	public User detail(@PathVariable int id) {

		// orElseGet : 지정한 데이터 타입이 없을 경우 대체할 데이터를 입력하여 넣을 수 있다.
//		User user = userRepository.findById(id).orElseGet(()->{
//			return new User();
//		});

		// orElseThrow : 지정한 데이터의 결과값이 없을 경우 예외처리를 할 수 있다.
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("『 " + id + " 』 유저는 없습니다.");
		});

		return user;
	}

	// 회원 가입
	// http://localhost:9090/blog/dummy/signup
	@PostMapping("/signup")
	public String signUp(@RequestBody User user) {

		log.info(">>>>User : {}", user);

		user.setRole(RoleType.USER);
		userRepository.save(user);

		return "회원가입이 완료되었습니다";

	}
	
	// 회원 수정
	@Transactional // 작업의 단위. 함수 종료시에 commit이 된다
	// 함수 종료시에 더티체킹을 하고 수정된 데이터가 있다면 commit이 된다.
	@PutMapping("/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User reqUser) {
		
		log.info(">> id : {}, >>>password {}, email >>>> {}",
				id, reqUser.getPassword(), reqUser.getEmail());
		
		// 1. 지정 사용자가 있는지 확인.
		// 2_1. 사용자가 있다면 넘겨받은 데이터로 수정 후 저장
		// 2_2. 사용자가 없다면 클라이언트에게 잘못된 요청임을 알림
		
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("수정에 실패 하였습니다.");
		});
		
		// 수정할 데이터의 값 받아오기
		user.setPassword(reqUser.getPassword());
		user.setEmail(reqUser.getEmail());
		
		// 수정한 데이터의 값을 저장하기
//		userRepository.save(user);
		
		return null;
	}

}


















