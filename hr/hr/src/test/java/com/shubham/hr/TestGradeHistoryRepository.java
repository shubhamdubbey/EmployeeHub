package com.shubham.hr;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import com.shubham.hr.entity.Grades;
import com.shubham.hr.entity.GradesHistory;
import com.shubham.hr.entity.Users;
import com.shubham.hr.repository.GradesHistoryRepository;
import com.shubham.hr.repository.GradesRepository;
import com.shubham.hr.repository.UsersRepository;
import com.shubham.hr.utility.Roles;
@DataJpaTest
@ContextConfiguration(classes = HrApplication.class)
class TestGradeHistoryRepository {
	@Autowired
	private GradesHistoryRepository gradeHistoryRepository;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private GradesRepository gradesRepository;
	@Test
	public void testFindAllPositive() {
		GradesHistory g=new GradesHistory();
		g.setId(1001);
		g.setAssignedon(LocalDate.now());
		
		Users u=new Users();
		u.setEmployeeId(1001);
		u.setFirstName("shubham");
		u.setLastName("dubey");
		u.setPhoneNumber("9755033217");
		u.setRoles(Roles.TRAVELDESKEXC);
		u.setEmailAddress("shubham@gmail.com");
		
		Grades gd = new Grades();
		gd.setIdentification(1);
		gd.setFullName("Shubham");
		g.setGrades(gd);
		u.setGrades(gd);
		g.setUsers(u);
		usersRepository.save(u);
		
		gradesRepository.save(gd);
		
		gradeHistoryRepository.save(g);
		
		Iterable<GradesHistory> it=gradeHistoryRepository.findAll();
		assertTrue(it.iterator().hasNext());
	}
	@Test
	public void testFindAllNegative() {
		Iterable<GradesHistory> it=gradeHistoryRepository.findAll();
		assertTrue(it.iterator().hasNext());
	}
	
	
	@Test
	public void testFindByIdPositive() {
		GradesHistory g=new GradesHistory();
		g.setId(1001);
		g.setAssignedon(LocalDate.now());
		
		Users u=new Users();
		u.setEmployeeId(1001);
		u.setFirstName("shubham");
		u.setLastName("dubey");
		u.setPhoneNumber("9755033217");
		u.setRoles(Roles.HR);
		u.setEmailAddress("shubham@gmail.com");

		Grades gd = new Grades();
		gd.setIdentification(1);
		gd.setFullName("Shubham");
		u.setGrades(gd);
		g.setGrades(gd);
		g.setUsers(u);
		usersRepository.save(u);
		
		gradesRepository.save(gd);
		
		gradeHistoryRepository.save(g);
				
		Optional<GradesHistory> grade=gradeHistoryRepository.findById(1001);
		assertTrue(!grade.isPresent());
	}
	
	@Test
	public void testFindByIdNegative() {
		Optional<GradesHistory> grade=gradeHistoryRepository.findById(1001);
		assertTrue(!grade.isPresent());
	}
	
	@Test
	public void testSavePositive() {
		GradesHistory g=new GradesHistory();
		g.setId(1001);
		g.setAssignedon(LocalDate.now());
		Users u=new Users();
		u.setEmployeeId(1001);
		u.setFirstName("shubham");
		u.setLastName("dubey");
		u.setPhoneNumber("9755033217");
		u.setRoles(Roles.HR);
		u.setEmailAddress("shubham@gmail.com");
		Grades gd = new Grades();
		gd.setIdentification(1);
		gd.setFullName("Shubham");
		g.setGrades(gd);
		u.setGrades(gd);
		gradesRepository.save(gd);
		g.setUsers(u);
		usersRepository.save(u);
		gradeHistoryRepository.save(g);
		Optional<GradesHistory> grade=gradeHistoryRepository.findById(1001);
		assertTrue(!grade.isPresent());
	}
	@Test
	public void testDeletePositive() {
		GradesHistory g=new GradesHistory();
		g.setId(1001);
		g.setAssignedon(LocalDate.now());
		Users u=new Users();
		u.setEmployeeId(1001);
		u.setFirstName("shubham");
		u.setLastName("dubey");
		u.setPhoneNumber("9755033217");
		u.setRoles(Roles.HR);
		u.setEmailAddress("shubham@gmail.com");

		Grades gd = new Grades();
		gd.setIdentification(1);
		gd.setFullName("Shubham");
		g.setGrades(gd);
		u.setGrades(gd);
		g.setUsers(u);
		usersRepository.save(u);
		gradesRepository.save(gd);
		gradeHistoryRepository.delete(g);
		Optional<GradesHistory> grade=gradeHistoryRepository.findById(1001);
		assertTrue(!grade.isPresent());
	}

}