package com.shubham.hr.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubham.hr.entity.Grades;
import com.shubham.hr.entity.GradesHistory;
import com.shubham.hr.entity.Users;
import com.shubham.hr.model.GradesDTO;
import com.shubham.hr.model.UsersDTO;
import com.shubham.hr.repository.*;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HrServiceImpl implements HrService{

	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private GradesRepository gradesRepository;
	@Autowired
	private GradesHistoryRepository gradesHistoryRepository;
	
	public String persistNewEmployees(UsersDTO usersDTO) {
		Users users=new Users();
		users.setEmployeeId(usersDTO.getEmployeeId());
		users.setFirstName(usersDTO.getFirstName());
		users.setLastName(usersDTO.getLastName());
		users.setEmailAddress(usersDTO.getEmailAddress());
		
		users.setRoles(usersDTO.getRoles());
		users.setPhoneNumber(usersDTO.getPhoneNumber());
		
		Grades grades = gradesRepository.findById(usersDTO.getGrade_id()).get();

		users.setGrades(grades);
		
//		gradesHistoryRepository.deleteById(usersRequest.getEmployeeId());
		
		GradesHistory gradesHistory = new GradesHistory();
		gradesHistory.setAssignedon(LocalDate.now());		
		gradesHistory.setGrades(grades);
		gradesHistory.setUsers(users);
		
		Users usersCreated=usersRepository.save(users);
		if(usersCreated!=null) {
			gradesHistoryRepository.save(gradesHistory);
			return "success";
		}
		else
		return "fail";
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
	public String updateEmployeeGarde(int id, int grade_id) {
	
		Optional<Users> optionalOfUsers=usersRepository.findById(id);
		Users usersUpdated=null;
		Optional<Grades> optionalGrades = gradesRepository.findById(grade_id);	
		if(optionalGrades.isEmpty()) return "Fail";
		Grades grades = optionalGrades.get();
		
		if(grades.getIdentification() < grade_id) return "Fail";
		
		Iterable<GradesHistory> gradesHistory = gradesHistoryRepository.findAll();
		List<GradesHistory> gradesHsitoryList = new ArrayList<>();
		for(GradesHistory g : gradesHistory) {
			if(g.getUsers().getEmployeeId() == id) {
				gradesHsitoryList.add(g);
			}
		}
		
		if(gradesHsitoryList.size() == 1) {
			long yearsDifference = ChronoUnit.YEARS.between(gradesHistoryRepository.findById(id).get().getAssignedon(), LocalDate.now());
			if(yearsDifference < 2) {
				return "fail";
			}
		}	
//		LocalDate minDate;		
		for(GradesHistory g : gradesHsitoryList) {
			
			if(ChronoUnit.DAYS.between(g.getAssignedon(), LocalDate.now()) < 365) {
				return "fail";
			}
			
		}		
		
		if(optionalOfUsers.isPresent()) {
	    	Users users =optionalOfUsers.get();
	    	users.setGrades(grades);
	    	
		usersUpdated=usersRepository.save(users);
		}
		if(usersUpdated!=null)
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
	
	@Override
	public UsersDTO getEmployeeById(int id) {
		Optional<Users> optionalOfUsers=usersRepository.findById(id);
		UsersDTO usersDTO = new UsersDTO();
	    if(optionalOfUsers.isPresent()) {
	    	Users users = optionalOfUsers.get();
	    	usersDTO.setEmployeeId(users.getEmployeeId());
			usersDTO.setFirstName(users.getFirstName());
			usersDTO.setLastName(users.getLastName());
			usersDTO.setEmailAddress(users.getEmailAddress());
			usersDTO.setGrade_id(id);
			usersDTO.setRoles(users.getRoles());
			usersDTO.setPhoneNumber(users.getPhoneNumber());
			
			return usersDTO;
	    }
	    else return null;
	   
	}
	
	@Override
	public String deleteEmployee(int id) {
		
		Optional<Users> users = usersRepository.findById(id);
		if(users.isPresent())
		{
			usersRepository.deleteById(id);
			gradesHistoryRepository.deleteById(id);
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

	public UsersRepository getUsersRepository() {
		return usersRepository;
	}

	public void setUsersRepository(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	public GradesRepository getGradesRepository() {
		return gradesRepository;
	}

	public void setGradesRepository(GradesRepository gradesRepository) {
		this.gradesRepository = gradesRepository;
	}

	public GradesHistoryRepository getGradesHistoryRepository() {
		return gradesHistoryRepository;
	}

	public void setGradesHistoryRepository(GradesHistoryRepository gradesHistoryRepository) {
		this.gradesHistoryRepository = gradesHistoryRepository;
	}

	public HrServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
}
