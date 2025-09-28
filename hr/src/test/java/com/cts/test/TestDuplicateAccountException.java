package com.cts.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.cts.hr.utility.DuplicateAccountException;

public class TestDuplicateAccountException {

	@Test
	public void testDuplicateAccountException() throws DuplicateAccountException{
		try {
			throw new DuplicateAccountException("exception thrown");
		} catch(DuplicateAccountException e) {
			assertTrue(true);
			assertNotNull(e);
		}
		
		}
}
