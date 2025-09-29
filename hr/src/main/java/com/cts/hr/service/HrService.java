package com.cts.hr.service;

import java.util.List;

import com.cts.hr.dto.GradesDTO;
import com.cts.hr.dto.HomeManagerDTO;
import com.cts.hr.dto.UsersDTO;
import com.cts.hr.utility.DuplicateAccountException;
import com.cts.hr.utility.GradeUpdateRuleViolationException;
import com.cts.hr.utility.InvalidInputException;

public interface HrService {
	
	List<UsersDTO> returnEmployeeList();
	String persistNewEmployees(UsersDTO usersDTO) throws DuplicateAccountException ;
	String deleteEmployee(int id);
	String updateEmployeeGarde(int id ,int grade_id) throws GradeUpdateRuleViolationException;
	UsersDTO getEmployeeById(int id) throws InvalidInputException;
	List<GradesDTO> returnGradesList();
    List<HomeManagerDTO> getHomeManagerList();
    HomeManagerDTO getHomeManagerById(int id) throws InvalidInputException;
    String updateHomeManager(int managerId, int userId) throws IllegalArgumentException;
}