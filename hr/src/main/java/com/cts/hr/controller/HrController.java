package com.cts.hr.controller;

import java.util.List;
import java.util.logging.Logger;

import com.cts.hr.dto.*;
import com.cts.hr.service.LeaveService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    @Autowired
//    private Logger logger;
	@Autowired
	private HrService hrService;

    @Autowired
    private LeaveService leaveService;

    /**
     * Handles the home onboaring page
     * @return ResponseEntity<String>
     */
    @RequestMapping("/")
    public ResponseEntity<String> welcomeHomePage(){
        return new ResponseEntity<>("Welcome to the HR Application", HttpStatus.OK);
    }

    /**
	 * Handles Retrieval of employee of given ID
	 * @return ResponseEntity<UsersDTO>
	 * 
	 */
	@GetMapping("employees/{empId}")
	public ResponseEntity<UsersDTO> retrieveEmployeeById(@PathVariable("empId")int empId) throws InvalidInputException{
		UsersDTO response=hrService.getEmployeeById(empId);
		return new ResponseEntity<UsersDTO>(response,HttpStatus.OK);
	}
	
	/** 
	 * Handles adding new employee
	 * @return ResponseEntity<String>
	 * 
	 */
	@PostMapping("employees")
	public ResponseEntity<String> persistEmployee(@RequestBody UsersDTO usersDto) throws DuplicateAccountException, InvalidInputException{
        log.info("I am inside the controller");
        if(usersDto.getEmployeeId() == 0) {
			throw new InvalidInputException("Input is invalid");
		}
		String result=hrService.persistNewEmployees(usersDto);
        log.info("Result while persisting data into DB : " + result);
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
	@PutMapping("employees/{empId}/{newGrade}")
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
	public ResponseEntity<List<UsersDTO>> returnEmployeeList(){

		List<UsersDTO> responseList = hrService.returnEmployeeList();
		ResponseEntity<List<UsersDTO>> responseEntity = null;
		if(!responseList.isEmpty()) {
			responseEntity = new ResponseEntity<List<UsersDTO>>(responseList, HttpStatus.OK);
		}
		else {
			responseEntity=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	
	/** 
	 * Handles Retrieval of all Grades
	 * @return ResponseEntity<List<GradesDTO>>
	 * 
	 */
	@GetMapping("grades")
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

    /**
     * Handles Retrieval of all Leaves for an Employee
     * @return ResponseEntity<LeavesDto>
     *
     */
    @GetMapping("getLeaves/{empId}")
    public ResponseEntity<LeavesDto>  returnLeavesList(@PathVariable("empId") int empId){

        LeavesDto leavesDto = leaveService.getLeaveById(empId);
        ResponseEntity<LeavesDto> responseEntity = new ResponseEntity<>(leavesDto, HttpStatus.OK);;

        return responseEntity;
    }

    /**
     * Handles Retrieval of all Leaves for an Employee
     * @return ResponseEntity<List<UsersDTO>>
     *
     */
    @GetMapping("applyLeaves")
    public ResponseEntity<LeaveRequestResponseDto>  applyLeave(@RequestBody LeaveRequestDto leaveRequestDto){

        ResponseEntity<LeaveRequestResponseDto> responseEntity = new ResponseEntity<>(leaveService.applyLeave(leaveRequestDto), HttpStatus.OK);;

        return responseEntity;
    }
}
