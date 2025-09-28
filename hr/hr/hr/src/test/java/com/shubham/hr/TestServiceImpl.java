package com.shubham.hr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.shubham.hr.entity.Grades;
import com.shubham.hr.entity.GradesHistory;
import com.shubham.hr.entity.Users;
import com.shubham.hr.model.GradesDTO;
import com.shubham.hr.model.UsersDTO;
import com.shubham.hr.repository.GradesHistoryRepository;
import com.shubham.hr.repository.GradesRepository;
import com.shubham.hr.repository.UsersRepository;
import com.shubham.hr.service.HrServiceImpl;
import com.shubham.hr.utility.Roles;


public class TestServiceImpl {
	
	@Mock
	private UsersRepository usersRepository;
	
	@Mock
	private GradesRepository gradesRepository;
	
	@Mock
	private GradesHistoryRepository gradesHistoryRepository;

	@InjectMocks
	HrServiceImpl hrServiceImpl;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetEmployeeByIdPositive() {
		// Mock data
		Users mockUsers = new Users();
		mockUsers.setEmployeeId(1001);
		mockUsers.setEmailAddress("shubham");
		mockUsers.setFirstName("shubham");
		mockUsers.setLastName("shubham");
		mockUsers.setPhoneNumber("9755033217");
		mockUsers.setRoles(Roles.HR);
		
		Optional<Users> optionalOfUsers = Optional.of(mockUsers);
		when(usersRepository.findById(mockUsers.getEmployeeId() + 1)).thenReturn(optionalOfUsers);
 
		// Call the service method
		UsersDTO result= hrServiceImpl.getEmployeeById(1002);
		assertNotNull(result);
		
	}
	
	@Test
	public void testGetEmployeeByIdNegaive() {
	
		Users mockUsers = new Users();
		mockUsers.setEmployeeId(1001);
		mockUsers.setEmailAddress("shubham");
		mockUsers.setFirstName("shubham");
		mockUsers.setLastName("shubham");
		mockUsers.setPhoneNumber("9755033217");
		mockUsers.setRoles(Roles.HR);
		
		Optional<Users> optionalOfUsers = Optional.of(mockUsers);
		when(usersRepository.findById(mockUsers.getEmployeeId())).thenReturn(optionalOfUsers);
 
		// Call the service method
		UsersDTO result= hrServiceImpl.getEmployeeById(1002);
		assertNull(result);
		
	}
 
	@Test
	public void testUpdateUsersGradePositive() {
		
		Users users = new Users();
		users.setEmployeeId(1001);
		users.setEmailAddress("shubham");
		users.setFirstName("shubham");
		users.setLastName("shubham");
		users.setPhoneNumber("9755033217");
		users.setRoles(Roles.EMPLOYEES);
		
		Grades g1 = new Grades();
		g1.setFullName("Good");
		g1.setIdentification(1);
		
		Grades g2 = new Grades();
		g2.setFullName("Good");
		g2.setIdentification(2);
		
		Optional<Grades> optionalOfGrades=Optional.of(g1);
		
		when(gradesRepository.findById(1)).thenReturn(optionalOfGrades);
		
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
		
		Grades gu = new Grades();
		gu.setIdentification(2);
		
		mockUsers.setGrades(gu);
		
		when(usersRepository.save(users)).thenReturn(mockUsers);
		
		String res= hrServiceImpl.updateEmployeeGarde(1001,1);
		
		assertEquals("success", res);
		
		
	}
	
	@Test
	public void testUpdateUsersGradeNegative() {
		
		Users users = new Users();
		users.setEmployeeId(1001);
		users.setEmailAddress("shubham");
		users.setFirstName("shubham");
		users.setLastName("shubham");
		users.setPhoneNumber("9755033217");
		users.setRoles(Roles.EMPLOYEES);
		
		Grades g1 = new Grades();
		g1.setFullName("Good");
		g1.setIdentification(1);
		
		Grades g2 = new Grades();
		g2.setFullName("Good");
		g2.setIdentification(2);
		
		Optional<Grades> optionalOfGrades1=Optional.of(g1);
		
		Optional<Grades> optionalOfGrades2=Optional.of(g1);
		
		when(gradesRepository.findById(1)).thenReturn(optionalOfGrades1);
		when(gradesRepository.findById(1)).thenReturn(optionalOfGrades2);
		
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
		
		Grades gu = new Grades();
		gu.setIdentification(2);
		
		mockUsers.setGrades(gu);
		
		when(usersRepository.save(users)).thenReturn(mockUsers);
		
		String res= hrServiceImpl.updateEmployeeGarde(1001,2);
		
		assertEquals("success", res);
	}
	
	@Test
	public void testDeleteEmployeePositive() {
		
		int employeeIdToBeDeleted = 1001;
		
		Users users = new Users();
		users.setEmployeeId(1001);
		users.setEmailAddress("shubham");
		users.setFirstName("shubham");
		users.setLastName("shubham");
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
		
		Optional<Users> optionalOfUsers = Optional.of(users);
		Optional<GradesHistory> optionalOfGradesHistory = Optional.of(gh);
		when(usersRepository.findById(users.getEmployeeId())).thenReturn(optionalOfUsers);
		when(gradesHistoryRepository.findById(users.getEmployeeId())).thenReturn(optionalOfGradesHistory);		
		
		String result = hrServiceImpl.deleteEmployee(employeeIdToBeDeleted);
		verify(usersRepository,times(1)).deleteById(1001);

		
		assertEquals("success",result);
		
	}
	
	@Test
	public void testDeleteEmployeeNegative() {
		
		int employeeIdToBeDeleted = 1002;
		
		Users users = new Users();
		users.setEmployeeId(1001);
		users.setEmailAddress("shubham");
		users.setFirstName("shubham");
		users.setLastName("shubham");
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
		
		Optional<Users> optionalOfUsers = Optional.of(users);
		Optional<GradesHistory> optionalOfGradesHistory = Optional.of(gh);
		when(usersRepository.findById(users.getEmployeeId())).thenReturn(optionalOfUsers);
		when(gradesHistoryRepository.findById(users.getEmployeeId())).thenReturn(optionalOfGradesHistory);		
		
		String result = hrServiceImpl.deleteEmployee(employeeIdToBeDeleted);

		
		assertNotEquals("success",result);
		
	}
	
	@Test
	public void testReturnEmployeeListPositive() {
		
		Users u1= new Users();
		u1.setEmailAddress("shubham@gmail.com");
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
		u1.setEmailAddress("shubham@gmail.com");
		u1.setEmployeeId(1001);
		u1.setFirstName("shubham");
		u1.setLastName("dubey");
		u1.setPhoneNumber("9755033217");
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
		
		Users u1= new Users();
		u1.setEmailAddress("shubham@gmail.com");
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
		u1.setEmailAddress("shubham@gmail.com");
		u1.setEmployeeId(1001);
		u1.setFirstName("shubham");
		u1.setLastName("dubey");
		u1.setPhoneNumber("9755033217");
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
		
		assertTrue(users.isEmpty());
	}
	
	@Test
	public void testReturnGradesListPositive() {

		
		Grades g1 = new Grades();
		g1.setFullName("good");
		g1.setIdentification(1);
		
		
		Grades g2 = new Grades();
		g2.setFullName("very good");
		g2.setIdentification(2);
		
		
		List<Grades> gl = new ArrayList<Grades>();
		gl.add(g1);
		gl.add(g2);
		
		Mockito.when(gradesRepository.findAll()).thenReturn(gl);
		
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
}
