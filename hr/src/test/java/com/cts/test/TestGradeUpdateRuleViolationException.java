package com.cts.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.cts.hr.utility.GradeUpdateRuleViolationException;

public class TestGradeUpdateRuleViolationException {

	@Test
	public void testGradeUpdateRuleViolationException() throws GradeUpdateRuleViolationException{
		try {
			throw new GradeUpdateRuleViolationException("exception thrown");
		} catch(GradeUpdateRuleViolationException e) {
			assertTrue(true);
			assertNotNull(e);
		}
		
		}
}
