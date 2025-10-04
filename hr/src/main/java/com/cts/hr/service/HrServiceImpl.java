package com.cts.hr.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cts.hr.entity.LoginDetails;
import com.cts.hr.repository.LoginDetailsRepository;
import lombok.extern.slf4j.XSlf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.hr.dto.GradesDTO;
import com.cts.hr.dto.UsersDTO;
import com.cts.hr.entity.Grades;
import com.cts.hr.entity.GradesHistory;
import com.cts.hr.entity.Users;
import com.cts.hr.repository.GradesHistoryRepository;
import com.cts.hr.repository.GradesRepository;
import com.cts.hr.repository.UsersRepository;
import com.cts.hr.utility.DuplicateAccountException;
import com.cts.hr.utility.GradeUpdateRuleViolationException;
import com.cts.hr.utility.GradesUpdateBusinessLogic;
import com.cts.hr.utility.InvalidInputException;
import lombok.extern.slf4j.Slf4j;
import jakarta.transaction.Transactional;

/**
 * @author Shubham Dubey
 * This class represents Service layer where we wrote all our business logics
 */
@Service
@Transactional
@Slf4j
public class HrServiceImpl implements HrService{

	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private GradesRepository gradesRepository;
	@Autowired 
	private GradesHistoryRepository gradesHistoryRepository;
	@Autowired
	private GradesUpdateBusinessLogic gradesUpdateBusinessLogic;
    @Autowired
    private LoginDetailsRepository loginDetailsRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
	public String updateEmployeeGarde(int id, int grade_id) throws GradeUpdateRuleViolationException{
		Optional<Users> optionalOfUsers=usersRepository.findById(id);
		if(optionalOfUsers.isEmpty()) return "fail";
		Users upsersUpdated = null;
		Optional<Grades> optionalGrades = gradesRepository.findById(grade_id);	
		if(optionalGrades.isEmpty()) return "fail";
		Grades grades = optionalGrades.get();
		if(gradesUpdateBusinessLogic.checkIfGradesIsLesserThanCurrentGrade(optionalOfUsers.get().getGrades().getIdentification(), grade_id));
		List<GradesHistory> gradesHistoryList = gradesUpdateBusinessLogic.giveMeListOfGradesHistory(gradesHistoryRepository.findAll(), id);
		if(gradesHistoryList.size() == 1) {
			
			gradesUpdateBusinessLogic.checkIfEmployeeIsNewAndEligible(gradesHistoryList);
		}
		if(gradesUpdateBusinessLogic.checkIfOldEmployeeIsEligibleForPromotion(gradesHistoryList));
		
	    Users users =optionalOfUsers.get();
	   	users.setGrades(grades);
	    	
	   	upsersUpdated=usersRepository.save(users);
		if(upsersUpdated!=null)
			{
				GradesHistory gHistory = new GradesHistory();
				gHistory.setAssignedon(LocalDate.now());		
				gHistory.setGrades(grades);
				gHistory.setUsers(usersRepository.findById(id).get());
				gradesHistoryRepository.save(gHistory);
				return "success";
			}
		
		return "fail";
			
	}

