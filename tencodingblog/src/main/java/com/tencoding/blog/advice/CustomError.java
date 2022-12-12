package com.tencoding.blog.advice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomError {

	String field;
	String msg;
	public void setMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	
}
