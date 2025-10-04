package com.cts.hr.dto;

public class LeaveRequestResponseDto {
    boolean applied = false;
    String message;

    public boolean isApplied() {
        return applied;
    }

    public void setApplied(boolean applied) {
        this.applied = applied;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
