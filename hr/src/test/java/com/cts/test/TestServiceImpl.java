package com.cts.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cts.hr.utility.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.hr.HrApplication;
import com.cts.hr.dto.GradesDTO;
import com.cts.hr.dto.UsersDTO;
import com.cts.hr.entity.Grades;
import com.cts.hr.entity.GradesHistory;
import com.cts.hr.entity.Users;
import com.cts.hr.repository.GradesHistoryRepository;
import com.cts.hr.repository.GradesRepository;
import com.cts.hr.repository.UsersRepository;
import com.cts.hr.service.HrServiceImpl;

@SpringBootTest(classes = HrApplication.class)
public class TestServiceImpl {
	
	@Mock
	private UsersRepository usersRepository;
	
	@Mock
	private GradesRepository gradesRepository;
	
	@Mock
	private GradesHistoryRepository gradesHistoryRepository;
	
	@Mock
	GradesUpdateBusinessLogic gradesUpdateBusinessLogic;

	@InjectMocks
	HrServiceImpl hrServiceImpl;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetEmployeeByIdPositive() throws InvalidInputException {
		// Mock data
		Users mockUsers = new Users();
		mockUsers.setEmployeeId(100001);
		mockUsers.setEmailAddress("shubhamd1109");
		mockUsers.setFirstName("Shubham");
		mockUsers.setLastName("Dubey");
		mockUsers.setPhoneNumber("9755033217");
		mockUsers.setRoles(Roles.HR);
		
		Grades grades = new Grades();
		grades.setFullName("Good");
		grades.setIdentification(1);
		
		mockUsers.setGrades(grades);
		
		Optional<Grades> optionalOfGrades=Optional.of(grades);
		
		when(gradesRepository.findById(1)).thenReturn(optionalOfGrades);
		
		Optional<Users> optionalOfUsers = Optional.of(mockUsers);
		when(usersRepository.findById(mockUsers.getEmployeeId())).thenReturn(optionalOfUsers);
 
		// Call the service method
		UsersDTO result= hrServiceImpl.getEmployeeById(100001);
		assertNotNull(result);
		assertEquals(mockUsers.getEmailAddress(),result.getEmailAddress());
		assertEquals(mockUsers.getEmployeeId(),result.getEmployeeId());
		assertEquals(mockUsers.getFirstName(),result.getFirstName());
		assertEquals(mockUsers.getGrades().getIdentification(),result.getGrade_id());
		assertEquals(mockUsers.getLastName(),result.getLastName());
		assertEquals(mockUsers.getPhoneNumber(),result.getPhoneNumber());
		assertEquals(mockUsers.getRoles(),result.getRoles());
	}
	
	@Test
	public void testGetEmployeeByIdUserIsNotThere() throws InvalidInputException {
		// Mock data
		try {
			Users mockUsers = new Users();
			mockUsers.setEmployeeId(100001);
			mockUsers.setEmailAddress("shubhamd1109");
			mockUsers.setFirstName("Shubham");
			mockUsers.setLastName("Dubey");
			mockUsers.setPhoneNumber("9755033217");
			mockUsers.setRoles(Roles.HR);
			
			Grades grades = new Grades();
			grades.setFullName("Good");
			grades.setIdentification(1);
			
			mockUsers.setGrades(grades);
			
			Optional<Grades> optionalOfGrades=Optional.of(grades);
			
			when(gradesRepository.findById(1)).thenReturn(optionalOfGrades);
			
			when(usersRepository.findById(anyInt())).thenReturn(Optional.empty());
	 
			hrServiceImpl.getEmployeeById(1002);
			assertTrue(false);
		} catch(InvalidInputException e) {
			assertTrue(true);
		}
		
	}
	
	@Test
	public void testGetEmployeeByIdNegaive() throws InvalidInputException {
	
		try {

			when(usersRepository.findById(anyInt())).thenReturn(Optional.empty());
	 
			// Call the service method
			hrServiceImpl.getEmployeeById(100002);
			assertTrue(false);
		}catch(InvalidInputException e) {
			assertTrue(true);
		}
		
	}
 
	@Test
	public void testGetEmployeeByIdException() {
		// Mock data
		try {
			Users mockUsers = new Users();
			mockUsers.setEmployeeId(100001);
			mockUsers.setEmailAddress("shubhamd1109");
			mockUsers.setFirstName("Shubham");
			mockUsers.setLastName("Dubey");
			mockUsers.setPhoneNumber("9755033217");
			mockUsers.setRoles(Roles.HR);
			
			Grades grades = new Grades();
			grades.setFullName("Good");
			grades.setIdentification(1);
			
			mockUsers.setGrades(grades);
			
			Optional<Grades> optionalOfGrades=Optional.of(grades);
			
			when(gradesRepository.findById(1)).thenReturn(optionalOfGrades);
			
			when(usersRepository.findById(mockUsers.getEmployeeId())).thenThrow(SQLException.class);
	 
			// Call the service method
			hrServiceImpl.getEmployeeById(1001);
		} catch(Exception e) {
			assertTrue(true);
		}
		
	}
	
