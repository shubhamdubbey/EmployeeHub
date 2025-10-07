package com.cts.hr.utility;

public enum ApprovalType {
    LEAVE("Leave"),
    HOME_MANAGER("Home Manager");
    private String value;
    ApprovalType(String value) {this.value = value;}
    public String getValue() {return value;}
}
