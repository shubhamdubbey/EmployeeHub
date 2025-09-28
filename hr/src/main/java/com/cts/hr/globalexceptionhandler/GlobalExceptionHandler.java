package com.cts.hr.globalexceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> exceptionHandler(Exception e) {
		ResponseEntity<String> errorResponse = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		return errorResponse;
	}
	
}
