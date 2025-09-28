package com.shubham.hr.utility;

public enum Roles {

	EMPLOYEES("Employees"),
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
