package com.tencoding.blog.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.tencoding.blog.model.RoleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity

@DynamicInsert // Default를 사용하면 넣어주어야 작동한다.
public class User {
	
	@Id // 프라이머키 지정
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB넘버링
	private int id;
	
	@Column(nullable = false, unique = true) // 제약 조건
	private String username;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false)
	private String email;
	
//	@ColumnDefault("'user'") // Default 설정 ★ ' 홑따옴표를 넣어 문자열이란 걸 인식시켜줘야함.
	// Enum으로 변경해서 사용 X ▲
	
	@Enumerated(EnumType.STRING) // DB에게 문자열 임을 알려주는 것
	private RoleType role; // 일반, 관리자, 매니저 계정 구분
	// user : 일반 계정, admin : 관리자, manager : 매니저
	
	private String oauth; // kakao, google 로그인
	
	
	@CreationTimestamp //시간 자동 입력
	private Timestamp createDate;

}
