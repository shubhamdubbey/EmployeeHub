package com.shubham.hr.globalexceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// here we are using spring AOP
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> exceptionHandler(Exception e) {
		ResponseEntity<String> errorResponse = new ResponseEntity<String>("Error", HttpStatus.BAD_REQUEST);
		return errorResponse;
	}
	
}
