package com.cts.hr.service;

import com.cts.hr.dto.LeaveRequestDto;
import com.cts.hr.dto.LeaveRequestResponseDto;
import com.cts.hr.dto.LeaveTrackerDTO;
import com.cts.hr.dto.LeavesDto;
import com.cts.hr.entity.Approvals;
import com.cts.hr.entity.LeaveTracker;
import com.cts.hr.entity.Leaves;
import com.cts.hr.entity.Users;
import com.cts.hr.repository.ApprovalsRepository;
import com.cts.hr.repository.LeaveRepository;
import com.cts.hr.repository.LeaveTrackerRepository;
import com.cts.hr.repository.UsersRepository;
import com.cts.hr.utility.ApprovalStatus;
import com.cts.hr.utility.ApprovalType;
import com.cts.hr.utility.LeaveType;
import com.cts.hr.utility.Utils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Shubham Dubey
 * This class represents Service layer where we wrote all our business logics
 */
@Service
@Transactional
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private LeaveTrackerRepository leaveTrackerRepository;

    @Autowired
    private ApprovalsRepository approvalsRepository;


    @Override
    public LeaveRequestResponseDto applyLeave(LeaveRequestDto leavesRequestDto) {
        LeaveRequestResponseDto response = new LeaveRequestResponseDto();

        Optional<Leaves> optionalLeaves = leaveRepository.findById(leavesRequestDto.getEmployeeId());

        if (optionalLeaves.isEmpty()) {
            response.setApplied(false);
            response.setMessage("No leave details found for this employee. Please contact HR.");
            return response;
        }

        Leaves leave = optionalLeaves.get();

        // Calculate total leave days (inclusive)
        int leavesCount = Math.toIntExact(
                ChronoUnit.DAYS.between(leavesRequestDto.getFromDate(), leavesRequestDto.getToDate())
        ) + 1;

        LeaveType type = leavesRequestDto.getLeaveType();

        // Check leave type and balance
        switch (type) {
            case SICK_LEAVE:
                if (leave.getSickLeave() < leavesCount) {
                    response.setApplied(false);
                    response.setMessage("Insufficient Sick Leave balance.");
                    return response;
                }
                leave.setSickLeave(leave.getSickLeave() - leavesCount);
                break;

            case CASUAL_LEAVE:
                if (leave.getCasualLeave() < leavesCount) {
                    response.setApplied(false);
                    response.setMessage("Insufficient Casual Leave balance.");
                    return response;
                }
                leave.setCasualLeave(leave.getCasualLeave() - leavesCount);
                break;

            case EARNED_LEAVE:
                if (leave.getEarnedLeave() < leavesCount) {
                    response.setApplied(false);
                    response.setMessage("Insufficient Earned Leave balance.");
                    return response;
                }
                leave.setEarnedLeave(leave.getEarnedLeave() - leavesCount);
                break;

            case PATERNITY_LEAVE:
                if (leave.getPaternityLeave() < leavesCount) {
                    response.setApplied(false);
                    response.setMessage("Insufficient Paternity Leave balance.");
                    return response;
                }
                leave.setPaternityLeave(leave.getPaternityLeave() - leavesCount);
                break;

            default:
                response.setApplied(false);
                response.setMessage("Unknown leave type.");
                return response;
        }

        // Save updated leave balance
        leaveRepository.save(leave);

        // Save leave request history
        LeaveTracker leaveTracker = new LeaveTracker();
        leaveTracker.setId(Utils.generateCorrelationId());
        leaveTracker.setEmployeeId(leavesRequestDto.getEmployeeId());
        leaveTracker.setLeaveType(type);
        leaveTracker.setFrom(leavesRequestDto.getFromDate());
        leaveTracker.setTo(leavesRequestDto.getToDate());
        leaveTracker.setReason(leavesRequestDto.getReason());
        leaveTracker.setStatus(ApprovalStatus.PENDING);

        // Successful response
        response.setApplied(true);
        response.setMessage("Leave applied successfully.");

        Optional<Users> optionalUser = usersRepository.findById(leavesRequestDto.getEmployeeId());
        if(optionalUser.isEmpty()) throw new IllegalArgumentException("No user found with the given employee ID.");
        else if(optionalUser.get().getHomeManagerId() == 0) return response;

        leaveTrackerRepository.save(leaveTracker);

        Approvals approvals = new Approvals();
        approvals.setId(leaveTracker.getId());
        approvals.setApproverId(optionalUser.get().getHomeManagerId());
        approvals.setEmployeeId(leaveTracker.getEmployeeId());
        approvals.setStatus(ApprovalStatus.PENDING);
        approvals.setApprovalType(ApprovalType.LEAVE);
        String request = "Requesting " + leavesCount + " days of " + type.getValue() + " from " +  leavesRequestDto.getFromDate().toString() + " to " + leavesRequestDto.getToDate().toString() +
                " for the reason: " + leavesRequestDto.getReason();

        approvals.setRequest(request);
        approvalsRepository.save(approvals);

        return response;
    }


    @Override
    public LeavesDto getLeaveById(int employeeId) {
        Optional<Leaves> leaves = leaveRepository.findById(employeeId);
        if(leaves.isPresent()){
            LeavesDto leavesDto = new LeavesDto();
            leavesDto.setEmployeeId(leaves.get().getEmployeeId());
            leavesDto.setCasualLeave(leaves.get().getCasualLeave());
            leavesDto.setEarnedLeave(leaves.get().getEarnedLeave());
            leavesDto.setPaternityLeave(leaves.get().getPaternityLeave());
            leavesDto.setSickLeave(leaves.get().getSickLeave());
            return leavesDto;
        }else throw new IllegalArgumentException("No leaves details for the employee. Please contact HR.");

    }

    @Override
    public List<LeaveTrackerDTO> getHistoricalLevaesById(int employeeId) {
        List<LeaveTracker> leaveTrackers = leaveTrackerRepository.findByEmployeeId(employeeId);
        return leaveTrackers.stream()
                .map(lt -> {
                    LeaveTrackerDTO dto = new LeaveTrackerDTO();
                    dto.setEmployeeId(lt.getEmployeeId());
                    dto.setFromDate(lt.getFrom());
                    dto.setToDate(lt.getTo());
                    dto.setReason(lt.getReason());
                    dto.setLeaveType(lt.getLeaveType());
                    dto.setStatus(lt.getStatus());
                    return dto;
                })
                .toList();
    }
}