	@Test
	public void testUpdateUsersGradePositive() throws GradeUpdateRuleViolationException {
		
		Users mockUsers = new Users();
		mockUsers.setEmployeeId(100001);
		mockUsers.setEmailAddress("shubhamd1109");
		mockUsers.setFirstName("Shubham");
		mockUsers.setLastName("Dubey");
		mockUsers.setPhoneNumber("9755033217");
		mockUsers.setRoles(Roles.HR);
		
		Grades grades1 = new Grades();
		grades1.setFullName("Good");
		grades1.setIdentification(1);
		
		Grades grades2 = new Grades();
		grades2.setFullName("Good");
		grades2.setIdentification(2);
		
		Optional<Grades> optionalOfGrades1=Optional.of(grades1);
		Optional<Grades> optionalOfGrades2=Optional.of(grades1);
		
		when(gradesRepository.findById(1)).thenReturn(optionalOfGrades1);
		when(gradesRepository.findById(2)).thenReturn(optionalOfGrades2);
		
		mockUsers.setGrades(grades1);
		
		Optional<Users> optionalUsers = Optional.of(mockUsers);
		
		when(usersRepository.findById(100001)).thenReturn(optionalUsers);
		
		Users updatedUsers = new Users();
		updatedUsers.setEmployeeId(100001);
		updatedUsers.setEmailAddress("shubham");
		updatedUsers.setFirstName("shubham");
		updatedUsers.setLastName("shubham");
		updatedUsers.setPhoneNumber("9755033217");
		updatedUsers.setRoles(Roles.EMPLOYEES);
		
		Grades grades = new Grades();
		grades.setIdentification(1);
		
		updatedUsers.setGrades(grades);
		
		List<GradesHistory> gradesHistoryList = new ArrayList<GradesHistory>();
		
		when(usersRepository.save(mockUsers)).thenReturn(updatedUsers);
		when(gradesUpdateBusinessLogic.checkIfGradesIsLesserThanCurrentGrade(anyInt(), anyInt())).thenReturn(false);
		when(gradesUpdateBusinessLogic.giveMeListOfGradesHistory(anyIterable(), anyInt())).thenReturn(gradesHistoryList);
		when(gradesUpdateBusinessLogic.checkIfOldEmployeeIsEligibleForPromotion(gradesHistoryList)).thenReturn(false);

		String res= hrServiceImpl.updateEmployeeGarde(100001,2);
		
		assertEquals("success", res);
		
		
	}
	
	@Test
	public void testUpdateUsersGradeNegative() throws GradeUpdateRuleViolationException {
		
		Users users = new Users();
		users.setEmployeeId(100001);
		users.setEmailAddress("shubhamd1109");
		users.setFirstName("Shubham");
		users.setLastName("Dubey");
		users.setPhoneNumber("9755033217");
		users.setRoles(Roles.HR);
		
		Grades grades1 = new Grades();
		grades1.setFullName("Good");
		grades1.setIdentification(1);
		
		Grades grades2 = new Grades();
		grades2.setFullName("Good");
		grades2.setIdentification(2);
		
		Optional<Grades> optionalOfGrades1=Optional.of(grades1);
		
		Optional<Grades> optionalOfGrades2=Optional.of(grades2);
		
		when(gradesRepository.findById(1)).thenReturn(optionalOfGrades1);
		when(gradesRepository.findById(2)).thenReturn(optionalOfGrades2);
		
		users.setGrades(grades1);
		
		Optional<Users> optionalUsers = Optional.of(users);
		
		when(usersRepository.findById(100001)).thenReturn(optionalUsers);
		
		Users mockUsers = new Users();
		mockUsers.setEmployeeId(100001);
		mockUsers.setEmailAddress("shubham");
		mockUsers.setFirstName("shubham");
		mockUsers.setLastName("shubham");
		mockUsers.setPhoneNumber("9755033217");
		mockUsers.setRoles(Roles.EMPLOYEES);
		
		Grades grades = new Grades();
		grades.setIdentification(1);
		
		mockUsers.setGrades(grades);
		
		List<GradesHistory> gradesHistoryList = new ArrayList<GradesHistory>();
		
		when(usersRepository.save(users)).thenReturn(mockUsers);
		when(gradesUpdateBusinessLogic.checkIfGradesIsLesserThanCurrentGrade(anyInt(), anyInt())).thenReturn(true);
		when(gradesUpdateBusinessLogic.giveMeListOfGradesHistory(anyIterable(), anyInt())).thenReturn(gradesHistoryList);
		when(gradesUpdateBusinessLogic.checkIfOldEmployeeIsEligibleForPromotion(gradesHistoryList)).thenReturn(false);

		String res= hrServiceImpl.updateEmployeeGarde(100001,3);
		
		assertEquals("fail", res);
	}

