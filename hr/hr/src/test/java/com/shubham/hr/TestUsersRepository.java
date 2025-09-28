package com.shubham.hr;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import com.shubham.hr.entity.Grades;
import com.shubham.hr.entity.Users;
import com.shubham.hr.repository.UsersRepository;
import com.shubham.hr.utility.Roles;
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
		u.setRoles(Roles.TRAVELDESKEXC);
		u.setEmailAddress("shubham@gmail.com");
		
		Grades g = new Grades();
		g.setFullName("good");
		g.setIdentification(1);
		u.setGrades(g);
		entityManager.persist(u);
		userRepository.save(u);
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
		g.setIdentification(10);
		
		u.setGrades(g);
		
		entityManager.persist(u);
		Optional<Users> grade=userRepository.findById(1001);
		assertTrue(grade.isPresent());
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
		
		u.setGrades(g);
		
		userRepository.save(u);
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
		
		entityManager.persist(u);
		userRepository.delete(u);
		Optional<Users> grade=userRepository.findById(1001);
		assertTrue(!grade.isPresent());
	}

}
