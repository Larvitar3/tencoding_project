package com.tencoding.blog.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tencoding.blog.dto.User;

// 시큐리티가 로그인 요청을 가로채서
// 로그인을 처리하고(DB) 완료되면 UserDetails 타입의
// 오브젝트를 시큐리티의 고유한 세션 저장소에 저장을 해준다.
// (우리가 새롭게 정의한 Object로 처리할 예정)

public class PrincipalDetail implements UserDetails {

	private User user;

	public PrincipalDetail(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 계정 권한을 반환 처리

		Collection<GrantedAuthority> collection = new ArrayList<>();

//		collection.add(new GrantedAuthority() {
//			@Override
//			public String getAuthority() {
//				// ROLE_ : 스프링 시큐리티 사용 시 무조건 넣어야 한다.
//				return "ROLE_" + user.getRole();
//			}
//		});

		collection.add(() -> {
			return "ROLE_" + user.getRole();
		});

		return collection;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// 계정이 만료되지 않았는지 리턴 false = 계정 만료, true = 계정 만료 X
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// 계정이 잠겼는지 리턴 false = 잠김, true = 안잠김
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// 비밀번호 만료 여부 false = 만료, true = 만료되지않음
		return true;
	}

	@Override
	public boolean isEnabled() {
		// 계정 활성화 여부 확인 false = 비활성화, true = 활성화
		return true;
	}

}
