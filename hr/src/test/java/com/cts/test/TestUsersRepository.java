package com.cts.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import com.cts.hr.HrApplication;
import com.cts.hr.entity.Grades;
import com.cts.hr.entity.GradesHistory;
import com.cts.hr.entity.Users;
import com.cts.hr.repository.UsersRepository;
import com.cts.hr.utility.Roles;
@DataJpaTest
@ContextConfiguration(classes = HrApplication.class)
class TestUsersRepository {
	
	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testFindAllPositive() {
		Users u=new Users();
		u.setEmployeeId(1001);
		u.setFirstName("shubham");
		u.setLastName("dubey");
		u.setPhoneNumber("9755033217");
		u.setRoles(Roles.HR);
		u.setEmailAddress("shubham@gmail.com");
		
		Grades g = new Grades();
		g.setFullName("good");
		g.setIdentification(1);
		u.setGrades(g);
		
		List<GradesHistory> gradesHistoryList = new ArrayList<>();
		gradesHistoryList.add(new GradesHistory());
		
		u.setGradesHisotry(gradesHistoryList);
		entityManager.persist(u);
		Iterable<Users> it=userRepository.findAll();
		assertTrue(it.iterator().hasNext());
	}
	@Test
	public void testFindAllNegative() {
		Iterable<Users> it=userRepository.findAll();
		assertTrue(it.iterator().hasNext());
	}
	
	
	@Test
	public void testFindByIdPositive() {
		Users u=new Users();
		u.setEmployeeId(1001);
		u.setFirstName("shubham");
		u.setLastName("dubey");
		u.setPhoneNumber("9755033217");
		u.setRoles(Roles.TRAVELDESKEXC);
		u.setEmailAddress("shubham@gmail.com");
		
		Grades g = new Grades();
		g.setFullName("good");
		g.setIdentification(1);
		
		entityManager.persist(g);
		
		u.setGrades(g);
		
		List<GradesHistory> gradesHistoryList = new ArrayList<>();
		gradesHistoryList.add(new GradesHistory());
		
		u.setGradesHisotry(gradesHistoryList);
		
		entityManager.persist(u);
		Optional<Users> user=userRepository.findById(1001);
		assertTrue(user.isPresent());
	}
	
	@Test
	public void testFindByIdNegative() {
		Optional<Users> grade=userRepository.findById(1001);
		assertTrue(!grade.isPresent());
	}
	
	@Test
	public void testSavePositive() {
		Users u=new Users();
		u.setEmployeeId(1001);
		u.setFirstName("shubham");
		u.setLastName("dubey");
		u.setPhoneNumber("9755033217");
		u.setRoles(Roles.TRAVELDESKEXC);
		u.setEmailAddress("shubham@gmail.com");
		
		Grades g = new Grades();
		g.setFullName("good");
		g.setIdentification(1);
		entityManager.persist(g);
		u.setGrades(g);
		
		List<GradesHistory> gradesHistoryList = new ArrayList<>();
		gradesHistoryList.add(new GradesHistory());
		
		u.setGradesHisotry(gradesHistoryList);
		
		entityManager.persist(u);
		Optional<Users> users=userRepository.findById(1001);
		assertTrue(users.isPresent());
	}
	@Test
	public void testDeletePositive() {
		Users u =new Users();
		u.setEmployeeId(1001);
		u.setFirstName("shubham");
		u.setLastName("dubey");
		u.setPhoneNumber("9755033217");
		u.setRoles(Roles.TRAVELDESKEXC);
		u.setEmailAddress("shubham@gmail.com");
		
		Grades g = new Grades();
		g.setFullName("good");
		g.setIdentification(10);
		
		u.setGrades(g);
		
		List<GradesHistory> gradesHistoryList = new ArrayList<>();
		gradesHistoryList.add(new GradesHistory());
		
		u.setGradesHisotry(gradesHistoryList);
		
		entityManager.persist(u);
		userRepository.delete(u);
		Optional<Users> grade=userRepository.findById(1001);
		assertTrue(grade.isEmpty());
	}

}
