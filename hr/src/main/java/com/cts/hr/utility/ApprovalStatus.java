package com.cts.hr.utility;

public enum ApprovalStatus {
    PENDING("Pending"),
    APPROVED("Approved"),
    REJECTED("Rejected");

    private String value;
    ApprovalStatus(String value) {this.value = value;}
    public String getValue() {return value;}
}