    @Transactional
	public String persistNewEmployees(UsersDTO usersDTO) throws DuplicateAccountException {
        if (usersDTO.getEmployeeId() == 0 || usersDTO.getFirstName() == null || usersDTO.getEmailAddress() == null) {
            throw new IllegalArgumentException("Employee ID, First Name, and Email are required.");
        }

        if (usersRepository.findById(usersDTO.getEmployeeId()).isPresent()) {
            throw new DuplicateAccountException("Employee with ID: " + usersDTO.getEmployeeId() + " already exists.");
        }

        if (usersRepository.existsByEmailAddress(usersDTO.getEmailAddress())) {
            throw new DuplicateAccountException("Email already registered: " + usersDTO.getEmailAddress());
        }

        // ✅ Create User object
        Users users = new Users();
        users.setEmployeeId(usersDTO.getEmployeeId());
        users.setFirstName(usersDTO.getFirstName());
        users.setLastName(usersDTO.getLastName());
        users.setEmailAddress(usersDTO.getEmailAddress());
        users.setRoles(usersDTO.getRoles());
        users.setPhoneNumber(usersDTO.getPhoneNumber());

        Optional<Grades> grades = gradesRepository.findById(usersDTO.getGrade_id());
        if (grades.isEmpty()) {
            throw new IllegalArgumentException("Invalid grade ID: " + usersDTO.getGrade_id());
        }
        users.setGrades(grades.get());

        Users usersCreated = usersRepository.save(users);

        GradesHistory gradesHistory = new GradesHistory();
        gradesHistory.setAssignedon(LocalDate.now());
        gradesHistory.setGrades(grades.get());
        gradesHistory.setUsers(usersCreated);
        gradesHistoryRepository.save(gradesHistory);

        LoginDetails loginDetails = new LoginDetails();
        loginDetails.setUsername(String.valueOf(usersCreated.getEmployeeId()));
        loginDetails.setRoles(usersCreated.getRoles());

        String rawPassword = usersCreated.getFirstName().toLowerCase() + "@" + usersCreated.getEmployeeId();
        loginDetails.setPassword(passwordEncoder.encode(rawPassword));

        loginDetailsRepository.save(loginDetails);

        return "success";
    }

	@Override
	public List<UsersDTO> returnEmployeeList() {
	
		Iterable<Users> usersList=usersRepository.findAll();
		List<UsersDTO> usersDTOList=new ArrayList<UsersDTO>();
		
		for(Users users:usersList) {
			
			UsersDTO usersDTO =new UsersDTO();
			
			usersDTO.setEmployeeId(users.getEmployeeId());
			usersDTO.setFirstName(users.getFirstName());
			usersDTO.setLastName(users.getLastName());
			usersDTO.setEmailAddress(users.getEmailAddress());
			usersDTO.setGrade_id(users.getGrades().getIdentification());
			usersDTO.setRoles(users.getRoles());
			usersDTO.setPhoneNumber(users.getPhoneNumber());
			
			usersDTOList.add(usersDTO);
		}
		return usersDTOList;
				
	}
	
	@Override
	public UsersDTO getEmployeeById(int id) throws InvalidInputException {
		Optional<Users> optionalOfUsers=usersRepository.findById(id);
	    if(!optionalOfUsers.isPresent()) throw new InvalidInputException("No records found");
	    	
	    UsersDTO usersDTO = new UsersDTO();
	    	
	    Users users = optionalOfUsers.get();
	    	
	   	usersDTO.setEmployeeId(users.getEmployeeId());
		usersDTO.setFirstName(users.getFirstName());
		usersDTO.setLastName(users.getLastName());
		usersDTO.setEmailAddress(users.getEmailAddress());
		usersDTO.setGrade_id(users.getGrades().getIdentification());
		usersDTO.setRoles(users.getRoles());
		usersDTO.setPhoneNumber(users.getPhoneNumber());
			
		return usersDTO;
	    
	   
	}
	
	@Override
	public String deleteEmployee(int id) {
		
		Optional<Users> users = usersRepository.findById(id);
		if(users.isPresent())
		{
			Iterable<GradesHistory> gradesHistoryList = gradesHistoryRepository.findAll();
			for(GradesHistory gradesHistory : gradesHistoryList) {
				if(gradesHistory.getUsers().getEmployeeId() == id) {
					gradesHistoryRepository.deleteById(gradesHistory.getId());
				}
			}
			usersRepository.deleteById(id);
			return "success";
		}
		else
		{
			return "fail";
		}
	}
	
	@Override
	public List<GradesDTO> returnGradesList() {
	
		Iterable<Grades> gradesList=gradesRepository.findAll();
		List<GradesDTO> gradesDTOList=new ArrayList<GradesDTO>();
		for(Grades grades:gradesList) {
			GradesDTO gradesDTO = new GradesDTO();
			gradesDTO.setIdentification(grades.getIdentification());
			gradesDTO.setFullName(grades.getFullName());
			gradesDTOList.add(gradesDTO);
		}
		return gradesDTOList;
		
	}
}
