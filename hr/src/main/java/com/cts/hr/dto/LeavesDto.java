package com.cts.hr.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LeavesDto {
    int employeeId;
    int sickLeave;
    int casualLeave;
    int earnedLeave;
    int paternityLeave;
}
