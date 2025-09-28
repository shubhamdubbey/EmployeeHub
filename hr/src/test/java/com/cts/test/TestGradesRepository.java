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
import com.cts.hr.repository.GradesRepository;
@DataJpaTest
@ContextConfiguration(classes = HrApplication.class)
class TestGradesRepository {
	@Autowired
	private GradesRepository gradeRepository;
	@Autowired
	private TestEntityManager entityManager;
	@Test
	public void testFindAllPositive() {
		Grades g=new Grades();
		g.setIdentification(1001);
		g.setFullName("Shubham");
		
		List<Users> lisUsers = new ArrayList<>();
		lisUsers.add(new Users());
		g.setUsers(lisUsers);
		
		List<GradesHistory> gradesHistoryList = new ArrayList<>();
		gradesHistoryList.add(new GradesHistory());
		g.setGradeshistory(gradesHistoryList);
		
		entityManager.persist(g);		
		Iterable<Grades> it=gradeRepository.findAll();
		assertTrue(it.iterator().hasNext());
	}
	@Test
	public void testFindAllNegative() {
		Iterable<Grades> it=gradeRepository.findAll();
		assertTrue(it.iterator().hasNext());
	}
	
	
	@Test
	public void testFindByIdPositive() {
		Grades g=new Grades();
		g.setIdentification(1001);
		g.setFullName("Dubey");
		List<Users> lisUsers = new ArrayList<>();
		lisUsers.add(new Users());
		g.setUsers(lisUsers);
		
		List<GradesHistory> gradesHistoryList = new ArrayList<>();
		gradesHistoryList.add(new GradesHistory());
		g.setGradeshistory(gradesHistoryList);
		entityManager.persist(g);
		Optional<Grades> grade=gradeRepository.findById(1001);
		assertTrue(grade.isPresent());
	}
	
	@Test
	public void testFindByIdNegative() {
		Optional<Grades> grade=gradeRepository.findById(1001);
		assertTrue(!grade.isPresent());
	}
	
	@Test
	public void testSavePositive() {
		Grades g=new Grades();
		g.setIdentification(1001);
		g.setFullName("Dubey");
		entityManager.persist(g);
		List<Users> lisUsers = new ArrayList<>();
		lisUsers.add(new Users());
		g.setUsers(lisUsers);
		
		List<GradesHistory> gradesHistoryList = new ArrayList<>();
		gradesHistoryList.add(new GradesHistory());
		g.setGradeshistory(gradesHistoryList);
		Optional<Grades> grade=gradeRepository.findById(1001);
		assertTrue(grade.isPresent());
	}
	@Test
	public void testDeletePositive() {
		Grades g=new Grades();
		g.setIdentification(1001);
		g.setFullName("Dubey");
		entityManager.persist(g);
		gradeRepository.delete(g);
		List<Users> lisUsers = new ArrayList<>();
		lisUsers.add(new Users());
		g.setUsers(lisUsers);
		
		List<GradesHistory> gradesHistoryList = new ArrayList<>();
		gradesHistoryList.add(new GradesHistory());
		g.setGradeshistory(gradesHistoryList);
		Optional<Grades> grade=gradeRepository.findById(1001);
		assertTrue(!grade.isPresent());
	}

}