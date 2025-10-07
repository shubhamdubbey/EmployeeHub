package com.cts.hr.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cts.hr.dto.ApprovalDto;
import com.cts.hr.entity.*;
import com.cts.hr.repository.*;
import com.cts.hr.utility.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.hr.dto.GradesDTO;
import com.cts.hr.dto.UsersDTO;
import lombok.extern.slf4j.Slf4j;
import jakarta.transaction.Transactional;

/**
 * @author Shubham Dubey
 * This class represents Service layer where we wrote all our business logics
 */
@Service
@Transactional
@Slf4j
public class HrServiceImpl implements HrService {

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
    private LeaveRepository leaveRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ApprovalsRepository approvalRepository;
    @Autowired
    private LeaveTrackerRepository leaveTrackerRepository;

    @Override
    public String updateEmployeeGarde(int id, int grade_id) throws GradeUpdateRuleViolationException {
        Optional<Users> optionalOfUsers = usersRepository.findById(id);
        if (optionalOfUsers.isEmpty()) return "fail";
        Users upsersUpdated = null;
        Optional<Grades> optionalGrades = gradesRepository.findById(grade_id);
        if (optionalGrades.isEmpty()) return "fail";
        Grades grades = optionalGrades.get();
        if (gradesUpdateBusinessLogic.checkIfGradesIsLesserThanCurrentGrade(optionalOfUsers.get().getGrades().getIdentification(), grade_id))
            ;
        List<GradesHistory> gradesHistoryList = gradesUpdateBusinessLogic.giveMeListOfGradesHistory(gradesHistoryRepository.findAll(), id);
        if (gradesHistoryList.size() == 1) {

            gradesUpdateBusinessLogic.checkIfEmployeeIsNewAndEligible(gradesHistoryList);
        }
        if (gradesUpdateBusinessLogic.checkIfOldEmployeeIsEligibleForPromotion(gradesHistoryList)) ;

        Users users = optionalOfUsers.get();
        users.setGrades(grades);

        GradesHistory gHistory = new GradesHistory();
        gHistory.setAssignedon(LocalDate.now());
        gHistory.setGrades(grades);
        gHistory.setUsers(usersRepository.findById(id).get());
        gradesHistoryRepository.save(gHistory);
        return "success";

    }

    @Transactional
    public String persistNewEmployees(UsersDTO usersDTO) throws DuplicateAccountException, HomeManagerUpdateRuleViolationException {
        if (usersDTO.getEmployeeId() == 0 || usersDTO.getFirstName() == null || usersDTO.getEmailAddress() == null) {
            throw new IllegalArgumentException("Employee ID, First Name, and Email are required.");
        }

        if (usersRepository.findById(usersDTO.getEmployeeId()).isPresent()) {
            throw new DuplicateAccountException("Employee with ID: " + usersDTO.getEmployeeId() + " already exists.");
        }

        if (usersRepository.existsByEmailAddress(usersDTO.getEmailAddress())) {
            throw new DuplicateAccountException("Email already registered: " + usersDTO.getEmailAddress());
        }

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
        System.out.println("before if condition");
        Optional<Users> homeManager = usersRepository.findById(usersDTO.getManagerId());
        if (homeManager.isPresent()) {
            System.out.println("Inside if condition");
            System.out.println(homeManager.get().getGrades().getIdentification() + " " + users.getGrades().getIdentification());
            if (homeManager.get().getGrades().getIdentification() > users.getGrades().getIdentification()) {
                throw new HomeManagerUpdateRuleViolationException("Manager's grade must be higher than employee's grade.");
            } else {
                users.setHomeManagerId(usersDTO.getManagerId());
            }
        } else if (usersRepository.count() == 0) {
            // First employee, no manager required
            users.setHomeManagerId(0);
        } else {
            throw new IllegalArgumentException("Invalid employee ID: " + usersDTO.getEmployeeId());
        }

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

        Leaves leaves = new Leaves();
        leaves.setEmployeeId(usersDTO.getEmployeeId());
        leaves.setPaternityLeave(5);
        int monthsRemaining = 12 - LocalDate.now().getMonthValue() + 1; // Including current month
        leaves.setEarnedLeave((int) (monthsRemaining * 1.5));
        leaves.setCasualLeave((int) (monthsRemaining * 0.5));
        leaves.setSickLeave(monthsRemaining);
        leaveRepository.save(leaves);

        return "success";
    }


    @Override
    public List<UsersDTO> returnEmployeeList() {

        Iterable<Users> usersList = usersRepository.findAll();
        List<UsersDTO> usersDTOList = new ArrayList<UsersDTO>();

        for (Users users : usersList) {

            UsersDTO usersDTO = new UsersDTO();

            usersDTO.setEmployeeId(users.getEmployeeId());
            usersDTO.setFirstName(users.getFirstName());
            usersDTO.setLastName(users.getLastName());
            usersDTO.setEmailAddress(users.getEmailAddress());
            usersDTO.setGrade_id(users.getGrades().getIdentification());
            usersDTO.setRoles(users.getRoles());
            usersDTO.setPhoneNumber(users.getPhoneNumber());
            usersDTO.setManagerId(users.getHomeManagerId());

            usersDTOList.add(usersDTO);
        }
        return usersDTOList;

    }

