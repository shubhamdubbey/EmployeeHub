package com.shubham.hr;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import com.shubham.hr.entity.Grades;
import com.shubham.hr.repository.GradesRepository;
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
		gradeRepository.save(g);
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
		Optional<Grades> grade=gradeRepository.findById(1001);
		assertTrue(!grade.isPresent());
	}

}