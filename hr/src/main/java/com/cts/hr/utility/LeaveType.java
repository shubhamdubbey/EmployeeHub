package com.cts.hr.utility;

public enum LeaveType {
    SICK_LEAVE("Sick_Leave"),
    CASUAL_LEAVE("Casual Leave"),
    EARNED_LEAVE("Earned Leave"),
    PATERNITY_LEAVE("Paternity Leave");

    private String value;

    LeaveType(String value) {this.value = value;}
    public String getValue() {return value;}

}
