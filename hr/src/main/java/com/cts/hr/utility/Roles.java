package com.cts.hr.utility;

public enum Roles {

	EMPLOYEES("Employees"),
    ADMIN("Admin"),
	HR("HR"),
	TRAVELDESKEXC("TravelDeskExe");

	String roles;
	
	Roles(String roles){
		this.roles = roles;
	}
	
	public String  getRoles() {
		return roles;
	}
}