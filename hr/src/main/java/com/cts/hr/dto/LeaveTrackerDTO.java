package com.cts.hr.dto;

import com.cts.hr.utility.LeaveType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveTrackerDTO {

    @NotNull
    private int employeeId;

    @NotNull
    private LeaveType leaveType;

    @NotNull
    private LocalDate from; // receive as string from frontend

    @NotNull
    private LocalDate to; // receive as string from frontend

    @NotNull
    private String reason;

}