package com.cts.hr.service;

import java.util.List;

import com.cts.hr.dto.ApprovalDto;
import com.cts.hr.dto.GradesDTO;
import com.cts.hr.dto.UsersDTO;
import com.cts.hr.utility.*;

public interface HrService {
	
	List<UsersDTO> returnEmployeeList();
	String persistNewEmployees(UsersDTO usersDTO) throws DuplicateAccountException, HomeManagerUpdateRuleViolationException;
	String deleteEmployee(int id);
	String updateEmployeeGarde(int id ,int grade_id) throws GradeUpdateRuleViolationException;
	UsersDTO getEmployeeById(int id) throws InvalidInputException;
	List<GradesDTO> returnGradesList();
    String updateHomeManager(int managerId, int userId) throws IllegalArgumentException, HomeManagerUpdateRuleViolationException, InvalidInputException;
    List<ApprovalDto> listOfApproval(int empId) throws InvalidInputException;
    String approveOrRejectRequest(String approvalId, ApprovalStatus status) throws InvalidInputException;
}