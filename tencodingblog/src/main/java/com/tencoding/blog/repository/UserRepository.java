package com.tencoding.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tencoding.blog.dto.User;

// DAO
// 굳이 bean으로 등록 요청을 하지 않더라도 등록을 시켜줌 JpaRepository
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	
	
}
