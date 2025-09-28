package com.shubham.hr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.shubham.hr.model.GradesDTO;

public class TestGradesDto {
	
	@Test
	public void testGradesDto() {
		GradesDTO gradesDto = new GradesDTO();
		gradesDto.setFullName("Grade 1");
		gradesDto.setIdentification(1);
		
		assertEquals("Grade 1", gradesDto.getFullName());
		assertEquals(1, gradesDto.getIdentification());
	}
	
}
