package com.cts.hr.dto;

import com.cts.hr.utility.Roles;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDTO {

	private int EmployeeId;
	private String FirstName;
	private String LastName;
	@Pattern(regexp="(^$|[0-9]{10})", message = "invalid phone number")
	private String PhoneNumber;
	private String EmailAddress;
	private Roles role;
	private int grade_id;
	
	public void setRoles(Roles role) {
		// TODO Auto-generated method stub
		this.role = role;
	}
	
	public Roles getRoles()
	{
		return this.role;
	}
}
