//package com.shubham.hr.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import java.util.List;
//import com.shubham.hr.model.GradesDTO;
//import com.shubham.hr.model.GradesResponse;
//import com.shubham.hr.model.UsersDTO;
//import com.shubham.hr.model.UsersRequest;
//import com.shubham.hr.model.UsersResponse;
//import com.shubham.hr.service.HrService;
//import com.shubham.hr.service.HrServiceImpl;
//
//@RestController
//@RequestMapping("api")
//public class HrController {
//
//	@Autowired
//	private HrServiceImpl hrServiceImpl;
//	
////	@GetMapping("employees")
////	public ResponseEntity<List<UsersDTO>> handleReturnEmployeeList(){
////		List<UsersDTO> responseList = hrServiceImpl.returnEmployeeList();
////		ResponseEntity<List<UsersDTO>> responseEntity = null;
////		if(!responseList.isEmpty()) {
////			responseEntity = new ResponseEntity<List<UsersDTO>>(responseList, HttpStatus.OK);
////		}
////		else {
////			responseEntity=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
////		}
////		return responseEntity;
////	}
////	
////	@GetMapping("grades")
////	public ResponseEntity<List<GradesDTO>> handleReturnGradesList(){
////		List<GradesDTO> responseList = hrServiceImpl.returnGradesList();
////		ResponseEntity<List<GradesDTO>> responseEntity = null;
////		if(!responseList.isEmpty()) {
////			responseEntity = new ResponseEntity<List<GradesDTO>>(responseList, HttpStatus.OK);
////		}
////		else {
////			responseEntity=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
////		}
////		return responseEntity;
////	}
////	
////	@GetMapping("employees/{empId}")
////	public ResponseEntity<?> handleGetEmployeeById(@PathVariable("empId")int empId){
////		UsersDTO response=hrServiceImpl.getEmployeeById(empId);
////		ResponseEntity<UsersDTO> responseEntity=null;
////		if(response.getEmployeeId() != 0) {
////			responseEntity=new ResponseEntity<UsersDTO>(response,HttpStatus.OK);
////		}else {
////			responseEntity=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
////		}
////		return responseEntity;
////	}
////	
////	@PostMapping("employees")
////	public ResponseEntity<?> persistEmployee(@RequestBody UsersDTO usersDto){
////		String result=hrServiceImpl.persistNewEmployees(usersDto);
////		if(result.equals("success")) {
////			return new ResponseEntity<>(HttpStatus.CREATED);
////		}else {
////			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
////		}
////		
////	}
////	
////	@PutMapping("employees/{empId}/{newGrade}")
////	public ResponseEntity<?> handleUpdateEmployeeSalary(@PathVariable("empId")int empId,@PathVariable("newGrade")int newGrade){
////		String result=hrServiceImpl.updateEmployeeGarde(empId, newGrade);
////		if(result.equals("success")) {
////			return new ResponseEntity<>(HttpStatus.ACCEPTED);
////		}else {
////			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
////		}
////	}
////	
////	@DeleteMapping("employees/{empId}")
////	public ResponseEntity<?> handleDeleteEmployee(@PathVariable("empId")int empId){
////		String result=hrServiceImpl.deleteEmployee(empId);
////		if(result.equals("success")) {
////			return new ResponseEntity<>(HttpStatus.OK);
////		}else {
////			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
////		}
////	}	
//}
