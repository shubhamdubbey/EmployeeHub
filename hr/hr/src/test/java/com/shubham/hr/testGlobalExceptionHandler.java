package com.shubham.hr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.shubham.hr.globalexceptionhandler.GlobalExceptionHandler;
import com.shubham.hr.utility.GradeUpdateRuleViolationException;

public class testGlobalExceptionHandler {

	@Autowired
	GlobalExceptionHandler exceptionHandler; 
	
	@Autowired
	GradeUpdateRuleViolationException exception;
	
	@Test
	public void testGlobalExceptionHandler() {
		ResponseEntity<String> responseEntity = exceptionHandler.exceptionHandler(exception);
		assertEquals("error",responseEntity.getBody());
	}
	
}
