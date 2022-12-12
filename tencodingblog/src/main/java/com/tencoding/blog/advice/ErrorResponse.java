package com.tencoding.blog.advice;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
	
	private String statusCode;
	private String requestURI;
	private int code;
	private String msg;
	private String resultCode;
	private List<CustomError> errorList;

}
