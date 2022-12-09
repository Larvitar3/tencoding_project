package com.tencoding.blog.dto;

import java.util.List;

import lombok.Data;

@Data
public class ErrorResponse {
	
	private String statusCode;
	private String requestURI;
	private String code;
	private String msg;
	private String resultCode;
	private List<CustomError> errorList;

}
