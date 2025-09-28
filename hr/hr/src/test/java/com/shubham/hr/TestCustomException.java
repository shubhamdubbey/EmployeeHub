package com.shubham.hr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.shubham.hr.utility.GradeUpdateRuleViolationException;

public class TestCustomException {

	@Test
	public void testTestException() throws GradeUpdateRuleViolationException{
		try {
			throw new GradeUpdateRuleViolationException("exception thrown");
		} catch(GradeUpdateRuleViolationException e) {
			assertTrue(true);
			assertNotNull(e);
		}
		
		}
}
