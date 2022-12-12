package com.tencoding.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.blog.dto.User;
import com.tencoding.blog.model.RoleType;
import com.tencoding.blog.repository.UserRepository;
 
// 스프링이 컴포넌트 스캔을 통해서 Bean으로 등록해준다 (IOC)
@Service
public class UserService {
	
	/*
	 * 서비스를 만드는 이유
	 * 트랜잭션 관리를 하기 위해
	 * 
	 */
	
	
	@Autowired
	private UserRepository userRepository;

	// 작업단위 - 하나의 기능 + 하나의 기능을 묶어서 단위의 기능을 처리
	// DB 수정 시 롤백 처리 가능
	@Transactional
	public int saveUser(User user) {
		
		try {
			user.setRole(RoleType.USER);
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	
	public User login(User user) {
		// 기본 레퍼지토리에 필요한 함수가 없을 경우 직접 생성한다.
//		userRepository.get
		User userEntity =
		userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//		userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		System.out.println("userEntity : " + userEntity);
		return userEntity;
	}
	
}