    @Override
    public UsersDTO getEmployeeById(int id) throws InvalidInputException {
        Optional<Users> optionalOfUsers = usersRepository.findById(id);
        if (!optionalOfUsers.isPresent()) throw new InvalidInputException("No records found");

        UsersDTO usersDTO = new UsersDTO();

        Users users = optionalOfUsers.get();

        usersDTO.setEmployeeId(users.getEmployeeId());
        usersDTO.setFirstName(users.getFirstName());
        usersDTO.setLastName(users.getLastName());
        usersDTO.setEmailAddress(users.getEmailAddress());
        usersDTO.setGrade_id(users.getGrades().getIdentification());
        usersDTO.setRoles(users.getRoles());
        usersDTO.setPhoneNumber(users.getPhoneNumber());
        usersDTO.setManagerId(users.getHomeManagerId());

        return usersDTO;


    }

    @Override
    public String deleteEmployee(int id) {

        Optional<Users> users = usersRepository.findById(id);
        if (users.isPresent()) {
            Iterable<GradesHistory> gradesHistoryList = gradesHistoryRepository.findAll();
            for (GradesHistory gradesHistory : gradesHistoryList) {
                if (gradesHistory.getUsers().getEmployeeId() == id) {
                    gradesHistoryRepository.deleteById(gradesHistory.getId());
                }
            }
            usersRepository.deleteById(id);
            return "success";
        } else {
            return "fail";
        }
    }

    @Override
    public List<GradesDTO> returnGradesList() {

        Iterable<Grades> gradesList = gradesRepository.findAll();
        List<GradesDTO> gradesDTOList = new ArrayList<GradesDTO>();
        for (Grades grades : gradesList) {
            GradesDTO gradesDTO = new GradesDTO();
            gradesDTO.setIdentification(grades.getIdentification());
            gradesDTO.setFullName(grades.getFullName());
            gradesDTOList.add(gradesDTO);
        }
        return gradesDTOList;

    }

    @Override
    public String updateHomeManager(int managerId, int empId) throws HomeManagerUpdateRuleViolationException, InvalidInputException {
        Optional<Users> homeManager = usersRepository.findById(managerId);
        if (homeManager.isPresent()) {
            Optional<Users> employee = usersRepository.findById(empId);
            if (employee.isPresent()) {
                if (homeManager.get().getGrades().getIdentification() > employee.get().getGrades().getIdentification()) {
                    throw new HomeManagerUpdateRuleViolationException("Manager's grade must be higher than employee's grade.");
                } else {
                    Approvals approvals = new Approvals();
                    approvals.setId(Utils.generateCorrelationId());
                    approvals.setApproverId(employee.get().getHomeManagerId());
                    approvals.setEmployeeId(empId);
                    approvals.setRequest("Requesting you to change my home manager from " + employee.get().getHomeManagerId() + " to " + managerId + ".");
                    approvals.setApprovalType(ApprovalType.HOME_MANAGER);
                    approvals.setStatus(ApprovalStatus.PENDING);

                    approvalRepository.save(approvals);

                    return "Success";
                }
            }
        } else {
            throw new InvalidInputException("Invalid manager's employee id");
        }
        return "failure";
    }

    @Override
    public List<ApprovalDto> listOfApproval(int empId) throws InvalidInputException {
        List<ApprovalDto> approvalDtoList = new ArrayList<>();
        Iterable<Approvals> approvals = approvalRepository.findByApproverIdAndStatus(empId, ApprovalStatus.PENDING);
        for (Approvals approval : approvals) {
            ApprovalDto approvalDto = new ApprovalDto();
            approvalDto.setId(approval.getId());
            approvalDto.setEmployeeId(approval.getEmployeeId());
            approvalDto.setRequest(approval.getRequest());
            approvalDto.setApprovalType(approval.getApprovalType());
            approvalDto.setStatus(approval.getStatus());
            approvalDtoList.add(approvalDto);
        }
        return approvalDtoList;
    }

    @Override
    public String approveOrRejectRequest(String approvalId, ApprovalStatus status) throws InvalidInputException {
        Optional<Approvals> optionalApproval = approvalRepository.findById(approvalId);
        if (optionalApproval.isEmpty()) {
            throw new InvalidInputException("Invalid approval request ID: " + approvalId);
        }

        Approvals approval = optionalApproval.get();
        if (approval.getStatus() != ApprovalStatus.PENDING) {
            throw new InvalidInputException("Approval request has already been processed.");
        }
        approval.setStatus(status);
        if(approval.getApprovalType() == ApprovalType.LEAVE) {
            Optional<LeaveTracker> optionalLeaveTracker = leaveTrackerRepository.findById(approval.getId());
            if (optionalLeaveTracker.isPresent()) {
                LeaveTracker leaveTracker = optionalLeaveTracker.get();
                leaveTracker.setStatus(status);
                leaveTrackerRepository.save(leaveTracker);
            } else {
                throw new InvalidInputException("Associated leave request not found for approval ID: " + approvalId);
            }
        } else if(approval.getApprovalType() == ApprovalType.HOME_MANAGER){
            if(status == ApprovalStatus.APPROVED){
                Optional<Users> employee = usersRepository.findById(approval.getEmployeeId());
                if(employee.isPresent()){
                    employee.get().setHomeManagerId(Integer.parseInt(approval.getRequest().substring(56,62)));
                    usersRepository.save(employee.get());
                    approval.setStatus(ApprovalStatus.APPROVED);
                    approvalRepository.save(approval);
                }else throw new IllegalArgumentException("Employee with given is not present.");
            } else if(status == ApprovalStatus.REJECTED){
                approval.setStatus(ApprovalStatus.REJECTED);
                approvalRepository.save(approval);
            } else throw new IllegalArgumentException("Status can only be APPROVED or REJECTED");
        }
        approvalRepository.save(approval);
        return "success";
    }
}
