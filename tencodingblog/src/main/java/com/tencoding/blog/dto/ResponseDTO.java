package com.tencoding.blog.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 같은 변수 이름으로 데이터 타입을 다르게 사용해야 할 때
// 제네릭을 사용한다.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {
	
	HttpStatus status;
	
	T body;
	
	
	
}
