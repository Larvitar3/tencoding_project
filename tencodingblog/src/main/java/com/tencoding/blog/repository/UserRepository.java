package com.tencoding.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tencoding.blog.dto.User;

// DAO
// 굳이 bean으로 등록 요청을 하지 않더라도 등록을 시켜줌 JpaRepository
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	// 없는 함수는 직접 함수를 만들거나 Spring JPA 네이밍 전략
	
	// 첫번째 방법 (네이밍 전략) : 네이밍 규칙에 따라 작성
	//SELECT * FROM user WHERE username = ?1 AND password = ?2
	User findByUsernameAndPassword(String username, String password);
	
	// 두번째 방법 (네이티브쿼리) : ? 뒤에 숫자를 넣으면 해당하는 번호의 값을 넣게된다.
	@Query(value = " SELECT * "
			+ " FROM user "
			+ " where username = ? "
			+ " and password = ? ", nativeQuery = true)
	User login(String username, String password);
}
