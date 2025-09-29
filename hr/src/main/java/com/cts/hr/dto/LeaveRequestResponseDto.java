package com.cts.hr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LeaveRequestResponseDto {
    boolean applied = false;
    String message;
}