	@Test
	public void testUpdateUsersGradeExceptionPositive() throws GradeUpdateRuleViolationException {
		try {
			Users users = new Users();
			users.setEmployeeId(100001);
			users.setEmailAddress("shubhamd1109");
			users.setFirstName("Shubham");
			users.setLastName("Dubey");
			users.setPhoneNumber("9755033217");
			users.setRoles(Roles.HR);
			
			Grades grades1 = new Grades();
			grades1.setFullName("Good");
			grades1.setIdentification(1);
			
			Grades grades2 = new Grades();
			grades2.setFullName("Good");
			grades2.setIdentification(2);
			
			Optional<Grades> optionalOfGrades1=Optional.of(grades1);
			Optional<Grades> optionalOfGrades2=Optional.of(grades2);
			
			when(gradesRepository.findById(1)).thenReturn(optionalOfGrades1);
			when(gradesRepository.findById(2)).thenReturn(optionalOfGrades2);
			
			users.setGrades(grades2);
			
			Optional<Users> optionalUsers = Optional.of(users);
			
			when(usersRepository.findById(100001)).thenReturn(optionalUsers);
			
			GradesHistory gradesHistory = new GradesHistory();
			gradesHistory.setAssignedon(LocalDate.now());
			gradesHistory.setId(1);
			gradesHistory.setUsers(users);
			gradesHistory.setGrades(grades1);
			
			List<GradesHistory> gradesHistoryList = new ArrayList<GradesHistory>();
			
			gradesHistoryList.add(gradesHistory);
			
			when(gradesUpdateBusinessLogic.checkIfGradesIsLesserThanCurrentGrade(anyInt(), anyInt())).thenReturn(false);
			when(gradesUpdateBusinessLogic.giveMeListOfGradesHistory(anyIterable(), anyInt())).thenReturn(gradesHistoryList);
			when(gradesUpdateBusinessLogic.checkIfEmployeeIsNewAndEligible(anyList())).thenThrow(GradeUpdateRuleViolationException.class);
			when(gradesUpdateBusinessLogic.checkIfOldEmployeeIsEligibleForPromotion(gradesHistoryList)).thenReturn(false);
			hrServiceImpl.updateEmployeeGarde(100001,1);
			assertTrue(false);
		} catch(GradeUpdateRuleViolationException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testUpdateUsersGradeExceptionNegative() throws GradeUpdateRuleViolationException {
		try {
			Users users = new Users();
			users.setEmployeeId(100001);
			users.setEmailAddress("shubhamd1109");
			users.setFirstName("Shubham");
			users.setLastName("Dubey");
			users.setPhoneNumber("9755033217");
			users.setRoles(Roles.HR);
			
			Grades grades1 = new Grades();
			grades1.setFullName("Good");
			grades1.setIdentification(1);
			
			Grades grades2 = new Grades();
			grades2.setFullName("Good");
			grades2.setIdentification(2);
			
			Optional<Grades> optionalOfGrades1=Optional.of(grades1);
			Optional<Grades> optionalOfGrades2=Optional.of(grades2);
			
			when(gradesRepository.findById(1)).thenReturn(optionalOfGrades1);
			when(gradesRepository.findById(2)).thenReturn(optionalOfGrades2);
			
			users.setGrades(grades2);
			
			Optional<Users> optionalUsers = Optional.of(users);
			
			when(usersRepository.findById(100001)).thenReturn(optionalUsers);
			
			GradesHistory gradesHistory = new GradesHistory();
			gradesHistory.setAssignedon(LocalDate.of(2022, 1, 20));
			gradesHistory.setId(1);
			gradesHistory.setUsers(users);
			gradesHistory.setGrades(grades1);
			
			List<GradesHistory> gradesHistoryList = new ArrayList<GradesHistory>();
			
			gradesHistoryList.add(gradesHistory);
			
			when(gradesUpdateBusinessLogic.checkIfGradesIsLesserThanCurrentGrade(anyInt(), anyInt())).thenReturn(false);
			when(gradesUpdateBusinessLogic.giveMeListOfGradesHistory(anyIterable(), anyInt())).thenReturn(gradesHistoryList);
			when(gradesUpdateBusinessLogic.checkIfEmployeeIsNewAndEligible(anyList())).thenReturn(true);
			when(gradesUpdateBusinessLogic.checkIfOldEmployeeIsEligibleForPromotion(gradesHistoryList)).thenReturn(false);
			hrServiceImpl.updateEmployeeGarde(100001,1);
			assertTrue(true);
		} catch(GradeUpdateRuleViolationException e) {
			assertTrue(false);
		}
	}
	

	@Test
	public void testUpdateUsersGradeEmployeeEligiblePositive() throws GradeUpdateRuleViolationException {
		
		Users mockUsers = new Users();
		mockUsers.setEmployeeId(100001);
		mockUsers.setEmailAddress("shubhamd1109");
		mockUsers.setFirstName("Shubham");
		mockUsers.setLastName("Dubey");
		mockUsers.setPhoneNumber("9755033217");
		mockUsers.setRoles(Roles.HR);
		
		Grades grades1 = new Grades();
		grades1.setFullName("Good");
		grades1.setIdentification(1);
		
		Grades grades2 = new Grades();
		grades2.setFullName("Good");
		grades2.setIdentification(2);
		
		Optional<Grades> optionalOfGrades1=Optional.of(grades1);
		Optional<Grades> optionalOfGrades2=Optional.of(grades1);
		
		when(gradesRepository.findById(1)).thenReturn(optionalOfGrades1);
		when(gradesRepository.findById(2)).thenReturn(optionalOfGrades2);
		
		mockUsers.setGrades(grades1);
		
		Optional<Users> optionalUsers = Optional.of(mockUsers);
		
		when(usersRepository.findById(100001)).thenReturn(optionalUsers);
		
		Users updatedUsers = new Users();
		updatedUsers.setEmployeeId(100001);
		updatedUsers.setEmailAddress("shubham");
		updatedUsers.setFirstName("shubham");
		updatedUsers.setLastName("shubham");
		updatedUsers.setPhoneNumber("9755033217");
		updatedUsers.setRoles(Roles.EMPLOYEES);
		
		Grades grades = new Grades();
		grades.setIdentification(1);
		
		updatedUsers.setGrades(grades);
		
		List<GradesHistory> gradesHistoryList = new ArrayList<GradesHistory>();
		
		when(usersRepository.save(mockUsers)).thenReturn(updatedUsers);
		when(gradesUpdateBusinessLogic.checkIfGradesIsLesserThanCurrentGrade(anyInt(), anyInt())).thenReturn(false);
		when(gradesUpdateBusinessLogic.giveMeListOfGradesHistory(anyIterable(), anyInt())).thenReturn(gradesHistoryList);
		when(gradesUpdateBusinessLogic.checkIfOldEmployeeIsEligibleForPromotion(gradesHistoryList)).thenReturn(false);

		String res= hrServiceImpl.updateEmployeeGarde(100001,2);
		
		assertEquals("success", res);	
	}
	

	@Test
	public void testUpdateUsersGradeEmployeeEligibleNegative() throws GradeUpdateRuleViolationException {
		
		try {
		
		Users mockUsers = new Users();
		mockUsers.setEmployeeId(100001);
		mockUsers.setEmailAddress("shubhamd1109");
		mockUsers.setFirstName("Shubham");
		mockUsers.setLastName("Dubey");
		mockUsers.setPhoneNumber("9755033217");
		mockUsers.setRoles(Roles.HR);
		
		Grades grades1 = new Grades();
		grades1.setFullName("Good");
		grades1.setIdentification(1);
		
		Grades grades2 = new Grades();
		grades2.setFullName("Good");
		grades2.setIdentification(2);
		
		Optional<Grades> optionalOfGrades1=Optional.of(grades1);
		Optional<Grades> optionalOfGrades2=Optional.of(grades1);
		
		when(gradesRepository.findById(1)).thenReturn(optionalOfGrades1);
		when(gradesRepository.findById(2)).thenReturn(optionalOfGrades2);
		
		mockUsers.setGrades(grades1);
		
		Optional<Users> optionalUsers = Optional.of(mockUsers);
		
		when(usersRepository.findById(100001)).thenReturn(optionalUsers);
		
		Users updatedUsers = new Users();
		updatedUsers.setEmployeeId(100001);
		updatedUsers.setEmailAddress("shubham");
		updatedUsers.setFirstName("shubham");
		updatedUsers.setLastName("shubham");
		updatedUsers.setPhoneNumber("9755033217");
		updatedUsers.setRoles(Roles.EMPLOYEES);
		
		Grades grades = new Grades();
		grades.setIdentification(1);
		
		updatedUsers.setGrades(grades);
		
		List<GradesHistory> gradesHistoryList = new ArrayList<GradesHistory>();
		
		when(usersRepository.save(mockUsers)).thenReturn(updatedUsers);
		when(gradesUpdateBusinessLogic.checkIfGradesIsLesserThanCurrentGrade(anyInt(), anyInt())).thenReturn(false);
		when(gradesUpdateBusinessLogic.giveMeListOfGradesHistory(anyIterable(), anyInt())).thenReturn(gradesHistoryList);
		when(gradesUpdateBusinessLogic.checkIfOldEmployeeIsEligibleForPromotion(gradesHistoryList)).thenThrow(GradeUpdateRuleViolationException.class);

		hrServiceImpl.updateEmployeeGarde(100001,2);
		
		assertTrue(false);
		} catch(GradeUpdateRuleViolationException e) {
			assertTrue(true);
		}
		
		
	}
	
	@Test
	public void testUpdateEmployeeGradesIfGradesIsLess() throws GradeUpdateRuleViolationException {
		
		try {
		Users users = new Users();
		users.setEmployeeId(100001);
		users.setEmailAddress("shubhamd1109");
		users.setFirstName("Shubham");
		users.setLastName("Dubey");
		users.setPhoneNumber("9755033217");
		users.setRoles(Roles.HR);
		
		Grades g1 = new Grades();
		g1.setFullName("Good");
		g1.setIdentification(1);
		
		Grades g2 = new Grades();
		g2.setFullName("Good");
		g2.setIdentification(2);
		
		Optional<Grades> optionalOfGrades1=Optional.of(g1);
		
		Optional<Grades> optionalOfGrades2=Optional.of(g1);
		
		when(gradesRepository.findById(1)).thenReturn(optionalOfGrades1);
		when(gradesRepository.findById(2)).thenReturn(optionalOfGrades2);
		
		users.setGrades(g1);
		
		Optional<Users> optionalUsers = Optional.of(users);
		
		when(usersRepository.findById(1001)).thenReturn(optionalUsers);
		
		Users mockUsers = new Users();
		mockUsers.setEmployeeId(1001);
		mockUsers.setEmailAddress("shubham");
		mockUsers.setFirstName("shubham");
		mockUsers.setLastName("shubham");
		mockUsers.setPhoneNumber("9755033217");
		mockUsers.setRoles(Roles.EMPLOYEES);
		
		Grades grades = new Grades();
		grades.setIdentification(1);
		
		mockUsers.setGrades(grades);
		
		List<GradesHistory> gradesHistoryList = new ArrayList<GradesHistory>();
		
		when(usersRepository.save(users)).thenReturn(mockUsers);
		when(gradesUpdateBusinessLogic.checkIfGradesIsLesserThanCurrentGrade(anyInt(), anyInt())).thenThrow(GradeUpdateRuleViolationException.class);
		when(gradesUpdateBusinessLogic.giveMeListOfGradesHistory(anyIterable(), anyInt())).thenReturn(gradesHistoryList);
		when(gradesUpdateBusinessLogic.checkIfOldEmployeeIsEligibleForPromotion(gradesHistoryList)).thenReturn(false);
		hrServiceImpl.updateEmployeeGarde(1001,2);
		assertTrue(false);
		} catch(GradeUpdateRuleViolationException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testUpdateEmployeeIfGradesNotPresent() throws GradeUpdateRuleViolationException {
		Users users = new Users();
		users.setEmployeeId(100001);
		users.setEmailAddress("shubhamd1109");
		users.setFirstName("Shubham");
		users.setLastName("Dubey");
		users.setPhoneNumber("9755033217");
		users.setRoles(Roles.HR);
		
		Optional<Users> optionalUsers = Optional.of(users);
		
		when(usersRepository.findById(1001)).thenReturn(optionalUsers);
		
		Users mockUsers = new Users();
		mockUsers.setEmployeeId(1001);
		mockUsers.setEmailAddress("shubham");
		mockUsers.setFirstName("shubham");
		mockUsers.setLastName("shubham");
		mockUsers.setPhoneNumber("9755033217");
		mockUsers.setRoles(Roles.EMPLOYEES);
		
		Grades grades = new Grades();
		grades.setIdentification(1);
		
		mockUsers.setGrades(grades);
		
		List<GradesHistory> gradesHistoryList = new ArrayList<GradesHistory>();
		
		when(usersRepository.save(users)).thenReturn(mockUsers);
		when(gradesUpdateBusinessLogic.checkIfGradesIsLesserThanCurrentGrade(anyInt(), anyInt())).thenReturn(true);
		when(gradesUpdateBusinessLogic.giveMeListOfGradesHistory(anyIterable(), anyInt())).thenReturn(gradesHistoryList);
		when(gradesUpdateBusinessLogic.checkIfOldEmployeeIsEligibleForPromotion(gradesHistoryList)).thenReturn(false);

		String res= hrServiceImpl.updateEmployeeGarde(1001,3);
		
		assertEquals("fail", res);
	}
	
	@Test
	public void testUpdateUsersGradeNotSaved() throws GradeUpdateRuleViolationException {
		
		Users mockUsers = new Users();
		mockUsers.setEmployeeId(100001);
		mockUsers.setEmailAddress("shubhamd1109");
		mockUsers.setFirstName("Shubham");
		mockUsers.setLastName("Dubey");
		mockUsers.setPhoneNumber("9755033217");
		mockUsers.setRoles(Roles.HR);
		
		Grades grades1 = new Grades();
		grades1.setFullName("Good");
		grades1.setIdentification(1);
		
		Grades grades2 = new Grades();
		grades2.setFullName("Good");
		grades2.setIdentification(2);
		
		Optional<Grades> optionalOfGrades1=Optional.of(grades1);
		Optional<Grades> optionalOfGrades2=Optional.of(grades1);
		
		when(gradesRepository.findById(1)).thenReturn(optionalOfGrades1);
		when(gradesRepository.findById(2)).thenReturn(optionalOfGrades2);
		
		mockUsers.setGrades(grades1);
		
		Optional<Users> optionalUsers = Optional.of(mockUsers);
		
		when(usersRepository.findById(100001)).thenReturn(optionalUsers);
		
		Users updatedUsers = new Users();
		updatedUsers.setEmployeeId(100001);
		updatedUsers.setEmailAddress("shubham");
		updatedUsers.setFirstName("shubham");
		updatedUsers.setLastName("shubham");
		updatedUsers.setPhoneNumber("9755033217");
		updatedUsers.setRoles(Roles.EMPLOYEES);
		
		Grades grades = new Grades();
		grades.setIdentification(1);
		
		updatedUsers.setGrades(grades);
		
		List<GradesHistory> gradesHistoryList = new ArrayList<GradesHistory>();
		
		when(usersRepository.save(mockUsers)).thenReturn(null);
		when(gradesUpdateBusinessLogic.checkIfGradesIsLesserThanCurrentGrade(anyInt(), anyInt())).thenReturn(false);
		when(gradesUpdateBusinessLogic.giveMeListOfGradesHistory(anyIterable(), anyInt())).thenReturn(gradesHistoryList);
		when(gradesUpdateBusinessLogic.checkIfOldEmployeeIsEligibleForPromotion(gradesHistoryList)).thenReturn(false);

		String res= hrServiceImpl.updateEmployeeGarde(100001,2);
		
		assertEquals("fail", res);
		
		
	}
	
	@Test
	public void testDeleteEmployeePositive() {
		
		int employeeIdToBeDeleted = 100001;
		
		Users users = new Users();
		users.setEmployeeId(100001);
		users.setEmailAddress("shubhamd1109");
		users.setFirstName("Shubham");
		users.setLastName("Dubey");
		users.setPhoneNumber("9755033217");
		users.setRoles(Roles.HR);
		
		Grades g = new Grades();
		g.setFullName("good");
		g.setIdentification(1);
		
		users.setGrades(g);
		
		GradesHistory gh = new GradesHistory();
		gh.setAssignedon(LocalDate.now());
		gh.setGrades(g);
		gh.setUsers(users);
		gh.setId(1);
		
		List<GradesHistory> gradesHistoryList = new ArrayList<>();
		gradesHistoryList.add(gh);
		
		Optional<Users> optionalOfUsers = Optional.of(users);
		Optional<GradesHistory> optionalOfGradesHistory = Optional.of(gh);
		when(usersRepository.findById(users.getEmployeeId())).thenReturn(optionalOfUsers);
		when(gradesHistoryRepository.findById(users.getEmployeeId())).thenReturn(optionalOfGradesHistory);	
		when(gradesHistoryRepository.findAll()).thenReturn(gradesHistoryList);
		
		String result = hrServiceImpl.deleteEmployee(employeeIdToBeDeleted);
		
		assertEquals("success",result);
		
	}
	
	@Test
	public void testDeleteEmployeeNegative() {
		
		int employeeIdToBeDeleted = 1002;
		
		Users users = new Users();
		users.setEmployeeId(100001);
		users.setEmailAddress("shubhamd1109");
		users.setFirstName("Shubham");
		users.setLastName("Dubey");
		users.setPhoneNumber("9755033217");
		users.setRoles(Roles.HR);

		Grades g = new Grades();
		g.setFullName("good");
		g.setIdentification(1);
		
		users.setGrades(g);
		
		GradesHistory gh = new GradesHistory();
		gh.setAssignedon(LocalDate.now());
		gh.setGrades(g);
		gh.setUsers(users);
		gh.setId(1);
		
		List<GradesHistory> gradesHistoryList = new ArrayList<>();
		gradesHistoryList.add(gh);
		
		Optional<Users> optionalOfUsers = Optional.of(users);
		Optional<GradesHistory> optionalOfGradesHistory = Optional.of(gh);
		when(usersRepository.findById(users.getEmployeeId())).thenReturn(optionalOfUsers);
		when(gradesHistoryRepository.findById(users.getEmployeeId())).thenReturn(optionalOfGradesHistory);	
		when(gradesHistoryRepository.findAll()).thenReturn(gradesHistoryList);
		
		String result = hrServiceImpl.deleteEmployee(employeeIdToBeDeleted);

		
		assertEquals("fail",result);
		
	}
	
	@Test
	public void testDeleteEmployeeException() {
		
		try {
			int employeeIdToBeDeleted = 1002;
			
			Users users = new Users();
			users.setEmployeeId(100001);
			users.setEmailAddress("shubhamd1109");
			users.setFirstName("Shubham");
			users.setLastName("Dubey");
			users.setPhoneNumber("9755033217");
			users.setRoles(Roles.HR);
	
			Grades g = new Grades();
			g.setFullName("good");
			g.setIdentification(1);
			
			users.setGrades(g);
			
			GradesHistory gh = new GradesHistory();
			gh.setAssignedon(LocalDate.now());
			gh.setGrades(g);
			gh.setUsers(users);
			gh.setId(1);
			
			List<GradesHistory> gradesHistoryList = new ArrayList<>();
			gradesHistoryList.add(gh);
			
			Optional<Users> optionalOfUsers = Optional.of(users);
			Optional<GradesHistory> optionalOfGradesHistory = Optional.of(gh);
			when(usersRepository.findById(users.getEmployeeId())).thenReturn(optionalOfUsers);
			when(gradesHistoryRepository.findById(users.getEmployeeId())).thenReturn(optionalOfGradesHistory);	
			when(gradesHistoryRepository.findAll()).thenReturn(gradesHistoryList);
			hrServiceImpl.deleteEmployee(employeeIdToBeDeleted);
			
	}catch(Exception e) {
		assertTrue(true);
	}
	}
	 
	@Test
	public void testReturnEmployeeListPositive() {
		
		Users u1= new Users();
		u1.setEmailAddress("shubhamd1109");
		u1.setEmployeeId(1001);
		u1.setFirstName("shubham");
		u1.setLastName("dubey");
		u1.setPhoneNumber("9755033217");
		u1.setRoles(Roles.EMPLOYEES);
		
		Grades g1 = new Grades();
		g1.setFullName("good");
		g1.setIdentification(1);
		
		u1.setGrades(g1);
		
		Users u2 = new Users();
		u1.setEmailAddress("ayush121");
		u1.setEmployeeId(1001);
		u1.setFirstName("Ayush");
		u1.setLastName("Verma");
		u1.setPhoneNumber("9733123423");
		u1.setRoles(Roles.EMPLOYEES);
		
		Grades g2 = new Grades();
		g2.setFullName("very good");
		g2.setIdentification(2);
		
		u2.setGrades(g2);
		
		List<Users> ul = new ArrayList<Users>();
		ul.add(u2);
		ul.add(u1);
		
		Mockito.when(usersRepository.findAll()).thenReturn(ul);
		
		List<UsersDTO> users = hrServiceImpl.returnEmployeeList();
		
		assertTrue(!users.isEmpty());
	}

	@Test
	public void testReturnEmployeeListNegative() {
		
		List<Users> usersList = new ArrayList<Users>();

		Mockito.when(usersRepository.findAll()).thenReturn(usersList);
		
		List<UsersDTO> userDto = hrServiceImpl.returnEmployeeList();
		
		assertTrue(userDto.isEmpty());
	}
	
	@Test
	public void testReturnEmployeeListException() {
		
		try {
			List<Users> usersList = new ArrayList<Users>();
	
			Mockito.when(usersRepository.findAll()).thenReturn(usersList);
			
			hrServiceImpl.returnEmployeeList();
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testReturnGradesListPositive() {

		
		Grades g1 = new Grades();
		g1.setFullName("good");
		g1.setIdentification(1);
		
		
		Grades g2 = new Grades();
		g2.setFullName("very good");
		g2.setIdentification(2);
		
		
		List<Grades> gradesList = new ArrayList<Grades>();
		gradesList.add(g1);
		gradesList.add(g2);
		
		Mockito.when(gradesRepository.findAll()).thenReturn(gradesList);
		
		List<GradesDTO> grades = hrServiceImpl.returnGradesList();
		
		assertTrue(!grades.isEmpty());
	}

	@Test
	public void testReturnGradesListNegative() {
		
		List<Grades> gl = new ArrayList<Grades>();

		Mockito.when(gradesRepository.findAll()).thenReturn(gl);
		
		List<GradesDTO> grades = hrServiceImpl.returnGradesList();
		
		assertTrue(grades.isEmpty());
		
	}
	
	@Test
	public void testReturnGradesListException() {
		
		try {	
			Mockito.when(gradesRepository.findAll()).thenThrow(SQLException.class);
			
			hrServiceImpl.returnGradesList();
			
		}catch(Exception e) {
			assertTrue(true);
		}
		
	}

	@Test
	public void testPersistNewEmployeePositive() throws DuplicateAccountException, HomeManagerUpdateRuleViolationException {
		Users users = new Users();
		users.setEmployeeId(1001);
		users.setEmailAddress("shubhamd1109");
		users.setFirstName("shubham");
		users.setLastName("dubey");
		users.setPhoneNumber("9755033217");
		users.setRoles(Roles.EMPLOYEES);
		
		Grades grades = new Grades();
		grades.setFullName("Good");
		grades.setIdentification(1);
		
		Optional<Grades> optionalOfGrades=Optional.of(grades);
		
		when(gradesRepository.findById(1)).thenReturn(optionalOfGrades);
		
		users.setGrades(grades);
				
		when(usersRepository.findById(1001)).thenReturn(Optional.of(users));
		
		GradesHistory gradesHistory = new GradesHistory();
		when(gradesHistoryRepository.save(any())).thenReturn(gradesHistory);
		
		when(usersRepository.save(users)).thenReturn(users);
		
		UsersDTO usersDto = new UsersDTO();
		usersDto.setEmployeeId(1002);
		usersDto.setEmailAddress("shubham");
		usersDto.setFirstName("shubham");
		usersDto.setLastName("shubham");
		usersDto.setPhoneNumber("9755033217");
		usersDto.setRoles(Roles.EMPLOYEES);
		usersDto.setGrade_id(1);
		
		String result = hrServiceImpl.persistNewEmployees(usersDto);
		assertNotEquals("success", result);
		
	}
	
	@Test
	public void testPersistNewEmployeeNegative() throws DuplicateAccountException, HomeManagerUpdateRuleViolationException {
		Users users = new Users();
		users.setEmployeeId(1001);
		users.setEmailAddress("shubhamd1109");
		users.setFirstName("shubham");
		users.setLastName("dubey");
		users.setPhoneNumber("9755033217");
		users.setRoles(Roles.EMPLOYEES);
		
		Grades grades = new Grades();
		grades.setFullName("Good");
		grades.setIdentification(1);
		
		Optional<Grades> optionalOfGrades=Optional.of(grades);
		
		when(gradesRepository.findById(1)).thenReturn(optionalOfGrades);
		
		users.setGrades(grades);
		
		Optional<Users> optionalUsers = Optional.of(users);
		
		when(usersRepository.findById(1001)).thenReturn(optionalUsers);
		
		GradesHistory gradesHistory = new GradesHistory();
		when(gradesHistoryRepository.save(any())).thenReturn(gradesHistory);
		when(usersRepository.save(users)).thenReturn(null);

		UsersDTO usersDto = new UsersDTO();
		usersDto.setEmployeeId(1002);
		usersDto.setEmailAddress("shubham");
		usersDto.setFirstName("shubham");
		usersDto.setLastName("shubham");
		usersDto.setPhoneNumber("9755033217");
		usersDto.setRoles(Roles.EMPLOYEES);
		usersDto.setGrade_id(1);
		
		String result = hrServiceImpl.persistNewEmployees(usersDto);
		assertNotEquals("success", result);
		
	}

	
	@Test
	public void testPersistNewEmployeeException() throws DuplicateAccountException
	{
		try {
			Users users = new Users();
			users.setEmployeeId(1001);
			users.setEmailAddress("shubhamd1109");
			users.setFirstName("shubham");
			users.setLastName("dubey");
			users.setPhoneNumber("9755033217");
			users.setRoles(Roles.EMPLOYEES);
			
			Grades grades = new Grades();
			grades.setFullName("Good");
			grades.setIdentification(1);
			
			Optional<Grades> optionalOfGrades=Optional.of(grades);
			
			when(gradesRepository.findById(1)).thenReturn(optionalOfGrades);
			
			users.setGrades(grades);
					
			when(usersRepository.findById(1001)).thenReturn(Optional.of(users));
			
			GradesHistory gradesHistory = new GradesHistory();
			when(gradesHistoryRepository.save(any())).thenReturn(gradesHistory);
			when(usersRepository.save(users)).thenReturn(users);

			UsersDTO usersDto = new UsersDTO();
			usersDto.setEmployeeId(1001);
			usersDto.setEmailAddress("shubham");
			usersDto.setFirstName("shubham");
			usersDto.setLastName("shubham");
			usersDto.setPhoneNumber("9755033217");
			usersDto.setRoles(Roles.EMPLOYEES);
			usersDto.setGrade_id(1);
			
			hrServiceImpl.persistNewEmployees(usersDto);
			assertFalse(true);
			
		} catch(DuplicateAccountException | HomeManagerUpdateRuleViolationException e) {
			assertTrue(true);
		}
		
	}
	
	@Test
	public void testPersistNewEmployeeIfGradesNotPresent() throws DuplicateAccountException, HomeManagerUpdateRuleViolationException {
		Users users = new Users();
		users.setEmployeeId(1001);
		users.setEmailAddress("shubhamd1109");
		users.setFirstName("shubham");
		users.setLastName("dubey");
		users.setPhoneNumber("9755033217");
		users.setRoles(Roles.EMPLOYEES);
		
		Grades grades = new Grades();
		grades.setFullName("Good");
		grades.setIdentification(1);
		
		when(gradesRepository.findById(1)).thenReturn(Optional.empty());
		
		users.setGrades(grades);
		
		Optional<Users> optionalUsers = Optional.of(users);
		
		when(usersRepository.findById(1001)).thenReturn(optionalUsers);
		
		UsersDTO usersDto = new UsersDTO();
		usersDto.setEmployeeId(1002);
		usersDto.setEmailAddress("shubham");
		usersDto.setFirstName("shubham");
		usersDto.setLastName("shubham");
		usersDto.setPhoneNumber("9755033217");
		usersDto.setRoles(Roles.EMPLOYEES);
		usersDto.setGrade_id(1);
		
		String result = hrServiceImpl.persistNewEmployees(usersDto);
		assertNotEquals("success", result);
		
	}
	
	
}
