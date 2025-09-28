package com.cts.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.cts.hr.entity.Grades;
import com.cts.hr.entity.GradesHistory;
import com.cts.hr.entity.Users;

public class TestGradeHistory {

	@Test
	public void testGradesHistory() {
		GradesHistory gradesHistory = new GradesHistory();
		
		gradesHistory.setAssignedon(LocalDate.now());
		gradesHistory.setId(1);
		
		Grades grades = new Grades();
		grades.setFullName("Grade 1");
		grades.setIdentification(1);
		
		Users users = new Users();
		users.setEmployeeId(100001);
		users.setGrades(grades);
		
		gradesHistory.setGrades(grades);
		gradesHistory.setUsers(users);
		
		assertEquals(LocalDate.now(), gradesHistory.getAssignedon());
		assertEquals(1, gradesHistory.getId());
		assertEquals("Grade 1", gradesHistory.getGrades().getFullName());
		assertEquals(100001, gradesHistory.getUsers().getEmployeeId());
	}
	
}
