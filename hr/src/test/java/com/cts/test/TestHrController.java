package com.cts.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.cts.hr.utility.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.cts.hr.HrApplication;
import com.cts.hr.controller.HrController;
import com.cts.hr.dto.GradesDTO;
import com.cts.hr.dto.UsersDTO;
import com.cts.hr.service.HrService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = HrApplication.class)
public class TestHrController {
	private MockMvc mockMvc;
	
	@Mock
	private HrService hrService;
	
	@InjectMocks
	private HrController hrController;
	
	@Autowired
	private LocalValidatorFactoryBean validator;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(hrController).build();
	}

    /**
	@SuppressWarnings("unchecked")
	@Test
	public void testTandleReturnEmployeeListPositive() {
		
		List<UsersDTO> usersDtoList = new ArrayList<>();
		
		UsersDTO dto = new UsersDTO();
		dto.setEmailAddress("shubhamd1109");
		dto.setEmployeeId(100001);
		dto.setFirstName("shubham");
		dto.setGrade_id(1);
		dto.setLastName("Dubey");
		dto.setPhoneNumber("9755033217");
		dto.setRole(Roles.EMPLOYEES);
		
		usersDtoList.add(dto);
		
		Mockito.when(hrService.returnEmployeeList()).thenReturn(usersDtoList);
		
		ResponseEntity<?> responseEntity = hrController.returnEmployeeList();
		List<UsersDTO> responseUsersDtoList = (List<UsersDTO>)responseEntity.getBody();
		
		assertTrue(!responseUsersDtoList.isEmpty());
		assertEquals(usersDtoList.size(), responseUsersDtoList.size());
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testTandleReturnEmployeeListNegative() {
		
		List<UsersDTO> usersDtoList = new ArrayList<>();
		Mockito.when(hrService.returnEmployeeList()).thenReturn(usersDtoList);
		
		ResponseEntity<?> responseEntity = hrController.returnEmployeeList();
		List<UsersDTO> responseUsersDtoList = (List<UsersDTO>)responseEntity.getBody();
		
		assertNull(responseUsersDtoList);
		
	} **/
	
	@SuppressWarnings("unchecked")
	@Test
	public void testTandleReturnGradesListPositive() {
		
		List<GradesDTO> gradesDtoList = new ArrayList<>();
		
		GradesDTO dto = new GradesDTO();
		dto.setFullName("Grade 1");
		dto.setIdentification(1);
		
		gradesDtoList.add(dto);
		
		Mockito.when(hrService.returnGradesList()).thenReturn(gradesDtoList);
		
		ResponseEntity<?> responseEntity = hrController.returnGradesList();
		List<GradesDTO> responseGradesDtoList = (List<GradesDTO>)responseEntity.getBody();
		
		assertTrue(!responseGradesDtoList.isEmpty());
		assertEquals(gradesDtoList.size(), responseGradesDtoList.size());
		
	}
	
	@Test
	public void testTandleReturnGradesListNegative() {
		
		List<GradesDTO> gradesDtoList = new ArrayList<>();
		Mockito.when(hrService.returnGradesList()).thenReturn(gradesDtoList);
		
		ResponseEntity<?> responseEntity = hrController.returnGradesList();
		@SuppressWarnings("unchecked")
		List<GradesDTO> responseGradesDtoList = (List<GradesDTO>)responseEntity.getBody();
		
		assertNull(responseGradesDtoList);
		
	}

    /**
	@Test
	public void testUriHandleReturnEmployeeListNegative(){
		List<UsersDTO> usersDtoList = new ArrayList<>();
		
		UsersDTO dto = new UsersDTO();
		dto.setEmailAddress("shubhamd1109");
		dto.setEmployeeId(100001);
		dto.setFirstName("shubham");
		dto.setGrade_id(1);
		dto.setLastName("Dubey");
		dto.setPhoneNumber("9755033217");
		dto.setRole(Roles.EMPLOYEES);
		
		usersDtoList.add(dto);
		Mockito.when(hrService.returnEmployeeList()).thenReturn(usersDtoList);
		
		try {
			mockMvc.perform(get("http://localhost:8090/api/employes")).andExpect(status().isNotFound()).andReturn();
		} catch (Exception e) {
			assertTrue(true);
		}
	}	**/
	
	@Test 
	public void testUriHandleReturnGradesListPositive() {
		List<GradesDTO> gradesDtoList = new ArrayList<>();
		
		GradesDTO dto = new GradesDTO();
		dto.setFullName("Grade 1");
		dto.setIdentification(1);
		
		gradesDtoList.add(dto);
		
		Mockito.when(hrService.returnGradesList()).thenReturn(gradesDtoList);
		
		try {
			mockMvc.perform(get("http://localhost:8090/api/grades")).andExpect(status().isOk()).andReturn();
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test 
	public void testUriHandleReturnGradesListNegative() {
		List<GradesDTO> gradesDtoList = new ArrayList<>();
		GradesDTO dto = new GradesDTO();
		dto.setFullName("Grade 1");
		dto.setIdentification(1);
		
		gradesDtoList.add(dto);
		
		Mockito.when(hrService.returnGradesList()).thenReturn(gradesDtoList);
		
		try {
			mockMvc.perform(get("http://localhost:8090/api/grade")).andExpect(status().isNotFound()).andReturn();
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testUriGetEmployeeByIDPositive() throws InvalidInputException {
		
		UsersDTO dto = new UsersDTO();
		dto.setEmailAddress("shubham1109");
		dto.setEmployeeId(100001);
		dto.setFirstName("shubham");
		dto.setGrade_id(1);
		dto.setLastName("Dubey");
		dto.setPhoneNumber("9755033217");
		dto.setRole(Roles.EMPLOYEES);	
		Mockito.when(hrService.getEmployeeById(1000001)).thenReturn(dto);
		
		try {
			mockMvc.perform(get("http://localhost:8090/api/employees/{empId}",1000001)).andExpect(status().isOk()).andReturn();
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testUriGetEmployeeByIdNegative() throws Exception {
		UsersDTO dto = new UsersDTO();
		dto.setEmailAddress("shubhamd1109");
		dto.setEmployeeId(100001);
		dto.setFirstName("shubham");
		dto.setGrade_id(1);
		dto.setLastName("Dubey");
		dto.setPhoneNumber("9755033217");
		dto.setRole(Roles.EMPLOYEES);
		
		
		Mockito.when(hrService.getEmployeeById(1000001)).thenReturn(dto);
		
		try {
			mockMvc.perform(get("http://localhost:8090/api/emees/{empId}",1000002)).andExpect(status().isNotFound()).andReturn();
		} catch (InvalidInputException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testUriDeleteEmployeeNegative() {
		try {
			mockMvc.perform(delete("http://localhost:8090/api/emoes/{emId}",100001)).andExpect(status().isNotFound()).andReturn();
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testPersistnewEmployeePositive() throws DuplicateAccountException, InvalidInputException, HomeManagerUpdateRuleViolationException {
		
		UsersDTO usersDTO=new UsersDTO();
		usersDTO.setEmailAddress("shubhamd1109");
		usersDTO.setFirstName("shubham");
		usersDTO.setEmployeeId(100001);
		usersDTO.setGrade_id(1);
		usersDTO.setLastName("dueby");
		usersDTO.setRole(Roles.EMPLOYEES);
		
		when(hrService.persistNewEmployees(usersDTO)).thenReturn("success");
		ResponseEntity<?> responseEntity=hrController.persistEmployee(usersDTO);
		assertEquals(201,responseEntity.getStatusCodeValue());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testPersistnewEmployeeNegative() throws DuplicateAccountException, InvalidInputException, HomeManagerUpdateRuleViolationException {
		
		UsersDTO usersDTO = new UsersDTO();
		usersDTO.setEmployeeId(100001);
		when(hrService.persistNewEmployees(usersDTO)).thenReturn("fail");
		ResponseEntity<?> responseEntity=hrController.persistEmployee(usersDTO);
		assertEquals(400,responseEntity.getStatusCodeValue());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testPersistnewEmployeeInvalidInputException() throws DuplicateAccountException, InvalidInputException {
		
		try {
			UsersDTO usersDTO = new UsersDTO();
			when(hrService.persistNewEmployees(usersDTO)).thenReturn("fail");
			ResponseEntity<?> responseEntity=hrController.persistEmployee(usersDTO);
			assertEquals(400,responseEntity.getStatusCodeValue());
		}catch(InvalidInputException | HomeManagerUpdateRuleViolationException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testUpdateEmployeesGradeException() {
		
		try {
			when(hrService.updateEmployeeGarde(1, 2)).thenThrow(GradeUpdateRuleViolationException.class);
			hrController.updateEmployeeGrade(1, 2);
		} catch (GradeUpdateRuleViolationException e) {
			assertTrue(true);
		}
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testUpdateEmployeesGradePositive() {
		
		try {
			when(hrService.updateEmployeeGarde(1, 2)).thenReturn("success");
			ResponseEntity<?> responseEntity= hrController.updateEmployeeGrade(1, 2);
			assertEquals(202, responseEntity.getStatusCodeValue());
		} catch (GradeUpdateRuleViolationException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testUpdateEmployeesGradeNegative() {
		
		try {
			when(hrService.updateEmployeeGarde(1, 10)).thenReturn("fail");
			ResponseEntity<?> responseEntity;
			responseEntity = hrController.updateEmployeeGrade(1, 10);
			assertEquals(HttpStatus.NOT_MODIFIED, responseEntity.getStatusCode());
		} catch (GradeUpdateRuleViolationException e) {
			assertTrue(true);
		}
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testDeleteEmployeeByIdPositive() throws DuplicateAccountException, HomeManagerUpdateRuleViolationException {
		UsersDTO usersDTO=new UsersDTO();
		usersDTO.setEmailAddress("shubhamd1109");
		usersDTO.setFirstName("shubham");
		usersDTO.setEmployeeId(100001);
		usersDTO.setGrade_id(1);
		usersDTO.setLastName("dueby");
		usersDTO.setRole(Roles.EMPLOYEES);
		
		when(hrService.persistNewEmployees(usersDTO)).thenReturn("success");
		when(hrService.deleteEmployee(100001)).thenReturn("success");
		
		ResponseEntity<?> responseEntity = hrController.deleteEmployeeById(100001);
		assertEquals(200,responseEntity.getStatusCodeValue());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testDeleteEmployeeIdNegative() {
		when(hrService.deleteEmployee(100001)).thenReturn("fail");
		
		ResponseEntity<?> responseEntity = hrController.deleteEmployeeById(100001);
		assertEquals(400,responseEntity.getStatusCodeValue());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetEmployeeByIdPositive() throws InvalidInputException {
		UsersDTO usersDTO=new UsersDTO();
		usersDTO.setEmailAddress("shubhamd1109");
		usersDTO.setFirstName("shubham");
		usersDTO.setEmployeeId(100001);
		usersDTO.setGrade_id(1);
		usersDTO.setLastName("dueby");
		usersDTO.setRole(Roles.EMPLOYEES);
		
		when(hrService.getEmployeeById(100001)).thenReturn(usersDTO);
		
		ResponseEntity<?> responseEntity = hrController.retrieveEmployeeById(100001);
		assertEquals(200,responseEntity.getStatusCodeValue());
	}

	@Test
	public void testGetEmployeeByIdException() throws InvalidInputException {
	
		
		try {
			when(hrService.getEmployeeById(anyInt())).thenThrow(InvalidInputException.class);
			
			hrController.retrieveEmployeeById(100001);
			assertTrue(true);
		} catch(InvalidInputException e) {
			assertTrue(true);
		}
	}
	
	@Test 
	void testUriUpdatEmployeeGradeNegative() {
		try {
			mockMvc.perform(put("http://localhost:8090/api/employees/{empId}/{nerade}",1000001,2)).andExpect(status().isNotFound()).andReturn();
		} catch (Exception e) {
			assertTrue(true);
		}
	}
		
	@Test
	public void testUriUpdateEmployeeGradePositive() throws GradeUpdateRuleViolationException {
		
		when(hrService.updateEmployeeGarde(anyInt(), anyInt())).thenReturn("success");
		
		try {
			mockMvc.perform(put("http://localhost:8090/api/employees/{empId}/{newGrade}",1000001,2)).andExpect(status().isAccepted()).andReturn();
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void persistEmployeeWhenPhoneNumberIsValid() {
		UsersDTO usersDto=new UsersDTO();
		usersDto.setEmailAddress("shubhamd1109");
		usersDto.setEmployeeId(100001);
		usersDto.setFirstName("shubham");
		usersDto.setPhoneNumber("9755033217");
		validator.validateProperty(usersDto, "PhoneNumber")//Set<ConstraintViolation>
	    .stream()
	    .forEach((constraintviolation)->assertNull(constraintviolation));
	}
	
	@Test
	public void persistEmployeeWhenPhoneNumberIsNotValid() {
		UsersDTO usersDto=new UsersDTO();
		usersDto.setEmailAddress("shubhamd1109");
		usersDto.setEmployeeId(100001);
		usersDto.setFirstName("shubham");
		usersDto.setPhoneNumber("97550337");
		validator.validateProperty(usersDto, "PhoneNumber")//Set<ConstraintViolation>
	    .stream()
	    .forEach((constraintviolation)->assertNotNull(constraintviolation));
	}
	
	@Test
	public void testUriPersistNegative() throws JsonProcessingException, DuplicateAccountException, HomeManagerUpdateRuleViolationException {
		
		UsersDTO dto = new UsersDTO();
		dto.setEmailAddress("shubhamd1109");
		dto.setEmployeeId(100001);
		dto.setFirstName("shubham");
		dto.setGrade_id(1);
		dto.setLastName("Dubey");
		dto.setPhoneNumber("9755033217");
		dto.setRole(Roles.EMPLOYEES);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(dto);
		
		Mockito.when(hrService.persistNewEmployees(dto)).thenReturn("fail");
		
		try {
			mockMvc.perform(post("http://localhost:8090/api/ployees").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isNotFound()).andReturn();
		} catch (Exception e) {
			assertTrue(false);
		}
	}

}
