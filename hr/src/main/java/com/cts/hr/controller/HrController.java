package com.cts.hr.controller;

import java.util.List;

import com.cts.hr.dto.LoginDetailsDto;
import com.cts.hr.jwtSecurity.JwtHelper;
import com.cts.hr.model.JwtResponse;
import com.cts.hr.service.LoginService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.hr.dto.GradesDTO;
import com.cts.hr.dto.UsersDTO;
import com.cts.hr.service.HrService;
import com.cts.hr.utility.DuplicateAccountException;
import com.cts.hr.utility.GradeUpdateRuleViolationException;
import com.cts.hr.utility.InvalidInputException;

/**
 * @author Shubham Dubey
 * This class represents Rest API endpoints for Human Resource API
 */
@RestController
@RequestMapping("api")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "HR Controller", description = "Endpoints for managing employees")
public class HrController {

	@Autowired
	private HrService hrService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginDetailsDto loginDetails) {

        jwtHelper.doAuthenticate(loginDetails.getUsername(), loginDetails.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDetails.getUsername());
        System.out.println(userDetails.getAuthorities());
        String token = jwtHelper.generateToken(userDetails);

        JwtResponse jwtResponse = new JwtResponse(token, loginDetails.getUsername(), userDetails.getAuthorities().toString());
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    /**
	 * Handles Retrieval of employee of given ID
	 * @return ResponseEntity<UsersDTO>
	 * 
	 */
	@GetMapping("getEmployeeById/{empId}")
    @PreAuthorize("hasAnyRole('HR','ADMIN','EMPLOYEE','TRAVELDESKEXC')")
	public ResponseEntity<UsersDTO> retrieveEmployeeById(@PathVariable("empId")int empId) throws InvalidInputException{
		UsersDTO response=hrService.getEmployeeById(empId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	/** 
	 * Handles adding new employee
	 * @return ResponseEntity<String>
	 * 
	 */
	@PostMapping("addEmployee")
    @PreAuthorize("hasAnyRole('HR','ADMIN')")
	public ResponseEntity<String> persistEmployee(@RequestBody UsersDTO usersDto) throws DuplicateAccountException, InvalidInputException{
        if(usersDto.getEmployeeId() == 0) {
			throw new InvalidInputException("Input is invalid");
		}
		String result=hrService.persistNewEmployees(usersDto);
		if(result.equals("success")) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
		}		
	}
	
	/** 
	 * Handles updating existing employee grade
	 * @return ResponseEntity<UsersDTO>
	 * 
	 */
	@PutMapping("updateEmployeesGrade/{empId}/{newGrade}")
    @PreAuthorize("hasAnyRole('HR','ADMIN')")
	public ResponseEntity<?> updateEmployeeGrade(@PathVariable("empId")int empId,@PathVariable("newGrade")int newGrade) throws GradeUpdateRuleViolationException{
		
			String result=hrService.updateEmployeeGarde(empId, newGrade);
			System.out.println(result);
			if(result.equals("success")) {
				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			}else {
				return new ResponseEntity<String>(result,HttpStatus.NOT_MODIFIED);
			}
		
	}
	
	/** 
	 * Handles deleting an employee
	 * @return ResponseEntity<String>
	 * 
	 */
	@DeleteMapping("deleteEmployee/{empId}")
    @PreAuthorize("hasAnyRole('HR','ADMIN')")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable("empId")int empId){
		String result=hrService.deleteEmployee(empId);
		if(result.equals("success")) {
			return new ResponseEntity<>(result,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
		}
	}
	
	/** 
	 * Handles Retrieval of all Employees
	 * @return ResponseEntity<List<UsersDTO>>
	 * 
	 */
	@GetMapping("getEmployees")
    @PreAuthorize("hasAnyRole('HR','ADMIN')")
	public ResponseEntity<List<UsersDTO>> returnEmployeeList(){

		List<UsersDTO> responseList = hrService.returnEmployeeList();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
	}
	
	/** 
	 * Handles Retrieval of all Grades
	 * @return ResponseEntity<List<GradesDTO>>
	 * 
	 */
	@GetMapping("grades")
    @PreAuthorize("hasAnyRole('HR','ADMIN')")
	public ResponseEntity<List<GradesDTO>> returnGradesList(){
        System.out.println("Inside grades controller");
		List<GradesDTO> responseList = hrService.returnGradesList();
		System.out.println(responseList);
		ResponseEntity<List<GradesDTO>> responseEntity = null;
		if(!responseList.isEmpty()) {
			responseEntity = new ResponseEntity<List<GradesDTO>>(responseList, HttpStatus.OK);
		}
		else {
			responseEntity=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
}
