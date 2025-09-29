package com.cts.hr.dto;

import com.cts.hr.utility.LeaveType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class LeaveRequestDto {
    int employeeId;
    LeaveType leaveType;
    LocalDate fromDate;
    LocalDate toDate;
    String reason;
}
