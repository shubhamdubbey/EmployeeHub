package com.shubham.hr.service;

import java.util.List;
import com.shubham.hr.model.GradesDTO;
import com.shubham.hr.model.UsersDTO;


public interface HrService {
	
	List<UsersDTO> returnEmployeeList();
	String persistNewEmployees(UsersDTO usersDTO);
	String deleteEmployee(int id);
	String updateEmployeeGarde(int id ,int grade_id);
	UsersDTO getEmployeeById(int id);
	List<GradesDTO> returnGradesList();
	
}
