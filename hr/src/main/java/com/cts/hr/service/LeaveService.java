package com.cts.hr.service;

import com.cts.hr.dto.LeaveRequestDto;
import com.cts.hr.dto.LeaveRequestResponseDto;
import com.cts.hr.dto.LeavesDto;

public interface LeaveService {
    LeaveRequestResponseDto applyLeave(LeaveRequestDto LeaveRequestDto);
    LeavesDto getLeaveById(int employeeId);
}
